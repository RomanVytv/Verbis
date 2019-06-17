package com.romanvytv.verbis.domain.usecases

import com.romanvytv.verbis.core.Either
import com.romanvytv.verbis.core.UseCase
import com.romanvytv.verbis.core.exception.Failure
import com.romanvytv.verbis.data.WordsRepository
import com.romanvytv.verbis.data.entities.Word

class GetWordDetailsUseCase
constructor(
	private val remote: WordsRepository.Network,
	private val local: WordsRepository.Local
) : UseCase<Word, Long>() {

	override suspend fun run(params: Long): Either<Failure, Word> {
		val word = local.getWordById(params)
		return remote.getWordDetails(word.word)
	}
}