package com.findfriend.ui.fragment

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.findfriend.R
import kotlinx.android.synthetic.main.fragment_animal_type_selector.*


class AnimalTypeSelectorFragment : Fragment() {

    private val TAG: String = javaClass.simpleName
    private val ARG_PARAM1 = "param1"
    private val ARG_PARAM2 = "param2"
    private val ANIMAL_TYPE = "animalType"

    private val DOG_TYPE = 1
    private val CAT_TYPE = 2

//    private var param1: String? = null
//    private var param2: String? = null
//
//    companion object {
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            AnimalTypeSelectorFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
////         mViewModel = ViewModelProviders.of(this@AnimalTypeSelectorFragment)
////            .get(AnimalTypeViewModel::class.java)
////        mViewModel.categoryListLiveData.observe(this, Observer {
////            it // it in Recycle View
//        //     })
//
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_animal_type_selector, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        select_dog_type.setOnClickListener {
            Log.d(TAG,"setOnClickListener ")
            val bundle = bundleOf(ANIMAL_TYPE to DOG_TYPE)
            if (Navigation.findNavController(it).currentDestination.id == R.id.destination_animal_type_selector){
                Navigation.findNavController(it)
                    .navigate(R.id.destination_animal_short_info_list, bundle)
            }

        }
        select_cat_type.setOnClickListener {
            //if (it.findNavController().currentDestination?.id == R.id.next_to_fragment_animal_short_info_list) {
            val bundle = bundleOf(ANIMAL_TYPE to CAT_TYPE)
            Log.d(TAG, " it " + it)
            it.findNavController()
                .navigate(R.id.destination_animal_short_info_list, bundle)
            //  }

//            val bundle = bundleOf(ANIMAL_TYPE to CAT_TYPE)
//            Navigation.findNavController(it)
//                .navigate(R.id.next_to_fragment_animal_short_info_list, bundle)
        }
        select_dog_and_cat_type.setOnClickListener {
            // Navigation.findNavController(it).navigate(R.id.next_to_fragment_animal_short_info_list)
            it.findNavController()
                .navigate(R.id.destination_animal_short_info_list)
        }
    }

//    private fun getNavController(): NavController {
//        val fragment: Fragment = FragmentManager.findFragmentById(R.id.nav_host_fragment)
//        check(fragment is NavHostFragment) {
//            ("Activity " + this
//                    + " does not have a NavHostFragment")
//        }
//        return fragment.navController
//    }

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
//        listener = null
//        super.onDetach()
//     }

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


}
