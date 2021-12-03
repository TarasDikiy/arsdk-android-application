package com.ntu.arapplication.activitycore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.arapplication.R;

public class CamActivity extends AppCompatActivity {

    private Button btnCam, btnMap, btnInv;
    private String currentActivityName;
    private Camera camera;
    private FrameLayout cameraView;
    private ShowCamera showCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam);
        //Инициализация фрейма камеры
        cameraView = findViewById(R.id.frmlayout_camera_view);

        //Инициализация кнопок
        btnCam = (Button) findViewById(R.id.btnCam_camlayout);
        btnMap = (Button) findViewById(R.id.btnMap_camlayout);
        btnInv = (Button) findViewById(R.id.btnInv_camlayout);

        //Запуск камеры
        camera = Camera.open();
        showCamera = new ShowCamera(this, camera);
        cameraView.addView(showCamera);

        //Обозначаем имя активити
        currentActivityName = CamActivity.class.getSimpleName();

        //Деактиватор кнопок
        BtnNavigationControl btnNavigationControl = new BtnNavigationControl();
        btnNavigationControl.setUnenable(btnCam, btnMap, btnInv, currentActivityName);

        //Переход по кнопкам
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CamActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
        btnInv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}