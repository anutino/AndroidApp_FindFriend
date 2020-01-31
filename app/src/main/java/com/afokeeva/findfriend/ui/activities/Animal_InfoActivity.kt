package com.afokeeva.findfriend.ui.activities
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.afokeeva.findfriend.R
import com.bumptech.glide.Glide

class Animal_InfoActivity : FragmentActivity() {
    val TAG = "Animal_InfoActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_info)
        val imagesList = mutableListOf<String>(
            "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg",
            "https://pp.userapi.com/c824502/v824502702/18b194/TAYpSgN3JeU.jpg",
            "https://wallscloud.net/uploads/cache/1266658504/blue-space-planet-krB5-1024x576-MM-90.jpg",
            "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg"
        )
        val pager = findViewById<ViewPager2>(R.id.viewPager2Images)
        pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        pager.adapter = ViewPagerAdapter(imagesList)
        var names = findViewById<TextView>(R.id.animalInfo_name_age_id)
        names.text = "Doggi, 1.2"
        var description = findViewById<TextView>(R.id.animalInfo_description)
        description.text = "description"
       // holder.itemView.findViewById<TextView>(R.id.animalInfo_description).text = "description"
    }
}

class ViewPagerAdapter(imagesList: List<String>) : RecyclerView.Adapter<ViewPagerAdapter.PagerVH>() {

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