package com.findfriend.ui.animaldetailedinfo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.findfriend.R
import com.bumptech.glide.Glide

class MediaViewPager2() : RecyclerView.Adapter<MediaViewPager2.PagerVH>() {
    var mResourcesPath = "http://10.0.3.2:8080/resourses/findfriend/"
    private lateinit var mMedia: List<String>

    fun setImageList(media: List<String>) {
        mMedia = media
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH = PagerVH(
        LayoutInflater.from(parent.context).inflate(R.layout.item_media, parent, false))

    override fun getItemCount(): Int = mMedia.size

    override fun onBindViewHolder(holder: PagerVH, position: Int) {
        val iv = holder.itemView.findViewById<ImageView>(R.id.item_media_content)
        Glide.with(holder.itemView).load(mResourcesPath + mMedia[position]).into(iv)
    }

    class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView)
}



