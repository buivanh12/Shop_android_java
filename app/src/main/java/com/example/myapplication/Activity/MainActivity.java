package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.Adapter.PopularAdapter;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.domain.PopularDomain;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        textView = findViewById(R.id.textView);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("USER_NAME");

        textView.setText(userName);

        statusBarColor();
        initRecyclerView();
        bottomNavigation1();
        bottomNavigation2();
        bottomNavigation3();
        bottomNavigation4();
    }

    private void bottomNavigation1() {
        binding.cartBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CartActivity.class)));
    }
    private void bottomNavigation2() {
        binding.profileBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ProfileActivity.class)));
    }
    private void bottomNavigation3() {
        binding.voucherBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, VoucherActivity.class)));
    }
    private void bottomNavigation4() {
        binding.qrBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, QrcodeActivity.class)));
    }
    private void statusBarColor() {
        Window window = MainActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.purple_Dark));
    }

    private void initRecyclerView() {
        ArrayList<PopularDomain> items = new ArrayList<>();
        items.add(new PopularDomain("T-shirt black", "item_1",15, 4, 500, "A"));
        items.add(new PopularDomain("Smart Watch", "item_2", 10, 4.5, 450, "B"));
        items.add(new PopularDomain("Phone", "item_3", 3, 4.9, 800, "C"));

        binding.PopularView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.PopularView.setAdapter(new PopularAdapter(items));
    }
}