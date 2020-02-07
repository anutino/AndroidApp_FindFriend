//package com.afokeeva.findfriend.listeners
//
//import androidx.recyclerview.widget.ItemTouchHelper
//import androidx.recyclerview.widget.RecyclerView
//import com.afokeeva.findfriend.ui.adapter.AnimalAdapter
//
//open class EditItemTouchHelper(mAdapter: AnimalAdapter ): ItemTouchHelper.Callback() {
//     var mAdapter = mAdapter
//    override fun getMovementFlags(
//        recyclerView: RecyclerView,
//        viewHolder: RecyclerView.ViewHolder
//    ): Int {
//        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
//        return makeMovementFlags(dragFlags, 0)    }
//
//    override fun onMove(
//        recyclerView: RecyclerView,
//        viewHolder: RecyclerView.ViewHolder,
//        target: RecyclerView.ViewHolder
//    ): Boolean {
//        mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());    }
//
//    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//}