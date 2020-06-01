package com.alesh.currencyconverter.ui.currencies.adapter

import android.util.Log
import androidx.core.widget.doBeforeTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.DiffUtil
import com.alesh.currencyconverter.R
import com.alesh.currencyconverter.common.base.DataBindingAdapter
import com.alesh.currencyconverter.common.base.DataBindingViewHolder
import com.alesh.currencyconverter.ui.currencies.adapter.model.VoCurrency
import kotlinx.android.synthetic.main.view_holder_currency.view.*

class CurrenciesAdapter(
    private val calculate: (currency: VoCurrency, inputValue: String) -> Unit
) : DataBindingAdapter<VoCurrency>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<VoCurrency>() {

        override fun areItemsTheSame(oldItem: VoCurrency, newItem: VoCurrency) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: VoCurrency, newItem: VoCurrency) =
            oldItem.abbreviation == newItem.abbreviation
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder<VoCurrency>, position: Int) {
        super.onBindViewHolder(holder, position)

        val etValue = holder.binding.root.etValue

        etValue.doOnTextChanged { text, _, _, _ ->
            if (etValue.hasFocus() /*&& etValue.text.toString() != text.toString()*/) {
                calculate(currentList[position], text.toString())
            }
        }
    }

    override fun getItemViewType(position: Int) = R.layout.view_holder_currency
}