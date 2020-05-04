package com.afokeeva.findfriend.listeners

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.afokeeva.findfriend.data.Animal


interface ItemTouchHelperAdapter {
    /**
     * Called when an item has been dragged far enough to trigger a move. This is called every time
     * an item is shifted, and not at the end of a "drop" event.
     *
     * @param fromPosition The start position of the moved item.
     * @param toPosition   Then end position of the moved item.
     */
    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean

    /**
     * Called when an item has been dismissed by a swipe.
     *
     * @param position The position of the item dismissed.
     */
    fun onItemDismiss(position: Int)
}

interface OnStartDragListener {
    /**
     * Called when a view is requesting a start of a drag.
     *
     * @param viewHolder The holder of the view to drag.
     */
    fun onStartDrag(viewHolder: RecyclerView.ViewHolder)
}
interface OnCustomerListChangedListener {
    fun onNoteListChanged(customers: List<Animal>)
}

interface ItemTouchHelperViewHolder {
    /**
     * Implementations should update the item view to indicate it's active state.
     */
    fun onItemSelected()


    /**
     * state should be cleared.
     */
    fun onItemClear()
}

open class EditItemTouchHelper(adapter: ItemTouchHelperAdapter ): ItemTouchHelper.Callback() {
    private lateinit var mAdapter: ItemTouchHelperAdapter

    init{
        mAdapter = adapter
    }


    override fun isLongPressDragEnabled(): Boolean {
        return true
    }


    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }


    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: ViewHolder): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return ItemTouchHelper.Callback.makeMovementFlags(dragFlags, swipeFlags)
    }


    override fun onMove(
        recyclerView: RecyclerView, viewHolder: ViewHolder,
        target: ViewHolder
    ): Boolean {
        mAdapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }


    override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
        mAdapter.onItemDismiss(viewHolder.adapterPosition)
    }


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
}


