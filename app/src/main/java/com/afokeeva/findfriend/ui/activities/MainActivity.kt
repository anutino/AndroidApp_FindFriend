package com.afokeeva.findfriend.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
 import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.R
import com.afokeeva.findfriend.viewmodels.SelectCategoryViewModel


class MainActivity : AppCompatActivity(){

    enum class Animals {
        DOG, CAT, DOGS_AND_CATS
    }
    companion object chosenAnimal {
        var chosenAnimal = 3 //default

        private fun chosenAnimal(animal: Animals) {
            when (animal) {
                Animals.DOG -> chosenAnimal = 1
                Animals.CAT -> chosenAnimal = 2
                Animals.DOGS_AND_CATS -> chosenAnimal = 3
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var listImg = SelectCategoryViewModel().categoryListLiveData
        print("result_ $listImg ")
        setContentView(com.afokeeva.findfriend.R.layout.activity_main)





       // var navController = Navigation.findNavController(this, com.afokeeva.findfriend.R.id.my_nav_host_fragment)
        //https@ //developer.android.com/jetpack/androidx/releases/lifecycle
    }

 }

