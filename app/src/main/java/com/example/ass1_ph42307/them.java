package com.example.ass1_ph42307;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class them extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);
        Button btnsubmit= findViewById(R.id.btnsubmit);
        EditText msv=findViewById(R.id.msv);
        EditText name =findViewById(R.id.name);
        EditText phongban=findViewById(R.id.phongban);
      btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(them.this, Nhan_vien.class);
                Bundle bundle = new Bundle();
                bundle.putString("msv",msv.getText().toString());
                bundle.putString("name",name.getText().toString());
                bundle.putString("phongban",phongban.getText().toString());
                intent1.putExtras(bundle);
                setResult(1,intent1);
                finish();
            }
        });
    }
}