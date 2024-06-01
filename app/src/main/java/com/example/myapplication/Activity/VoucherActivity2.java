package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityVoucher2Binding;

public class VoucherActivity2 extends AppCompatActivity {

    private ActivityVoucher2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVoucher2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bottomNavigation8();
        bottomNavigation9();
        bottomNavigation10();
    }
    private void bottomNavigation8() {
        binding.backBtn.setOnClickListener(v -> startActivity(new Intent(VoucherActivity2.this, MainActivity.class)));
    }
    private void bottomNavigation9() {
        binding.btnVoucher1.setOnClickListener(v -> startActivity(new Intent(VoucherActivity2.this, VoucherActivity.class)));
    }
    private void bottomNavigation10() {
        binding.btnVoucher3.setOnClickListener(v -> startActivity(new Intent(VoucherActivity2.this, VoucherActivity3.class)));
    }
}
