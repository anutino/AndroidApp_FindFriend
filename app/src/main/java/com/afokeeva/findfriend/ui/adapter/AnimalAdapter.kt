package com.afokeeva.findfriend.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import androidx.compose.Context
import androidx.compose.ViewGroup
import androidx.constraintlayout.widget.Constraints.TAG
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.afokeeva.findfriend.data.Animal
import com.afokeeva.findfriend.listeners.EditItemTouchHelper
import com.afokeeva.findfriend.ui.viewholder.AnimalViewHolder

class AnimalAdapter(context : Context) : PagedListAdapter<Animal, AnimalViewHolder>(DIFF_CALLBACK){
    val context = context
    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<Animal>() {
            override fun areItemsTheSame(oldConcert: Animal,
                                         newConcert: Animal) = oldConcert.id == newConcert.id

            override fun areContentsTheSame(oldConcert: Animal,
                                            newConcert: Animal) = oldConcert == newConcert
        }}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        Log.d(TAG, "onCreateViewHolder ")
        return AnimalViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal: Animal? = getItem(position)
        Log.d(TAG, "onBindViewHolder  " + getItem(position))
        holder.bind(animal)
        //holder.bind(getItem(position))
    }



}