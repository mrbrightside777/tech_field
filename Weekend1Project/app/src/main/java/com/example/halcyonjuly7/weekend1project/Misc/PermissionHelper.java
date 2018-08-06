package com.example.halcyonjuly7.weekend1project.Misc;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.PermissionChecker;

import java.security.Permission;
import java.util.ArrayList;

public class PermissionHelper {



    public static boolean is_granted(Context context, String permission) {
        return PermissionChecker.checkSelfPermission(context, permission) == PermissionChecker.PERMISSION_GRANTED;
    }

    public static String[] check_permission(Context context, String[] permissions) {

        ArrayList<String> invalid_permissions = new ArrayList<>();

        for (int index = 0; index < permissions.length; index++) {
            if (PermissionHelper.is_granted(context, permissions[index]))
                invalid_permissions.add(permissions[index]);
        }
        return (String[]) invalid_permissions.toArray();
    }

    public static void activity_ask_perm(Activity activity, String[] permissions, int req_code) {
        ActivityCompat.requestPermissions(activity, permissions, req_code);
    }

    public static void frag_ask_perm(Fragment frag, String[] permission, int req_code) {
        frag.requestPermissions(permission, req_code);
    }
}
