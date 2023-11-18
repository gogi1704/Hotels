package com.l_george.hotels.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.l_george.hotels.databinding.FragmentReservationBinding
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.PredefinedSlots
import ru.tinkoff.decoro.watchers.MaskFormatWatcher


class ReservationFragment : Fragment() {
    private lateinit var binding: FragmentReservationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReservationBinding.inflate(layoutInflater, container, false)

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