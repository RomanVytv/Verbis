package com.romanvytv.verbis.home

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.lapism.searchview.Search
import com.romanvytv.verbis.R
import com.romanvytv.verbis.core.failure
import com.romanvytv.verbis.core.observe
import com.romanvytv.verbis.core.platform.BaseFragment
import com.romanvytv.verbis.data.entities.Word
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*


const val ONLY_FAVORITES = "only_favorites"

class HomeFragment : BaseFragment() {

	override fun layoutId() = R.layout.fragment_home

	private lateinit var viewModel: HomeViewModel
	private lateinit var wordsAdapter: WordsListAdapter

	private var isKeyboardShowing = false

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		view.viewTreeObserver.addOnGlobalLayoutListener { onLayoutChanged(view) }
		initSearchBar()
		initRecycleView()

		viewModel = obtainViewModel {
			observe(wordsAll, ::showWords)
			failure(failure, ::handleFail)
		}

		viewModel.load()

	}

	private fun initRecycleView() = with(wordsRecyclerView) {
		layoutManager = LinearLayoutManager(context)
		addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
	}

	private fun initSearchBar() = with(persistentSearchView) {
		setLogoIcon(R.drawable.ic_search)


		setOnQueryTextListener(object : Search.OnQueryTextListener {

			override fun onQueryTextSubmit(query: CharSequence?): Boolean {
				progressBarHome.visibility = View.VISIBLE
				viewModel.searchWord(query.toString())

				return false
			}

			override fun onQueryTextChange(newText: CharSequence?) {
				if (wordsAdapter.itemCount > 0)
					wordsAdapter.filter.filter(newText)
			}
		})
	}

	private fun showWords(words: List<Word>?) {
		if (words == null || words.isEmpty())
			tvNoWords.visibility = View.VISIBLE

		progressBarHome.visibility = View.GONE
		wordsAdapter = WordsListAdapter(words ?: Collections.emptyList())
		wordsAdapter.favoriteClickListener = object : WordsListAdapter.FavoriteClickListener {
			override fun onFavoriteClick(wordId: Long?, isFavorite: Boolean) {
				viewModel.setFavorite(wordId, isFavorite)
			}
		}
		wordsRecyclerView.adapter = wordsAdapter

	}

	fun filterFavorites(onlyFavorites: Boolean) = wordsAdapter.getFavoriteFilter(onlyFavorites).filter("")

	private fun onLayoutChanged(view: View) {
		val r = Rect()
		view.getWindowVisibleDisplayFrame(r)
		val screenHeight = view.rootView.height
		val keypadHeight = screenHeight - r.bottom

		if (keypadHeight > screenHeight * 0.15) { // 0.15 ratio is perhaps enough to determine keypad height.
			// keyboard is opened
			if (!isKeyboardShowing) {
				isKeyboardShowing = true
				onKeyboardVisibilityChanged(true)
			}
		} else {
			// keyboard is closed
			if (isKeyboardShowing) {
				isKeyboardShowing = false
				onKeyboardVisibilityChanged(false)
			}
		}
	}

	private fun onKeyboardVisibilityChanged(isVisible: Boolean) {
		if (!isVisible) {
			persistentSearchView.setQuery("", false)
			viewModel.load()
		}
	}
}