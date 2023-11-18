package com.l_george.hotels.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.l_george.hotels.databinding.RoomItemLayoutBinding
import com.l_george.hotels.domain.models.roomModel.RoomModel
import com.l_george.hotels.ui.adapters.callBacks.RoomCallBack
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

interface RoomClickListener {
    fun openRoom(roomId: Int)
}

class RoomAdapter(private val listener: RoomClickListener) :
    ListAdapter<RoomModel, RoomAdapter.RoomViewHolder>(RoomCallBack()) {

    class RoomViewHolder(
        private val binding: RoomItemLayoutBinding,
        private val listener: RoomClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RoomModel) {
            with(binding) {
                carouselImages.setData(getCarouselItem(item.image_urls))
                val rec = recyclerViewPer.apply {
                    layoutManager =
                        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                }
                rec.adapter = PeculiaritiesAdapter(item.peculiarities)

                textName.text = item.name
                textPrice.text = item.price.toString()
                priceForIt.text = item.price_per
                buttonOpenRoom.setOnClickListener {
                    listener.openRoom(item.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val binding =
            RoomItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoomViewHolder(binding , listener)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}

fun getCarouselItem(urls: List<String>): List<CarouselItem> {
    return urls.map { CarouselItem(it) }
}