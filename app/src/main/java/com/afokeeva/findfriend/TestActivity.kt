package com.afokeeva.findfriend

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.afokeeva.findfriend.infoFromServer.Tables.Test
import com.bumptech.glide.Glide
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DiffUtil
import androidx.lifecycle.MutableLiveData
import androidx.paging.*
import com.afokeeva.findfriend.MainActivity
import androidx.paging.PagedList
import android.os.Looper
import android.os.Handler
import java.util.concurrent.Executor
import androidx.lifecycle.LiveData
import com.afokeeva.findfriend.infoFromServer.ServerRequest
import android.R.attr.description
import com.afokeeva.findfriend.TestActivity.arrayObj.listAnimals
import android.content.ClipData.Item
import androidx.paging.PageKeyedDataSource
import com.afokeeva.findfriend.TestDataSourceFactory
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.annotation.NonNull
import java.util.concurrent.Executors


class TestActivity : AppCompatActivity() {
    //https://codelabs.developers.google.com/codelabs/android-paging/index.html?index=..%2F..%2Findex#9

    //https://www.simplifiedcoding.net/android-paging-library-tutorial/#Android-Paging-Library-Tutorial-Source-Code
    companion object arrayObj {
        val TAG = "SearchActivity"
        var listTests = mutableListOf<Test>()//: ArrayList<Test> = ArrayList()
        var testArray = listOf(
            "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg",
            "https://wallscloud.net/uploads/cache/1266658504/blue-space-planet-krB5-1024x576-MM-90.jpg",
            "https://avatars.mds.yandex.net/get-pdb/1025580/369ea651-40b4-4467-91da-069ba497b3d1/s1200",
            "https://pp.userapi.com/c824502/v824502702/18b194/TAYpSgN3JeU.jpg"
        )
        var listAnimals = listOf<Test>(
        Test(1,  "dog1",  "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg"),
        Test(2,  "dog1",  "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg"),
        Test(3,  "dog1",  "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg")).toMutableList()
    }

    @SuppressLint("WrongThread")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.afokeeva.findfriend.R.layout.activity_search)
        val rv = findViewById<RecyclerView>(com.afokeeva.findfriend.R.id.recycleViewImage)
        val articleLiveData: LiveData<PagedList<Test>>
        var test: Test? = null
        var testFactory = TestDataSourceFactory()
        val dataSource: LiveData<PageKeyedDataSource<Integer, Test>>
         dataSource = testFactory.getItemLiveDataSource()

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(10)
            .build()

        var executor : MainThreadExecutor = MainThreadExecutor()

       /* val list1 = PagedList.Builder(testFactory.getItemLiveDataSource(), config)
            .setFetchExecutor(executor)
            .setNotifyExecutor(executor)
            .build();
        */
        rv.adapter=TestAdapter()
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
       // rv.setHasFixedSize(true)
    }

     class MainThreadExecutor : Executor {
         private val mHandler = Handler(Looper.getMainLooper())
         override fun execute(command: Runnable) {
             mHandler.post(command)
         }
     }
}

      class TestDataSourceFactory : DataSource.Factory<Integer, Test>() {
        private val liveDataSource = MutableLiveData<PageKeyedDataSource<Integer, Test>>()

        override fun create(): DataSource<Integer, Test> {
             var itemDataSource = TestDataSource()
             liveDataSource.postValue(itemDataSource)
         return itemDataSource
        }

          fun getItemLiveDataSource(): MutableLiveData<PageKeyedDataSource<Integer, Test>> {
             return liveDataSource
         }

     }

    class TestDataSource: PageKeyedDataSource<Integer, Test>(){
        var page_size = 10
        var firsPage = 1

        override fun loadInitial(
            params: LoadInitialParams<Integer>,
            callback: LoadInitialCallback<Integer, Test>
        ) {
            val i = 1
            // callback.onResult(ServerRequest.test(i, params.requestedLoadSize), 0, 0 ,0)
            // callback.onResult(ServerRequest.test(), 1, 10 ,Integer(1), null)
//            for(t in 1..5) {
//                test.trackId = 1
//                test.artistName = "name_1"
//                test.artworkUrl30 = "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg"
//                listAnimals.add(test)
//            }
            callback.onResult(listAnimals, 1, 10 ,Integer(1), null)
           println("__________________loadInitial_____________ " + listAnimals.size)
        }

        override fun loadAfter(
            params: LoadParams<Integer>,
            callback: LoadCallback<Integer, Test>
        ) {
            println("___________________loadAfter____________ ")        }

        override fun loadBefore(
            params: LoadParams<Integer>,
            callback: LoadCallback<Integer, Test>
        ) {
            println("____________________loadBefore___________ " )
        }
    }


    class TestAdapter : PagedListAdapter<Test, TestViewHolders>(DIFF_CALLBACK){
        companion object {
            private val DIFF_CALLBACK = object :
                DiffUtil.ItemCallback<Test>() {
                // Concert details may have changed if reloaded from the database,
                // but ID is fixed.
                override fun areItemsTheSame(oldConcert: Test,
                                             newConcert: Test) = oldConcert.trackId == newConcert.trackId

                override fun areContentsTheSame(oldConcert: Test,
                                                newConcert: Test) = oldConcert == newConcert
            }}

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolders {
                val inflater = LayoutInflater.from(parent.context)
                return TestViewHolders(inflater, parent)
        }

        override fun onBindViewHolder(holder: TestViewHolders, position: Int) {
            val animal: Test? = getItem(position)
                print("__________________ " + animal)
            print("+++++ " + getItem(position) )
            holder.bind(animal)
            //holder.bind(getItem(position))
        }
}


    class TestViewHolders(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(
        com.afokeeva.findfriend.R.layout.block, parent, false)) {
        var iv: ImageView? = null
        var names: TextView? = null
        var ages: TextView? = null

        init {
            iv = itemView.findViewById(com.afokeeva.findfriend.R.id.imageViewAnimal)
            names = itemView.findViewById(com.afokeeva.findfriend.R.id.textViewName)
            ages = itemView.findViewById(com.afokeeva.findfriend.R.id.textViewAge)
        }

        fun bind(test: Test?) {
            if (test != null) {
                Glide
                    .with(itemView)
                    .load(test.artworkUrl30)
                    .into(iv)

                names?.text = test.artistName
               // ages?.text = test.
            } else {
                names?.text = "Loading..."
                ages?.text = "Loading..."
            }
        }
    }







