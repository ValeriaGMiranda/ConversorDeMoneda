package com.valesoft.conversordemoneda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.valesoft.conversordemoneda.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);
        vm.getMConversion().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                binding.tvConversion.setText(aDouble.toString());
            }
        });

        binding.rbEuroDolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.etMontoDolar.setText("");
                binding.etMontoDolar.setEnabled(false);
                binding.etMontoEuro.setEnabled(true);
                binding.tvConversion.setText("");
            }
        });

        binding.rbDolarEuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.etMontoEuro.setText("");
                binding.etMontoEuro.setEnabled(false);
                binding.etMontoDolar.setEnabled(true);
                binding.tvConversion.setText("");

            }
        });

        binding.bConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.convertirMonto(binding.etMontoDolar.getText().toString(),binding.etMontoEuro.getText().toString(),
                binding.rbDolarEuro.isChecked(), binding.rbEuroDolar.isChecked());
            }
        });
    }
}