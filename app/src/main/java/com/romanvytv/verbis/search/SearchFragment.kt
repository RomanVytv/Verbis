package com.romanvytv.verbis.search

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.romanvytv.verbis.R
import com.romanvytv.verbis.core.platform.BaseFragment
import com.romanvytv.verbis.data.entities.Word
import kotlinx.android.synthetic.main.fragment_today.*
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment() {

	override fun layoutId() = R.layout.fragment_search

	private val searchViewModel: SearchViewModel by viewModel()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
	}

	override fun onResume() {
		super.onResume()

//		searchViewModel.getRandomWord().observe(this,
//			Observer<Word> { word: Word? -> handleWord(word) })
	}

	private fun handleWord(word: Word?) {
		if (word != null)
			textView.text = word.toString()
		else
			textView.text = "word = null"
	}
}