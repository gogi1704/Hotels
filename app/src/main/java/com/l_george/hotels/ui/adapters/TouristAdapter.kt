package com.l_george.hotels.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.l_george.hotels.R
import com.l_george.hotels.databinding.TouristItemLayoutBinding
import com.l_george.hotels.domain.models.touristModel.CONTENT_TYPE_COUNTRY
import com.l_george.hotels.domain.models.touristModel.CONTENT_TYPE_DATE
import com.l_george.hotels.domain.models.touristModel.CONTENT_TYPE_NAME
import com.l_george.hotels.domain.models.touristModel.CONTENT_TYPE_PASSPORT_DATE
import com.l_george.hotels.domain.models.touristModel.CONTENT_TYPE_PASSPORT_NUM
import com.l_george.hotels.domain.models.touristModel.CONTENT_TYPE_SECOND_NAME
import com.l_george.hotels.domain.models.touristModel.TouristModel
import com.l_george.hotels.domain.models.touristModel.TouristViewType
import com.l_george.hotels.ui.adapters.callBacks.TouristCallBack

interface TouristClickListener {
    fun addTourist()
    fun open(itemId: Int, isOpen: Boolean)

    fun saveData(itemId: Int, contentType: String, content: String)
}

class TouristAdapter(private val listener: TouristClickListener) :
    ListAdapter<TouristModel, TouristAdapter.TouristViewHolder>(TouristCallBack()) {

    class TouristViewHolder(
        private val binding: TouristItemLayoutBinding,
        private val listener: TouristClickListener,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TouristModel) {

            with(binding) {
                if (item.isOpen) {
                    touristInfo.visibility = View.VISIBLE
                    buttonOpen.rotation = 180f
                } else {
                    buttonOpen.rotation = 0f
                    touristInfo.visibility = View.GONE
                }

                inputName.onFocusChangeListener =
                    OnFocusChangeListener { v, hasFocus ->
                        if (!hasFocus) {
                            listener.saveData(item.id, CONTENT_TYPE_NAME, inputName.text.toString())
                        }
                    }

                inputSecondName.onFocusChangeListener =
                    OnFocusChangeListener { v, hasFocus ->
                        if (!hasFocus) {
                            listener.saveData(
                                item.id,
                                CONTENT_TYPE_SECOND_NAME,
                                inputSecondName.text.toString()
                            )
                        }
                    }

                inputDate.onFocusChangeListener =
                    OnFocusChangeListener { v, hasFocus ->
                        if (!hasFocus) {
                            listener.saveData(item.id, CONTENT_TYPE_DATE, inputDate.text.toString())
                        }
                    }

                inputCountry.onFocusChangeListener =
                    OnFocusChangeListener { v, hasFocus ->
                        if (!hasFocus) {
                            listener.saveData(
                                item.id,
                                CONTENT_TYPE_COUNTRY,
                                inputCountry.text.toString()
                            )
                        }
                    }

                inputPasportNum.onFocusChangeListener =
                    OnFocusChangeListener { v, hasFocus ->
                        if (!hasFocus) {
                            listener.saveData(
                                item.id,
                                CONTENT_TYPE_PASSPORT_NUM,
                                inputPasportNum.text.toString()
                            )
                        }
                    }

                inputPasportDate.onFocusChangeListener =
                    OnFocusChangeListener { v, hasFocus ->
                        if (!hasFocus) {
                            listener.saveData(
                                item.id,
                                CONTENT_TYPE_PASSPORT_DATE,
                                inputPasportDate.text.toString()
                            )
                        }
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
                        inputCountry.setText(item.country)
                        inputPasportNum.setText(item.passportNum)
                        inputPasportDate.setText(item.passportDate)

                        if (item.isChecked) {
                            if (item.name.isEmpty()) {
                                inputName.error = "Поле не может быть пустым !"
                                nameInputLayout.setBoxBackgroundColorResource(R.color.text_bg_error)
                            } else {
                                nameInputLayout.setBoxBackgroundColorResource(R.color.text_bg_normal)
                                inputName.error = null
                            }

                            if (item.secondName.isEmpty()) {
                                inputSecondName.error = "Поле не может быть пустым !"
                                secondNameInputLayout.setBoxBackgroundColorResource(R.color.text_bg_error)

                            } else {
                                secondNameInputLayout.setBoxBackgroundColorResource(R.color.text_bg_normal)
                                inputSecondName.error = null
                            }

                            if (item.date.isEmpty()) {
                                dateInputLayout.setBoxBackgroundColorResource(R.color.text_bg_error)
                                inputDate.error = "Поле не может быть пустым !"
                            } else {
                                dateInputLayout.setBoxBackgroundColorResource(R.color.text_bg_normal)
                                inputDate.error = null
                            }

                            if (item.country.isEmpty()) {
                                countryInputLayout.setBoxBackgroundColorResource(R.color.text_bg_error)
                                inputCountry.error = "Поле не может быть пустым !"

                            } else {
                                inputCountry.error = null
                                countryInputLayout.setBoxBackgroundColorResource(R.color.text_bg_normal)

                            }

                            if (item.passportNum.isEmpty()) {
                                pasportNumInputLayout.setBoxBackgroundColorResource(R.color.text_bg_error)
                                inputPasportNum.error = "Поле не может быть пустым !"

                            } else {
                                pasportNumInputLayout.setBoxBackgroundColorResource(R.color.text_bg_normal)
                                inputPasportNum.error = null
                            }

                            if (item.passportDate.isEmpty()) {
                                pasportDateInputLayout.setBoxBackgroundColorResource(R.color.text_bg_error)
                                inputPasportDate.error = "Поле не может быть пустым !"
                            } else {
                                pasportDateInputLayout.setBoxBackgroundColorResource(R.color.text_bg_normal)
                                inputPasportDate.error = null
                            }
                        }
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