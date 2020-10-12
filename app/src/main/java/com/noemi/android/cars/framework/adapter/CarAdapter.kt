package com.noemi.android.cars.framework.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.noemi.android.cars.R
import com.noemi.android.cars.databinding.ItemCarBinding
import com.noemi.android.cars.presentation.cars.CarViewModel
import com.noemi.android.core.domain.Car

class CarAdapter(
    private val carViewModel: CarViewModel
) : ListAdapter<Car, CarVH>(CarDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarVH {
        val binding: ItemCarBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_car,
            parent,
            false
        )
        return CarVH(binding, carViewModel)
    }

    override fun onBindViewHolder(holder: CarVH, position: Int) {
        holder.onBind(getItem(position))
    }
}