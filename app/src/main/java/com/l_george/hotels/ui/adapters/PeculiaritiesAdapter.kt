package com.l_george.hotels.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.l_george.hotels.databinding.PeculiaritiesItemLayoutBinding

class PeculiaritiesAdapter(private val list: List<String>) :
    RecyclerView.Adapter<PeculiaritiesAdapter.PeculiaritiesViewHolder>() {

    class PeculiaritiesViewHolder(private val binding: PeculiaritiesItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.peculiaritiesTitle.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeculiaritiesViewHolder {
        val binding = PeculiaritiesItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PeculiaritiesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PeculiaritiesViewHolder, position: Int) {

        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}