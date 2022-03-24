package com.findfriend.ui.animalshortinfo

import android.content.res.Resources
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.compose.ViewGroup
import androidx.constraintlayout.widget.Constraints
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.findfriend.app.R
import com.findfriend.domain.model.ShortAnimalInfo
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.findfriend.data.networkservice.AppConstants.RESOURCE_PATH
import java.io.File
import java.net.URI

class AnimalShortInfoAdapter(listener: OnItemClickListener) : RecyclerView.Adapter<ItemAnimalViewHolder>() {

    private var mItems: List<ShortAnimalInfo> = mutableListOf()
    private var mOnItemClickListener = listener

    interface OnItemClickListener {
        fun onItemClick(view: View, itemId: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAnimalViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemAnimalViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ItemAnimalViewHolder, position: Int) {
        val selectedAnimal: ShortAnimalInfo = mItems[position]
        holder.bind(selectedAnimal, mOnItemClickListener)
    }

    fun setItems(info: List<ShortAnimalInfo>) {
        mItems = info
    }

    override fun getItemCount() = mItems.size

}

@GlideModule
class ItemAnimalViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(R.layout.item_animal_short_info, parent, false)) {

    private var id: Int = 0
    var mImage: ImageView? = null
    var favorite: ImageView? = null
    var name: TextView? = null

    init {
        mImage = itemView.findViewById(R.id.animal_image)
        favorite = itemView.findViewById(R.id.favorite)
        name = itemView.findViewById(R.id.animal_name_with_age)

    }

    fun bind(animal: ShortAnimalInfo?, itemListener: AnimalShortInfoAdapter.OnItemClickListener) {
        if (animal != null) {
            Log.d(Constraints.TAG, "animal.img_url  " + animal.mainPicture)
            //val uri = Uri.parse(ContextCompat.getDrawable(ContextCompat.a, R.drawable.dog1).toString())
            var resId =  R.drawable.dog1
            when (animal.mainPicture){
                "dog1.jpg" -> { resId = R.drawable.dog1}
                "dog2.jpg" -> { resId = R.drawable.dog2}
                "dog3.jpg" -> { resId = R.drawable.dog3}
                "dog4.jpg" -> { resId = R.drawable.dog4}
                "dog5.jpg" -> { resId = R.drawable.dog5}
                "dog6.jpg" -> { resId = R.drawable.dog6}
                "dog7.jpg" -> { resId = R.drawable.dog7}
                "dog8.jpg" -> { resId = R.drawable.dog8}
                "cat1.jpg" -> { resId = R.drawable.cat1}
                "cat2.jpg" -> { resId = R.drawable.cat2}
                "cat3.jpg" -> { resId = R.drawable.cat3}
            }
             Glide
                .with(itemView)
                .load(resId)
              //  .load(uri)
                //.load(RESOURCE_PATH + animal.mainPicture)
                .into(mImage)

            name?.text = animal.name

            favorite?.isSelected = animal.favorite
            id = animal.id
            itemView.setOnClickListener {
                itemListener.onItemClick(it, id)
                Log.d(Constraints.TAG, "setOnClickListener animal?.id " + id)
            }
        } else {
            name?.text = "Loading..."
        }

        favorite?.setOnClickListener {
            Log.d(Constraints.TAG, "favorite setOnClickListener ")
        }
    }

}
