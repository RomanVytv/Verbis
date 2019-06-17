package com.romanvytv.verbis.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.romanvytv.verbis.core.BaseViewModel
import com.romanvytv.verbis.core.UseCase
import com.romanvytv.verbis.data.entities.Word
import com.romanvytv.verbis.domain.usecases.GetAllWordsUseCase
import com.romanvytv.verbis.domain.usecases.GetRandomWordUseCase
import com.romanvytv.verbis.domain.usecases.SetFavoriteUseCase
import org.koin.core.get
import java.util.*

class HomeViewModel : BaseViewModel() {

	private var getAllWordsUsecase: GetAllWordsUseCase = get()
	private var setFavoriteUseCase: SetFavoriteUseCase = get()

	private var getRandomWordUseCase: GetRandomWordUseCase = get()


	val wordsAll: MutableLiveData<List<Word>> = MutableLiveData()

	fun load() {

//		for (i in 1..5)
//			getRandomWordUseCase(viewModelScope, UseCase.None())

		getAllWordsUsecase(viewModelScope, UseCase.None()) { it.either(::handleFailure, ::handleWords) }
	}

	fun searchWord(query: String) {
		getRandomWordUseCase(viewModelScope, UseCase.None()) { it.either(::handleFailure, ::handleQueriedWord) }
	}

	private fun handleQueriedWord(word: Word) {
		wordsAll.value = Collections.singletonList(word)
	}

	private fun handleWords(words: List<Word>) {
		wordsAll.value = words
	}

	fun setFavorite(wordId: Long?, isFavorite: Boolean) {
		setFavoriteUseCase(viewModelScope, Word(wordId, isFavorite))
	}


}
