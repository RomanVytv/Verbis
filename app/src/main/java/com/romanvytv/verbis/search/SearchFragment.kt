package com.romanvytv.verbis.search

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.Navigation
import com.romanvytv.verbis.R
import com.romanvytv.verbis.core.platform.BaseFragment
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : BaseFragment() {

	override fun layoutId() = R.layout.fragment_search

	private lateinit var searchViewModel: SearchViewModel

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		with(persistentSearchView) {
			setVoiceInputButtonDrawable(null)
			setOnLeftBtnClickListener {
				Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).popBackStack()
			}

			setOnSearchConfirmedListener { _, query ->
				hideKeyboard()
				performClick()

				Toast.makeText(requireContext(), query, Toast.LENGTH_SHORT).show()
			}
		}


	}
}