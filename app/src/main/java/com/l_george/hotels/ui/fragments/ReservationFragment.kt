package com.l_george.hotels.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.l_george.hotels.R
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
    private lateinit var mask: MaskImpl
    private lateinit var formatWatcher: MaskFormatWatcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().applicationContext as HotelApp).appComponent.inject(this)
        reserveViewModel =
            ViewModelProvider(this, reserveViewModelFactory)[ReserveViewModel::class.java]


        mask = MaskImpl.createTerminated(PredefinedSlots.RUS_PHONE_NUMBER).apply {
            placeholder = '*'
            isShowingEmptySlots = true
        }
        formatWatcher = MaskFormatWatcher(mask)

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
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReservationBinding.inflate(layoutInflater, container, false)

        with(binding) {
            formatWatcher.installOn(inputNumber)
            inputNumber.setText("+")
            binding.recyclerTourist.adapter = adapter

            inputEmail.onFocusChangeListener =
                View.OnFocusChangeListener { v, hasFocus ->
                    if (!hasFocus && !inputEmail.isEmailValid()) {
                        inputEmail.error = "проверьте валидность данных"
                        emailInputLayout.setBoxBackgroundColorResource(R.color.text_bg_error)
                    }else{
                        emailInputLayout.setBoxBackgroundColorResource(R.color.text_bg_normal)
                    }

                }

            inputNumber.onFocusChangeListener =
                View.OnFocusChangeListener { v, hasFocus ->
                    if (!hasFocus && !inputNumber.isPhoneValid()) {
                        inputNumber.error = "проверьте валидность данных"
                        numberInputLayout.setBoxBackgroundColorResource(R.color.text_bg_error)
                    }else{
                        numberInputLayout.setBoxBackgroundColorResource(R.color.text_bg_normal)
                    }
                }

            buttonComplete.setOnClickListener {
                clearFocusFromChildren(recyclerTourist)
                clearFocusFromChildren(byuerInfoBlock)
                if (inputNumber.text.toString().isEmpty() || !inputNumber.isPhoneValid()) {
                    inputNumber.error = "проверьте валидность данных"
                    numberInputLayout.setBoxBackgroundColorResource(R.color.text_bg_error)
                }else{
                    numberInputLayout.setBoxBackgroundColorResource(R.color.text_bg_normal)
                }

                if (inputEmail.text.toString().isEmpty() || !inputEmail.isEmailValid()) {
                    inputEmail.error = "проверьте валидность данных"
                    emailInputLayout.setBoxBackgroundColorResource(R.color.text_bg_error)
                }else{
                    emailInputLayout.setBoxBackgroundColorResource(R.color.text_bg_normal)
                }
                reserveViewModel.checkAll()
            }

            buttonBack.setOnClickListener {
                findNavController().navigateUp()
            }

            reserveViewModel.errorLiveData.observe(viewLifecycleOwner) {
                if (it != null) {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }


            reserveViewModel.listTouristLiveData.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }

            reserveViewModel.reserveModelLiveData.observe(viewLifecycleOwner) {
                if (it != null) {
                    fillScreenData(it)
                }
            }

            reserveViewModel.reservedCompletedMutableLiveData.observe(viewLifecycleOwner) {
                if (it != null) {
                    if (it && inputNumber.isPhoneValid() && inputEmail.isEmailValid()) {
                        findNavController().navigate(R.id.action_reservationFragment_to_completeFragment)
                        reserveViewModel.reset()
                    } else {
                        Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT)
                            .show()
                    }
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
            buttonComplete.text = StringBuilder()
                .append("Оплатить")
                .append(" ")
                .append(fullPrice.toString())
        }
    }


    private fun EditText.isEmailValid(): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(this.text.toString()).matches()
    }

    private fun EditText.isPhoneValid(): Boolean {
        return android.util.Patterns.PHONE.matcher(this.text.toString()).matches()
    }

}