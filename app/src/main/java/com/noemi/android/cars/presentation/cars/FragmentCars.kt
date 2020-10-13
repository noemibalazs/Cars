package com.noemi.android.cars.presentation.cars

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.noemi.android.cars.R
import com.noemi.android.cars.databinding.FragmentCarsBinding
import com.noemi.android.cars.framework.adapter.CarAdapter
import com.noemi.android.cars.presentation.util.CARS_LIST_SAVED_STATE
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class FragmentCars : Fragment() {

    private val carViewModel: CarViewModel by stateViewModel()
    private lateinit var binding: FragmentCarsBinding
    private lateinit var carAdapter: CarAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cars, container, false)
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
        binding.rvCars.adapter = carAdapter
    }

    private fun initObservers() {
        val savedState = carViewModel.handle.contains(CARS_LIST_SAVED_STATE)

        if (!savedState) {
            carViewModel.carList.observe(viewLifecycleOwner, {
                carAdapter.submitList(it)
                carViewModel.addCars2DB(it)
                carViewModel.saveCarsDueConfigurationChanges(it)
            })
        } else {
            carViewModel.getCarsLiveData().observe(viewLifecycleOwner, {
                carAdapter.submitList(null)
                carAdapter.submitList(it)
            })
        }


        carViewModel.failureError.observe(viewLifecycleOwner, {
            showErrorMessage()
        })

        carViewModel.loading.observe(viewLifecycleOwner, {
            binding.pbCars.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    private fun showErrorMessage() {
        context?.let {
            Toast.makeText(it, R.string.txt_error, Toast.LENGTH_LONG).show()
        }
    }
}