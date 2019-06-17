package com.romanvytv.verbis.domain.usecases

import com.romanvytv.verbis.core.Either
import com.romanvytv.verbis.core.UseCase
import com.romanvytv.verbis.core.exception.Failure
import com.romanvytv.verbis.data.WordsRepository
import com.romanvytv.verbis.data.entities.Word

class SearchWordUseCase
constructor(
	private val remote: WordsRepository.Network,
	private val local: WordsRepository.Local
) : UseCase<Word, String>() {

	override suspend fun run(params: String): Either<Failure, Word> {
		val word = remote.getWordDetails(params)

		word.either({}, ::handleWord)

		return word
	}

	private fun handleWord(word: Word) {
		if (word.word.isNotEmpty())
			local.saveWord(word)
	}
}
