package com.afokeeva.findfriend.infoFromServer

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.afokeeva.findfriend.infoFromServer.Tables.Animal
import com.bumptech.glide.Glide
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DiffUtil
import androidx.lifecycle.MutableLiveData
import androidx.paging.*
import com.afokeeva.findfriend.MainActivity
import androidx.paging.PagedList
import android.os.Looper
import android.os.Handler
import java.util.concurrent.Executor
import androidx.lifecycle.LiveData


class SearchActivity2 : AppCompatActivity() {
    companion object arrayObj {
        val TAG = "SearchActivity"
        var listAnimals = mutableListOf<Animal>()//: ArrayList<Animal> = ArrayList()
        var testArray = listOf(
            "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg",
            "https://wallscloud.net/uploads/cache/1266658504/blue-space-planet-krB5-1024x576-MM-90.jpg",
            "https://avatars.mds.yandex.net/get-pdb/1025580/369ea651-40b4-4467-91da-069ba497b3d1/s1200",
            "https://pp.userapi.com/c824502/v824502702/18b194/TAYpSgN3JeU.jpg"
        )
    }

    @SuppressLint("WrongThread")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listAnimals = listOf<Animal>(
            Animal(1, 1.0, "dog1", "", 1, "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg"),
            Animal(
                2,
                1.5,
                "dog2",
                "",
                1,
                "https://wallscloud.net/uploads/cache/1266658504/blue-space-planet-krB5-1024x576-MM-90.jpg"
            ),
            Animal(
                3,
                2.0,
                "dog3",
                "",
                1,
                "https://avatars.mds.yandex.net/get-pdb/1025580/369ea651-40b4-4467-91da-069ba497b3d1/s1200"
            ),
            Animal(
                4,
                2.5,
                "dog4",
                "",
                1,
                "https://pp.userapi.com/c824502/v824502702/18b194/TAYpSgN3JeU.jpg"
            ),
            Animal(5, 3.5, "dog1", "", 1, "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg"),
            Animal(6, 3.5, "dog1", "", 1, "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg"),
            Animal(1, 1.0, "dog1", "", 1, "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg"),
            Animal(1, 1.0, "dog1", "", 1, "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg"),
            Animal(1, 1.0, "dog1", "", 1, "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg"),
            Animal(1, 1.0, "dog1", "", 1, "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg"),
            Animal(1, 1.0, "dog1", "", 1, "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg")
        ).toMutableList()

        when {
            MainActivity.chosenAnimal == 1 -> { //Dogs
                println("_____SearchActivity--1")
                //ServerRequest.findAllAnimalsByType(1)
            }
            MainActivity.chosenAnimal == 2 -> //Cats
                println("_____SearchActivity--2")
            //ServerRequest.findAllAnimalsByType(2)
            MainActivity.chosenAnimal == 3 -> //Dogs_and_Cats
                println("_____SearchActivity--3")
            // ServerRequest.findAllAnimals()
        }
        //ArrayListAnimals are got
        setContentView(com.afokeeva.findfriend.R.layout.activity_search)
        val rv = findViewById<RecyclerView>(com.afokeeva.findfriend.R.id.recycleViewImage)
        //rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        /* val articleLiveData: LiveData<PagedList<Animal>>
        var dataSource = AnimalDataSource()
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(10)
            .build()

        var executor: MainThreadExecutor = MainThreadExecutor()

        val list = PagedList.Builder(dataSource, config)
            .setFetchExecutor(executor)
            .setNotifyExecutor(executor)
            .build();

        rv.adapter = AnimalAdapter()
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv.layoutManager
        var linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv.adapter = Adapter(listAnimals)
        rv.layoutManager = linearLayoutManager
        rv.setHasFixedSize(true)
    }*/
    }
}






