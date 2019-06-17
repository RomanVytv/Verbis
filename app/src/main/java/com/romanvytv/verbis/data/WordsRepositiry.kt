package com.romanvytv.verbis.data

import android.util.Log
import com.google.gson.JsonSyntaxException
import com.romanvytv.verbis.core.Either
import com.romanvytv.verbis.core.exception.Failure
import com.romanvytv.verbis.data.entities.TodayWord
import com.romanvytv.verbis.data.entities.Word
import com.romanvytv.verbis.data.local.AppDatabase
import com.romanvytv.verbis.data.network.WordsApi
import kotlinx.coroutines.Deferred
import retrofit2.Response


//TODO: handle repos local/remote
abstract class WordsRepository {

	class Network
	constructor(private val api: WordsApi) : WordsRepository() {

		suspend fun randomWord(): Either<Failure, Word> =
			request(api.getRandomWordAsync(), { it }, Word.empty())

		suspend fun getWordDetails(word: String): Either<Failure, Word> =
			request(api.getWordDetailsAsync(word), { it }, Word.empty())

	}

	class Local
	constructor(private val db: AppDatabase) {

		fun getTodaysWord(): TodayWord? = db.todayWordDao().getLastTodayWord()

		fun saveTodayWord(newWord: TodayWord): Long = db.todayWordDao().insert(newWord)

		fun saveWord(newWord: Word) {
			newWord.id = db.wordDao().insert(newWord)
		}

		fun getWord(word: String) = db.wordDao().getWord(word)

		fun getWordById(wordId: Long) = db.wordDao().getWordById(wordId)

		fun getAllWords() = db.wordDao().getAllWords()

		fun setFavotire(wordId: Long, isFavorite: Boolean) = db.wordDao().setFavorite(wordId, isFavorite)
	}

	protected suspend fun <T, R> request(
		request: Deferred<Response<T>>,
		transform: (T) -> R,
		default: T
	): Either<Failure, R> {
		var response: Response<T>? = null

		try {
			response = request.await()
		} catch (ex: JsonSyntaxException) {
			Log.e("JsonSyntaxException", ex.stackTrace.toString())
		}

		return when (response != null && response.isSuccessful) {
			true -> Either.Right(transform((response.body() ?: default)))
			false -> Either.Left(Failure.ServerError)
		}
	}
}