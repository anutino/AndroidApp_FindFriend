package com.afokeeva.findfriend.ui.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.afokeeva.findfriend.R
import com.afokeeva.findfriend.viewpager.MediaViewPager2

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AnimalInfoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

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
        val imagesList = mutableListOf<String>(
            "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg",
            "https://pp.userapi.com/c824502/v824502702/18b194/TAYpSgN3JeU.jpg",
            "https://wallscloud.net/uploads/cache/1266658504/blue-space-planet-krB5-1024x576-MM-90.jpg",
            "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg"
        )
        var view = inflater.inflate(R.layout.fragment_animal_info, container, false)
        val pager = view.findViewById<ViewPager2>(R.id.viewPager2_media)
        pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        pager.adapter = MediaViewPager2(imagesList)
        var names = view.findViewById<TextView>(R.id.animal_info_name_age_id)
        names.text = "Doggi, 1.2"
        var description = view.findViewById<TextView>(R.id.animal_info_description)
        description.text = "description"
        // holder.itemView.findViewById<TextView>(R.id.animalInfo_description).text = "description"
        return view
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
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AnimalInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
