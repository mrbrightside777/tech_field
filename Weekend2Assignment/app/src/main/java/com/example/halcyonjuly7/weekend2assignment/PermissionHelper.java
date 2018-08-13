package com.example.halcyonjuly7.weekend2assignment;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;

import java.util.ArrayList;
import java.util.List;

public class PermissionHelper {

    Activity context;

    public PermissionHelper(Activity context) {
        this.context = context;
    }

    public String[] check_permissions(String[] permissions) {
        List<String> un_granted_permissions = new ArrayList<>();
        for (int index = 0; index < permissions.length; index++) {
            if (PermissionChecker.checkSelfPermission(context, permissions[index]) != PermissionChecker.PERMISSION_GRANTED)
                un_granted_permissions.add(permissions[index]);
        }
        return un_granted_permissions.toArray(new String[un_granted_permissions.size()]);
    }

    public void requestPermissions(String[] permissions, int req_code) {
        ActivityCompat.requestPermissions(context, permissions, req_code);
    }

}
