package com.example.myapplication.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private FirebaseUser currentUser;

    private TextView nameTxt, pointTxt, voucherTxt;
    private EditText editTextName, editTextDob, editTextGender, editTextNationalID, editTextEmail, editTextPassword;
    private Button buttonSave, buttonExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference("Users").child(currentUser.getUid());

        nameTxt = findViewById(R.id.nameTxt);
        pointTxt = findViewById(R.id.pointTxt);
        voucherTxt = findViewById(R.id.voucherTxt);
        editTextName = findViewById(R.id.editTextName);
        editTextDob = findViewById(R.id.editTextDob);
        editTextGender = findViewById(R.id.editTextGender);
        editTextNationalID = findViewById(R.id.editTextNationalID);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSave = findViewById(R.id.buttonSave);
        buttonExit = findViewById(R.id.buttonExit);

        // Lắng nghe sự thay đổi dữ liệu người dùng từ Firebase Database
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue(String.class);
                String points = dataSnapshot.child("points").getValue(String.class);
                String vouchers = dataSnapshot.child("vouchers").getValue(String.class);
                String birthDay = dataSnapshot.child("birthDay").getValue(String.class);
                String gender = dataSnapshot.child("gender").getValue(String.class);
                String nationalID = dataSnapshot.child("nationalID").getValue(String.class);
                String email = dataSnapshot.child("email").getValue(String.class);
                String password = dataSnapshot.child("password").getValue(String.class);

                // Hiển thị thông tin người dùng
                nameTxt.setText(name);
                pointTxt.setText(points);
                voucherTxt.setText(vouchers);

                // Đặt giá trị mặc định cho các EditText
                editTextName.setText(name);
                editTextDob.setText(birthDay);
                editTextGender.setText(gender);
                editTextNationalID.setText(nationalID);
                editTextEmail.setText(email);
                editTextPassword.setText(password);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Xử lý lỗi nếu cần thiết
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lưu thông tin người dùng sau khi sửa đổi
                String newEmail = editTextEmail.getText().toString().trim();
                String newPassword = editTextPassword.getText().toString().trim();
                // Lưu thông tin mới vào Firebase Database
                mDatabase.child("email").setValue(newEmail);
                mDatabase.child("password").setValue(newPassword);
                // Lưu các thông tin khác tương tự
            }
        });

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
