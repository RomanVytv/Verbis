package com.romanvytv.verbis.domain.usecases

import com.romanvytv.verbis.core.Either
import com.romanvytv.verbis.core.UseCase
import com.romanvytv.verbis.core.exception.Failure
import com.romanvytv.verbis.data.WordsRepository
import com.romanvytv.verbis.data.entities.Word

class GetRandomWordUseCase
constructor(private val repository: WordsRepository.Network) : UseCase<Word, UseCase.None>(){

    override suspend fun run(params: None): Either<Failure, Word> = repository.randomWord()
}