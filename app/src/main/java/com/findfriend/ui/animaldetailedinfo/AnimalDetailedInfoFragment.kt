package com.findfriend.ui.animaldetailedinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.findfriend.app.R
import com.findfriend.domain.model.ShortAnimalInfo
import com.findfriend.di.AppComponent
import com.findfriend.BaseFragment
import com.google.android.material.navigation.NavigationView

class AnimalDetailedInfoFragment : BaseFragment<AnimalDetailedInfoViewModel>(),
    NavigationView.OnNavigationItemSelectedListener {

    private lateinit var mViewPager: ViewPager2
    private lateinit var mName_Age: TextView
    private lateinit var mDescription: TextView
    private lateinit var mFavoriteButton: ImageButton
    private lateinit var mMediaViewPager: MediaViewPager2
    private lateinit var mItem: ShortAnimalInfo

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_animal_detailed_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initViewModal()
    }

    private fun initView(view: View) {
        mViewPager = view.findViewById(R.id.viewPager2_media)
        mName_Age = view.findViewById(R.id.animal_info_name_with_age)
        mDescription = view.findViewById(R.id.animal_info_description)
        mFavoriteButton = view.findViewById(R.id.button_favorite)
        mMediaViewPager = MediaViewPager2()
    }

    private fun initViewModal() {
        mViewModel.resultLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it.mediaList != null) {
                    mMediaViewPager.setImageList(it.mediaList)
                    mViewPager.adapter = mMediaViewPager
                }
                mDescription.text = it.description
                mName_Age.text =
                    """${mViewModel.getNameSelectedItem()} ${mViewModel.getNameSelectedItem()}"""
            }
        })

        mViewModel.loadAnimalDetailedInfo()
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        return true
    }

    override fun getViewModelClass(): Class<AnimalDetailedInfoViewModel> {
        return AnimalDetailedInfoViewModel::class.java
    }

    override fun injectDependencies(appComponent: AppComponent) {
        appComponent.inject(this)
    }
}
