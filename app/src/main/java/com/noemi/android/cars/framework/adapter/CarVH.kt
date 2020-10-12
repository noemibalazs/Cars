package com.noemi.android.cars.framework.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.noemi.android.cars.R
import com.noemi.android.cars.databinding.ItemCarBinding
import com.noemi.android.cars.presentation.cars.CarViewModel
import com.noemi.android.core.domain.Car

class CarVH(
    private val binding: ItemCarBinding,
    private val carViewModel: CarViewModel
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(car: Car) {
        binding.viewModel = carViewModel
        binding.apply {
            Glide.with(binding.root.context).load(car.model.photoUrl).error(R.drawable.jaguar)
                .placeholder(R.drawable.jaguar).into(binding.ivCar)

            binding.tvCarModel.text = car.model.title
        }
    }
}