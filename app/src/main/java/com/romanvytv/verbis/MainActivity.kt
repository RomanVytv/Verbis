package com.romanvytv.verbis

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.get
import com.romanvytv.verbis.home.HomeFragment
import com.romanvytv.verbis.home.ONLY_FAVORITES
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

	lateinit var navigationController: NavController

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		navigationController = Navigation.findNavController(this, R.id.nav_host_fragment)
		bottom_navigation.setOnNavigationItemSelectedListener {
			when (it.itemId) {
				R.id.homeFragment -> navigateHome(false)
				R.id.favoritesFragment -> navigateHome(true)
				R.id.detailsFragment -> navigationController.navigate(R.id.detailsFragment)
				R.id.settingsFragment -> navigationController.navigate(R.id.settingsFragment)
			}
			return@setOnNavigationItemSelectedListener true
		}
	}

	private fun navigateHome(onlyFavorites: Boolean) =
		if (navigationController.currentDestination == navigationController.graph[R.id.homeFragment]) {
			(nav_host_fragment.childFragmentManager.fragments[0] as HomeFragment).filterFavorites(onlyFavorites)
		} else {
			val bundle = Bundle()
			bundle.putBoolean(ONLY_FAVORITES, onlyFavorites)
			navigationController.navigate(R.id.homeFragment, bundle)
		}
}
