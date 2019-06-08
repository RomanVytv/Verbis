package com.romanvytv.verbis.data

import com.romanvytv.verbis.core.Either
import com.romanvytv.verbis.core.exception.Failure
import com.romanvytv.verbis.data.entities.TodayWordEntity
import com.romanvytv.verbis.data.entities.WordEntity
import com.romanvytv.verbis.data.network.WordsApi
import kotlinx.coroutines.Deferred
import retrofit2.Response


//TODO: handle repos local/remote
abstract class WordsRepository {

    class Network
    constructor(private val api: WordsApi) : WordsRepository() {

        suspend fun randomWord(): Either<Failure, WordEntity> =
            request(api.getRandomWordAsync(), { it }, WordEntity.empty())

        suspend fun getWordDetails(word: String): Either<Failure, WordEntity> =
            request(api.getWordDetailsAsync(word), { it }, WordEntity.empty())

    }

    class Local
    constructor() {

        fun getTodaysWord(): TodayWordEntity {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        fun saveTodayWord(newWord: TodayWordEntity) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }

    protected suspend fun <T, R> request(
        request: Deferred<Response<T>>,
        transform: (T) -> R,
        default: T
    ): Either<Failure, R> {
        val response = request.await()
        return when (response.isSuccessful) {
            true -> Either.Right(transform((response.body() ?: default)))
            false -> Either.Left(Failure.ServerError)
        }
    }
}