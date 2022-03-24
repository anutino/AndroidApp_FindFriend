package com.findfriend.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.findfriend.app.R
import com.findfriend.data.Constants
import com.findfriend.ui.animalshortinfo.AnimalShortInfoViewModel
import it.sephiroth.android.library.rangeseekbar.RangeSeekBar
import it.sephiroth.android.library.rangeseekbar.RangeSeekBar.OnRangeSeekBarChangeListener

class FilterFragment : DialogFragment(), View.OnClickListener {

    private var mIsSelectedDog = false
    private var mIsSelectedCat = false

    private var mAgeFrom: Int = 0
    private var mAgeTo: Int = 20
    private lateinit var mButtonDog: Button
    private lateinit var mButtonCat: Button
    private lateinit var mButtonApply: Button
    private lateinit var mButtonCancel: Button
    private lateinit var mAgeMaxTextView: TextView
    private lateinit var mAgeMinTextView: TextView
    private lateinit var mViewModel: AnimalShortInfoViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_filter_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mButtonDog = view.findViewById(R.id.dog)
        mButtonCat = view.findViewById(R.id.cat)
        mButtonApply = view.findViewById(R.id.fragment_filter_apply)
        mButtonCancel = view.findViewById(R.id.fragment_filter_cancel)
        mButtonCancel.setOnClickListener { dismiss() }
        var seekBarAge = view.findViewById<RangeSeekBar>(R.id.seek_bar)
        seekBarAge.thumbStart.setTint(resources.getColor(R.color.purple_dark))
        seekBarAge.thumbEnd.setTint(resources.getColor(R.color.purple_dark))
        mAgeMaxTextView = view.findViewById(R.id.ageMax)
        mAgeMinTextView = view.findViewById(R.id.ageMin)

        seekBarAge.setOnRangeSeekBarChangeListener(object : OnRangeSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: RangeSeekBar) {
            }

            override fun onStartTrackingTouch(seekBar: RangeSeekBar) {
            }

            override fun onProgressChanged(
                seekBar: RangeSeekBar,
                progressStart: Int,
                progressEnd: Int,
                fromUser: Boolean
            ) {
                mAgeFrom = progressStart
                mAgeTo = progressEnd
                mAgeMaxTextView.text = progressStart.toString()
                mAgeMinTextView.text = progressEnd.toString()
            }
        })

        mButtonDog.setOnClickListener(this)
        mButtonCat.setOnClickListener(this)
        mButtonApply.setOnClickListener {
            when {
                mIsSelectedDog -> {
                    mViewModel.setType(Constants.AnimalType.DOG.ordinal)
                    mViewModel.loadAnimalListFilteredByAge(
                        mAgeMaxTextView.text.toString(), mAgeMinTextView.text.toString())
                }
                mIsSelectedCat -> {
                    mViewModel.setType(Constants.AnimalType.CAT.ordinal)
                    mViewModel.loadAnimalListFilteredByAge(
                        mAgeMaxTextView.text.toString(), mAgeMinTextView.text.toString())
                }
                else -> {
                    mViewModel.setType(Constants.AnimalType.ALL.ordinal)
                    mViewModel.loadAnimalListFilteredByAge(
                        mAgeMaxTextView.text.toString(), mAgeMinTextView.text.toString())
                }
            }
            dialog?.dismiss()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.dog -> {
                if (mIsSelectedDog) {
                    mIsSelectedDog = false
                    mButtonDog.isSelected = false
                } else if (!mIsSelectedDog) {
                    v.findViewById<Button>(R.id.dog)
                    mButtonDog.isSelected = true
                    mIsSelectedDog = true
                }
            }
            R.id.cat -> {
                if (mIsSelectedCat) {
                    mButtonCat.isSelected = false
                    mIsSelectedCat = false
                } else if (!mIsSelectedCat) {
                    mButtonCat.isSelected = true
                    mIsSelectedCat = true
                }
            }
        }
    }

    fun setViewModel(viewModel: AnimalShortInfoViewModel) {
        mViewModel = viewModel
    }

}


