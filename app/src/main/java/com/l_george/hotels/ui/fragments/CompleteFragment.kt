package com.l_george.hotels.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.l_george.hotels.databinding.FragmentCompleteBinding

class CompleteFragment : Fragment() {
    private lateinit var binding: FragmentCompleteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompleteBinding.inflate(layoutInflater , container , false)
        return binding.root
    }

}