package com.afokeeva.findfriend

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.afokeeva.findfriend.infoFromServer.Tables.Animal
import com.bumptech.glide.Glide
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DiffUtil
import androidx.lifecycle.MutableLiveData
import androidx.paging.*
import androidx.paging.PagedList
import android.os.Looper
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.fragment.app.FragmentManager
import java.util.concurrent.Executor
import androidx.lifecycle.LiveData
import androidx.paging.PageKeyedDataSource
import androidx.recyclerview.widget.GridLayoutManager
import com.afokeeva.findfriend.SearchActivity.arrayObj.TAG
import com.afokeeva.findfriend.SearchActivity.arrayObj.listTest
import com.bumptech.glide.annotation.GlideModule
import java.util.concurrent.Executors

class SearchActivity : AppCompatActivity() {
    /*TODO 1.change ItemClickListener()
         2. maybe AnimalViewHolders remove to TestAdapter
     //https://codelabs.developers.google.com/codelabs/android-paging/index.html?index=..%2F..%2Findex#9
     //https://www.simplifiedcoding.net/android-paging-library-tutorial/#Android-Paging-Library-Tutorial-Source-Code
     //https://bloggie.io/@_junrong/part-1-understanding-the-paging-library-pagedlist
      listAnimals = listOf<Animal>(
            Animal(5, 3.5, "dog1", "", 1, "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg"),
            Animal(6, 3.5, "dog1", "", 1, "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg"),
            Animal(1, 1.0, "dog1", "", 1, "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg"),
            Animal(1, 1.0, "dog1", "", 1, "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg"),
            Animal(1, 1.0, "dog1", "", 1, "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg"),
            Animal(1, 1.0, "dog1", "", 1, "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg"),
            Animal(1, 1.0, "dog1", "", 1, "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg")
        ).toMutableList()
     */

     companion object arrayObj {
        val TAG = "TestActivity"
        var listTests = mutableListOf<Animal>()//: ArrayList<Animal> = ArrayList()
        var listTest = listOf<Animal>(
        Animal(1,  1.2, "dog1",  "",1, "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg"),
        Animal(2,  1.2,"dog1",  "", 1, "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg"),
        Animal(3,  1.2,"dog1",  "", 1, "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg")).toMutableList()
    }

    @SuppressLint("WrongThread")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val rv = findViewById<RecyclerView>(R.id.activity_search_rvAnimalImages)
        intent = Intent(this@SearchActivity, Animal_InfoActivity::class.java)
        rv.adapter = TestAdapter(  intent, this)
        //rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv.layoutManager = GridLayoutManager(this,2)
        rv.addItemDecoration(GridItemDecoration(10,2))
        //var spacingInPixels = getResources().getDimensionPixelSize(R.dimen.);
       // rv.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
        var testFactory = AnimalDataSourceFactory()
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(10)
            .build()

        var executor : MainThreadExecutor = MainThreadExecutor()
        val pagedList = PagedList.Builder<Integer, Animal>(AnimalDataSourceFactory().create(), config)
            .setFetchExecutor(Executors.newSingleThreadExecutor())
            .setNotifyExecutor(Executors.newSingleThreadExecutor())
            .build()

        (rv.adapter as TestAdapter).submitList(pagedList)
        var itemPagedList : LiveData<PagedList<Animal>>
            itemPagedList = (LivePagedListBuilder(AnimalDataSourceFactory(), config))
                .build();
         rv.setHasFixedSize(true)

