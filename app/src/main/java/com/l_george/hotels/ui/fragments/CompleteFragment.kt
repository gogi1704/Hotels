package com.l_george.hotels.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.l_george.hotels.R
import com.l_george.hotels.databinding.FragmentCompleteBinding
import kotlin.random.Random

class CompleteFragment : Fragment() {
    private lateinit var binding: FragmentCompleteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompleteBinding.inflate(layoutInflater, container, false)

        with(binding) {
            textReservNum.text = StringBuilder()
                .append( requireActivity().getString(R.string.text_reserv_part1))
                .append(Random.nextInt(100_000, 1_000_000))
                .append(" ")
                .append(requireActivity().getString(R.string.text_reserv_part2))

            buttonBack.setOnClickListener {
                findNavController().navigateUp()
            }

            buttonComplete.setOnClickListener {
                findNavController().navigate(
                    R.id.action_completeFragment_to_hotelsFragment,null,
                    NavOptions.Builder()
                        .setEnterAnim(R.anim.in_right)
                        .setExitAnim(R.anim.out_left)
                        .setPopEnterAnim(R.anim.in_left)
                        .setPopExitAnim(R.anim.out_right)
                        .setPopUpTo(R.id.hotelsFragment, true).build()
                )

            }
        }

        return binding.root
    }

}