package ca.gbc.comp3074.fahim.mina.labex5

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat

class MainActivity : ComponentActivity() {

    private lateinit var locationManager: LocationManager

    // Values displayed on the screen
    private var latitude by mutableStateOf("Not available")
    private var longitude by mutableStateOf("Not available")
    private var status by mutableStateOf("Tap the button")

    // Receive updates when GPS location changes
    private val locationListener = object : LocationListener {

        override fun onLocationChanged(location: Location) {
            latitude = location.latitude.toString()
            longitude = location.longitude.toString()
            status = "GPS location received!"
        }

        override fun onProviderEnabled(provider: String) {
            status = "GPS enabled"
        }

        override fun onProviderDisabled(provider: String) {
            status = "Please turn on GPS"
        }

        @Suppress("DEPRECATION")
        override fun onStatusChanged(
            provider: String?,
            status: Int,
            extras: Bundle?
        ) {
            // Not needed for this lab
        }
    }

    // Ask the user for location permission
    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->

        if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true) {
            startGPS()
        } else {
            status = "Precise location permission is required"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get LocationManager
        locationManager = getSystemService(
            Context.LOCATION_SERVICE
        ) as LocationManager

        setContent {
            MaterialTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "My GPS Location",
                        fontSize = 26.sp
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    Text(text = "Latitude: $latitude")

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(text = "Longitude: $longitude")

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(onClick = {
                        checkPermission()
                    }) {
                        Text("Get GPS Location")
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(text = status)
                }
            }
        }
    }

    // Check permission before using GPS
    private fun checkPermission() {

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            startGPS()
        } else {
            permissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    // Start receiving GPS updates
    private fun startGPS() {

        if (!locationManager.isProviderEnabled(
                LocationManager.GPS_PROVIDER
            )
        ) {
            status = "Please enable GPS in Settings"
            return
        }

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        status = "Waiting for GPS location..."

        locationManager.removeUpdates(locationListener)

        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            2000L,
            0f,
            locationListener,
            Looper.getMainLooper()
        )
    }

    override fun onStop() {
        super.onStop()

        // Stop GPS updates when app is not visible
        locationManager.removeUpdates(locationListener)
    }
}