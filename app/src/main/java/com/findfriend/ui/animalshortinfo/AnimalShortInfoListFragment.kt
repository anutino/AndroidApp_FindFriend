package com.findfriend.ui.animalshortinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import com.findfriend.app.R
import kotlinx.android.synthetic.main.fragment_searching_animal.view.*
import com.findfriend.di.AppComponent
import com.findfriend.ui.animalshortinfo.AnimalShortInfoAdapter.OnItemClickListener
import com.findfriend.BaseFragment
import com.findfriend.MainActivity
import com.findfriend.ui.dialog.FilterFragment

class AnimalShortInfoListFragment : BaseFragment<AnimalShortInfoViewModel>() {

    private lateinit var mRecyclerView: AnimalShortRecyclerView
    private lateinit var mAdapter: AnimalShortInfoAdapter
    private lateinit var mFilterButton: Button
    private lateinit var mDialogFilter: FilterFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_searching_animal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mFilterButton = view.findViewById(R.id.button_filter)
        mDialogFilter = FilterFragment()
        mFilterButton.setOnClickListener {
            fragmentManager?.let {
                mDialogFilter.setViewModel(mViewModel)
                mDialogFilter.show(it, "FilterDialog")
            }
        }
        mRecyclerView = view.recycle_view_animal_short_info
        var listener = object : OnItemClickListener {
            override fun onItemClick(view: View, itemId: Int) {
                    mViewModel.setSelectedItem(itemId)
                    (requireActivity() as MainActivity).navigate(view,
                        R.id.destination_animal_detailed_info)
                }
        }
        mAdapter = AnimalShortInfoAdapter(listener)
        mRecyclerView.setAdapter(mAdapter)
        mViewModel.resultLive.observe(viewLifecycleOwner, Observer {
            it?.let {
                mAdapter.setItems(it)
                mAdapter.notifyDataSetChanged()
            }
        })
        mViewModel.loadAnimalList()
    }

    override fun getViewModelClass(): Class<AnimalShortInfoViewModel> {
        return AnimalShortInfoViewModel::class.java
    }

    override fun injectDependencies(appComponent: AppComponent) {
        appComponent.inject(this)
    }

}
