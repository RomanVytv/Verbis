package com.romanvytv.verbis.domain.usecases

import android.text.format.DateUtils
import com.romanvytv.verbis.core.Either
import com.romanvytv.verbis.core.UseCase
import com.romanvytv.verbis.core.exception.Failure
import com.romanvytv.verbis.data.WordsRepository
import com.romanvytv.verbis.data.entities.TodayWordEntity
import com.romanvytv.verbis.data.entities.WordEntity
import java.util.*

class GetTodaysWordUseCase
constructor(
    private val localRepo: WordsRepository.Local,
    private val remoteRepo: WordsRepository.Network
) : UseCase<WordEntity, UseCase.None>() {

    override suspend fun run(params: None): Either<Failure, WordEntity> {
        val existingTodayWord = localRepo.getTodaysWord()

        if (DateUtils.isToday(existingTodayWord.date.time))
            return remoteRepo.getWordDetails(existingTodayWord.word)

        val newWord = remoteRepo.randomWord()

        if (newWord.isLeft)
            return Either.Left(Failure.ServerError)

        newWord.either({}, { localRepo.saveTodayWord(TodayWordEntity(it.word, Date())) })

        return newWord
    }
}