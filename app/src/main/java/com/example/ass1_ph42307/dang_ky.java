package com.example.ass1_ph42307;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ass1_ph42307.dao.nguoiDungDao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class dang_ky extends AppCompatActivity {
    Intent i = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        Button btnveman2 = findViewById(R.id.btnveman2);
        EditText eddn = findViewById(R.id.eddn);
        EditText edmk = findViewById(R.id.edmk);
        EditText edmk2 = findViewById(R.id.edmk2);
        Button btndangky = findViewById(R.id.btndangky);
        ImageView mat1=findViewById(R.id.mat1);
        ImageView mat2=findViewById(R.id.mat2);

        final boolean[] isPasswordVisible = {false};
        mat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible[0]) {
                    edmk.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mat1.setImageResource(R.drawable.mat_eye_24);
                    isPasswordVisible[0] = false;
                } else {
                    edmk.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mat1.setImageResource(R.drawable.mat_eye_24);
                    isPasswordVisible[0] = true;
                }

            }});


        mat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible[0]) {
                    edmk2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mat2.setImageResource(R.drawable.mat_eye_24);
                    isPasswordVisible[0] = false;
                } else {
                    edmk2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mat2.setImageResource(R.drawable.mat_eye_24);
                    isPasswordVisible[0] = true;
                }

            }});
        nguoiDungDao nguoidungdao=new nguoiDungDao(this);
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taikhoan = eddn.getText().toString();
                String matkhau = edmk.getText().toString();
                String matkhau2 = edmk2.getText().toString();
                if (TextUtils.isEmpty(taikhoan) | TextUtils.isEmpty(matkhau) | TextUtils.isEmpty(matkhau2)) {
                    Toast.makeText(dang_ky.this, "Điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    boolean check=nguoidungdao.luumk(taikhoan,matkhau);

                    if (matkhau.equals(matkhau2)||check) {
                            Toast.makeText(dang_ky.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            //gửi ữ liệu đến màn2
                            finish();
                    } else {
                        Toast.makeText(dang_ky.this, "Đăng ký không thành công", Toast.LENGTH_SHORT).show();
                    }

                }
//                Tạo một đối tượng User mới với thông tin này

            }
        });
        btnveman2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(dang_ky.this, dang_nhap.class);
                startActivity(i);
            }
        });
//        // Lấy thông tin từ các EditText trên giao diện đăng ký
//        String taikhoan = eddn.getText().toString();
//        String matkhau = edmk.getText().toString();
//// Tạo một đối tượng User mới với thông tin này
//        User user = new User(taikhoan, matkhau);
//// Lưu thông tin User vào file văn bản bằng cách sử dụng FileOutputStream và ObjectOutputStream
//        try {
//            FileOutputStream fos = openFileOutput("filethong_tin.txt", Context.MODE_PRIVATE);
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            oos.writeObject(user);
//            oos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}