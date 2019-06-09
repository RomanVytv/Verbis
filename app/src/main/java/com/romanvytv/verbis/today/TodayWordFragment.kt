package com.romanvytv.verbis.today

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.romanvytv.verbis.R
import com.romanvytv.verbis.core.UseCase
import com.romanvytv.verbis.core.exception.Failure
import com.romanvytv.verbis.core.platform.BaseFragment
import com.romanvytv.verbis.data.WordsRepository
import com.romanvytv.verbis.data.entities.Word
import com.romanvytv.verbis.data.network.WordsApi
import com.romanvytv.verbis.domain.usecases.GetRandomWordUseCase
import kotlinx.android.synthetic.main.fragment_today.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodayWordFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_today, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textView.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val useCase = GetRandomWordUseCase(WordsRepository.Network(WordsApi.create()))
                useCase(UseCase.None()) { it.either(::handleError, ::handleSuccess) }
            }
        }

    }

    private fun handleError(failure: Failure) =
        notify(failure.toString())

    private fun handleSuccess(wordEntity: Word) {
        textView.text = wordEntity.toString()
        Log.d("zalu", wordEntity.toString())
    }
}