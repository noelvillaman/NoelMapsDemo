package com.software.namalliv.noelmapsdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.software.namalliv.noelmapsdemo.databinding.ActivityMapsBinding
import java.lang.Exception

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private val losAngeles = LatLng(34.04692127928215, -118.24748421830992)
    private val duarteMetroSD = LatLng(18.483206099709005, -69.91415440992316)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        mMap.addMarker(MarkerOptions().position(duarteMetroSD).title("Parada Metro Duarte"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(duarteMetroSD, 13f))
        mMap.uiSettings.apply {
            isTiltGesturesEnabled = true
        }
        changeMapStyle(mMap)
    }

    private fun changeMapStyle(googleMap: GoogleMap){
        try {
            val success = googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    this,
                    R.raw.style
                )
            )
            if (!success){
                Log.d("TAG", "Failed to add style")
            }
        } catch (e: Exception){
            Log.d("TAG", e.toString())
        }
    }
}