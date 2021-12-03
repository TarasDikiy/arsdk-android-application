package com.ntu.arapplication.activitycore;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.arapplication.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.example.arapplication.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private View decorView;
    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private Button btnCam, btnMap, btnInv;
    private String currentActivityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility == 0) decorView.setSystemUiVisibility(hideSystemBars());
            }
        });

        //Обозначение кнопок
        btnMap = (Button) findViewById(R.id.btnMap);
        btnCam = (Button) findViewById(R.id.btnCam);
        btnInv = (Button) findViewById(R.id.btnInv);

        //Обозначение имени активити
        currentActivityName = MapsActivity.class.getSimpleName();

        //Деактивация кнопок
        BtnNavigationControl btnNavigationControl = new BtnNavigationControl();
        btnNavigationControl.setUnenable(btnCam, btnMap, btnInv, currentActivityName);

        //Переход по кнопкам
        btnCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapsActivity.this, CamActivity.class);
                startActivity(intent);
            }
        });
        btnInv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            decorView.setSystemUiVisibility(hideSystemBars());
        }
    }
    //TODO Тут нужно убрать системные кнопки!
    public int hideSystemBars() {
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                /*| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;*/

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        mMap.setMyLocationEnabled(true);

        //TODO Тут нужно подключить mongoDB
        //MongoDB mongoDB = new MongoDB();


        //TODO Неправильно отображаются цвета зон!
        int color = R.color.defaultCircle;
        CircleOptions circleOptions = new CircleOptions()
                .center(new LatLng(50.450150397043096, 30.62600433826447)).radius(50)
                .fillColor(color)
                .strokeColor(R.color.defaultCircleStroke)
                .strokeWidth(2);
        mMap.addCircle(circleOptions);

    }


}