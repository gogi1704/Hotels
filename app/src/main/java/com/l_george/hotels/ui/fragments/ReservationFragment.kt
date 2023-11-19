package com.l_george.hotels.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.l_george.hotels.app.HotelApp
import com.l_george.hotels.databinding.FragmentReservationBinding
import com.l_george.hotels.domain.models.reserveModel.ReserveModel
import com.l_george.hotels.ui.adapters.TouristAdapter
import com.l_george.hotels.ui.adapters.TouristClickListener
import com.l_george.hotels.viewModels.reserveViewModel.ReserveViewModel
import com.l_george.hotels.viewModels.reserveViewModel.ReserveViewModelFactory
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.PredefinedSlots
import ru.tinkoff.decoro.watchers.MaskFormatWatcher
import javax.inject.Inject


class ReservationFragment : Fragment() {
    private lateinit var binding: FragmentReservationBinding

    @Inject
    lateinit var reserveViewModelFactory: ReserveViewModelFactory
    private lateinit var reserveViewModel: ReserveViewModel
    private lateinit var adapter: TouristAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReservationBinding.inflate(layoutInflater, container, false)
        (requireActivity().applicationContext as HotelApp).appComponent.inject(this)
        reserveViewModel =
            ViewModelProvider(this, reserveViewModelFactory)[ReserveViewModel::class.java]

        val mask = MaskImpl.createTerminated(PredefinedSlots.RUS_PHONE_NUMBER).apply {
            placeholder = '*'
            isShowingEmptySlots = true
        }
        val formatWatcher = MaskFormatWatcher(mask)

        adapter = TouristAdapter(object : TouristClickListener {
            override fun addTourist() {
                reserveViewModel.addNewTourist()
            }

            override fun open(itemId: Int, isOpen: Boolean) {
                clearFocusFromChildren(binding.recyclerTourist)
                reserveViewModel.openTouristItem(itemId, isOpen)
            }

            override fun saveData(itemId: Int, contentType: String, content: String) {
                reserveViewModel.saveDate(itemId, contentType, content)
            }
        })

        with(binding) {
            formatWatcher.installOn(inputNumber)
            inputNumber.setText("+")
            binding.recyclerTourist.adapter = adapter

            inputEmail.addTextChangedListener {
                if (it.toString().length > 5 && !inputEmail.isEmailValid()) {
                    inputEmail.error = "invalid format"
                }
            }

            buttonComplete.setOnClickListener {
                clearFocusFromChildren(recyclerTourist)
                reserveViewModel.checkAll()
            }


            reserveViewModel.listTouristLiveData.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }

            reserveViewModel.reserveModelLiveData.observe(viewLifecycleOwner) {
                if (it != null) {
                    fillScreenData(it)
                }
            }
        }


        return binding.root
    }

    private fun clearFocusFromChildren(view: View) {
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                clearFocusFromChildren(view.getChildAt(i))
            }
        } else (view as? EditText)?.clearFocus()
    }

    private fun fillScreenData(it: ReserveModel) {
        val fullPrice = it.fuel_charge + it.service_charge + it.tour_price
        with(binding) {
            rating.text = it.horating.toString()
            ratingName.text = it.rating_name
            textName.text = it.hotel_name
            textAdress.text = it.hotel_adress
            departureInput.text = it.departure
            cityInput.text = it.arrival_country
            nightsInput.text = it.number_of_nights.toString()
            hotelInput.text = it.hotel_name
            roomInput.text = it.room
            nutritionInput.text = it.nutrition
            dateInput.text = buildString {
                append(it.tour_date_start)
                append("-")
                append(it.tour_date_stop)
            }

            textTourInput.text = it.tour_price.toString()
            textFuelPriceInput.text = it.fuel_charge.toString()
            textServicePriceInput.text = it.service_charge.toString()
            textFinalPriceInput.text = fullPrice.toString()
        }
    }


    private fun EditText.isEmailValid(): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(this.text.toString()).matches()
    }

}