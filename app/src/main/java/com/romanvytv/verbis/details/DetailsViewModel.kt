package com.romanvytv.verbis.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.romanvytv.verbis.core.BaseViewModel
import com.romanvytv.verbis.core.UseCase
import com.romanvytv.verbis.data.entities.Word
import com.romanvytv.verbis.domain.usecases.GetRandomWordUseCase
import com.romanvytv.verbis.domain.usecases.GetTodaysWordUseCase
import org.koin.core.get

class DetailsViewModel : BaseViewModel() {

	init {
		load()
	}

	private lateinit var getTodaysWordUseCase: GetTodaysWordUseCase
	private lateinit var getRandomWordUseCase: GetRandomWordUseCase
	val word: MutableLiveData<Word> = MutableLiveData()

	override fun load() {
		getTodaysWordUseCase = get()
//		getRandomWordUseCase = get()
		getTodaysWordUseCase(viewModelScope, UseCase.None()) { it.either(::handleFailure, ::handleWord) }
//		getRandomWordUseCase(viewModelScope, UseCase.None()) { it.either(::handleFailure, ::handleWord) }
	}

	private fun handleWord(word: Word) {
		this.word.value = word
	}

	fun setFavorite(isFavorite: Boolean) {
		//TODO implement
	}
}
