package com.danycarreto.movieapp.cinemas

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.danycarreto.movieapp.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class CinemaActivity : AppCompatActivity(), OnMapReadyCallback {


    private lateinit var fragmentMap: SupportMapFragment
    private lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cinema)
        fragmentMap = supportFragmentManager
            .findFragmentById(R.id.mapCinema) as SupportMapFragment
        fragmentMap.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        loadCinemas()
    }

    fun loadCinemas(){
        val cinemexReal = LatLng(19.4364687,-99.149468)
        val cinemexVallejo = LatLng(19.468312,-99.1470277)
        val cinemexDelta = LatLng(19.4028844,-99.155805)

        googleMap.addMarker(
            MarkerOptions()
                .position(cinemexReal)
                .title("Cinemex Real")
                .draggable(true)
        )
        googleMap.addMarker(
            MarkerOptions()
                .position(cinemexVallejo)
                .title("Cinemex Vallejo")
        )
        googleMap.addMarker(
            MarkerOptions()
                .position(cinemexDelta)
                .title("Cinemex Delta")
        )
        googleMap.moveCamera(CameraUpdateFactory
            .newLatLngZoom(cinemexDelta, 12f))
        getLocationPermission()

    }

    fun getLocationPermission(){
        if(ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
        == PackageManager.PERMISSION_GRANTED){
            updateLocationUI()
        }else{
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                100
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            100->{
                if(grantResults.isNotEmpty()){
                    updateLocationUI()
                }
            }
        }

    }

    fun updateLocationUI(){
        googleMap.isTrafficEnabled = true
        googleMap.isMyLocationEnabled = true
        googleMap.uiSettings.isMyLocationButtonEnabled = true
    }

}
