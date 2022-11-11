package com.findfriend.ui.animalshortinfo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.findfriend.app.R
import com.findfriend.ui.di.AppComponent
import com.findfriend.ui.animalshortinfo.AnimalShortInfoAdapter.OnItemClickListener
import com.findfriend.BaseFragment
import com.findfriend.ui.dialog.FilterFragment

class AnimalShortInfoListFragment : BaseFragment<AnimalShortInfoViewModel>() {

    private val animalType: AnimalShortInfoListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_searching_animal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println(animalType.animalTypeArgs.type)
        val filterButton: (Button) = view.findViewById(R.id.button_filter)
        val dialogFilter = FilterFragment()
        filterButton.setOnClickListener {
            parentFragmentManager.let {
                dialogFilter.setViewModel(viewModel)
                dialogFilter.show(it, "FilterDialog")
            }
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycle_view_animal_short_info)
        val listener = object : OnItemClickListener {
            override fun onItemClick(view: View, itemId: Int) {
                findNavController().navigate(
                    AnimalShortInfoListFragmentDirections.actionToAnimalDetailedInfo(itemId))
            }
        }
        val adapter = AnimalShortInfoAdapter(listener)
        recyclerView.adapter = adapter
        viewModel.resultLive.observe(viewLifecycleOwner) {
            it?.let {
                Log.d("AFF", "AnimalShortInfoAdapter " + it.size)
                adapter.setItems(it)
            }
        }
        viewModel.loadAnimalList(animalType.animalTypeArgs.type.ordinal)
    }

    override fun getViewModelClass(): Class<AnimalShortInfoViewModel> {
        return AnimalShortInfoViewModel::class.java
    }

    override fun injectDependencies(appComponent: AppComponent) {
        appComponent.inject(this)
    }

}
