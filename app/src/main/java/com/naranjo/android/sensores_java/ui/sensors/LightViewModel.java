package com.naranjo.android.sensores_java.ui.sensors;

import android.hardware.SensorManager;
import android.util.Log;

import androidx.lifecycle.ViewModel;

public class LightViewModel extends ViewModel {
    private LightFragment fragment;

    public void viewInfo(){
        SensorManager manager =
        fragment.getBinding().powerLightResult.setText("Hello world");
    }

    public void setFragment(LightFragment fragment) {
        this.fragment = fragment;
    }

    // TODO: Implement the ViewModel
}