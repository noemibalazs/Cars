package com.noemi.android.cars.presentation.distance

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.noemi.android.cars.R
import com.noemi.android.cars.databinding.FragmentDistanceBinding
import com.noemi.android.cars.framework.adapter.CarAdapter
import com.noemi.android.cars.presentation.cars.CarViewModel
import com.noemi.android.cars.presentation.helper.DataManager
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.logger.KOIN_TAG

class FragmentDistance : Fragment() {

    private val carViewModel: CarViewModel by viewModel()
    private lateinit var binding: FragmentDistanceBinding
    private lateinit var carAdapter: CarAdapter
    private lateinit var locationManager: LocationManager
    private var location: Location? = null
    private val dataManager: DataManager by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_distance, container, false)

        locationManager = context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        activity?.let {
            if (ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return binding.root
            }
        }

        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            200,
            1f,
            MyLocationListener()
        )
        locationManager.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER,
            200,
            1f,
            MyLocationListener()
        )

        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

        if (location == null)
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

        if (location != null) {
            location?.let {
                dataManager.setLastKnownLatitude(it.latitude)
                dataManager.setLastKnowLongitude(it.longitude)
            }
        }

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
        carViewModel.showsCarListFromDB()

        carViewModel.mutableCarsFromDB.observe(viewLifecycleOwner, {
            carViewModel.carListFromDB(it)
        })

        carViewModel.mutableCarsBasedOnDistance.observe(viewLifecycleOwner, {
            carAdapter.submitList(it)
        })
    }

    inner class MyLocationListener : LocationListener {

        override fun onLocationChanged(location: Location) {
            val longitude = location.longitude
            val latitude = location.latitude
            Log.d(KOIN_TAG, "My location coordinates are lng: $longitude and lat: $latitude")
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        }
    }
}