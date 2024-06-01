package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Signup_Activity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText Password1, Password2;
    private Button btn_sign_up;
    private ImageView arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        // khai báo
        auth = FirebaseAuth.getInstance();
        Password1 = findViewById(R.id.Password1);
        Password2 = findViewById(R.id.Password2);
        btn_sign_up = findViewById(R.id.btn_sign_up);
        arrow = findViewById(R.id.arrow);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String nationalID = intent.getStringExtra("nationalID");
        String email = intent.getStringExtra("email");
        String birthDay = intent.getStringExtra("birthDay");
        String gender = intent.getStringExtra("gender");
        String password = intent.getStringExtra("password");

        // thiết lập sự kiện
        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password1 = Password1.getText().toString().trim();
                String password2 = Password2.getText().toString().trim();

                if (password1.isEmpty() || password2.isEmpty()) {
                    Toast.makeText(Signup_Activity.this, "Chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (!password2.equals(password1)) {
                    Toast.makeText(Signup_Activity.this, "Thông tin nhập sai", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(email, password1, name, nationalID, birthDay, gender);
                }
            }
        });

        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void registerUser(String email, String password, String name, String nationalID, String birthDay, String gender) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = auth.getCurrentUser();
                if (user != null) {
//                    // Xác thực người dùng
//                    user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> emailTask) {
//                            if (emailTask.isSuccessful()) {
//                                Toast.makeText(Signup_Activity.this, "Vui lòng kiểm tra email để xác nhận đăng ký!", Toast.LENGTH_SHORT).show();
//                            } else {
//                                Toast.makeText(Signup_Activity.this, "Đã xảy ra lỗi khi gửi email xác nhận!", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });

                    Map<String, Object> userInfo = new HashMap<>();
                    userInfo.put("name", name);
                    userInfo.put("nationalID", nationalID);
                    userInfo.put("email", email);
                    userInfo.put("birthDay", birthDay);
                    userInfo.put("gender", gender);
                    userInfo.put("password", password);

                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(user.getUid())
                            .setValue(userInfo)
                            .addOnCompleteListener(task1 -> {
                                if (task1.isSuccessful()) {
                                    Toast.makeText(Signup_Activity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Signup_Activity.this, Login_Activity.class));
                                    finish();
                                } else {
                                    Toast.makeText(Signup_Activity.this, "Đăng kí thất bại: " + task1.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            } else {
                Toast.makeText(Signup_Activity.this, "Đăng kí thất bại: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
