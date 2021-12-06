package com.ntu.arapplication.activitycore;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Permissions {
    private final int MY_PERMISSIONS_REQUEST_CAMERA = 100;
    private static int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 99;
    private static int MY_PERMISSIONS_REQUEST_COARSE_LOCATION = 98;

    //проверка наличия разрешения на доступ к данным о местоположении
    public void locationPermission(Context context, Activity activity) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //Запрос на доступ к данным о локации
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_FINE_LOCATION);
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_COARSE_LOCATION);
        }
    }

    //проверка наличия разрешения на доступ к камере
    public void camPermissions(Context context, Activity activity){
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //Запрос на доступ к камере
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
        }
    }

    public void getAllPermissions(Context context, Activity activity){
        locationPermission(context, activity);
        camPermissions(context, activity);
    }
    
}
