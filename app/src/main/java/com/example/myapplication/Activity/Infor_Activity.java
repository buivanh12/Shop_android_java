package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class Infor_Activity extends AppCompatActivity {

    private EditText etName, etNationalID, etEmail, etBirthDay;
    private RadioButton rbMale, rbFemale;
    private Button btn_submit;
    private ImageView arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor);

        // Initialize views
        etName = findViewById(R.id.Ename);
        etNationalID = findViewById(R.id.Enational_ID);
        etEmail = findViewById(R.id.Eemail);
        etBirthDay = findViewById(R.id.Ebirth_day);
        rbMale = findViewById(R.id.Rbtn_nam);
        rbFemale = findViewById(R.id.Rbtn_nu);
        btn_submit = findViewById(R.id.btn_submit);
        arrow = findViewById(R.id.arrow);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString().trim();
                String nationalID = etNationalID.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String birthDay = etBirthDay.getText().toString().trim();
                boolean isMale = rbMale.isChecked();
                boolean isFemale = rbFemale.isChecked();

                if (name.isEmpty() || nationalID.isEmpty() || email.isEmpty() || birthDay.isEmpty() || (!isMale && !isFemale)) {
                    Toast.makeText(Infor_Activity.this, "Chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Infor_Activity.this, Signup_Activity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("nationalID", nationalID);
                    intent.putExtra("email", email);
                    intent.putExtra("birthDay", birthDay);
                    intent.putExtra("gender", isMale ? "Male" : "Female");
                    startActivity(intent);
                }
            }
        });

        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
