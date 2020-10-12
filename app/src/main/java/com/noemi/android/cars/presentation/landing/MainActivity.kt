package com.noemi.android.cars.presentation.landing

import android.Manifest
import android.app.AlertDialog
import android.app.Dialog
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.noemi.android.cars.R
import com.noemi.android.cars.presentation.battery.FragmentBattery
import com.noemi.android.cars.presentation.cars.FragmentCars
import com.noemi.android.cars.presentation.distance.FragmentDistance
import com.noemi.android.cars.presentation.map.FragmentMap
import com.noemi.android.cars.presentation.plate.FragmentPlateNumber
import com.noemi.android.cars.presentation.util.PERMISSION_CODE

import kotlinx.android.synthetic.main.activity_main.*
import org.koin.core.logger.KOIN_TAG

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checksForPermissions()

        val mapFragment = FragmentMap()
        val carsFragment = FragmentCars()
        val plateFragment = FragmentPlateNumber()
        val batteryFragment = FragmentBattery()
        val distanceFragment = FragmentDistance()

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {

                R.id.navigation_cars -> {
                    setFragment(carsFragment)
                    true
                }

                R.id.navigation_map -> {
                    setFragment(mapFragment)
                    true
                }

                R.id.navigation_sort_by_plate -> {
                    setFragment(plateFragment)
                    true
                }

                R.id.navigation_sort_by_battery -> {
                    setFragment(batteryFragment)
                    true
                }

                R.id.navigation_sort_by_distance -> {
                    setFragment(distanceFragment)
                    true
                }

                else -> false
            }
        }

        bottom_navigation.selectedItemId = R.id.navigation_cars
    }

    private fun checksForPermissions() {
        if (!isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION) ||
            !isPermissionGranted(Manifest.permission.ACCESS_COARSE_LOCATION)
        ) {
            requestPermissions()
        }
    }

    private fun isPermissionGranted(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions() {
        val permissionArray = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        ActivityCompat.requestPermissions(
            this,
            permissionArray,
            PERMISSION_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(KOIN_TAG, "Permission is granted!")
            } else {
                informUserPermissionsAreNecessary()
            }
        }
    }

    private fun informUserPermissionsAreNecessary() {
        val dialog = AlertDialog.Builder(this).create()
        dialog.setTitle(getString(R.string.txt_permission_are_necessary_title))
        dialog.setMessage(getString(R.string.txt_permission_are_necessary_body))
        dialog.setButton(
            Dialog.BUTTON_POSITIVE,
            getString(R.string.txt_alert_ok)
        ) { _, _ -> requestPermissions() }
        dialog.show()
    }

    private fun setFragment(fragment: Fragment) {
        val transition = supportFragmentManager.beginTransaction()
        transition.replace(R.id.frame, fragment).commit()
    }
}


