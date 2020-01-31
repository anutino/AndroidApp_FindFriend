package com.afokeeva.findfriend.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import com.afokeeva.findfriend.R

enum class ChosenAnimal{
    DOG, CAT, ALL
}
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
class FilterFragment : Fragment(), SeekBar.OnSeekBarChangeListener, View.OnClickListener {
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private var age = 5.0
    private var TAG = "FilterFragment"
    private var isPressedDog = false
    private var isPressedCat = false
    private var chosenAnimal = ChosenAnimal.ALL
    lateinit var btnDog : Button
    lateinit var btnCat: Button
    lateinit var btnApply: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_filter, container, false)
        btnDog = view.findViewById<Button>(R.id.choose_dog_id)
        btnCat = view.findViewById<Button>(R.id.choose_cat_id)
        btnApply = view.findViewById<Button>(R.id.fragment_filter_apply)
        var seekBarAge = view.findViewById<SeekBar>(R.id.seekBarAge)
        seekBarAge.setOnSeekBarChangeListener(this)
        btnDog.setOnClickListener(this)
        btnCat.setOnClickListener(this)
        btnApply.setOnClickListener {
            if(isPressedDog)  chosenAnimal = ChosenAnimal.DOG
            else if(isPressedCat) chosenAnimal =
                ChosenAnimal.CAT
            else if (isPressedDog && isPressedCat) chosenAnimal =
                ChosenAnimal.ALL
            listener?.onFragmentInteraction(age,chosenAnimal.name)
        }
        return view
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.choose_dog_id ->{
                if(isPressedDog) {
                    v.findViewById<Button>(R.id.choose_dog_id)
                        .setBackgroundColor(getResources().getColor(R.color.blue))
                    isPressedDog = false
                }else if(!isPressedDog){
                    v.findViewById<Button>(R.id.choose_dog_id)
                        .setBackgroundColor(getResources().getColor(R.color.blue_light))
                    isPressedDog=true
                }
            }
            R.id.choose_cat_id ->{
                if(isPressedCat) {
                    v.findViewById<Button>(R.id.choose_cat_id)
                        .setBackgroundColor(getResources().getColor(R.color.red))
                    isPressedCat = false
                }else if(!isPressedCat){
                    v.findViewById<Button>(R.id.choose_cat_id)
                        .setBackgroundColor(getResources().getColor(R.color.red_light))
                    isPressedCat=true
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
    fun setOnHeadlineSelectedListener(listener: OnFragmentInteractionListener) {
        this.listener = listener
    }

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
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        age = progress.toDouble()
    }
    override fun onStartTrackingTouch(seekBar: SeekBar?) {
    }
    override fun onStopTrackingTouch(seekBar: SeekBar?) {
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FilterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FilterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}


