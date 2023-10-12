package com.example.ass1_ph42307;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ass1_ph42307.dao.nguoiDungDao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class dang_nhap extends AppCompatActivity {
    Intent i = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        EditText edinhap = findViewById(R.id.edinhap);
        EditText edipass = findViewById(R.id.edipass);
        Button btndk = findViewById(R.id.btndk);
        ImageView mat=findViewById(R.id.mat);
        Button btndn = findViewById(R.id.btndn);

        // Khởi tạo SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
// Lấy trạng thái của checkbox từ SharedPreferences
        boolean isChecked = sharedPreferences.getBoolean("isChecked", false);
// Lấy tên người dùng từ SharedPreferences
        String username = sharedPreferences.getString("username", "");
// Lấy mật khẩu từ SharedPreferences
        String password = sharedPreferences.getString("password", "");
// Khởi tạo checkbox và đặt trạng thái của nó
        CheckBox checkBox = findViewById(R.id.checkbox);
        checkBox.setChecked(isChecked);
// Nếu checkbox được chọn, lưu tên người dùng và mật khẩu vào SharedPreferences
        if (checkBox.isChecked()) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isChecked", true);
            editor.putString("username", username);
            editor.putString("password", password);
            editor.apply();
        }

        final boolean[] isPasswordVisible = {false};
        mat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible[0]) {
                    edipass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mat.setImageResource(R.drawable.mat_eye_24);
                    isPasswordVisible[0] = false;
                } else {
                    edipass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mat.setImageResource(R.drawable.mat_eye_24);
                    isPasswordVisible[0] = true;
                }

            }});
        btndk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent();
                i.setClass(dang_nhap.this, dang_ky.class);
                startActivity(i);
            }
        });
        nguoiDungDao nguoidungdao=new nguoiDungDao(this);
        btndn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             String use =edinhap.getText().toString();
             String pass=edipass.getText().toString();
                boolean check =nguoidungdao.checkLogin(use, pass);
                if (use.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(dang_nhap.this, "Điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (check) {
                    Toast.makeText(dang_nhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(dang_nhap.this, Trang_chu.class));
                } else {
                    Toast.makeText(dang_nhap.this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}