package com.findfriend.ui.unknown

import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.ViewGroup
import androidx.constraintlayout.widget.Constraints.TAG
import androidx.recyclerview.widget.RecyclerView
import com.findfriend.app.R
import com.findfriend.domain.model.Animal
import com.bumptech.glide.annotation.GlideModule


@GlideModule
class ItemFavoriteAnimalViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(
        inflater.inflate(
            R.layout.item_favorite_animal_info, parent, false
        )
    ) {
    var image: ImageView? = null
    var favorite: ImageView? = null
    var name: TextView? = null
    //   val context = context

    init {
        image = itemView.findViewById(R.id.animal_image)
        favorite = itemView.findViewById(R.id.favorite)
        name = itemView.findViewById(R.id.animal_name_with_age)
    }

    fun bind(animal: Animal?) {
        image?.setImageResource(R.drawable.dog);
        //Log.d(TAG, "bind ")//+ listAnimal.size)
        if (animal != null) {
//            Glide
//                .with(itemView)
//                .load(Animal.img_url)
//                .into(image)

            name?.text = animal.name
            favorite?.isPressed = animal.favorite

        } else {
            name?.text = "Loading..."
        }

        //TODO addFavorite
        //favorite.setOnClickListener({})

        itemView.setOnClickListener {
            Log.d(TAG, "setOnClickListener ")
           // Navigation.findNavController(it).navigate(com.findfriend.R.id.next_action)
        }
    }

}