package com.findfriend.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.findfriend.BaseFragment
import com.findfriend.app.R
import com.findfriend.ui.di.AppComponent

class ProfileFragment : BaseFragment<ProfileViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var addImages = view.findViewById<Button>(R.id.add_images)
        addImages.setOnClickListener { viewModel.addImages() }
        var addAnimal = view.findViewById<Button>(R.id.add_animals)
        addAnimal.setOnClickListener { viewModel.addAnimal() }
    }

    override fun getViewModelClass(): Class<ProfileViewModel> {
        return ProfileViewModel::class.java
    }

    override fun injectDependencies(appComponent: AppComponent) {
        appComponent.inject(this)
    }


}