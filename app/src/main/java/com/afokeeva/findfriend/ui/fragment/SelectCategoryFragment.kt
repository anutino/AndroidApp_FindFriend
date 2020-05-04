package com.afokeeva.findfriend.ui.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.afokeeva.findfriend.R
import com.afokeeva.findfriend.viewmodel.SelectCategoryViewModel
import kotlinx.android.synthetic.main.fragment_select_category.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SelectCategoryFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    //private var listener: OnFragmentInteractionListener? = null
    private lateinit var mViewModel: SelectCategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this@SelectCategoryFragment)
            .get(SelectCategoryViewModel::class.java)
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
    ): View? {
        //var listImg = SelectCategoryViewModel().categoryListLiveData
        // print("result_ $listImg ")
        return inflater.inflate(R.layout.fragment_select_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        select_category_dog.setOnClickListener {
            Navigation.findNavController(it).navigate(com.afokeeva.findfriend.R.id.next_action)
        }
        select_category_cat.setOnClickListener {
            Navigation.findNavController(it).navigate(com.afokeeva.findfriend.R.id.next_action)
        }
        select_category_dog_and_cat.setOnClickListener {
            Navigation.findNavController(it).navigate(com.afokeeva.findfriend.R.id.next_action)
        }
    }

    fun getPhoto() {// TODO RecycleView and Glide

    }

//    fun onButtonPressed(uri: Uri) {
//        listener?.onFragmentInteraction(uri)
//    }
//
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

//    override fun onClick(v: View) {
//        when (v.id) {
////            R.id.imageButtonDog -> {
////                chosenAnimal(Animals.DOG); startActivity(Intent(this@MainActivity, SearchActivity::class.java))}
////            R.id.imageButtonCat -> {
////                chosenAnimal(Animals.CAT); startActivity(Intent(this@MainActivity, SearchActivity::class.java))}
////            R.id.imageButtonDogAndCat -> {
////                chosenAnimal(Animals.DOGS_AND_CATS); startActivity(Intent(this@MainActivity, SearchActivity::class.java))}
//        }
//    }


    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

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
    }
}
