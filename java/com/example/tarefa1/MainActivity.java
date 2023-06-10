package com.example.tarefa1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                LocationManager mLocManager;
                LocationListener mLocListener;
                mLocManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                mLocListener = new MinhaLocalizacao();

                if (ActivityCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{
                                    Manifest.permission.ACCESS_FINE_LOCATION
                            }, 1);
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{
                                    Manifest.permission.ACCESS_NETWORK_STATE
                            }, 1);
                    return;
                }
                mLocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocListener);
                if (mLocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                    String texto = "Latitude: " + MinhaLocalizacao.latitude + "/n" +
                            "Longitude: " + MinhaLocalizacao.longitude + "/n";
                    Toast.makeText(MainActivity.this, texto, Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "GPS Desabilitado!", Toast.LENGTH_LONG).show();
                }

                WebView wv = findViewById(R.id.webv);
                wv.getSettings().setJavaScriptEnabled(true);
                wv.loadUrl("https://www.google.com/maps/search/?api=1&query="+MinhaLocalizacao.latitude+", "+MinhaLocalizacao.longitude);
            }
        });
    }

}