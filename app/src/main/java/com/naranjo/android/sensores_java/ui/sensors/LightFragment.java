package com.naranjo.android.sensores_java.ui.sensors;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.naranjo.android.sensores_java.R;
import com.naranjo.android.sensores_java.databinding.FragmentLightBinding;

public class LightFragment extends Fragment {

    private LightViewModel mViewModel;
    private FragmentLightBinding binding;

    public static LightFragment newInstance() {
        return new LightFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentLightBinding.inflate(inflater,container,false);
        mViewModel = new ViewModelProvider(this).get(LightViewModel.class);
        mViewModel.setFragment(this);
        mViewModel.viewInfo();
        View root = binding.getRoot();
        return root;
        //return inflater.inflate(R.layout.fragment_light, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LightViewModel.class);
        // TODO: Use the ViewModel
    }

    public FragmentLightBinding getBinding(){
        return binding;
    }

}