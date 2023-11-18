package com.l_george.hotels.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.l_george.hotels.app.HotelApp
import com.l_george.hotels.databinding.FragmentRoomBinding
import com.l_george.hotels.viewModels.roomViewModel.RoomViewModel
import com.l_george.hotels.viewModels.roomViewModel.RoomViewModelFactory
import javax.inject.Inject

class RoomFragment : Fragment() {
    private lateinit var binding: FragmentRoomBinding
    private lateinit var roomViewModel: RoomViewModel
    @Inject
    lateinit var viewModelFactory: RoomViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRoomBinding.inflate(layoutInflater, container, false)
        (requireActivity().applicationContext as HotelApp).appComponent.inject(this)

        roomViewModel = ViewModelProvider(owner = this, viewModelFactory)[RoomViewModel::class.java]



        return binding.root
    }


}