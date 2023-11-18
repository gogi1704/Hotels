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
import com.l_george.hotels.viewModels.reserveViewModel.ReserveViewModel
import com.l_george.hotels.viewModels.reserveViewModel.ReserveViewModelFactory
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.PredefinedSlots
import ru.tinkoff.decoro.watchers.MaskFormatWatcher
import javax.inject.Inject


class ReservationFragment : Fragment() {
    private lateinit var binding: FragmentReservationBinding
    @Inject lateinit var reserveViewModelFactory: ReserveViewModelFactory
    private lateinit var reserveViewModel: ReserveViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReservationBinding.inflate(layoutInflater, container, false)
        (requireActivity().applicationContext as HotelApp).appComponent.inject(this)
        reserveViewModel = ViewModelProvider(this , reserveViewModelFactory)[ReserveViewModel::class.java]

        val mask = MaskImpl.createTerminated(PredefinedSlots.RUS_PHONE_NUMBER)
        mask.placeholder='*'
        mask.isShowingEmptySlots = true
        val formatWatcher = MaskFormatWatcher(mask)

        with(binding) {
            formatWatcher.installOn(inputNumber)
            inputNumber.setText("+")

            inputEmail.addTextChangedListener {
                if (it.toString().length > 5 && !inputEmail.isEmailValid()) {
                    inputEmail.error = "invalid format"
                }
            }
        }


        return binding.root
    }


    private fun EditText.isEmailValid(): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(this.text.toString()).matches()
    }

}