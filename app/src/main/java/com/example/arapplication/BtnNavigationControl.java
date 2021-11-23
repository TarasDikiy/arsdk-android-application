package com.example.arapplication;

import android.widget.Button;

public class BtnNavigationControl {
    public void setUnenable(Button btnCam, Button btnMap, Button btnInv, String currentActivityName){

        if (currentActivityName.equals("MapsActivity")) {
            btnMap.setEnabled(false);
        }
        if (currentActivityName.equals("CamActivity")) {
            btnCam.setEnabled(false);
        }
        if (currentActivityName.equals("InvActivity")) {
            btnInv.setEnabled(false);
        }
    }
}
