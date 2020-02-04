package com.afokeeva.findfriend.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import android.net.Uri
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import com.afokeeva.findfriend.R
import com.afokeeva.findfriend.ui.fragment.AnimalInfoFragment
import com.afokeeva.findfriend.ui.fragment.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

//https://github.com/ResoCoder/navigation-component-tutorial/blob/master/app/src/main/java/com/resocoder/navigationtut/MainActivity.kt
class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener,  SearchFragment.OnFragmentInteractionListener, AnimalInfoFragment.OnFragmentInteractionListener{
       override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.afokeeva.findfriend.R.layout.activity_main)
        var navController = Navigation.findNavController(this, com.afokeeva.findfriend.R.id.nav_host_fragment)
        //https@ //developer.android.com/jetpack/androidx/releases/lifecycle
        setupBottomNavMenu(navController)
      }

    private fun setupBottomNavMenu(navController: NavController) {
        bttm_nav?.let {
            NavigationUI.setupWithNavController(it, navController)
        }

        bttm_nav.setOnClickListener{

        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val navController = Navigation.findNavController(this,com.afokeeva.findfriend.R.id.nav_host_fragment)
        val navigated = NavigationUI.onNavDestinationSelected(item!!, navController)
        return navigated || super.onOptionsItemSelected(item)
    }


    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){
            R.id.nav_favorite -> {
                return true
            }
            R.id.nav_search -> {
                return true
            }
            R.id.nav_profile-> {
                return true
            }
        }
        return false
    }

    override fun onFragmentInteraction(uri: Uri) {
    }

 }

