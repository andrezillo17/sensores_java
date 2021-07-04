package com.naranjo.android.sensores_java.ui.sensors;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.naranjo.android.sensores_java.databinding.FragmentBatteryBinding;

public class BatteryFragment extends Fragment {
    private BatteryViewModel viewModel;
    private FragmentBatteryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(BatteryViewModel.class);
        viewModel.setFragment(this);
        binding = FragmentBatteryBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        viewModel.getLevel();
        return root;
    }

    public FragmentBatteryBinding getBinding() {
        return binding;
    }
}
