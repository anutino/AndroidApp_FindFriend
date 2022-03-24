package com.findfriend.ui.mainmenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.findfriend.app.R
import com.findfriend.di.AppComponent
import com.findfriend.BaseFragment
import com.findfriend.MainActivity
import com.findfriend.data.Constants
import kotlinx.android.synthetic.main.fragment_animal_type_selector.*

class AnimalTypeSelectorFragment : BaseFragment<AnimalTypeViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_animal_type_selector, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.resultLiveDate.observe(viewLifecycleOwner, Observer {
            it?.let {
                initDogTypeSelector()
                initCatTypeSelector()
                initAllTypeSelector()

                /*for (animalType in it) {
                    when (animalType.name) {
                        Constants.AnimalType.DOG.toString() -> initDogTypeSelector()
                        Constants.AnimalType.CAT.toString() -> initCatTypeSelector()
                        Constants.AnimalType.ALL.toString() -> initAllTypeSelector()
                    }
                }*/
            }
        })
        mViewModel.loadTypeList()
    }

    private fun initDogTypeSelector() {
        dog_type.visibility = View.VISIBLE
        dog_type.setOnClickListener {
                mViewModel.setSelectedItem(Constants.AnimalType.DOG.ordinal)
            (requireActivity() as MainActivity).navigate(it,
                R.id.destination_animal_short_info_list)

        }
    }

    private fun initCatTypeSelector() {
        cat_type.visibility = View.VISIBLE
        cat_type.setOnClickListener {
            mViewModel.setSelectedItem(Constants.AnimalType.CAT.ordinal)
            (requireActivity() as MainActivity).navigate(it,
                R.id.destination_animal_short_info_list)
        }
    }

    private fun initAllTypeSelector() {
        all_types.visibility = View.VISIBLE
        all_types.setOnClickListener {
            mViewModel.setSelectedItem(Constants.AnimalType.ALL.ordinal)
            (requireActivity() as MainActivity).navigate(it,
                R.id.destination_animal_short_info_list)
        }
    }


    override fun getViewModelClass(): Class<AnimalTypeViewModel> {
        return AnimalTypeViewModel::class.java
    }

    override fun injectDependencies(appComponent: AppComponent) {
        appComponent.inject(this)
    }

}
