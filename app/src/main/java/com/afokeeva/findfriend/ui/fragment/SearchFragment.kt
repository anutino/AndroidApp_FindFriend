package com.afokeeva.findfriend.ui.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afokeeva.findfriend.data.Animal
import java.util.concurrent.Executors
import android.annotation.SuppressLint
import android.os.Handler
import androidx.compose.Looper
import com.afokeeva.findfriend.data.AnimalDataSourceFactory
import com.afokeeva.findfriend.ui.adapter.AnimalAdapter
import java.util.concurrent.Executor
import android.R
import android.R.attr.colorPrimaryDark
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.recyclerview.widget.ItemTouchHelper
import com.afokeeva.findfriend.listeners.EditItemTouchHelper
import com.afokeeva.findfriend.viewmodel.AnimalsListViewModel


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private var useSearchFragment = true // if false - use Favorites Fragment


    companion object listAnimalObject {
        var listAnimal = listOf<Animal>(
            Animal(1, 1.2, "cat", "", 1, "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg"),
            Animal(2, 1.2, "cat", "", 1, "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg"),
            Animal(3, 1.2, "cat", "", 1, "https://i.ytimg.com/vi/-PB8fVx4axw/hqdefault.jpg")
        ).toMutableList()
    }
//    private val model: AnimalsListViewModel by viewModels {
//        object : AbstractSavedStateViewModelFactory(this, null) {
//            override fun <T : ViewModel?> create(
//                key: String,
//                modelClass: Class<T>,
//                handle: SavedStateHandle
//            ): T {
//                val repoTypeParam = intent.getIntExtra(KEY_REPOSITORY_TYPE, 0)
//                val repoType = RedditPostRepository.Type.values()[repoTypeParam]
//                val repo = ServiceLocator.instance(this@RedditActivity)
//                    .getRepository(repoType)
//                @Suppress("UNCHECKED_CAST")
//                return SubRedditViewModel(repo, handle) as T
//            }
//        }
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        val model: AnimalsListViewModel by viewModels()
        model.getAnimalInfo().observe(this, Observer<List<Animal>>{ it ->
            // update UI
        })

    }
    @SuppressLint("WrongThread")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        //<--- test
        // work --->

//        var mItems : List<Animal>? = null
//        var mViewModel = ViewModelProviders.of(this@SearchFragment).get(AnimalsListViewModel::class.java)
//        mViewModel.animalsListLiveData.observe(this, Observer {
//            mItems = it // it in Recycle View
//        })

        /*var view = inflater.inflate(com.afokeeva.findfriend.R.layout.fragment_search, container, false)
        var rv = view.findViewById(com.afokeeva.findfriend.R.id.fragment_search_rvAnimalImages) as RecyclerView
        rv.layoutManager = GridLayoutManager(activity,2)

        rv.adapter = activity?.let { AnimalAdapter(it, mItems) }
        rv.layoutManager = GridLayoutManager(activity, 2)
       // rv.addItemDecoration(GridItemDecoration(10, 2)) maybe it's unnecessary
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(10)
            .build()

        var executor: MainThreadExecutor = MainThreadExecutor()
        val pagedList =
            PagedList.Builder<Integer, Animal>(AnimalDataSourceFactory(useSearchFragment).create(), config)
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .setNotifyExecutor(Executors.newSingleThreadExecutor())
                .build()

        (rv.adapter as AnimalAdapter).submitList(pagedList)
        var itemPagedList: LiveData<PagedList<Animal>>
        itemPagedList = (LivePagedListBuilder(AnimalDataSourceFactory(useSearchFragment), config))
            .build();
        rv.setHasFixedSize(true)
        (rv.adapter as AnimalAdapter).notifyDataSetChanged()
        pagedList.dataSource


        val callback = EditItemTouchHelper(rv.adapter as AnimalAdapter)
        val mItemTouchHelper = ItemTouchHelper(callback)
        mItemTouchHelper.attachToRecyclerView(rv)
        rv.addItemDecoration(mItemTouchHelper
           //object HorizontalDividerItemDecoration.Builder(this)
             //   .colorResId(R.color.background_dark)
               // .size(2)
                //.build()
        )*/
        return view
    }

    //test for getting info
//    private val model: AnimalsListViewModel by viewModels()
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        model.getAnimalInfo().observe(this, Observer<List<Animal>>{ it ->
//            // update UI
//        })
//    }

    class MainThreadExecutor : Executor {
        private val mHandler = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable){
            mHandler.post(command)
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment SearchFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            SearchFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}




