package com.afokeeva.findfriend

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.afokeeva.findfriend.infoFromServer.SearchActivity2

class MainActivity : AppCompatActivity(),  View.OnClickListener {

    enum class Animals {
        DOG, CAT, DOGS_AND_CATS
    }
    companion object chosenAnimal {
        var chosenAnimal = 3 //default

        private fun chosenAnimal(animal: MainActivity.Animals) {
            when (animal) {
                MainActivity.Animals.DOG -> chosenAnimal = 1
                MainActivity.Animals.CAT -> chosenAnimal = 2
                MainActivity.Animals.DOGS_AND_CATS -> chosenAnimal = 3
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.imageButtonDog -> { chosenAnimal(Animals.DOG); startActivity(Intent(this@MainActivity, TestActivity::class.java))}
            R.id.imageButtonCat -> { chosenAnimal(Animals.CAT); startActivity(Intent(this@MainActivity, SearchActivity::class.java))}
            R.id.imageButtonDogAndCat -> { chosenAnimal(Animals.DOGS_AND_CATS); startActivity(Intent(this@MainActivity, SearchActivity::class.java))}
        }
    }

 }

