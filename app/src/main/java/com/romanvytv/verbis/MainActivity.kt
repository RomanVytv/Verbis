package com.romanvytv.verbis

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navigationController.addOnDestinationChangedListener { _, destination, _ -> updateToolbar(destination) }

        NavigationUI.setupWithNavController(bottom_navigation, navigationController)
    }

    private fun updateToolbar(destination: NavDestination) {
        toolbar_main.title = destination.label
    }
}
