package com.findfriend

import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.findfriend.ui.animaldetailedinfo.AnimalDetailedInfoFragment
import com.findfriend.ui.animalshortinfo.AnimalShortInfoListFragment
import com.findfriend.ui.fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener,
    AnimalShortInfoListFragment.OnFragmentInteractionListener,
    FilterFragment.OnFragmentInteractionListener,
    AnimalDetailedInfoFragment.OnFragmentInteractionListener {

    private val TAG: String = javaClass.simpleName
    private lateinit var mNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mNavController = Navigation.findNavController(this,
            R.id.nav_host_fragment)
        setupBottomNavMenu(mNavController)
    }

    private fun setupBottomNavMenu(navController: NavController) {
        bottom_nav?.let {
            NavigationUI.setupWithNavController(it, navController)
        }
        bottom_nav.setOnNavigationItemSelectedListener(this)
        bottom_nav.selectedItemId = R.id.item_search
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        mNavController =
            Navigation.findNavController(this, R.id.nav_host_fragment)
        val navigated = NavigationUI.onNavDestinationSelected(item!!, mNavController)
        return navigated || super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.item_favorite -> {
                mNavController.navigate(R.id.destination_favorite)
                return true;
            }
            R.id.item_search -> {
                mNavController.navigate(R.id.destination_animal_type_selector)
                return true;
            }
            R.id.item_profile -> {
                mNavController.navigate(R.id.destination_profile)
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
    }

}
