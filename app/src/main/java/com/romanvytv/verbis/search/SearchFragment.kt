package com.romanvytv.verbis.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.romanvytv.verbis.R
import com.romanvytv.verbis.core.exception.Failure
import com.romanvytv.verbis.core.platform.BaseFragment
import com.romanvytv.verbis.data.entities.Word
import kotlinx.android.synthetic.main.fragment_today.*

class SearchFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onResume() {
        super.onResume()

        val model = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        model.getRandomWord().observe(this, Observer<Word> { word: Word? -> handleWord(word) })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        val model = ViewModelProviders.of(this).get(SearchViewModel::class.java)
//        model.getRandomWord().observe(this, Observer<Word> { word: Word? -> handleWord(word) })
//
//        textView.setOnClickListener {
//            model.loadWord()
//        }
//        textView.setOnClickListener {
//
//            CoroutineScope(Dispatchers.IO).launch {
//                val local = WordsRepository.Local(AppDatabase.newInstance())
//                val network = WordsRepository.Network(WordsApi.create())
//                val useCase = GetTodaysWordUseCase(local, network)
//
//                useCase(UseCase.None()) { it.either(::handleFailure, ::handleWord) }
//            }
//        }
    }

    private fun handleFailure(failure: Failure) = notify(failure.toString())

    private fun handleWord(word: Word?) {
        if (word != null)
            textView.text = word.toString()
        else
            textView.text = "word = null"
    }
}