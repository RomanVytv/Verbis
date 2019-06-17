package com.romanvytv.verbis.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.romanvytv.verbis.core.BaseViewModel
import com.romanvytv.verbis.core.UseCase
import com.romanvytv.verbis.data.entities.Word
import com.romanvytv.verbis.domain.usecases.GetAllWordsUseCase
import com.romanvytv.verbis.domain.usecases.SearchWordUseCase
import com.romanvytv.verbis.domain.usecases.SetFavoriteUseCase
import org.koin.core.get

class HomeViewModel : BaseViewModel() {

	private var getAllWordsUsecase: GetAllWordsUseCase = get()
	private var setFavoriteUseCase: SetFavoriteUseCase = get()
	private var searchWordUseCase: SearchWordUseCase = get()


	val wordsAll: MutableLiveData<List<Word>> = MutableLiveData()

	fun load() {
		getAllWordsUsecase(viewModelScope, UseCase.None()) { it.either(::handleFailure, ::handleWords) }
	}

	fun searchWord(query: String) {
		searchWordUseCase(viewModelScope, query) { it.either(::handleFailure, ::handleQueriedWord) }
	}

	private fun handleQueriedWord(word: Word) {
		val list = ArrayList<Word>()
		list.add(word)
		wordsAll.value = list
	}

	private fun handleWords(words: List<Word>) {
		wordsAll.value = words
	}

	fun setFavorite(wordId: Long?, isFavorite: Boolean) {
		setFavoriteUseCase(viewModelScope, Word(wordId, isFavorite))
	}


}
