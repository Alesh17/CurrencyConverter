package com.alesh.currencyconverter.ui.settings.adapter

import androidx.recyclerview.widget.DiffUtil
import com.alesh.currencyconverter.R
import com.alesh.currencyconverter.common.base.DataBindingAdapter
import com.alesh.currencyconverter.common.base.DataBindingViewHolder
import com.alesh.currencyconverter.ui.model.VoCurrency
import kotlinx.android.synthetic.main.view_holder_settings.view.*

class SettingsAdapter(
    private val addToFavorites: (currency: VoCurrency) -> Unit,
    private val removeFromFavorites: (currency: VoCurrency) -> Unit
) : DataBindingAdapter<VoCurrency>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<VoCurrency>() {

        override fun areItemsTheSame(oldItem: VoCurrency, newItem: VoCurrency) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: VoCurrency, newItem: VoCurrency) =
            oldItem.isFavorite == newItem.isFavorite
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder<VoCurrency>, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.binding.root.mySwitch.setOnClickListener {
            val currentItem = currentList[position]
            if (currentItem.isFavorite) removeFromFavorites(currentItem)
            else addToFavorites(currentItem)
            currentList[position].isFavorite = currentItem.isFavorite.not()
        }
    }

    override fun getItemViewType(position: Int) = R.layout.view_holder_settings
}