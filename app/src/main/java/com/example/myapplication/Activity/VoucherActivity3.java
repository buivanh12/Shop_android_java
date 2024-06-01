package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityVoucher3Binding;

public class VoucherActivity3 extends AppCompatActivity {
    private ActivityVoucher3Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVoucher3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bottomNavigation11();
        bottomNavigation12();
        bottomNavigation13();
    }
    private void bottomNavigation11() {
        binding.backBtn.setOnClickListener(v -> startActivity(new Intent(VoucherActivity3.this, MainActivity.class)));
    }
    private void bottomNavigation12() {
        binding.btnVoucher1.setOnClickListener(v -> startActivity(new Intent(VoucherActivity3.this, VoucherActivity.class)));
    }
    private void bottomNavigation13() {
        binding.btnVoucher3.setOnClickListener(v -> startActivity(new Intent(VoucherActivity3.this, VoucherActivity2.class)));
    }
}