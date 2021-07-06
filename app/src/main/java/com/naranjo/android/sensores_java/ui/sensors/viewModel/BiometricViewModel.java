package com.naranjo.android.sensores_java.ui.sensors.viewModel;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.biometric.BiometricPrompt;
import androidx.lifecycle.ViewModel;

import com.naranjo.android.sensores_java.ui.sensors.BiometricFragment;

import static android.hardware.biometrics.BiometricManager.Authenticators.*;

public class BiometricViewModel extends ViewModel {
    private BiometricFragment fragment;

    public void  getBiometric(){
        BiometricPrompt.AuthenticationCallback object = new BiometricPrompt.AuthenticationCallback(){
            public void onAuthenticationError(
                    @BiometricPrompt.AuthenticationError int errorCode,
                    @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode,errString);
                fragment.getBinding().resultBiometric.setText("Lectura de huella dactilar fallida");
            }
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.
                    AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                fragment.getBinding().resultBiometric.setText("Lectura de huella dactilar OK");
            }
        };
        BiometricPrompt pront = new BiometricPrompt(fragment.getActivity(),object);
        @SuppressLint("WrongConstant") BiometricPrompt.PromptInfo promptInfo =
                new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Prueba de huella dactilar")
                .setSubtitle("Log in using your biometric credential")
                .setNegativeButtonText("Cancelar")
                .setAllowedAuthenticators(BIOMETRIC_WEAK)
                .build();
        pront.authenticate(promptInfo);
    }

    public void setFragment(BiometricFragment fragment) {
        this.fragment = fragment;
    }

}
