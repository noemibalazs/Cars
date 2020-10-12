package com.noemi.android.cars.framework.adapter

import androidx.recyclerview.widget.DiffUtil
import com.noemi.android.core.domain.Car

class CarDiffUtil : DiffUtil.ItemCallback<Car>() {
    override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean {
        return oldItem == newItem
    }
}