package com.example.recipes.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.recipes.R
import com.example.recipes.databinding.ActivityRecipeMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class RecipeMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityRecipeMapBinding

    private lateinit var map: GoogleMap

    private lateinit var location: DoubleArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        location = intent.getDoubleArrayExtra(RECIPE_LOCATION_EXTRA_NAME) as DoubleArray

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        createMarker()
    }

    private fun createMarker() {
        val coordinates = LatLng(location[0], location[1])
        map.addMarker(MarkerOptions().position(coordinates))
        map.moveCamera(CameraUpdateFactory.newLatLng(coordinates))

    }

    companion object {
        const val RECIPE_LOCATION_EXTRA_NAME = "LOCATION_EXTRA_NAME"
    }
}