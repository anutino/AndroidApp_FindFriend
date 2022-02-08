package com.findfriend.ui.unknown

import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.ViewGroup
import androidx.constraintlayout.widget.Constraints.TAG
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.findfriend.R
import com.findfriend.data.ShortAnimalInfo
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule


@GlideModule
class ItemAnimalListViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(
        inflater.inflate(
            R.layout.item_animal_short_info, parent, false
        )
    ) {

    private val ID = "id"
    private val NAME = "name"
    private val AGE = "age"
    private val TYPE = "type"
    var mImage: ImageView? = null
    var favorite: ImageView? = null
    var name: TextView? = null
    var mResourcesPath = "http://10.0.3.2:8080/resourses/findfriend/"

    init {
        mImage = itemView.findViewById(R.id.animal_image)
        favorite = itemView.findViewById(R.id.favorite)
        name = itemView.findViewById(R.id.animal_name_with_age)
    }

    fun bind(animal: ShortAnimalInfo?) {
        if (animal != null) {
              Log.d(TAG, "animal.img_url  "+animal.mainPicture)
            Glide
                .with(itemView)
                .load(mResourcesPath + animal.mainPicture)
                .into(mImage)

            name?.text = animal.name

            if (animal.favorite) {
                favorite?.setColorFilter(R.color.red)
            } else {
                favorite?.setColorFilter(R.color.blue)
            }

        } else {
            name?.text = "Loading..."
        }

        //TODO addFavorite
        //favorite.setOnClickListener({})

        itemView.setOnClickListener {
            Log.d(TAG, "setOnClickListener ")

            var bundle = bundleOf(ID to animal?.id,
           NAME to animal?.name,
            AGE to animal?.age,
            TYPE to animal?.type)
             Navigation.findNavController(it)
                .navigate(R.id.destination_animal_detailed_info, bundle)
        }

        favorite?.setOnClickListener {
            Log.d(TAG, "favorite setOnClickListener ")
        }

    }

}