package com.romanvytv.verbis.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.romanvytv.verbis.core.BaseViewModel
import com.romanvytv.verbis.core.UseCase
import com.romanvytv.verbis.data.entities.Word
import com.romanvytv.verbis.domain.usecases.GetTodaysWordUseCase
import com.romanvytv.verbis.domain.usecases.TODAY_WORD_ID
import org.koin.core.get

class DetailsViewModel : BaseViewModel() {

	private lateinit var getTodaysWordUseCase: GetTodaysWordUseCase
	val word: MutableLiveData<Word> = MutableLiveData()

	fun load(wordId: Long = TODAY_WORD_ID) {

		if (wordId == TODAY_WORD_ID) {
			getTodaysWordUseCase = get()
			getTodaysWordUseCase(viewModelScope, UseCase.None()) { it.either(::handleFailure, ::handleWord) }
			return
		}


	}

	private fun handleWord(word: Word) {
		this.word.value = word
	}

	fun setFavorite(isFavorite: Boolean) {
		//TODO implement
	}
}
