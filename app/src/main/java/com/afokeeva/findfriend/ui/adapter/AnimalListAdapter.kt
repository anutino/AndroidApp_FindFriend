package com.afokeeva.findfriend.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import androidx.compose.Context
import androidx.compose.ViewGroup
import androidx.constraintlayout.widget.Constraints.TAG
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.afokeeva.findfriend.data.Animal
import com.afokeeva.findfriend.listeners.ItemTouchHelperAdapter
import com.afokeeva.findfriend.ui.viewholder.ItemAnimalViewHolder
import java.util.*

class AnimalListAdapter(val context: Context, var mItems : List<Animal>) : PagedListAdapter<Animal, ItemAnimalViewHolder>(DIFF_CALLBACK),
    ItemTouchHelperAdapter {
    //TODO test CGET INFO !!! https://www.codeproject.com/Articles/1041390/Remember-Drag-and-Drop-Position-with-RecyclerView

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<Animal>() {
            override fun areItemsTheSame(oldConcert: Animal,
                                         newConcert: Animal) = oldConcert.id == newConcert.id

            override fun areContentsTheSame(oldConcert: Animal,
                                            newConcert: Animal) = oldConcert == newConcert
        }}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAnimalViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        Log.d(TAG, "onCreateViewHolder ")
        return ItemAnimalViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: ItemAnimalViewHolder, position: Int) {
//        val animal: Animal? = getItem(position)
        val selectedAnimal : Animal = mItems[position]
        Log.d(TAG, "onBindViewHolder  " + getItem(position))
        holder.bind(selectedAnimal)
        //holder.bind(getItem(position))
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) : Boolean {
        Collections.swap(mItems, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    override fun onItemDismiss(position: Int) {

    }
}