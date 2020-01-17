package com.afokeeva.findfriend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.afokeeva.findfriend.infoFromServer.Tables.Animal
import com.bumptech.glide.Glide
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.Toast
import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.afokeeva.findfriend.SearchActivity.arrayObj.listAnimals
import androidx.lifecycle.MutableLiveData
import androidx.paging.*


class SearchActivity : AppCompatActivity() {
    companion object arrayObj {
        val TAG = "SearchActivity"
       var listAnimals = mutableListOf<Animal>()//: ArrayList<Animal> = ArrayList()
        var testArray = listOf(
            "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg",
            "https://wallscloud.net/uploads/cache/1266658504/blue-space-planet-krB5-1024x576-MM-90.jpg",
            "https://avatars.mds.yandex.net/get-pdb/1025580/369ea651-40b4-4467-91da-069ba497b3d1/s1200",
            "https://pp.userapi.com/c824502/v824502702/18b194/TAYpSgN3JeU.jpg"
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         listAnimals = listOf<Animal>(
             Animal(1, 1.0, "dog1", "", 1, "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg"),
             Animal(
                 2,
                 1.5,
                 "dog2",
                 "",
                 1,
                 "https://wallscloud.net/uploads/cache/1266658504/blue-space-planet-krB5-1024x576-MM-90.jpg"
             ),
             Animal(
                 3,
                 2.0,
                 "dog3",
                 "",
                 1,
                 "https://avatars.mds.yandex.net/get-pdb/1025580/369ea651-40b4-4467-91da-069ba497b3d1/s1200"
             ),
             Animal(
                 4,
                 2.5,
                 "dog4",
                 "",
                 1,
                 "https://pp.userapi.com/c824502/v824502702/18b194/TAYpSgN3JeU.jpg"
             ),
             Animal(5, 3.5, "dog1", "", 1, "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg"),
             Animal(6, 3.5, "dog1", "", 1, "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg"),
            Animal(1, 1.0, "dog1", "", 1, "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg"),
            Animal(1, 1.0, "dog1", "", 1, "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg"),
            Animal(1, 1.0, "dog1", "", 1, "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg"),
            Animal(1, 1.0, "dog1", "", 1, "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg"),
            Animal(1, 1.0, "dog1", "", 1, "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg")
         ).toMutableList()


        setContentView(R.layout.activity_search)
        val rv = findViewById<RecyclerView>(R.id.recycleViewImage)
        //rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
      /*  var dataSource = AnimalDataSource(EmployeeStorage())
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(10)
            .build()

        var animalDataSourceFactory : AnimalDataSourceFactory
        rv.adapter=AnimalAdapter()
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv.layoutManager
        var totalItemCount: Int
        var isLoading = true
        var linearLayoutManager  = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        var pastVisiblesItems: Int
        var visibleItemCount: Int

        rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0)
                {
                    visibleItemCount = linearLayoutManager.getChildCount()
                    totalItemCount = linearLayoutManager.getItemCount()
                    pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition()

                    if (isLoading) {
                        if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                            isLoading = false
                            println(  "Last Item Wow !")
                            Toast.makeText(this@SearchActivity, "Loading data completed", Toast.LENGTH_SHORT)
                            //Do pagination.. i.e. fetch new data
                        }
                    }
                }
            }
        })

        rv.layoutManager = linearLayoutManager
        rv.setHasFixedSize(true)
        rv.adapter = Adapter(listAnimals)*/

    }

    /*class AnimalDataSourceFactory(private val employeeStorage_: EmployeeStorage) : DataSource.Factory<Integer, Animal>() {

        val itemLiveDataSource = MutableLiveData<PageKeyedDataSource<Integer, Animal>>()
        var  employeeStorage : EmployeeStorage = employeeStorage_
        override fun create(): DataSource<Integer, Animal> {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

   class AnimalDataSource(private var employeeStorage: EmployeeStorage) :
        PositionalDataSource<Animal>() {
        override fun loadInitial(
            params: PositionalDataSource.LoadInitialParams,
            callback: PositionalDataSource.LoadInitialCallback<Animal>
        )

        {
            Log.d(TAG,"loadInitial, requestedStartPosition = " + params.requestedStartPosition +
                    ", requestedLoadSize = " + params.requestedLoadSize
            )
            val result = employeeStorage!!.getData(params.requestedStartPosition, params.requestedLoadSize)
            callback.onResult(result, 0)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Animal>) {
            Log.d(TAG,"loadRange, startPosition = " + params.startPosition + ", loadSize = " + params.loadSize )
            val result = employeeStorage!!.getData(params.startPosition, params.loadSize)
            callback.onResult(result)
        }
    }


    class AnimalAdapter : PagedListAdapter<Animal, AnimalViewHolders>(DIFF_CALLBACK){
        companion object {
            private val DIFF_CALLBACK = object :
                DiffUtil.ItemCallback<Animal>() {
                // Concert details may have changed if reloaded from the database,
                // but ID is fixed.
                override fun areItemsTheSame(oldConcert: Animal,
                                             newConcert: Animal) = oldConcert.id == newConcert.id

                override fun areContentsTheSame(oldConcert: Animal,
                                                newConcert: Animal) = oldConcert == newConcert
            }}

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolders {
                val inflater = LayoutInflater.from(parent.context)
                return AnimalViewHolders(inflater, parent)
        }

        override fun onBindViewHolder(holder: AnimalViewHolders, position: Int) {
            val animal: Animal = listAnimals[position]
            holder.bind(animal)
        }

}

    class AnimalViewHolders(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(R.layout.block, parent, false)){
        var iv: ImageView? = null
        var names: TextView? = null
        var ages: TextView? = null

        init{
            iv = itemView.findViewById(R.id.imageViewAnimal)
            names = itemView.findViewById(R.id.textViewName)
            ages = itemView.findViewById(R.id.textViewAge)
        }
        fun bind(animal: Animal) {
            Glide
                .with(itemView)
                .load(animal.img_url)
                .into(iv)

            names?.text = animal.name
            ages?.text = animal.age.toString()
        }
    }

}

class EmployeeStorage {
    fun getData(requestedStartPosition: Int, requestedLoadSize: Int): MutableList<Animal> {
        return listAnimals
    }
}
  class AnimalDataSourceFactory(employeeStorage_: EmployeeStorage) : DataSource.Factory<Integer, Animal>() {
     val  employeeStorage : EmployeeStorage = employeeStorage_

      override fun create(): DataSource<Integer, Animal>? {
           return SearchActivity.AnimalDataSource(employeeStorage)
      }
}



   class AnimalViewHolders(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(R.layout.block, parent, false)){
       var iv: ImageView? = null
       var names: TextView? = null
       var ages: TextView? = null

       init{
           iv = itemView.findViewById(R.id.imageViewAnimal)
           names = itemView.findViewById(R.id.textViewName)
           ages = itemView.findViewById(R.id.textViewAge)
       }
       fun bind(animal: Animal) {
           Glide
               .with(itemView)
               .load(animal.img_url)
               .into(iv)

           names?.text = animal.name
           ages?.text = animal.age.toString()
       }
   }

   class Adapter(private val listAnimals : List<Animal>) : RecyclerView.Adapter<AnimalViewHolders>(){
       override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolders {
           val inflater = LayoutInflater.from(parent.context)
           return AnimalViewHolders(inflater, parent)
       }

       override fun getItemCount(): Int {
           return listAnimals.size
       }

       override fun onBindViewHolder(holder: AnimalViewHolders, position: Int) {
           val animal: Animal = listAnimals[position]
           holder.bind(animal)
       }
   }
*/
}





