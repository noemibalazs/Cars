package com.noemi.android.cars.presentation.distance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.noemi.android.cars.R
import com.noemi.android.cars.databinding.FragmentDistanceBinding
import com.noemi.android.cars.framework.adapter.CarAdapter
import com.noemi.android.cars.presentation.cars.CarViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentDistance : Fragment() {

    private val carViewModel: CarViewModel by viewModel()
    private lateinit var binding: FragmentDistanceBinding
    private lateinit var carAdapter: CarAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_distance, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBinding()
        initObservers()
    }

    private fun initBinding() {
        binding.viewModel = carViewModel
        carAdapter = CarAdapter(carViewModel)
        binding.rvDistance.adapter = carAdapter
    }

    private fun initObservers() {

    }
}