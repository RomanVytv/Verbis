package com.romanvytv.verbis.domain.usecases

import com.romanvytv.verbis.core.Either
import com.romanvytv.verbis.core.UseCase
import com.romanvytv.verbis.core.exception.Failure
import com.romanvytv.verbis.data.WordsRepository
import com.romanvytv.verbis.data.entities.Word

class GetRandomWordUseCase
constructor(private val repository: WordsRepository.Network) : UseCase<Word, UseCase.None>() {

	private var resultsCount = 0

	override suspend fun run(params: None): Either<Failure, Word> {
		var result: Either<Failure, Word>

		do {
			result = repository.randomWord()
		} while (!isWordValid(result))

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