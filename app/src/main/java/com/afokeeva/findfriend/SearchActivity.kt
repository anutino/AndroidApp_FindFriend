package com.afokeeva.findfriend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    val array = listOf("http://www.camdenconference.org/wp-content/uploads/2017/01/CC_30th-Anniv-Booklet-PAGE-1-cropped.jpg")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        list_item.adapter = adapter
    }

    fun getImageUrl(){
    }
    val adapter = object : BaseAdapter(){

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view = LayoutInflater.from(this@SearchActivity).inflate(R.layout.block,parent, false)
            view?.let {
                Glide
                    .with(view)
                    .load(getItem(position))
                    .into(it.findViewById<ImageView>(R.id.imageViewAnimal));
            }
            return view
        }

        override fun getItem(position: Int): Any {
            return array[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return array.size
        }

    }


}
