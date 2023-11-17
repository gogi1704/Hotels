package com.l_george.hotels.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.l_george.hotels.R
import com.l_george.hotels.app.HotelApp
import com.l_george.hotels.databinding.FragmentHotelsBinding
import com.l_george.hotels.ui.adapters.PeculiaritiesAdapter
import com.l_george.hotels.viewModels.HotelViewModel
import com.l_george.hotels.viewModels.HotelViewModelFactory

import javax.inject.Inject

class HotelsFragment : Fragment() {
    private lateinit var binding: FragmentHotelsBinding

    @Inject
    lateinit var vmFactory: HotelViewModelFactory
    private lateinit var viewModel: HotelViewModel
    private lateinit var adapter: PeculiaritiesAdapter
    private lateinit var recyclerPerculiarities: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().applicationContext as HotelApp).appComponent.inject(this)
        viewModel = ViewModelProvider(this, vmFactory)[HotelViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHotelsBinding.inflate(layoutInflater, container, false)

        viewModel.getHotel()

        with(binding) {
            recyclerPerculiarities = recyclerViewPer.apply {
                layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            }

            val carousel = carouselImages
            carousel.registerLifecycle(viewLifecycleOwner)



            viewModel.hotelLiveData.observe(viewLifecycleOwner) {
                if (it != null) {
                    adapter =
                        PeculiaritiesAdapter(it.about_the_hotel.peculiarities)
                    recyclerPerculiarities.adapter = adapter


                    textAdress.text = it.adress
                    textPrice.text = buildString {
                        append("oт ")
                        append(it.minimal_price)
                    }
                    textName.text = it.name
                    rating.text = it.rating.toString()
                    ratingName.text = it.rating_name
                    priceForIt.text = it.price_for_it
                    textDescription.text = it.about_the_hotel.description

                }
            }

            viewModel.hotelImagesLiveData.observe(viewLifecycleOwner) {
                carousel.addData(it)
            }

            buttonToRooms.setOnClickListener {
                findNavController().navigate(R.id.action_hotelsFragment_to_roomFragment)
            }
        }

        return binding.root
    }

}