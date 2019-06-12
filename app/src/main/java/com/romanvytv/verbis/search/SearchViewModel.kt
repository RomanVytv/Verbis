package com.romanvytv.verbis.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.romanvytv.verbis.core.BaseViewModel
import com.romanvytv.verbis.core.UseCase
import com.romanvytv.verbis.core.exception.Failure
import com.romanvytv.verbis.data.entities.Word
import com.romanvytv.verbis.domain.usecases.GetRandomWordUseCase

class SearchViewModel
constructor(
	private val getRandomWordUseCase: GetRandomWordUseCase
) : BaseViewModel() {


	private val randomWord: MutableLiveData<Word> by lazy {
		MutableLiveData<Word>().also { loadWord() }
	}

	fun getRandomWord(): LiveData<Word> {
		return randomWord
	}

	private fun loadWord() {
		getRandomWordUseCase(viewModelScope, UseCase.None()) { it.either(::handleFailure, ::handleWord) }
	}

	private fun handleFailure(failure: Failure) {
		randomWord.value = Word.empty()
	}

	private fun handleWord(word: Word) {
		randomWord.value = word
	}
}