package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login_Activity extends AppCompatActivity {

    private Button btn_login;
    private EditText Password, Email;
    private ImageView close;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        //khởi tạo
        auth = FirebaseAuth.getInstance();
        btn_login = findViewById(R.id.btn_login);
        Password = findViewById(R.id.Password);
        Email = findViewById(R.id.Email);
        close = findViewById(R.id.close);

        //thiết lập sự kiện
        btn_login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();

                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    if (!password.isEmpty()){
                        auth.signInWithEmailAndPassword(email, password)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        String userId = auth.getCurrentUser().getUid();
                                        FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("name").get()
                                                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                                                        if (task.isSuccessful()) {
                                                            String userName = task.getResult().getValue(String.class);
                                                            Intent intent = new Intent(Login_Activity.this, MainActivity.class);
                                                            intent.putExtra("USER_NAME", userName);
                                                            startActivity(intent);
                                                            finish();
                                                        } else {
                                                            Log.e("Login", "Failed to retrieve user name");
                                                            Toast.makeText(Login_Activity.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
//                                        startActivity(new Intent(Login_Activity.this, MainActivity.class));
//                                        finish();
                                    }

                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Login_Activity.this, "Mật khẩu hoặc email không đúng!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        Password.setError("Mật khẩu chưa được nhập");
                    }
                } else if(email.isEmpty()){
                    Email.setError("Email chưa được nhập");
                } else {
                    Email.setError("Hãy nhập email");
                }
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
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
}