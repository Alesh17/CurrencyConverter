package com.alesh.currencyconverter.ui.currencies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alesh.currencyconverter.R
import com.alesh.domain.model.dto.Currency

class CurrenciesAdapter(): ListAdapter<Currency, CurrenciesAdapter.ItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_currency, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    inner class ItemViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        private val tvSerialNumber = item.findViewById(R.id.tvSerialNumber) as TextView
        private val ivHeliPhoto = item.findViewById(R.id.ivHeliPhoto) as ImageView

        fun bind(item: Shipment, position: Int) = with(itemView) {

            sbTimeline.isEnabled = false
            tvSerialNumber.isEnabled = false

            tvSerialNumber.text = "#${item.serialNumber.toString()}"
            tvHelicopterType.text = item.helicopterType
            tvModeOfTransport.text = item.modeOfTransport
            tvPickupDate.text = item.pickupDate!!.toDateWithMonthWord()
            tvExpectedDeliveryDate.text = item.expectedDeliveryDate!!.toDateWithMonthWord()
            tvPickupCity.text = item.pickupLocationCity
            tvPickupCountry.text = item.pickupLocationCountry
            tvDeliveryCity.text = item.deliveryLocationCity
            tvDeliveryCountry.text = item.deliveryLocationCountry

            ivHeliPhoto.setHeliImage(itemView.context, item.image)
            sbTimeline.progress = getProgress(
                item.pickupDate!!,
                item.expectedDeliveryDate!!
            )

            if (item.status!!) {
                tvStatus.text = context.getString(R.string.delivered)
                TextViewCompat.setTextAppearance(tvStatus, R.style.StatusDoneStyle)
                ivStatus.setImageResource(R.drawable.ic_status_done)
            }

            setOnClickListener { openDetails(position) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Currency>() {
        override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean =
            oldItem == newItem
    }
}

