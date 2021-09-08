package com.plugin.pengaduandesa

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.plugin.pengaduandesa.databinding.ActivityPickLocationBinding

class PickLocationActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMapClickListener {
    private lateinit var binding : ActivityPickLocationBinding
    private lateinit var mapFragment: SupportMapFragment
    private lateinit var googleMap: GoogleMap
    private lateinit var myLocation : Location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPickLocationBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)
        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync({
            googleMap = it
            googleMap?.setOnMapClickListener(this@PickLocationActivity)
        })
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this@PickLocationActivity)
        getLocation()
    }

    override fun onMapReady(p0: GoogleMap) {
        googleMap = p0
        if(ActivityCompat.checkSelfPermission(
                this@PickLocationActivity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this@PickLocationActivity,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ){
            return
        }

        googleMap!!.isMyLocationEnabled = true
    }

    @SuppressLint("MissingPermission")
    private fun getLocation(){
        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location : Location? ->
            location?.let{ currentLocation ->
                val myCurrentLocation = LatLng(currentLocation.latitude, currentLocation.longitude)
                googleMap.addMarker(MarkerOptions().position(myCurrentLocation).title("Location"))
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myCurrentLocation, 15f));

            }
        }
    }


    override fun onMapClick(p0: LatLng) {
        val marker = MarkerOptions().position(LatLng(p0!!.latitude, p0.longitude))
        googleMap.clear()
        googleMap.addMarker(marker)

        showAlertDialog("Pick this location ?", p0)

    }

    private fun showAlertDialog(message : String, latLng: LatLng){
        AlertDialog.Builder(this@PickLocationActivity).apply {
            setMessage(message)
            setPositiveButton("OK"){ d, _ ->
                val returnIntent = Intent(this@PickLocationActivity, CreatePengaduanActivity::class.java).apply {
                    putExtra("LATITUDE", latLng.latitude.toString())
                    putExtra("LONGITUDE", latLng.longitude.toString())
                }
                setResult(Activity.RESULT_OK, returnIntent)
                finish()
            }
            setNegativeButton("No"){d, _ ->
                d.cancel()
            }
        }.show()
    }
}