package com.alesh.currencyconverter.ui.currencies.adapter

import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.DiffUtil
import com.alesh.currencyconverter.R
import com.alesh.currencyconverter.common.base.DataBindingAdapter
import com.alesh.currencyconverter.common.base.DataBindingViewHolder
import com.alesh.currencyconverter.ui.model.VoCurrency
import com.alesh.currencyconverter.widget.CurrencyEditText
import kotlinx.android.synthetic.main.view_holder_currency.view.*

class CurrenciesAdapter(
    private val calculate: (currency: VoCurrency) -> Unit
) : DataBindingAdapter<VoCurrency>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<VoCurrency>() {

        override fun areItemsTheSame(oldItem: VoCurrency, newItem: VoCurrency) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: VoCurrency, newItem: VoCurrency) =
            oldItem.abbreviation == newItem.abbreviation
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder<VoCurrency>, position: Int) {
        super.onBindViewHolder(holder, position)
        val etValue = holder.binding.root.etValue as CurrencyEditText
        etValue.doOnTextChanged { text, _, _, _ ->
            currentList[position]
                .takeIf { etValue.hasFocus() }
                ?.also { it.value = text.toString() }
                ?.let { calculate(it) }
        }
    }

    override fun getItemViewType(position: Int) = R.layout.view_holder_currency
}