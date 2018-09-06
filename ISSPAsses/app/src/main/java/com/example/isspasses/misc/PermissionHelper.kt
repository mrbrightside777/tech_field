package com.example.isspasses.misc

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionHelper(val activity: Activity) {

    fun filter_invalid_permissions(permissions:List<String>):List<String> {
        return permissions.filter {
            ContextCompat.checkSelfPermission(activity, it) != PackageManager.PERMISSION_GRANTED
        }
    }

    fun request_permissions(permissions: List<String>, req_code:Int) {
        ActivityCompat.requestPermissions(activity, permissions.toTypedArray(), req_code)
    }
}