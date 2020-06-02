package com.alesh.currencyconverter.ui.settings.adapter

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class DragManageAdapter(
    private val adapter: SettingsAdapter,
    dragDirs: Int,
    swipeDirs: Int
) : ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        adapter.swapItems(viewHolder.adapterPosition, target.adapterPosition)
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}
}