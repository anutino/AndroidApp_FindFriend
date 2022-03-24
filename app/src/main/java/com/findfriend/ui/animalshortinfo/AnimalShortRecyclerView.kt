package com.findfriend.ui.animalshortinfo

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AnimalShortRecyclerView : RecyclerView {
    companion object{
        const val SPAN_COUNT = 4
    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr)

    init {
        layoutManager = GridLayoutManager(context, SPAN_COUNT)
    }

    fun setAdapter(adapter: AnimalShortInfoAdapter) {
        this.adapter = adapter
    }
}