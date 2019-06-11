package com.romanvytv.verbis.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.romanvytv.verbis.core.UseCase
import com.romanvytv.verbis.core.exception.Failure
import com.romanvytv.verbis.data.WordsRepository
import com.romanvytv.verbis.data.entities.Word
import com.romanvytv.verbis.data.network.WordsApi
import com.romanvytv.verbis.domain.usecases.GetRandomWordUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class SearchViewModel
constructor() : ViewModel() {

    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val randomWord: MutableLiveData<Word> by lazy {
        MutableLiveData<Word>().also { loadWord() }
    }

    fun getRandomWord(): LiveData<Word> {
        return randomWord
    }

    fun loadWord() = uiScope.launch {
        Log.d("qqqq", "loadWOrd")
        val getRandomWordUseCase = GetRandomWordUseCase(WordsRepository.Network(WordsApi.create()))
        getRandomWordUseCase(UseCase.None()) { it.either(::handleFailure, ::handleWord) }
    }

    private fun handleFailure(failure: Failure) {
        randomWord.value = Word.empty()
    }

    private fun handleWord(word: Word) {
        randomWord.value = word
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}