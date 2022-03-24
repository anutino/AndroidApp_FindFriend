package com.findfriend

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.findfriend.app.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

open class MainActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var mNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment)
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

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    fun navigate(@NonNull view: View, @IdRes resId: Int) {
        Navigation.findNavController(view).navigate(resId)
    }

}

