package com.l_george.hotels.ui.adapters.callBacks

import androidx.recyclerview.widget.DiffUtil
import com.l_george.hotels.domain.models.touristModel.TouristModel

class TouristCallBack : DiffUtil.ItemCallback<TouristModel>() {
    override fun areItemsTheSame(oldItem: TouristModel, newItem: TouristModel): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: TouristModel, newItem: TouristModel): Boolean =
        oldItem == newItem
}