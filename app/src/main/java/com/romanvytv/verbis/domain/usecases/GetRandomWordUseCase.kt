package com.romanvytv.verbis.domain.usecases

import android.util.Log
import com.romanvytv.verbis.core.Either
import com.romanvytv.verbis.core.UseCase
import com.romanvytv.verbis.core.exception.Failure
import com.romanvytv.verbis.data.WordsRepository
import com.romanvytv.verbis.data.entities.Word

class GetRandomWordUseCase
constructor(
	private val repository: WordsRepository.Network,
	private val local: WordsRepository.Local
) : UseCase<Word, UseCase.None>() {

	private var resultsCount = 0

	override suspend fun run(params: None): Either<Failure, Word> {
		var result: Either<Failure, Word>

		do {
			result = repository.randomWord()
		} while (!isWordValid(result))


		result.either({}, ::saveWord)

		return result
	}

	private fun saveWord(word: Word) {
		Log.d("zall", word.word)
		local.saveWord(word)
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