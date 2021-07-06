package com.naranjo.android.sensores_java.ui.sensors;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.naranjo.android.sensores_java.databinding.FragmentBiometricBinding;
import com.naranjo.android.sensores_java.ui.sensors.viewModel.BiometricViewModel;

public class BiometricFragment extends Fragment {
    private BiometricViewModel viewModel;
    private FragmentBiometricBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(BiometricViewModel.class);
        viewModel.setFragment(this);
        binding = FragmentBiometricBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        viewModel.getBiometric();
        return root;
    }

    public FragmentBiometricBinding getBinding(){
        return binding;
    }
}
