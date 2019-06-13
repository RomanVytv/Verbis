package com.romanvytv.verbis.domain.usecases

import android.text.format.DateUtils
import com.romanvytv.verbis.core.Either
import com.romanvytv.verbis.core.UseCase
import com.romanvytv.verbis.core.exception.Failure
import com.romanvytv.verbis.data.WordsRepository
import com.romanvytv.verbis.data.entities.TodayWord
import com.romanvytv.verbis.data.entities.Word

class GetTodaysWordUseCase
constructor(
	private val localRepo: WordsRepository.Local,
	private val remoteRepo: WordsRepository.Network
) : UseCase<Word, UseCase.None>() {

	private var resultsCount = 0

	override suspend fun run(params: None): Either<Failure, Word> {
		val existingTodayWord = localRepo.getTodaysWord()

		if (existingTodayWord != null && DateUtils.isToday(existingTodayWord.timeStamp))
			return remoteRepo.getWordDetails(existingTodayWord.word)

		var result: Either<Failure, Word>

		do {
			result = remoteRepo.randomWord()
		} while (!isWordValid(result))

		result.either({}, { localRepo.saveTodayWord(TodayWord(it.word)) })

		return result
	}

	private fun isWordValid(result: Either<Failure, Word>): Boolean {
		if (result.isLeft)
			return true

		result.either({}, ::setResultsCount)

		return resultsCount in 1..3
	}

	private fun setResultsCount(word: Word) {
		resultsCount = word.results.size
	}
}