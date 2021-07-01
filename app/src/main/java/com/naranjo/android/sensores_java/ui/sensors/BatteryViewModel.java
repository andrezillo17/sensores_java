package com.naranjo.android.sensores_java.ui.sensors;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.naranjo.android.sensores_java.databinding.FragmentBatteryBinding;

public class BatteryViewModel extends ViewModel {

    private FragmentBatteryBinding binding;
    private BatteryFragment fragment;

    public void setFragment(BatteryFragment fragment) {
        this.fragment = fragment;
    }

    public void getLevel(){
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        if(fragment!=null){
            Intent batteryStatus = fragment.getContext().registerReceiver(null, ifilter);

            int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

            float batteryPct = level * 100 / (float)scale;

            int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            fragment.getBinding().textBatteryPorcentaje.setText(Float.toString(batteryPct).concat("%"));
        }else {
            Log.i("BatteryViewModel","La instancia de la clase es nula");
        }


    }

}
