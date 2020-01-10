package com.afokeeva.findfriend

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.afokeeva.findfriend.infoFromServer.NetworkService
import com.afokeeva.findfriend.infoFromServer.Tables.Animal
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(),  View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NetworkService.companionFun()
            .instance
            .getJSONApi()
            .findAllAnimals()
            .enqueue(object : Callback<List<Animal>>{
                override fun onResponse(call: Call<List<Animal>>,response: Response<List<Animal>>) {
                    if(response.isSuccessful) {
                        print("success")
                    }else {
                        print("not success")
                    }
                }

                override fun onFailure(call: Call<List<Animal>>, t: Throwable) {
                    print("onFailure")
                }
            })


    }
    override fun onClick(v: View) {
        when(v.id) {
            R.id.imageButtonDog -> startActivity(Intent(this@MainActivity, SearchActivity::class.java))
            R.id.imageButtonCat -> startActivity(Intent(this@MainActivity, SearchActivity::class.java))
            R.id.imageButtonDogAndCat -> startActivity(Intent(this@MainActivity, SearchActivity::class.java))
        }
    }

 }

