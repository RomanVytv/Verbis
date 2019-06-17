package com.romanvytv.verbis.domain.usecases

import com.romanvytv.verbis.core.Either
import com.romanvytv.verbis.core.UseCase
import com.romanvytv.verbis.core.exception.Failure
import com.romanvytv.verbis.data.WordsRepository
import com.romanvytv.verbis.data.entities.Word

class GetAllWordsUseCase
constructor(
	private val localRepo: WordsRepository.Local
) : UseCase<List<Word>, UseCase.None>() {

	override suspend fun run(params: None): Either<Failure, List<Word>> {
		val words = localRepo.getAllWords()
		return Either.Right(words)
	}
}