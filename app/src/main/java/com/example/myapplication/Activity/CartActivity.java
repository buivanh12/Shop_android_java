package com.example.myapplication.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.Adapter.CartAdapter;
import com.example.myapplication.Helper.ChangeNumberItemsListener;
import com.example.myapplication.Helper.ManagmentCart;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityCartBinding;

public class CartActivity extends AppCompatActivity {
    private ManagmentCart managmentCart;
    ActivityCartBinding binding;
    double tax;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        managmentCart = new ManagmentCart(this);
        backBtn = findViewById(R.id.backBtn);

        setVariable();
        initlist();
        caculatorCart();
        statusBarColor();

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void statusBarColor() {
        Window window = CartActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(CartActivity.this, R.color.purple_Dark));
    }
    private void initlist() {
        if(managmentCart.getListCart().isEmpty()){
            binding.emptyTxt.setVisibility(View.VISIBLE);
            binding.scroll.setVisibility(View.GONE);
        }else {
            binding.emptyTxt.setVisibility(View.GONE);
            binding.scroll.setVisibility(View.VISIBLE);
        }
        binding.cartView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.cartView.setAdapter(new CartAdapter(managmentCart.getListCart(), () -> caculatorCart()));
    }
    private void caculatorCart(){
        double percentTax = 0.02;
        double delivery = 10;
        tax = Math.round(managmentCart.getTotalFee()*percentTax*100)/100;

        double total = Math.round((managmentCart.getTotalFee() + tax + delivery) * 100) / 100;
        double itemtotal = Math.round(managmentCart.getTotalFee()*100) / 100;
        binding.totalFeeTxt.setText("$" + itemtotal);
        binding.taxTxt.setText("$" + tax);
        binding.deliveryTxt.setText("$" + delivery);
        binding.totalTxt.setText("$" + total);
    }
    private void setVariable() {
        binding.backBtn.setOnClickListener(v -> finish());
    }
}