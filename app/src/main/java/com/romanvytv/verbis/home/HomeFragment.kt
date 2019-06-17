package com.romanvytv.verbis.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.romanvytv.verbis.R
import com.romanvytv.verbis.core.platform.BaseFragment
import kotlinx.android.synthetic.main.fragment_search.*

const val ONLY_FAVORITES = "only_favorites"

class HomeFragment : BaseFragment() {

	override fun layoutId() = R.layout.fragment_home

	private lateinit var homeViewModel: HomeViewModel

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		with(persistentSearchView) {
			setVoiceInputButtonDrawable(null)
			hideLeftButton()

			setOnSearchConfirmedListener { _, query ->
				hideKeyboard()
				performClick()

				Toast.makeText(requireContext(), query, Toast.LENGTH_SHORT).show()
			}
		}

		Toast.makeText(
			requireContext(),
			"onViewCreated - " + arguments?.getBoolean(ONLY_FAVORITES).toString(),
			Toast.LENGTH_SHORT
		).show()
	}

	fun filterFavorites(onlyFavorites: Boolean) {
		Toast.makeText(requireContext(), "filterFavorites - $onlyFavorites", Toast.LENGTH_SHORT).show()
	}
}