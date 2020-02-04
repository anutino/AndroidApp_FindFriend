package com.afokeeva.findfriend.ui.fragment
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import androidx.fragment.app.DialogFragment
import com.afokeeva.findfriend.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FilterDialogFragment : DialogFragment(), SeekBar.OnSeekBarChangeListener, View.OnClickListener {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: SearchFragment.OnFragmentInteractionListener? = null
    private var isPressedDog = false
    private var isPressedCat = false
    private var age = 5.0
    private var TAG = "FilterDialogFragment"



//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//        seekBarAge.setOnSeekBarChangeListener(this)
//        btnDog.setOnClickListener(this)
//        btnCat.setOnClickListener(this)
//        btnApply.setOnClickListener {
////            if(isPressedDog)  chosenAnimal = ChosenAnimal.DOG
////            else if(isPressedCat) chosenAnimal =
////                ChosenAnimal.CAT
////            else if (isPressedDog && isPressedCat) chosenAnimal =
////                ChosenAnimal.ALL
////            listener?.onFragmentInteraction(age,chosenAnimal.name)
//    }}


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


    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        age = p1.toDouble()
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
    }
    /*
      fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filter_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view : View) {
        btnDog = view.findViewById<Button>(R.id.choose_dog_id)
        btnCat = view.findViewById<Button>(R.id.choose_cat_id)
        btnApply = view.findViewById<Button>(R.id.fragment_filter_apply)
        seekBarAge = view.findViewById<SeekBar>(R.id.seekBarAge)

    }


    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
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
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FilterDialogFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FilterDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }*/
}
