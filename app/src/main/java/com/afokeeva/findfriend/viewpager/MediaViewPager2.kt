package com.afokeeva.findfriend.viewpager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.afokeeva.findfriend.R
import com.bumptech.glide.Glide

class MediaViewPager2(imagesList: List<String>) : RecyclerView.Adapter<MediaViewPager2.PagerVH>() {

    var imagesList = imagesList
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH =
            PagerVH(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.animal_image_page,
                    parent,
                    false
                )
            )

        override fun getItemCount(): Int = imagesList.size

        override fun onBindViewHolder(holder: PagerVH, position: Int) {
            var iv = holder.itemView.findViewById<ImageView>(R.id.itemImageViewAnimal)
            Glide.with(holder.itemView).load(imagesList[position]).into(iv)
        }

        class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView)
    }
/*class AnimalImageSlidePageFragment : Fragment() {
    private var mUserView: TextView? = null

    //companion object {
    fun getInstance(name: String): Fragment {
        val fragment = AnimalImageSlidePageFragment()
        val arg = Bundle()
        arg.putString("name", name)
        fragment.arguments = arg
        return fragment
    }
    // }
    val ARGUMENT_PAGE_NUMBER = "arg_page_number"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.animal_image_page, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mUserView = view.findViewById(R.id.imageViewAnimal)
        val name = arguments?.getString("name")
        mUserView?.text = "Hello $name"
    }

    fun newInstance(page: Int): AnimalImageSlidePageFragment {
        val pageFragment = AnimalImageSlidePageFragment()
        val arguments = Bundle()
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page)
        pageFragment.setArguments(arguments)
        return pageFragment
    }
}*/



