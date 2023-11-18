package com.l_george.hotels.ui.adapters.callBacks

import androidx.recyclerview.widget.DiffUtil
import com.l_george.hotels.domain.models.roomModel.RoomModel

class RoomCallBack : DiffUtil.ItemCallback<RoomModel>() {
    override fun areItemsTheSame(oldItem: RoomModel, newItem: RoomModel): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: RoomModel, newItem: RoomModel): Boolean =
        oldItem == newItem
}