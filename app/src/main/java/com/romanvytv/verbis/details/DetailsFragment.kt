package com.romanvytv.verbis.details

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.romanvytv.verbis.R
import com.romanvytv.verbis.core.failure
import com.romanvytv.verbis.core.observe
import com.romanvytv.verbis.core.platform.BaseFragment
import com.romanvytv.verbis.data.entities.Result
import com.romanvytv.verbis.data.entities.Word
import com.romanvytv.verbis.domain.usecases.TODAY_WORD_ID
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.list_item_result.view.*

class DetailsFragment : BaseFragment() {
	override fun layoutId() = R.layout.fragment_details

	private lateinit var viewModel: DetailsViewModel

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		viewModel = obtainViewModel {
			observe(word, ::init)
			failure(failure, ::handleFail)
		}

		viewModel.load(arguments?.getLong("wordId") ?: TODAY_WORD_ID)
	}

	private fun init(word: Word?) {
		word?.let {
			progressBar.visibility = View.GONE
			tvWord.text = word.word

			setFavoriteIcon(word.isFavorite)
			ivFavorite.setOnClickListener {
				viewModel.setFavorite(!word.isFavorite)
				setFavoriteIcon(word.isFavorite)
			}

			if (!TextUtils.isEmpty(word.pronunciation.value)) {
				tvPronunciation.text = "[/${word.pronunciation.value}/]"
				tvPronunciation.visibility = View.VISIBLE
			} else {
				tvPronunciation.visibility = View.GONE
			}
		}

		word?.results.let {
			for (result in word!!.results)
				llResultsContainer.addView(getResultView(result))
		}
	}

	private fun getResultView(result: Result): View {
		val view = View.inflate(this.context, R.layout.list_item_result, null)

		view.tvPartOfSpeech.text = result.partOfSpeech

		if (result.definition.isNotEmpty())
			view.tvDefinition.text = result.definition

		if (result.typeOf.isNotEmpty()) {
			view.tvTypeOf.text = result.typeOf.joinToString()
			view.tvTypeOf.visibility = View.VISIBLE
			view.tvTypeOfLbl.visibility = View.VISIBLE
		} else {
			view.tvTypeOfLbl.visibility = View.GONE
			view.tvTypeOfLbl.visibility = View.GONE
		}

		if (result.derivation.isNotEmpty()) {
			view.tvDerivation.text = result.derivation.joinToString()
			view.tvDerivation.visibility = View.VISIBLE
			view.tvDerivationLbl.visibility = View.VISIBLE
		} else {
			view.tvDerivationLbl.visibility = View.GONE
			view.tvDerivationLbl.visibility = View.GONE
		}

		return view
	}

	private fun setFavoriteIcon(isFavorite: Boolean) {
		ivFavorite.visibility = View.VISIBLE

		if (isFavorite)
			ivFavorite.setImageResource(R.drawable.ic_star_yellow)
		else
			ivFavorite.setImageResource(R.drawable.ic_star_border_yellow)
	}
}