package com.afokeeva.findfriend.ui.viewholder

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.Context
import androidx.compose.View
import androidx.compose.ViewGroup
import androidx.constraintlayout.widget.Constraints.TAG
import androidx.core.view.ViewCompat.startDragAndDrop
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.afokeeva.findfriend.data.Animal
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import kotlinx.android.synthetic.main.fragment_select_category.*


@GlideModule
class AnimalViewHolder(inflater: LayoutInflater, parent: ViewGroup, context : Context) : RecyclerView.ViewHolder(inflater.inflate(
    com.afokeeva.findfriend.R.layout.item_image_animal_with_name, parent, false)) {
    var iv: ImageView? = null
    var names: TextView? = null
    val context = context

    init {
        iv = itemView.findViewById(com.afokeeva.findfriend.R.id.item_image_view_animal)
        names = itemView.findViewById(com.afokeeva.findfriend.R.id.item_name_age )
    }

    fun bind(Animal: Animal?) {
        Log.d(TAG,"bind " )//+ listAnimal.size)
        if (Animal != null) {
            Glide
                .with(itemView)
                .load(Animal.img_url)
                .into(iv)

            names?.text = Animal.name
        } else {
            names?.text = "Loading..."
        }

        itemView.setOnClickListener {
            Log.d(TAG, "setOnClickListener " )
            Navigation.findNavController(it).navigate(com.afokeeva.findfriend.R.id.next_action)
        }

        }
}