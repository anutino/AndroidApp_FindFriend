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

    val array = listOf("https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg",

        "https://wallscloud.net/uploads/cache/1266658504/blue-space-planet-krB5-1024x576-MM-90.jpg",

        "https://avatars.mds.yandex.net/get-pdb/1025580/369ea651-40b4-4467-91da-069ba497b3d1/s1200",

        "https://pp.userapi.com/c824502/v824502702/18b194/TAYpSgN3JeU.jpg"

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        imageList.adapter = adapter
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
