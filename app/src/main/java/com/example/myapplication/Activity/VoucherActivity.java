package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityVoucherBinding;

public class VoucherActivity extends AppCompatActivity {

    private ActivityVoucherBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVoucherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bottomNavigation5();
        bottomNavigation6();
        bottomNavigation7();
    }
    private void bottomNavigation5() {
        binding.backBtn.setOnClickListener(v -> startActivity(new Intent(VoucherActivity.this, MainActivity.class)));
    }
    private void bottomNavigation6() {
        binding.btnVoucher2.setOnClickListener(v -> startActivity(new Intent(VoucherActivity.this, VoucherActivity2.class)));
    }
    private void bottomNavigation7() {
        binding.btnVoucher3.setOnClickListener(v -> startActivity(new Intent(VoucherActivity.this, VoucherActivity3.class)));
    }
}