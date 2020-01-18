package com.danycarreto.movieapp.cinemas

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.net.Uri
import android.os.Bundle
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.danycarreto.movieapp.R
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_cinema.*

class CinemaActivity : AppCompatActivity(), OnMapReadyCallback,
    GoogleApiClient.ConnectionCallbacks, LocationListener,
    GoogleMap.OnMarkerClickListener {

    override fun onMarkerClick(marker: Marker?): Boolean {
        println(marker?.position?.latitude)
        println(marker?.position?.longitude)
        txtTitle.text = marker?.title
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        return true
    }


    private lateinit var fragmentMap: SupportMapFragment
    private lateinit var googleMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var currentLatitude = 0.0
    private var currentLongitude = 0.0
    private lateinit var googleApiClient: GoogleApiClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cinema)
        fragmentMap = supportFragmentManager
            .findFragmentById(R.id.mapCinema) as SupportMapFragment
        fragmentMap.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        googleApiClient = GoogleApiClient.Builder(this)
            .addConnectionCallbacks(this)
            .addApi(LocationServices.API)
            .build()
        locationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(10 * 1000)
            .setExpirationDuration(10 * 1000)

        locationCallback =object: LocationCallback(){
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                for(location in locationResult.locations){
                    setNewLocation(location)
                }
            }
        }

        //BottomSheet
        bottomSheetBehavior = BottomSheetBehavior.from(llBottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
    }

    fun setNewLocation(location:Location){
        currentLatitude = location.latitude
        currentLongitude = location.longitude
        val currentPosition = LatLng(currentLatitude, currentLongitude)
        googleMap.addMarker(MarkerOptions().position(currentPosition))
        googleMap.moveCamera(CameraUpdateFactory
            .newLatLngZoom(currentPosition, 17f))
    }

    override fun onResume() {
        super.onResume()
        fusedLocationClient
            .requestLocationUpdates(locationRequest,
                locationCallback, Looper.getMainLooper())
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        //GoogleMap.MAP_TYPE_NORMAL
        //GoogleMap.MAP_TYPE_SATELLITE
        //GoogleMap.MAP_TYPE_TERRAIN
        //GoogleMap.MAP_TYPE_HYBRID
        //googleMap.uiSettings.setAllGesturesEnabled(false)
        googleMap.setOnMarkerClickListener(this)
        googleMap.setOnMapClickListener {
            try {
                val i = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(
                        "geo:<${it?.latitude}>,<${it?.longitude}>?q=" +
                                "<${it?.latitude}>,<${it.longitude}>(${getString(R.string.app_name)})"
                    )
                )
                i.setClassName(
                    "com.google.android.apps.maps",
                    "com.google.android.maps.MapsActivity"
                )
                startActivity(i)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        googleMap.setOnMarkerDragListener(object : GoogleMap.OnMarkerDragListener {
            override fun onMarkerDragEnd(marker: Marker?) {
                println("DragEndLatitude:  ${marker?.position?.latitude}")
                println("DragEndLongitude:  ${marker?.position?.longitude}")
            }

            override fun onMarkerDragStart(marker: Marker?) {
                println("DragStartLatitude:  ${marker?.position?.latitude}")
                println("DragStartLongitude:  ${marker?.position?.longitude}")
            }

            override fun onMarkerDrag(marker: Marker?) {
                println("DragLatitude:  ${marker?.position?.latitude}")
                println("DragLongitude:  ${marker?.position?.longitude}")
            }

        })
        googleMap.isBuildingsEnabled = false
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
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
        )
        googleMap.addMarker(
            MarkerOptions()
                .position(cinemexDelta)
                .title("Cinemex Delta")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.logo_login))
        )
        googleMap.moveCamera(CameraUpdateFactory
            .newLatLngZoom(cinemexDelta, 12f))
        getLocationPermission()

    }

    fun getLocationPermission(){
        if ((ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED)
            &&
            (ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
            == PackageManager.PERMISSION_GRANTED)
        ) {
            updateLocationUI()
        }else{
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION),
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


    override fun onConnected(p0: Bundle?) {

    }

    override fun onConnectionSuspended(p0: Int) {

    }

    override fun onLocationChanged(location: Location) {
        setNewLocation(location)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

    }

    override fun onProviderEnabled(provider: String?) {

    }

    override fun onProviderDisabled(provider: String?) {

    }


    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, CinemaActivity::class.java))
        }
    }


}
