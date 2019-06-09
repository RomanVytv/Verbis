package com.romanvytv.verbis.domain.usecases

import android.text.format.DateUtils
import com.romanvytv.verbis.core.Either
import com.romanvytv.verbis.core.UseCase
import com.romanvytv.verbis.core.exception.Failure
import com.romanvytv.verbis.data.WordsRepository
import com.romanvytv.verbis.data.entities.TodayWord
import com.romanvytv.verbis.data.entities.Word
import java.util.*

class GetTodaysWordUseCase
constructor(
    private val localRepo: WordsRepository.Local,
    private val remoteRepo: WordsRepository.Network
) : UseCase<Word, UseCase.None>() {

    override suspend fun run(params: None): Either<Failure, Word> {
        val existingTodayWord = localRepo.getTodaysWord()

        if (DateUtils.isToday(existingTodayWord.timeStamp))
            return remoteRepo.getWordDetails(existingTodayWord.word)

        val newWord = remoteRepo.randomWord()

        if (newWord.isLeft)
            return Either.Left(Failure.ServerError)

        newWord.either({}, { localRepo.saveTodayWord(TodayWord(it.word)) })

        return newWord
    }
}