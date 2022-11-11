package com.findfriend.ui.mainmenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.findfriend.app.R
import com.findfriend.ui.di.AppComponent
import com.findfriend.BaseFragment
import com.findfriend.app.databinding.FragmentAnimalTypeSelectorBinding
import com.findfriend.data.Constants

class AnimalTypeSelectorFragment : BaseFragment<AnimalTypeSelectorViewModel>() {

    lateinit var binding: FragmentAnimalTypeSelectorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_animal_type_selector, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.resultLiveDate.observe(viewLifecycleOwner) {
            it?.let {
                for (animalType in it) {
                    when (animalType.name) {
                        Constants.AnimalType.DOG.toString() -> {
                            binding.dogType.visibility = View.VISIBLE
                            binding.dogType.setOnClickListener {
                                navigateToAnimalShortInfoList(Constants.AnimalType.DOG)
                            }
                        }
                        Constants.AnimalType.CAT.toString() -> {
                            binding.catType.visibility = View.VISIBLE
                            binding.catType.setOnClickListener {
                                navigateToAnimalShortInfoList(Constants.AnimalType.CAT)
                            }
                        }
                        Constants.AnimalType.ALL.toString() ->
                            binding.allTypes.setOnClickListener {
                                navigateToAnimalShortInfoList(Constants.AnimalType.ALL)
                            }
                    }
                }
            }
        }
        viewModel.loadTypes()
    }

    private fun navigateToAnimalShortInfoList(type: Constants.AnimalType) {
        findNavController().navigate(
            AnimalTypeSelectorFragmentDirections.actionToAnimalShortInfoList(Constants(type)))
    }

    override fun getViewModelClass(): Class<AnimalTypeSelectorViewModel> {
        return AnimalTypeSelectorViewModel::class.java
    }

    override fun injectDependencies(appComponent: AppComponent) {
        appComponent.inject(this)
    }

}
