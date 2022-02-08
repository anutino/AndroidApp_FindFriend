package com.findfriend.ui.animalfavorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.findfriend.ui.animalshortinfo.AnimalShortInfoViewModel
import androidx.lifecycle.Observer
import com.findfriend.R
import com.findfriend.data.ShortAnimalInfo
import com.findfriend.ui.adapter.AnimalListAdapter
import kotlinx.android.synthetic.main.fragment_favorite.view.*

class FavoriteFragment : Fragment() {
    private val TAG: String =javaClass.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mViewModel = ViewModelProviders.of(this@FavoriteFragment)
            .get(AnimalShortInfoViewModel::class.java)
        mViewModel.resultLive.observe(this, Observer{
        })

        val list = mutableListOf<ShortAnimalInfo>()

        list.add(0, ShortAnimalInfo(0, 1.0, "Iris",  "2", "file:///C:/opt/animals/dog/olhon.jpg", false))
        list.add(1, ShortAnimalInfo(0, 1.0, "Iris",  "2", "file:///C:/opt/animals/dog/olhon.jpg", true))
        list.add(2, ShortAnimalInfo(0, 1.0, "Iris",  "2", "file:///C:/opt/animals/dog/olhon.jpg", true))
        list.add(3, ShortAnimalInfo(0, 1.0, "Iris",  "2", "file:///C:/opt/animals/dog/olhon.jpg", true))
        list.add(4, ShortAnimalInfo(0, 1.0, "Iris",  "2", "file:///C:/opt/animals/dog/olhon.jpg", true))

        Log.d(TAG, " onViewCreated list")

        val recyclerView = view.recycle_view_favorite_animal_info
        recyclerView.layoutManager = GridLayoutManager(context, 4)
        val myAdapter = AnimalListAdapter()
        myAdapter.setItems(list)
        recyclerView.adapter = myAdapter


//        //подписываем адаптер на изменения списка
//        mViewModel.getAnimalInfo().observe(this, Observer {
//            it?.let {
//                recyclerView.adapter.refreshItems(it)
//            }
//        })
    }

}