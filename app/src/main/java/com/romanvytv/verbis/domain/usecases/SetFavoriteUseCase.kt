package com.romanvytv.verbis.domain.usecases

import com.romanvytv.verbis.core.Either
import com.romanvytv.verbis.core.UseCase
import com.romanvytv.verbis.core.exception.Failure
import com.romanvytv.verbis.data.WordsRepository
import com.romanvytv.verbis.data.entities.Word

class SetFavoriteUseCase
constructor(
	private val localRepo: WordsRepository.Local
) : UseCase<UseCase.None, Word>() {

	override suspend fun run(params: Word): Either<Failure, None> {
		params.id?.let { localRepo.setFavotire(it, params.isFavorite) }
		return Either.Right(None())
	}
}