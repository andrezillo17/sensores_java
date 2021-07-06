package com.naranjo.android.sensores_java.ui.sensors;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.naranjo.android.sensores_java.databinding.FragmentBatteryBinding;

public class BatteryViewModel extends ViewModel {
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
            boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                    status == BatteryManager.BATTERY_STATUS_FULL;

            int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            int voltage = batteryStatus.getIntExtra(BatteryManager.EXTRA_VOLTAGE,-1);
            int temperature = batteryStatus.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,-1);
            float tempTwo = ((float) temperature) / 10;

            fragment.getBinding().textBatteryPorcentaje.setText(Float.toString(batteryPct).concat("%"));
            fragment.getBinding().textBatteryVol.setText("Estado:"+isCharging(isCharging)+
                    " \t Type Charging:"+typeCharge(chargePlug)+" \t vol:"+voltage+"mV");
            fragment.getBinding().textBatteryTemp.setText(tempTwo+" °C");
        }else {
            Log.i("BatteryViewModel","La instancia de la clase es nula");
        }


    }
    private String typeCharge(int typeCharge){
        switch (typeCharge){
            case BatteryManager.BATTERY_PLUGGED_USB:
                return "USB";
            case BatteryManager.BATTERY_PLUGGED_AC:
                return "AC";
            default:
                return "Not are charging";
        }
    }
    private String isCharging(boolean status){
        return status?"Charging":"Not charging";
    }

}
