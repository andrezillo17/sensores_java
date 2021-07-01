package com.naranjo.android.sensores_java.ui.sensors;

import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.naranjo.android.sensores_java.databinding.FragmentGpsBinding;

public class GPSFragment extends Fragment {
    private GPSViewModel gpsViewModel;
    private FragmentGpsBinding binding;
    private FusedLocationProviderClient fusedLocationClient;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        gpsViewModel = new ViewModelProvider(this).get(GPSViewModel.class);
        binding = FragmentGpsBinding.inflate(inflater,container,false);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this.getActivity());
        gpsViewModel.obtenerUbicacion(fusedLocationClient,this.getActivity(),this);
        View root = binding.getRoot();
        final TextView textView = binding.textGps;
        gpsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
    public void setTextGPS(Location location){
        final TextView textViewLocation = binding.textLocation;
        textViewLocation.setText("Ubicación: Lat:"+location.getLatitude()+
                " Lon:"+location.getLongitude());

        binding.textAltitudResult.setText(Double.toString(location.getAltitude()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
