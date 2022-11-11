package com.findfriend.ui.animalfavorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.findfriend.app.R
import com.findfriend.ui.di.AppComponent
import com.findfriend.ui.animalshortinfo.AnimalShortInfoAdapter
import com.findfriend.BaseFragment
import com.findfriend.MainActivity

class AnimalFavoriteFragment : BaseFragment<AnimalsFavoriteListViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.resultLiveData.observe(viewLifecycleOwner, Observer {
        })
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycle_view_favorite_animal_info)
        val adapter = AnimalShortInfoAdapter(object : AnimalShortInfoAdapter.OnItemClickListener {
            override fun onItemClick(view: View, itemId: Int) {
                (requireActivity() as MainActivity).navigate(view,
                    R.id.destination_animal_detailed_info)
            }
        })
        recyclerView.adapter = adapter
    }

    override fun getViewModelClass(): Class<AnimalsFavoriteListViewModel> {
        return AnimalsFavoriteListViewModel::class.java
    }

    override fun injectDependencies(appComponent: AppComponent) {
        appComponent.inject(this)
    }

}