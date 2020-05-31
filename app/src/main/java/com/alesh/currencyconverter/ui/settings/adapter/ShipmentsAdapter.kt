package com.alesh.currencyconverter.ui.settings.adapter

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
import com.heliskycargo.R
import com.heliskycargo.domain.model.dto.Shipment
import com.heliskycargo.utils.toDateWithMonthWord
import com.heliskycargo.utils.view.setHeliImage

class ShipmentsAdapter(
    private val openDetails: (itemPosition: Int) -> Unit,
    private val getProgress: (pickupDate: String, expectedDate: String) -> Int
) : ListAdapter<Shipment, ShipmentsAdapter.ItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_shipment, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    inner class ItemViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        private val tvSerialNumber = item.findViewById(R.id.tvSerialNumber) as TextView
        private val ivHeliPhoto = item.findViewById(R.id.ivHeliPhoto) as ImageView
        private val tvHelicopterType = item.findViewById(R.id.tvHelicopterType) as TextView
        private val tvModeOfTransport = item.findViewById(R.id.tvModeOfTransport) as TextView
        private val tvPickupDate = item.findViewById(R.id.tvPickupDate) as TextView
        private val tvExpectedDeliveryDate =
            item.findViewById(R.id.tvExpectedDeliveryDate) as TextView
        private val sbTimeline = item.findViewById(R.id.sbTimeline) as SeekBar
        private val tvPickupCity = item.findViewById(R.id.tvPickupCity) as TextView
        private val tvPickupCountry = item.findViewById(R.id.tvPickupCountry) as TextView
        private val tvDeliveryCity = item.findViewById(R.id.tvDeliveryCity) as TextView
        private val tvDeliveryCountry = item.findViewById(R.id.tvDeliveryCountry) as TextView
        private val ivStatus = item.findViewById(R.id.ivStatus) as ImageView
        private val tvStatus = item.findViewById(R.id.tvStatus) as TextView

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

    class DiffCallback : DiffUtil.ItemCallback<Shipment>() {
        override fun areItemsTheSame(oldItem: Shipment, newItem: Shipment): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Shipment, newItem: Shipment): Boolean =
            oldItem == newItem
    }
}

