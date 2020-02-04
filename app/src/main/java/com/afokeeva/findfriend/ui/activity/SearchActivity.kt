package com.afokeeva.findfriend

class SearchActivity {

}
/*
: FragmentActivity(), FilterFragment.OnFragmentInteractionListener{
 /*
    var TAG = "SearchActivity"

    /*TODO 1.change ItemClickListener()
      2. maybe AnimalViewHolders remove to AnimalAdapter
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
        val TAG = "AnimalActivity"
        var listAnimals = mutableListOf<Animal>()//: ArrayList<Animal> = ArrayList()
        var listAnimal = listOf<Animal>(
            Animal(1,  1.2, "dog1",  "",1, "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg"),
            Animal(2,  1.2,"dog1",  "", 1, "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg"),
            Animal(3,  1.2,"dog1",  "", 1, "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg")).toMutableList()
        var listAnimalUPDATE = listOf<Animal>(
            Animal(1,  1.2, "cat",  "",1, "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg"),
            Animal(2,  1.2,"cat",  "", 1, "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg"),
            Animal(3,  1.2,"cat",  "", 1, "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg")).toMutableList()
    }

    @SuppressLint("WrongThread")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val rv = findViewById<RecyclerView>(R.id.activity_search_rvAnimalImages)
        intent = Intent(this@SearchActivity, Animal_InfoActivity::class.java)
        rv.adapter = AnimalAdapter(intent, this)
        //rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv.layoutManager = GridLayoutManager(this, 2)
        rv.addItemDecoration(GridItemDecoration(10, 2))
        //var spacingInPixels = getResources().getDimensionPixelSize(R.dimen.);
        // rv.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
        var AnimalFactory = AnimalDataSourceFactory()
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(10)
            .build()

        var executor: MainThreadExecutor = MainThreadExecutor()
        val pagedList =
            PagedList.Builder<Integer, Animal>(AnimalDataSourceFactory().create(), config)
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .setNotifyExecutor(Executors.newSingleThreadExecutor())
                .build()

        (rv.adapter as AnimalAdapter).submitList(pagedList)
        var itemPagedList: LiveData<PagedList<Animal>>
        itemPagedList = (LivePagedListBuilder(AnimalDataSourceFactory(), config))
            .build();
        rv.setHasFixedSize(true)
        (rv.adapter as AnimalAdapter).notifyDataSetChanged()

         pagedList.dataSource

    }

    @SuppressLint("WrongThread")
    fun updateAnimalList(){
    }

    override fun onResume(){
        super.onResume()
        var filterFragment = FilterFragment()

        Log.d(TAG, "0 ")
        var btnFilter = findViewById<Button>(R.id.btnFilter)
        btnFilter?.setOnClickListener {
            if(filterFragment.isAdded && filterFragment.isHidden){
                Log.d(TAG, "1 ")
                supportFragmentManager.beginTransaction()
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .show(filterFragment)
                .commit()
            }
            else if(!filterFragment.isAdded){
                supportFragmentManager.beginTransaction()
                .add(R.id.activity_search_fragment_filter, filterFragment).show(filterFragment)
                .commit()
            }else {
                Log.d(TAG, "2 ")
                supportFragmentManager.beginTransaction()
                .hide(filterFragment)
                .commit()
            }
        }
    }

     class MainThreadExecutor : Executor {
         private val mHandler = Handler(Looper.getMainLooper())
         override fun execute(command: Runnable){
             mHandler.post(command)
         }
     }
    override fun onFragmentInteraction(age: Double, animal: String) {
        Log.d(TAG, "on Fragment in Act $age  $animal" )
        updateAnimalList()
    }

    override fun onAttachFragment(fragment: androidx.fragment.app.Fragment) {
        super.onAttachFragment(fragment)
        if (fragment is FilterFragment) {
            fragment.setOnHeadlineSelectedListener(this)
        }
    }
}

      class AnimalDataSourceFactory : DataSource.Factory<Integer, Animal>() {
        private val liveDataSource = MutableLiveData<PageKeyedDataSource<Integer, Animal>>()
          var itemDataSource = AnimalDataSource()

          override fun create(): DataSource<Integer, Animal> {
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
            callback.onResult(listAnimal, 1, 4 ,Integer(1), null)
           // callback.onResult(listAnimal, Integer(0), Integer(FIRST_PAGE+1))
            Log.d(TAG, "loadInitial  " + listAnimal.size)
        }

        override fun loadAfter(
            params: LoadParams<Integer>,
            callback: LoadCallback<Integer, Animal>
        ) {
           //  callback.onResult(listAnimal, params.key )
            Log.d(TAG, "loadAfter " )
        }

        override fun loadBefore(
            params: LoadParams<Integer>,
            callback: LoadCallback<Integer, Animal>
        ) {
           //callback.onResult(listAnimal, params.key)
            Log.d(TAG, "loadBefore " )
        }
    }

    class AnimalAdapter(intent: Intent, context : Context) : PagedListAdapter<Animal, AnimalViewHolders>(DIFF_CALLBACK){
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
            Log.d(TAG,"bind " + listAnimal.size)
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
class GridItemDecoration(gridSpacingPx: Int, gridSize: Int) : RecyclerView.ItemDecoration() {
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







*/