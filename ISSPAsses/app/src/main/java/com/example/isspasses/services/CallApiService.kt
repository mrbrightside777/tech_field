package com.example.isspasses.services

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.widget.Toast
import com.example.isspasses.data.misc.Constants
import com.example.isspasses.di.components.DaggerActivityMainViewModelComponent
import com.example.isspasses.di.module.RetrofitModule
import com.example.isspasses.network.retrofit.IssEndpoints
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch


class CallApiService:Service() {
    private lateinit var handler: Handler
    private lateinit var runnable:Runnable
    private val INTERVAL = 60000L
    private lateinit var endpoints:IssEndpoints

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        endpoints = DaggerActivityMainViewModelComponent.builder().retrofitModule(RetrofitModule( Constants.Web.BASE_URL)).build().get_enpoints()
        handler = Handler()
        runnable = Runnable {
            launch(context = CommonPool) {
                val item = endpoints.iss_current().execute().body()!!.issPosition
                sendBroadcast(Intent("swag").apply {
                    putExtra("lat", item?.latitude)
                    putExtra("lon", item?.longitude)
                })
            }

            handler.postDelayed(runnable, INTERVAL)
        }

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        handler.postDelayed(runnable, 3000)
        return Service.START_STICKY
    }
}