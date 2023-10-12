package com.example.ass1_ph42307;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Trang_chu extends AppCompatActivity {
Intent i=new Intent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        Button btnpb=findViewById(R.id.btnpb);
        Button btnnv=findViewById(R.id.btnnv);
        Button btnthoat=findViewById(R.id.btnthoat);
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=new Intent(Trang_chu.this, dang_nhap.class);
                startActivity(i);
            }
        });
        btnnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=new Intent(Trang_chu.this, Nhan_vien.class);
                startActivity(i);
            }
        });
        btnpb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i= new Intent(Trang_chu.this, Phong_ban.class);
                startActivity(i);
            }
        });
    }
}