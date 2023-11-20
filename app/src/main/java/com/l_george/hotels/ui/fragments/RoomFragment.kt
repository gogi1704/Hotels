package com.l_george.hotels.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.l_george.hotels.R
import com.l_george.hotels.app.HotelApp
import com.l_george.hotels.databinding.FragmentRoomBinding
import com.l_george.hotels.ui.adapters.RoomAdapter
import com.l_george.hotels.ui.adapters.RoomClickListener
import com.l_george.hotels.viewModels.roomViewModel.RoomViewModel
import com.l_george.hotels.viewModels.roomViewModel.RoomViewModelFactory
import javax.inject.Inject

class RoomFragment : Fragment() {
    private lateinit var binding: FragmentRoomBinding
    private lateinit var roomViewModel: RoomViewModel
    private lateinit var roomsRecyclerView: RecyclerView
    private lateinit var roomAdapter: RoomAdapter
    @Inject
    lateinit var viewModelFactory: RoomViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (requireActivity().applicationContext as HotelApp).appComponent.inject(this)
        roomViewModel = ViewModelProvider(owner = this, viewModelFactory)[RoomViewModel::class.java]
        roomAdapter = RoomAdapter(object : RoomClickListener {
            override fun openRoom(roomId: Int) {
                findNavController().navigate(R.id.action_roomFragment_to_reservationFragment)

            }
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRoomBinding.inflate(layoutInflater, container, false)
        val hotelName = arguments?.getString("name_hotel")

        with(binding) {
            textMainTitle.text = hotelName

            roomsRecyclerView = recyclerRooms.apply {
                adapter = roomAdapter
            }

            roomViewModel.listRoomsLiveData.observe(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    roomAdapter.submitList(it)
                }
            }

            roomViewModel.errorLiveData.observe(viewLifecycleOwner){
                if (it!=null){
                    Toast.makeText(requireContext() , it.message , Toast.LENGTH_SHORT).show()
                }
            }

            roomViewModel.progressState.observe(viewLifecycleOwner){
                progress.visibility = if (it)View.VISIBLE else View.GONE
            }

            buttonBack.setOnClickListener {
                findNavController().navigateUp()
            }

        }



        return binding.root
    }


}