        var btnFilter = findViewById<Button>(R.id.btnFilter)
 //        btnFilter?.setOnClickListener {
//             supportFragmentManager.beginTransaction()
//             .add(R.id.activity_search_fragment_filter, FilterFragment())
//             .addToBackStack(null)
//             .commit()
//        }
    }

    class GridItemDecoration(gridSpacingPx: Int, gridSize: Int) : RecyclerView.ItemDecoration() { //TODO improve understanding
        private var mSizeGridSpacingPx: Int = gridSpacingPx
        private var mGridSize: Int = gridSize

        private var mNeedLeftSpacing = false

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            val frameWidth = ((parent.width - mSizeGridSpacingPx.toFloat() * (mGridSize - 1)) / mGridSize).toInt()
            val padding = parent.width / mGridSize - frameWidth
            val itemPosition = (view.getLayoutParams() as RecyclerView.LayoutParams).viewAdapterPosition//guarantees receipt of a specific list index
            if (itemPosition < mGridSize) {
                outRect.top = 0
            } else {
                outRect.top = mSizeGridSpacingPx
            }
            if (itemPosition % mGridSize == 0) {
                outRect.left = 0
                outRect.right = padding
                mNeedLeftSpacing = true
            } else if ((itemPosition + 1) % mGridSize == 0) {
                mNeedLeftSpacing = false
                outRect.right = 0
                outRect.left = padding
            } else if (mNeedLeftSpacing) {
                mNeedLeftSpacing = false
                outRect.left = mSizeGridSpacingPx - padding
                if ((itemPosition + 2) % mGridSize == 0) {
                    outRect.right = mSizeGridSpacingPx - padding
                } else {
                    outRect.right = mSizeGridSpacingPx / 2
                }
            } else if ((itemPosition + 2) % mGridSize == 0) {
                mNeedLeftSpacing = false
                outRect.left = mSizeGridSpacingPx / 2
                outRect.right = mSizeGridSpacingPx - padding
            } else {
                mNeedLeftSpacing = false
                outRect.left = mSizeGridSpacingPx / 2
                outRect.right = mSizeGridSpacingPx / 2
            }
            outRect.bottom = 0
        }
    }


     class MainThreadExecutor : Executor {
         private val mHandler = Handler(Looper.getMainLooper())
         override fun execute(command: Runnable){
             mHandler.post(command)
         }
     }
}

      class AnimalDataSourceFactory : DataSource.Factory<Integer, Animal>() {
        private val liveDataSource = MutableLiveData<PageKeyedDataSource<Integer, Animal>>()

        override fun create(): DataSource<Integer, Animal> {
             var itemDataSource = AnimalDataSource()
             liveDataSource.postValue(itemDataSource)
         return itemDataSource
        }

          fun getItemLiveDataSource(): MutableLiveData<PageKeyedDataSource<Integer, Animal>> {
              return liveDataSource
         }
     }

    class AnimalDataSource: PageKeyedDataSource<Integer, Animal>(){
        val PAGE_SIZE = 50;
        val FIRST_PAGE = 9;
        var initialParams : LoadInitialParams<Integer>? = null

        override fun loadInitial(
            params: LoadInitialParams<Integer>,
            callback: LoadInitialCallback<Integer, Animal>
        ) {
            val i = 1
            // callback.onResult(ServerRequest.Animal(i, params.requestedLoadSize), 0, 0 ,0)
            // callback.onResult(ServerRequest.Animal(), 1, 10 ,Integer(1), null)
            callback.onResult(listTest, 1, 4 ,Integer(1), null)
           // callback.onResult(listTest, Integer(0), Integer(FIRST_PAGE+1))
            Log.d(TAG, "loadInitial  " + listTest.size)
        }

        override fun loadAfter(
            params: LoadParams<Integer>,
            callback: LoadCallback<Integer, Animal>
        ) {
           //  callback.onResult(listTest, params.key )
            Log.d(TAG, "loadAfter " )
        }

        override fun loadBefore(
            params: LoadParams<Integer>,
            callback: LoadCallback<Integer, Animal>
        ) {
           //callback.onResult(listTest, params.key)
            Log.d(TAG, "loadBefore " )
        }
    }

    class TestAdapter(intent: Intent, context : Context) : PagedListAdapter<Animal, AnimalViewHolders>(DIFF_CALLBACK){

        val intent = intent
        val context = context
        companion object {
            private val DIFF_CALLBACK = object :
                DiffUtil.ItemCallback<Animal>() {
                override fun areItemsTheSame(oldConcert: Animal,
                                             newConcert: Animal) = oldConcert.id == newConcert.id

                override fun areContentsTheSame(oldConcert: Animal,
                                              newConcert: Animal) = oldConcert == newConcert
            }}

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolders {
                val inflater = LayoutInflater.from(parent.context)
                Log.d(TAG, "onCreateViewHolder ")
                return AnimalViewHolders(inflater, parent, intent, context)
        }

        override fun onBindViewHolder(holder: AnimalViewHolders, position: Int) {
            val animal: Animal? = getItem(position)
            Log.d(TAG, "onBindViewHolder  " + getItem(position))
            holder.bind(animal)
            //holder.bind(getItem(position))
        }
        }

    @GlideModule
    class AnimalViewHolders(inflater: LayoutInflater, parent: ViewGroup, intent: Intent, context : Context) : RecyclerView.ViewHolder(inflater.inflate(
        com.afokeeva.findfriend.R.layout.item_image_animal_with_name, parent, false)) {
        var iv: ImageView? = null
        var names: TextView? = null
        var intent = intent
        val context = context

        init {
            iv = itemView.findViewById(com.afokeeva.findfriend.R.id.item_imageView_Animal)
            names = itemView.findViewById(com.afokeeva.findfriend.R.id.item_name_age )
        }

        fun bind(Animal: Animal?) {
            Log.d(TAG,"bind " + listTest.size)
            if (Animal != null) {
                Glide
                    .with(itemView)
                    .load(Animal.img_url)
                    .into(iv)

                names?.text = Animal.name
            } else {
                names?.text = "Loading..."
            }

            itemView.setOnClickListener (View.OnClickListener() {
                Log.d(TAG, "setOnClickListener " )
                context.startActivity(intent)
            })
        }
    }







