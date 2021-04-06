package com.findfriend.ui.fragment

//import androidx.fragment.app.viewModels
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.findfriend.R
import com.findfriend.data.ShortAnimalInfo
import com.findfriend.ui.adapter.AnimalListAdapter
import com.findfriend.viewmodel.AnimalShortInfoViewModel
import com.findfriend.viewmodel.AnimalTypeViewModel
import kotlinx.android.synthetic.main.fragment_searching_animal.view.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AnimalShortInfoListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AnimalShortInfoListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AnimalShortInfoListFragment : Fragment() {
    private val TAG: String = "AnimalShortInfoListFragment"
    private val ANIMAL_TYPE = "animalType"

    private var TYPE = 0
    lateinit var mRecyclerView: RecyclerView
    private var listener: OnFragmentInteractionListener? = null
    private var useSearchingAnimalFragment = true // if false - use Favorites Fragment
    lateinit var mFilterButton: Button
    private lateinit var mDialogFilter: FilterFragment
    private var isShowFilter: Boolean = false
    lateinit var mHandle: Handler

    companion object {
        lateinit var mViewModel: AnimalShortInfoViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            TYPE = it.getInt(ANIMAL_TYPE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_searching_animal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        //  initViewModel() not delete!!!
        //   initObserver() not delete!!!
    }

    private fun initViewModel() {
        mViewModel = ViewModelProviders.of(activity!!).get(AnimalShortInfoViewModel::class.java)
//        mViewModel = ViewModelProviders.of(this)
//            .get(AnimalTypeViewModel::class.java)

        if (TYPE != 0) {
            mViewModel.loadAnimalListFilteredByType(TYPE)
        } else {
            mViewModel.loadAllAnimals()
        }
    }
    fun fastBlur(context: Context, sentBitmap: Bitmap, radius: Long): Bitmap {
        var width = Math.round(sentBitmap.getWidth() * 0.8f);
        var height = Math.round(sentBitmap.getHeight() * 0.8f);

        var inputBitmap = Bitmap.createScaledBitmap(sentBitmap, width, height, false);
        var outputBitmap = Bitmap.createBitmap(inputBitmap);

        var rs = RenderScript.create(context);
        var theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        var tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        var tmpOut = Allocation.createFromBitmap(rs, outputBitmap);
        theIntrinsic.setRadius(radius.toFloat());
        theIntrinsic.setInput(tmpIn);
        theIntrinsic.forEach(tmpOut);
        tmpOut.copyTo(outputBitmap);
        return outputBitmap;
    }
    private fun takeScreenShot(activity: Activity): Bitmap {
        var view = activity.window.decorView
        view.isDrawingCacheEnabled = true
        view.buildDrawingCache()
        var b1 = view.getDrawingCache()
        var frame = Rect()
        view.getWindowVisibleDisplayFrame(frame)
        var b = Bitmap.createBitmap(b1, frame.left, frame.top, frame.width(), frame.height())
        view.destroyDrawingCache()
        return fastBlur(activity.applicationContext, b, 24)
    }

    private fun initView(view: View) {
        mFilterButton = view.findViewById(R.id.button_filter)
        mDialogFilter = FilterFragment()
      //  var background: Drawable
     //   var blurBitmap: Bitmap
   //     blurBitmap = takeScreenShot(activity!!)
            //  background = BitmapDrawable(resources, blurBitmap)

        mFilterButton.setOnClickListener {
            fragmentManager?.let {

                 mDialogFilter.show(it, "FilterDialog")
//                mDialogFilter.dialog!!
//                    .window!!.setBackgroundDrawable(background)
//                val alertDialog: AlertDialog = AlertDialog.Builder(activity,R.style.Blur)
//                    .setTitle("Уточнить")
//                    .setView(R.layout.fragment_filter_dialog)
//                    .setPositiveButton("OK", null)
//                    .setNegativeButton("Cancel", null)
//                    .create()
//                alertDialog.setCanceledOnTouchOutside(true)
//                alertDialog.show()
            }
        }
        mRecyclerView = view.recycle_view_animal_short_info
        mRecyclerView.layoutManager = GridLayoutManager(context, 4)
        var adapter = AnimalListAdapter()


        val list = mutableListOf<ShortAnimalInfo>()
        list.add(0,
            ShortAnimalInfo(0, 1.0, "Iris", "2", "file:///C:/opt/animals/dog/olhon.jpg", false))
        list.add(1,
            ShortAnimalInfo(0, 1.0, "Iris", "2", "file:///C:/opt/animals/dog/olhon.jpg", true))
        list.add(2,
            ShortAnimalInfo(0, 1.0, "Iris", "2", "file:///C:/opt/animals/dog/olhon.jpg", true))
        list.add(3,
            ShortAnimalInfo(0, 1.0, "Iris", "2", "file:///C:/opt/animals/dog/olhon.jpg", true))
        list.add(4,
            ShortAnimalInfo(0, 1.0, "Iris", "2", "file:///C:/opt/animals/dog/olhon.jpg", true))
        adapter.setItems(list)

        mRecyclerView.adapter = adapter

    }

    private fun initObserver() {
        mViewModel.getAnimalInfo().observe(this, Observer {
            it?.let {
                (mRecyclerView.adapter as AnimalListAdapter).refreshItems(it)
                Log.d("AF", " initObserver AnimalListAdapter")
                //myAdapter.setItem(it)
                //     Log.d("AF", "observe " + it.size + " "+it)
            }
        })
    }
//
//    fun onClickItem(selectedButton : Button, id : Int){
//        selectedButton.setOnClickListener {
//            val bundle = bundleOf("animalId" to id)
//            Navigation.findNavController(it).navigate(com.findfriend.R.id.next_action, bundle)
//        }
//    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$TAG must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

}
