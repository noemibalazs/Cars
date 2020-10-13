package com.noemi.android.cars.presentation.battery

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.noemi.android.cars.R
import com.noemi.android.cars.databinding.FragmentByBatteryBinding
import com.noemi.android.cars.framework.adapter.CarAdapter
import com.noemi.android.cars.presentation.cars.CarViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentBattery : Fragment() {

    private val carViewModel: CarViewModel by viewModel()
    private lateinit var binding: FragmentByBatteryBinding
    private lateinit var carAdapter: CarAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_by_battery, container, false)
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
        binding.rvBatteryPercentage.adapter = carAdapter
    }

    private fun initObservers() {
        carViewModel.mutableBatteryFilteredCars.observe(viewLifecycleOwner, {
            carAdapter.submitList(null)
            carAdapter.submitList(it)
            showOrHideErrorLayout(it.isNullOrEmpty())
            hideSoftKeyBoard()
        })
    }

    private fun showOrHideErrorLayout(show: Boolean) {
        binding.rvBatteryPercentage.isVisible = !show
        binding.clEmptyContainer.isVisible = show
    }

    private fun hideSoftKeyBoard() {
        val inputMethodManager =
            activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.etBatteryPercentage.windowToken, 0)
    }
}