package com.l_george.hotels.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.l_george.hotels.R
import com.l_george.hotels.databinding.TouristItemLayoutBinding
import com.l_george.hotels.domain.models.touristModel.TouristModel
import com.l_george.hotels.domain.models.touristModel.TouristViewType
import com.l_george.hotels.ui.adapters.callBacks.TouristCallBack

interface TouristClickListener {
    fun addTourist()
    fun open(itemId: Int, isOpen: Boolean)
}

class TouristAdapter(private val listener: TouristClickListener) :
    ListAdapter<TouristModel, TouristAdapter.TouristViewHolder>(TouristCallBack()) {

    class TouristViewHolder(
        private val binding: TouristItemLayoutBinding,
        private val listener: TouristClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TouristModel) {
            with(binding) {
                if (item.isOpen) {
                    touristInfo.visibility = View.VISIBLE
                } else {
                    touristInfo.visibility = View.GONE
                }


                when (item.typeView) {
                    TouristViewType.TypeAddTourist -> {
                        textTouristNumber.text = "Добавить туриста"
                        buttonOpen.setImageResource(R.drawable.button_add)
                        buttonOpen.setOnClickListener {
                            listener.addTourist()
                        }
                    }

                    TouristViewType.TypeTourist -> {
                        textTouristNumber.text = getTouristNumberById(item.id)
                        buttonOpen.setOnClickListener {
                            listener.open(item.id, item.isOpen)
                        }
                        touristCard.setOnClickListener {
                            listener.open(item.id, item.isOpen)
                        }

                        inputName.setText(item.name)
                        inputSecondName.setText(item.secondName)
                        inputDate.setText(item.date)
                        inputPasportNum.setText(item.passportNum)
                        inputPasportDate.setText(item.passportDate)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TouristViewHolder {
        val binding =
            TouristItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TouristViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: TouristViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

private fun getTouristNumberById(id: Int): String {
    return when (id) {
        0 -> {
            "Первый турист"
        }

        1 -> {
            "Второй турист"
        }

        2 -> {
            "Третий турист"
        }

        3 -> {
            "Четвертый турист"
        }

        4 -> {
            "Пятый турист"
        }

        5 -> {
            "Шестой турист"
        }

        6 -> {
            "Седьмой турист"
        }

        7 -> {
            "Восьмой турист"
        }

        else -> {
            "Может хватит?"
        }
    }
}