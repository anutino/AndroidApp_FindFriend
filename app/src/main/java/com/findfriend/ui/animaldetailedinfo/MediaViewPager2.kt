package com.findfriend.ui.animaldetailedinfo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.findfriend.app.R

class MediaViewPager2 : RecyclerView.Adapter<MediaViewPager2.PagerVH>() {

    private lateinit var media: List<Int>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH = PagerVH(
        LayoutInflater.from(parent.context).inflate(R.layout.item_media, parent, false))

    override fun getItemCount(): Int = media.size

    override fun onBindViewHolder(holder: PagerVH, position: Int) {
        val iv = holder.itemView.findViewById<ImageView>(R.id.item_media_content)
        Glide.with(holder.itemView.context)
            .load(media[position])
            .into(iv)
    }

    fun setImageList(media: List<Int>) {
        this.media = media
        notifyDataSetChanged()
    }

    class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView)
}



