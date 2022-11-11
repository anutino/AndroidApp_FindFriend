package com.findfriend.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.findfriend.app.R
import com.findfriend.data.Constants
import com.findfriend.ui.animalshortinfo.AnimalShortInfoViewModel
import it.sephiroth.android.library.rangeseekbar.RangeSeekBar
import it.sephiroth.android.library.rangeseekbar.RangeSeekBar.OnRangeSeekBarChangeListener

class FilterFragment : DialogFragment(), View.OnClickListener {

    private var isSelectedDog = false
    private var isSelectedCat = false

    private var ageFrom: Int = 0
    private var ageTo: Int = 20
    private lateinit var buttonDog: ImageButton
    private lateinit var buttonCat: ImageButton
    private lateinit var buttonApply: Button
    private lateinit var buttonCancel: Button
    private lateinit var ageMaxTextView: TextView
    private lateinit var ageMinTextView: TextView
    private lateinit var viewModel: AnimalShortInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_filter_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        buttonDog = view.findViewById(R.id.dog)
        buttonCat = view.findViewById(R.id.cat)
        buttonApply = view.findViewById(R.id.fragment_filter_apply)
        buttonCancel = view.findViewById(R.id.fragment_filter_cancel)
        buttonCancel.setOnClickListener { dismiss() }
        val seekBarAge = view.findViewById<RangeSeekBar>(R.id.seek_bar)
        seekBarAge.thumbStart.setTint(resources.getColor(R.color.purple_dark))
        seekBarAge.thumbEnd.setTint(resources.getColor(R.color.purple_dark))
        ageMaxTextView = view.findViewById(R.id.ageMin)
        ageMinTextView = view.findViewById(R.id.ageMax)

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
                ageFrom = progressStart
                ageTo = progressEnd
                ageMaxTextView.text = progressStart.toString()
                ageMinTextView.text = progressEnd.toString()
            }
        })

        buttonDog.setOnClickListener(this)
        buttonCat.setOnClickListener(this)
        buttonApply.setOnClickListener {
            when {
                isSelectedDog -> {
                    viewModel.setType(Constants.AnimalType.DOG.ordinal)
                    viewModel.getAnimalShortInfoListFilteredByAge(
                        ageMaxTextView.minEms, ageMinTextView.maxEms)
                }
                isSelectedCat -> {
                    viewModel.setType(Constants.AnimalType.CAT.ordinal)
                    viewModel.getAnimalShortInfoListFilteredByAge(
                        ageMaxTextView.minEms, ageMinTextView.maxEms)
                }
                else -> {
                    viewModel.setType(Constants.AnimalType.ALL.ordinal)
                    viewModel.getAnimalShortInfoListFilteredByAge(
                        ageMaxTextView.minEms, ageMinTextView.maxEms)
                }
            }
            dialog?.dismiss()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.dog -> {
                if (isSelectedDog) {
                    isSelectedDog = false
                    buttonDog.isSelected = false
                } else if (!isSelectedDog) {
                    v.findViewById<Button>(R.id.dog)
                    buttonDog.isSelected = true
                    isSelectedDog = true
                }
            }
            R.id.cat -> {
                if (isSelectedCat) {
                    buttonCat.isSelected = false
                    isSelectedCat = false
                } else if (!isSelectedCat) {
                    buttonCat.isSelected = true
                    isSelectedCat = true
                }
            }
        }
    }

    fun setViewModel(viewModel: AnimalShortInfoViewModel) {
        this.viewModel = viewModel
    }

}




