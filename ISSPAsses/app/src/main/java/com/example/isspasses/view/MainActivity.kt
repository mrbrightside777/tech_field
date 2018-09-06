package com.example.isspasses.view

import android.Manifest
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.isspasses.R
import com.example.isspasses.adapters.ClosedAdapter
import com.example.isspasses.data.pojo.IssNowResponse
import com.example.isspasses.data.pojo.IssPosition
import com.example.isspasses.data.pojo.Response
import com.example.isspasses.databinding.ActivityMainBinding
import com.example.isspasses.di.components.DaggerActivityMainComponent
import com.example.isspasses.di.module.ActivityMainBindingsModule
import com.example.isspasses.misc.GpsHelper
import com.example.isspasses.misc.PermissionHelper
import com.example.isspasses.services.CallApiService
import com.example.isspasses.viewmodels.ActivityMainViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnSuccessListener

//import com.example.isspasses.di.components.DaggerActivityMainModelViewComponent

class MainActivity : AppCompatActivity() {

    private lateinit var bindings:ActivityMainBinding
    private lateinit var viewmodel: ActivityMainViewModel
    private lateinit var permission_helper: PermissionHelper
    private lateinit var gps_helper:GpsHelper
    private val GPS_PERMISSIONS = mutableListOf(Manifest.permission.ACCESS_COARSE_LOCATION)
    private val GPS_REQ_CODE = 1
    private lateinit var googleMap:GoogleMap
    private lateinit var br:BroadcastReceiver


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindings = DaggerActivityMainComponent
                .builder()
                .activityMainBindingsModule(ActivityMainBindingsModule(this, R.layout.activity_main))
                .build()
                .get_bindings()
        viewmodel = ViewModelProviders.of(this).get(ActivityMainViewModel::class.java)
        permission_helper = PermissionHelper(this)
        gps_helper = GpsHelper(this)
        viewmodel.observe_items(this, object: Observer<List<Response?>> {
            override fun onChanged(t: List<Response?>) {
                val adapter = (bindings.passRecycler.adapter as ClosedAdapter<List<Response?>>)
                adapter.clear_items()
                adapter.add_items(t)
                adapter.notify_change()
            }
        })
        bindings.mapFrag.onCreate(savedInstanceState)
        viewmodel.observe_iss_pos(this, object: Observer<IssPosition> {
            override fun onChanged(t: IssPosition?) {
                googleMap.clear()
                googleMap.addMarker(MarkerOptions().position(LatLng(t?.latitude!!.toDouble(), t?.longitude!!.toDouble())))
            }

        })
        get_data()

        bindings.searchGetPasses.setOnClickListener {
            get_data()
        }
        br = BR()
        bindings.mapFrag.getMapAsync({
            googleMap = it
            googleMap.uiSettings.isMapToolbarEnabled = false
            googleMap.uiSettings.setAllGesturesEnabled(false)
            startService(Intent(this, CallApiService::class.java))
            registerReceiver(br, IntentFilter("swag"))
        })


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode) {
            GPS_REQ_CODE -> {
                if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                   get_last_known_location()
                }
            }
        }
    }

    private fun get_last_known_location() {
        gps_helper.last_known_loc(object : OnSuccessListener<Location> {
            override fun onSuccess(loc: Location?) {
                viewmodel.get_response(loc!!.latitude, loc!!.longitude)
            }

        })
    }

    private fun get_data() {
        val ungranted = permission_helper.filter_invalid_permissions(mutableListOf(Manifest.permission.ACCESS_COARSE_LOCATION))
        if (ungranted.size > 0) {
            permission_helper.request_permissions(ungranted, GPS_REQ_CODE)
        } else {
            get_last_known_location()
        }
    }

    override fun onStart() {
        super.onStart()
        bindings.mapFrag.onStart()
    }

    override fun onResume() {
        super.onResume()
        bindings.mapFrag.onResume()
    }

    override fun onStop() {
        super.onStop()
        bindings.mapFrag.onStop()
        stopService(Intent(this, CallApiService::class.java))
        unregisterReceiver(br)
    }

    override fun onDestroy() {
        super.onDestroy()
        bindings.mapFrag.onDestroy()
    }

    inner class BR:BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {

            val latLng = LatLng(intent!!.getStringExtra("lat").toDouble(), intent.getStringExtra("lon").toDouble())
            googleMap.clear()
            googleMap.addMarker(MarkerOptions().position(latLng).title("ISS current position")).showInfoWindow()
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        }

    }
}
