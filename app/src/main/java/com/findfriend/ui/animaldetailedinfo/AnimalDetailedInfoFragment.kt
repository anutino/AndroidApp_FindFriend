package com.findfriend.ui.animaldetailedinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.findfriend.app.R
import com.findfriend.domain.model.ShortAnimalInfo
import com.findfriend.ui.di.AppComponent
import com.findfriend.BaseFragment
import com.google.android.material.navigation.NavigationView

class AnimalDetailedInfoFragment : BaseFragment<AnimalDetailedInfoViewModel>(),
    NavigationView.OnNavigationItemSelectedListener {

    private val args: AnimalDetailedInfoFragmentArgs by navArgs()
    private val animalId by lazy { args.animalId }
    private lateinit var viewPager: ViewPager2
    private lateinit var nameAge: TextView
    private lateinit var description: TextView
    private lateinit var favoriteButton: ImageButton
    private lateinit var mediaViewPager: MediaViewPager2
    private lateinit var item: ShortAnimalInfo

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
        viewPager = view.findViewById(R.id.viewPager2_media)
        viewPager.apply {
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 2
        }
        viewPager.setPadding(48, 10, 48, 10);
        var c = CompositePageTransformer()
        c.addTransformer(MarginPageTransformer(24))

        viewPager.setPageTransformer(object : ViewPager2.PageTransformer {
            override fun transformPage(page: View, position: Float) {
                page.translationX = -5 * position
                page.scaleY = 1 - (0.15f * kotlin.math.abs(position))
            }
        })
        viewPager.setPageTransformer(c)
        nameAge = view.findViewById(R.id.animal_info_name_with_age)
        description = view.findViewById(R.id.animal_info_description)
        favoriteButton = view.findViewById(R.id.button_favorite)
        mediaViewPager = MediaViewPager2()
    }

    private fun initViewModal() {
        viewModel.resultLiveData.observe(viewLifecycleOwner) {
            it?.let {
                mediaViewPager.setImageList(it.mediaList)
                viewPager.adapter = mediaViewPager
                description.text = it.description
                nameAge.text = "${it.name}, ${it.age}"
                favoriteButton.isSelected = it.favorite
            }
        }
         viewModel.getDetailedInfo(animalId)
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
