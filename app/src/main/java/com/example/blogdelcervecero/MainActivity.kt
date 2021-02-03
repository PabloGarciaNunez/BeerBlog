package com.example.blogdelcervecero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val navigationView: BottomNavigationView by lazy { findViewById(R.id.navigationView) }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.setupNavigation()
    }

    //region PRIVATE_METHODS

    /**
     * Setup navigation view with bottom navigation view
     */
    private fun setupNavigation() {

        navigationView.itemIconTintList = null
        val navHostFragment = supportFragmentManager.findFragmentById(
                R.id.nav_host_fragment
        ) as NavHostFragment
        val navController: NavController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->

            this.changeBottomBarVisibility(destination)
        }
        NavigationUI.setupWithNavController(navigationView, navController)
    }

    /**
     * Change bottom navigation bar visibility
     *
     * @param destination - navigation destination
     */
    private fun changeBottomBarVisibility(destination: NavDestination) {

        navigationView.visibility = when (destination.id) {

            R.id.beerDetailFragment,
            R.id.newBeerFragment -> View.GONE
            else -> View.VISIBLE
        }
    }

    //endregion
}
