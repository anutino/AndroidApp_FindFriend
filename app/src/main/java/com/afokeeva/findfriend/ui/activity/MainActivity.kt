package com.afokeeva.findfriend.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import android.net.Uri
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import com.afokeeva.findfriend.ui.fragment.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import androidx.fragment.app.Fragment
import com.afokeeva.findfriend.ui.fragment.SelectCategoryFragment


//https://github.com/ResoCoder/navigation-component-tutorial/blob/master/app/src/main/java/com/resocoder/navigationtut/MainActivity.kt
//https://www.raywenderlich.com/6014-the-navigation-architecture-component-tutorial-getting-started

//https://inkscape.org/release/inkscape-0.92.4/ drawable

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener,  SearchFragment.OnFragmentInteractionListener{
       override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.afokeeva.findfriend.R.layout.activity_main)
        var navController = Navigation.findNavController(this, com.afokeeva.findfriend.R.id.nav_host_fragment)
        //https@ //developer.android.com/jetpack/androidx/releases/lifecycle
        setupBottomNavMenu(navController)
      }

    private fun setupBottomNavMenu(navController: NavController) {
        bottom_nav?.let {
            NavigationUI.setupWithNavController(it, navController)
        }
        bottom_nav.setOnNavigationItemSelectedListener(this)

        //navController.navigateUp()
       // navController.popBackStack()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val navController = Navigation.findNavController(this,com.afokeeva.findfriend.R.id.nav_host_fragment)
        val navigated = NavigationUI.onNavDestinationSelected(item!!, navController)
        return navigated || super.onOptionsItemSelected(item)
    }


    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        var fragment : Fragment
        when(p0.itemId){ //TODO change Fragments!
            com.afokeeva.findfriend.R.id.destination_favorite -> {
                fragment = SearchFragment()
                return loadFragment(fragment)
            }
            com.afokeeva.findfriend.R.id.destination_search -> {
                fragment = SelectCategoryFragment()
                return loadFragment(fragment)
            }
            com.afokeeva.findfriend.R.id.destination_profile-> {
                fragment = SearchFragment()
                return loadFragment(fragment)
            }
        }
        return false
    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .replace(com.afokeeva.findfriend.R.id.nav_host_fragment, fragment)
                .commit()
            return true
        }
        return false
    }

    override fun onFragmentInteraction(uri: Uri) {
    }

    override fun onBackPressed() {
//        val fragment = supportFragmentManager.findFragmentById(R.id.fl_home_fragment)
//        if (fragment !is IOnBackPressed || !(fragment as IOnBackPressed).onBackPressed()) {
//            super.onBackPressed()
//        }
    }

 }

