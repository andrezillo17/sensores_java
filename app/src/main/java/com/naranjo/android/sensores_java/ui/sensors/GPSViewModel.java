package com.naranjo.android.sensores_java.ui.sensors;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class GPSViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public GPSViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Información de GPS");
    }

    public void obtenerUbicacion(FusedLocationProviderClient fusedLocationClient,
                                  FragmentActivity activity,
                                  GPSFragment gpsFragment)  {
        if (ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(activity,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.i("GPSViewModel","No hay permisos de ubicación completos");
            return;
        }
        FusedLocationProviderClient fusedLocationProviderClient =
                new FusedLocationProviderClient(activity);
       Task<Location> locationTask = fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(activity, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(final Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            gpsFragment.setTextGPS(location);
                            Log.i("GPSViewModel","UBICACION: Lon:"+location.getLongitude()+" Lat:"+location.getLatitude());
                        }else{
                            Log.i("GPSViewModel","Location es null");
                        }
                    }
                });
    }

    public LiveData<String> getText() {
        return mText;
    }
}
