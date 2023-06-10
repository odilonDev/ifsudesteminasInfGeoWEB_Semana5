package com.example.tarefa1;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import androidx.annotation.NonNull;

public class MinhaLocalizacao implements LocationListener {

    public static double latitude;
    public static double longitude;

    @Override
    public void onLocationChanged(@NonNull Location location) {
        location.getLatitude();
        location.getLongitude();
        latitude = location.getLatitude();
        longitude = location.getLongitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }
}
