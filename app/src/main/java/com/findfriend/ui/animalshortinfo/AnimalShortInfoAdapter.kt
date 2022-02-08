package com.findfriend.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.ViewGroup
import androidx.constraintlayout.widget.Constraints
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.findfriend.R
 import com.findfriend.data.ShortAnimalInfo
import com.findfriend.listener.ItemTouchHelperAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.findfriend.ui.animalshortinfo.AnimalShortInfoListFragment
import java.util.*

class AnimalListAdapter : RecyclerView.Adapter<ItemAnimalViewHolder>(), ItemTouchHelperAdapter {
    private var mItems: List<ShortAnimalInfo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAnimalViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemAnimalViewHolder(inflater, parent) // инициализирует views для списка
    }

    override fun onBindViewHolder(holder: ItemAnimalViewHolder, position: Int) {
        val selectedAnimal: ShortAnimalInfo = mItems[position]
        holder.bind(selectedAnimal)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        Collections.swap(mItems, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }
    fun setItems(info : List<ShortAnimalInfo>){
        mItems = info
        notifyDataSetChanged()
    }

    override fun onItemDismiss(position: Int) {
    }

    override fun getItemCount() = mItems.size

    fun refreshItems(items: List<ShortAnimalInfo>) {
        this.mItems = items
        notifyDataSetChanged()
    }
}

@GlideModule
class ItemAnimalViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(R.layout.item_animal_short_info, parent, false)) {

    private var TAG = "ItemAnimalViewHolder"
    private var id : Int = 0
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
            Log.d(Constraints.TAG, "animal.img_url  " + animal.age + " "+animal.id)
            Glide
                .with(itemView)
                .load(mResourcesPath + animal.mainPicture)
                .into(mImage)

            name?.text = animal.name

            favorite?.isSelected = animal.favorite
            id = animal.id
            itemView.setOnClickListener {
                Log.d(Constraints.TAG, "setOnClickListener animal?.id "+id)
                AnimalShortInfoListFragment.mViewModel.setAnimalInfoById(id)

                Navigation.findNavController(it)
                    .navigate(R.id.destination_animal_detailed_info)
            }
        } else {
            name?.text = "Loading..."
        }

        //TODO addFavorite
        //favorite.setOnClickListener({})


        favorite?.setOnClickListener {
            Log.d(Constraints.TAG, "favorite setOnClickListener ")
        }
    }
}
