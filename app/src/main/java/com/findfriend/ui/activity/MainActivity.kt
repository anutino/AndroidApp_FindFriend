package com.findfriend.ui.activity

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.findfriend.R
import com.findfriend.ui.fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener,
    AnimalShortInfoListFragment.OnFragmentInteractionListener,
    FilterFragment.OnFragmentInteractionListener,
    AnimalDetailedInfoFragment.OnFragmentInteractionListener {

    private val TAG: String = "MainActivity"
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController =
            Navigation.findNavController(this, R.id.nav_host_fragment)
        setupBottomNavMenu(navController)
    }

    private fun setupBottomNavMenu(navController: NavController) {
        bottom_nav?.let {
            NavigationUI.setupWithNavController(it, navController)
        }
        bottom_nav.setOnNavigationItemSelectedListener(this)
        bottom_nav.selectedItemId = R.id.item_search
        //bottomNavigationView.getMenu().findItem(bottomNavigationView.getSelectedItemId())
        //onNavigationItemSelected(bottom_nav.menu.findItem(com.findfriend.R.id.destination_search))
        //navController.navigateUp()
        //navController.popBackStack()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        navController =
            Navigation.findNavController(this, R.id.nav_host_fragment)
        val navigated = NavigationUI.onNavDestinationSelected(item!!, navController)
        return navigated || super.onOptionsItemSelected(item)
    }

    //https://medium.com/@smihajlovskih/create-instagram-like-backstack-4711600c5bff
    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) { //TODO change Fragments!
            R.id.item_favorite -> {
                Log.d(TAG, "FAVORITE ")
                 navController.navigate(R.id.destination_favorite)
                return true;
            }
            R.id.item_search -> {
                Log.d(TAG, "SEARCH")
                navController.navigate(R.id.destination_animal_type_selector)
                return true;
            }
            R.id.item_profile -> {
                Log.d(TAG, "PROFILE ")
                navController.navigate(R.id.destination_profile)
                return true;
            }
        }
        return false
    }

    override fun onFragmentInteraction(uri: Uri) {
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    override fun onFragmentInteraction(age: Double, animal: String) {
        TODO("Not yet implemented")
    }

}

