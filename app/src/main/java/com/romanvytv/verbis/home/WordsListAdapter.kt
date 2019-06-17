package com.romanvytv.verbis.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.romanvytv.verbis.R
import com.romanvytv.verbis.data.entities.Word
import kotlinx.android.synthetic.main.list_item_word.view.*


class WordsListAdapter
constructor(private val wordsList: List<Word>) : RecyclerView.Adapter<WordsListAdapter.ViewHolder>(), Filterable {

	private var wordsListFiltered: List<Word> = wordsList

	lateinit var favoriteClickListener: FavoriteClickListener

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
		ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_word, parent, false))

	override fun getItemCount() = wordsListFiltered.size

	override fun onBindViewHolder(holder: ViewHolder, position: Int) =
		holder.bind(wordsListFiltered[position], favoriteClickListener)

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

		fun bind(word: Word, favoriteClickListener: FavoriteClickListener) {
			itemView.tvWord.text = word.word
			itemView.tvPronunciation.text = word.pronunciation?.value
			setFavoriteIcon(word.isFavorite)
			itemView.ivFavorite.setOnClickListener {
				word.isFavorite = !word.isFavorite
				favoriteClickListener.onFavoriteClick(word.id, word.isFavorite)
				setFavoriteIcon(word.isFavorite)
			}
		}

		private fun setFavoriteIcon(isFavorite: Boolean) = if (isFavorite)
			itemView.ivFavorite.setImageResource(R.drawable.ic_star_yellow)
		else
			itemView.ivFavorite.setImageResource(R.drawable.ic_star_border_yellow)

	}

	override fun getFilter(): Filter {
		return object : Filter() {
			override fun performFiltering(charSequence: CharSequence): FilterResults {
				val charString = charSequence.toString()
				if (charString.isEmpty()) {
					wordsListFiltered = wordsList
				} else {
					val filteredList = ArrayList<Word>()
					for (row in wordsList) {
						if (row.word.toLowerCase().contains(charString.toLowerCase()))
							filteredList.add(row)
					}

					wordsListFiltered = filteredList
				}

				val filterResults = FilterResults()
				filterResults.values = wordsListFiltered
				return filterResults
			}

			override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
				wordsListFiltered = filterResults.values as ArrayList<Word>
				notifyDataSetChanged()
			}
		}
	}

	fun getFavoriteFilter(onlyFavorites: Boolean): Filter {
		return object : Filter() {
			override fun performFiltering(charSequence: CharSequence): FilterResults {
				wordsListFiltered = if (!onlyFavorites)
					wordsList
				else
					wordsList.filter { it.isFavorite }

				val filterResults = FilterResults()
				filterResults.values = wordsListFiltered
				return filterResults
			}

			override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
				wordsListFiltered = filterResults.values as ArrayList<Word>
				notifyDataSetChanged()
			}
		}
	}

	interface FavoriteClickListener {
		fun onFavoriteClick(wordId: Long?, isFavorite: Boolean)
	}
}