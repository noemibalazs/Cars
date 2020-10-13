package com.noemi.android.cars.presentation.map

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.noemi.android.cars.R
import com.noemi.android.cars.databinding.FragmentMapBinding
import com.noemi.android.core.domain.Car
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import org.koin.core.logger.KOIN_TAG

class FragmentMap : Fragment(), OnMapReadyCallback {

    private val mapViewModel: MapViewModel by stateViewModel()
    private lateinit var binding: FragmentMapBinding
    private var cars = mutableListOf<Car>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
        initObservers()
    }

    private fun initBinding() {
        binding.viewModel = mapViewModel
        setUpMap()
    }

    private fun setUpMap() {
        activity?.let { activity ->
            val googleApiAvailable = GoogleApiAvailability.getInstance()
                .isGooglePlayServicesAvailable(activity) == ConnectionResult.SUCCESS

            if (googleApiAvailable) {
                val mapFragment =
                    childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
                if (mapFragment == null) {
                    val newMapFragment = SupportMapFragment.newInstance()
                    childFragmentManager.beginTransaction().replace(R.id.map, newMapFragment)
                        .commitAllowingStateLoss()
                    newMapFragment.getMapAsync(this)
                } else {
                    mapFragment.getMapAsync(this)
                }
            } else {
                Toast.makeText(activity, R.string.txt_error, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun initObservers() {
        mapViewModel.mutableCarList.observe(viewLifecycleOwner, {
            cars.addAll(it)
        })
    }

    override fun onMapReady(map: GoogleMap) {
        try {
            val success =
                map.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.style_json))
            if (success)
                Log.d(KOIN_TAG, "onMapReady style is successful!")
        } catch (e: Resources.NotFoundException) {
            Log.e(KOIN_TAG, "onMapReady exception: ${e.message}")
        }

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(54.66855, 25.23474), 12f))
        showCarsOnMap(cars, map)
    }

    private fun showCarsOnMap(cars: List<Car>, googleMap: GoogleMap) {
        cars.forEach { car ->
            val marker = googleMap.addMarker(
                MarkerOptions().position(
                    LatLng(
                        car.location.latitude,
                        car.location.longitude
                    )
                ).title(car.plateNumber)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
            )
            marker.showInfoWindow()
        }
    }
}