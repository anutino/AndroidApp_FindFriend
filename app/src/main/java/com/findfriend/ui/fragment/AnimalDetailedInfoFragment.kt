package com.findfriend.ui.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.widget.ViewPager2
import com.findfriend.R
import com.findfriend.data.ShortAnimalInfo
import com.findfriend.viewmodel.AnimalDetailedInfoViewModel
import com.findfriend.viewpager.MediaViewPager2
import com.google.android.material.navigation.NavigationView

class AnimalDetailedInfoFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {

    private val TAG: String = javaClass.simpleName
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var mViewModel: AnimalDetailedInfoViewModel
    private lateinit var mViewPager: ViewPager2
    private lateinit var mName_Age: TextView
    private lateinit var mDescription: TextView
    private lateinit var mFavoriteButton: ImageButton
    private lateinit var mMediaViewPager: MediaViewPager2
    private lateinit var mItem : ShortAnimalInfo

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_animal_detailed_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initViewModal()
        initObserver()
    }

    private fun initView(view: View) {
        mItem = AnimalShortInfoListFragment.mViewModel.getItemAnimalInfo()
        mViewPager = view.findViewById(R.id.viewPager2_media)
        mName_Age = view.findViewById(R.id.animal_info_name_with_age)
        mName_Age.text = """${mItem.name} ${mItem.age}"""
        mDescription = view.findViewById(R.id.animal_info_description)
        mFavoriteButton = view.findViewById(R.id.button_favorite)
        mMediaViewPager = MediaViewPager2()
    }

    private fun initViewModal() {
        mViewModel = ViewModelProviders.of(this@AnimalDetailedInfoFragment)
            .get(AnimalDetailedInfoViewModel::class.java)
        mViewModel.loadAnimalDetailedInfo(mItem.id)
    }

    private fun initObserver() {
        mViewModel.getAnimalDetailedInfo().observe(this, Observer {
            it?.let {
                 if(it.mediaList != null) {
                    mMediaViewPager.setImageList(it.mediaList)
                    mViewPager.adapter = mMediaViewPager
                }
                 mDescription.text = it.description
               // mFavoriteButton.isEnabled = it.favorite
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw java.lang.RuntimeException(
                "$context must implement OnFragmentInteractionListener"
            )
        }
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
     }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        return true
    }
}
