package com.afokeeva.findfriend.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.afokeeva.findfriend.viewmodel.SelectCategoryViewModel
import kotlinx.android.synthetic.main.fragment_select_category.*
import android.view.View as View1


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SelectCategoryFragment : Fragment(), android.view.View.OnHoverListener {
    private var param1: String? = null
    private var param2: String? = null
   // private var listener: OnFragmentInteractionListener? = null
    private lateinit var mViewModel : SelectCategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this@SelectCategoryFragment).get(SelectCategoryViewModel::class.java)
        mViewModel.categoryListLiveData.observe(this, Observer {
            it // it in Recycle View
        })
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View1? {
      //  var listImg = SelectCategoryViewModel().categoryListLiveData //TODO RECYCLE VIEW,  UNCOMMENT
        var view = inflater.inflate(com.afokeeva.findfriend.R.layout.fragment_select_category, container, false)
        return  view
    }

    override fun onViewCreated(view: View1, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageButtonDog.setOnClickListener {
            Navigation.findNavController(it).navigate(com.afokeeva.findfriend.R.id.next_action)
        }
        imageButtonCat.setOnClickListener {
            Navigation.findNavController(it).navigate(com.afokeeva.findfriend.R.id.next_action)
        }
        imageButtonDogAndCat.setOnClickListener {
            Navigation.findNavController(it).navigate(com.afokeeva.findfriend.R.id.next_action)
        }

    }

    override fun onResume() {
        super.onResume()
        print("_____________")
        add_button.isEnabled = false

        add_button.setOnHoverListener(View1.OnHoverListener { view, motionEvent ->
            //print("__ ACTION ?? " + motionEvent.action.toString())
            when(motionEvent.action){
                MotionEvent.ACTION_MOVE -> {
                Log.d("af", "__ ACTION_MOVE")
            }
                MotionEvent.ACTION_DOWN -> {
                    Log.d("af", "__ ACTION_DOWN")
                }
                MotionEvent.ACTION_CANCEL -> {
                    Log.d("af", "__ ACTION_CANCEL")
                }
                MotionEvent.ACTION_HOVER_ENTER -> {
                    Log.d("af", "__ ACTION_HOVER_ENTER")
                }
                MotionEvent.ACTION_HOVER_EXIT -> {
                    Log.d("af", "__ ACTION_HOVER_EXIT")
                }
                MotionEvent.ACTION_HOVER_MOVE -> {
                    Log.d("af", "__ ACTION_HOVER_MOVE")
                }
                else ->  print("__ ACTION_MOVE NOTHING")
            }
            return@OnHoverListener true
         })

        }

    override fun onHover(p0: android.view.View?, p1: MotionEvent?): Boolean {
        return when (p0 == delete_button) {
            when (p1?.action) {
                MotionEvent.ACTION_MOVE -> {
                    Log.d("af", "__ ACTION_MOVE")
                }
                MotionEvent.ACTION_DOWN -> {
                    Log.d("af", "__ ACTION_DOWN")
                }
                MotionEvent.ACTION_CANCEL -> {
                    Log.d("af", "__ ACTION_CANCEL")
                }
                MotionEvent.ACTION_HOVER_ENTER -> {
                    Log.d("af", "__ ACTION_HOVER_ENTER")
                }
                MotionEvent.ACTION_HOVER_EXIT -> {
                    Log.d("af", "__ ACTION_HOVER_EXIT")
                }
                MotionEvent.ACTION_HOVER_MOVE -> {
                    Log.d("af", "__ ACTION_HOVER_MOVE")
                }
                else -> print("__ ACTION_MOVE NOTHING")
            } ->
                return true
            else -> true
        }
    }

    fun getPhoto(){// TODO RecycleView and Glide
    }

//        fun onButtonPressed(uri: Uri) {
//        listener?.onFragmentInteraction(uri)
//    }
/*
    override fun onAttach(context: Context) {
        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
//        }
    }

    override fun onDetach() {
        super.onDetach()
       // listener = null
    }



//    interface OnFragmentInteractionListener {
//        fun onFragmentInteraction(uri: Uri)
//    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SelectCategoryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SelectCategoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }*/
}
