package com.findfriend.ui.animalshortinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.findfriend.R
import it.sephiroth.android.library.rangeseekbar.RangeSeekBar
import it.sephiroth.android.library.rangeseekbar.RangeSeekBar.OnRangeSeekBarChangeListener

//
//enum class ChosenAnimal {
//    DOG, CAT, ALL
//}

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FilterFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FilterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FilterFragment : DialogFragment(), View.OnClickListener {
    private val DOG = 1
    private val CAT = 2
    private val ALL = 3
    private val DEFAULT_VALUE_AGE = 0
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private var mIsSelectedDog = false
    private var mIsSelectedCat = false
    private var mSelectedAnimal = ALL

    private var mAgeFrom: Int = DEFAULT_VALUE_AGE
    private var mAgeTo: Int = DEFAULT_VALUE_AGE
    private lateinit var mButtonDog: Button
    private lateinit var mButtonCat: Button
    private lateinit var mButtonApply: Button
    private lateinit var mButtonCancel: Button
    private lateinit var mAgeMaxTextView: TextView
    private lateinit var mAgeMinTextView: TextView
    private lateinit var mViewModel: AnimalShortInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_filter_dialog, container, false)
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
        mViewModel = ViewModelProviders.of(activity!!).get(AnimalShortInfoViewModel::class.java)

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
            if (mIsSelectedDog) {
                mSelectedAnimal = DOG
            } else if (mIsSelectedCat) {
                mSelectedAnimal = CAT
            } else if (mIsSelectedDog && mIsSelectedCat) {
                mSelectedAnimal =
                    ALL
            }

            if (mSelectedAnimal == ALL) {
                mViewModel.loadAnimalListFilteredByAge(
                    mAgeMaxTextView.text.toString(), mAgeMinTextView.text.toString())
            } else {
                mViewModel.loadAnimalListFilteredByAgeAndType(
                    mAgeMaxTextView.text.toString(),
                    mAgeMinTextView.text.toString(),
                    mSelectedAnimal)
            }
            dialog?.dismiss()
        }
        return view
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.dog -> {
                if (mIsSelectedDog) {
                    //   v.findViewById<Button>(R.id.choose_dog)
                    //      .setBackgroundColor(resources.getColor(R.color.blue))
                    //  mButtonDog.background.colorFilter = ColorFilter(resources.getColor(R.color.red))
                    mIsSelectedDog = false
                    mButtonDog.isSelected = false
                } else if (!mIsSelectedDog) {
                    v.findViewById<Button>(R.id.dog)
                    mButtonDog.isSelected = true
                    //   mButtonDog.setBackgroundColor(resources.getColor(R.color.black))
                    mIsSelectedDog = true
                }
            }
            R.id.cat -> {
                if (mIsSelectedCat) {
                    //   v.findViewById<Button>(R.id.cat).setBackgroundColor(0)
                    mButtonCat.isSelected = false
                    mIsSelectedCat = false
                } else if (!mIsSelectedCat) {
                    mButtonCat.isSelected = true
                    mIsSelectedCat = true
                }
            }
        }
    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
//        }
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//        listener = null
//    }
//
//    fun setOnHeadlineSelectedListener(listener: OnFragmentInteractionListener) {
//        this.listener = listener
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(age: Double, animal: String)
    }

    // companion object {
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FilterFragment.
     */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            FilterFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
    //  }

}


