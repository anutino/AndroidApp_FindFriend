package com.findfriend.ui.animalfavorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.findfriend.app.R
import com.findfriend.domain.model.ShortAnimalInfo
import com.findfriend.di.AppComponent
import com.findfriend.ui.animalshortinfo.AnimalShortInfoAdapter
 import com.findfriend.BaseFragment
import com.findfriend.MainActivity
import kotlinx.android.synthetic.main.fragment_favorite.view.*

class AnimalFavoriteFragment : BaseFragment<AnimalsFavoriteListViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.resultLiveData.observe(this, Observer{
        })

        val list = mutableListOf<ShortAnimalInfo>()

        list.add(0, ShortAnimalInfo(0, 1.0, "Iris",  "2", "dog1.jpg", false))
        list.add(1, ShortAnimalInfo(0, 1.0, "Iris",  "2", "dog2.jpg", true))
        list.add(2, ShortAnimalInfo(0, 1.0, "Iris",  "2", "dog3.jpg", true))
        list.add(3, ShortAnimalInfo(0, 1.0, "Iris",  "2", "dog4.jpg", true))
        list.add(4, ShortAnimalInfo(0, 1.0, "Iris",  "2", "dog5.jpg", true))
        list.add(5, ShortAnimalInfo(0, 1.0, "Iris",  "2", "dog6.jpg", true))
        list.add(6, ShortAnimalInfo(0, 1.0, "Iris",  "2", "dog7.jpg", true))
        list.add(7, ShortAnimalInfo(0, 1.0, "Iris",  "2", "dog8.jpg", true))
        list.add(8, ShortAnimalInfo(0, 1.0, "Iris",  "2", "cat1.jpg", true))
        list.add(9, ShortAnimalInfo(0, 1.0, "Iris",  "2", "cat2.jpg", true))
        list.add(10, ShortAnimalInfo(0, 1.0, "Iris",  "2", "cat3.jpg", true))

        Log.d("TAG", " onViewCreated list")

        val recyclerView = view.recycle_view_favorite_animal_info
         var listener = object : AnimalShortInfoAdapter.OnItemClickListener {
            override fun onItemClick(view: View, itemId: Int) {
                (requireActivity() as MainActivity).navigate(view,
                    R.id.destination_animal_detailed_info)
            }
        }
         val adapter = AnimalShortInfoAdapter(listener)
        recyclerView.setAdapter(adapter)
        adapter.setItems(list)
     }

    override fun getViewModelClass(): Class<AnimalsFavoriteListViewModel> {
        return AnimalsFavoriteListViewModel::class.java
    }

    override fun injectDependencies(appComponent: AppComponent) {
        appComponent.inject(this)
    }

}