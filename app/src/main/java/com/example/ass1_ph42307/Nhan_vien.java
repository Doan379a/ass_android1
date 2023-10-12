package com.example.ass1_ph42307;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ass1_ph42307.adapter.nhanvienadapter;
import com.example.ass1_ph42307.model.nhanvien;

import java.util.ArrayList;

public class Nhan_vien extends AppCompatActivity {
    private ArrayList<nhanvien> litsStuden = new ArrayList<>();
    SearchView searchView;
    Intent i = new Intent();
    private nhanvienadapter adapter = new nhanvienadapter(this, litsStuden);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_vien);
        ListView lvsview = findViewById(R.id.listnv);
        ImageView imgmuiten=findViewById(R.id.immui_ten);
        Button btnadd = findViewById(R.id.btnadd);
        Toolbar toolbar=findViewById(R.id.toolbarr);
        setSupportActionBar(toolbar);//gán toolbar thay option
        //them doi tuong vao list
        litsStuden.add(new nhanvien("NV001","Nguyễn Văn B","Hành chính"));
        litsStuden.add(new nhanvien("NV002","Nguyễn Văn C","Nhân sự"));
        litsStuden.add(new nhanvien("NV003","Nguyễn Văn D","Nhân sự"));
        litsStuden.add(new nhanvien("NV004","Nguyễn Văn E","Đào tạo"));
        //do du lieu len listview

        lvsview.setAdapter(adapter);
        ActivityResultLauncher<Intent> getsadd = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == 1) {
                            Intent intent = result.getData();
                            if (intent != null) {
                                Bundle bundle = intent.getExtras();
                                String msv = bundle.getString("msv");
                                String name = bundle.getString("name");
                                String phongban = bundle.getString("phongban");
                                litsStuden.add(new nhanvien(msv, name, phongban));
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                }
        );
        imgmuiten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Nhan_vien.this,Trang_chu.class);
                startActivity(i);
            }
        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Nhan_vien.this, them.class);
                getsadd.launch(intent);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mn_option,menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.timkiem).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                nhanvienadapter.get
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==R.id.them){
            i=new Intent(Nhan_vien.this,them.class);

            Toast.makeText(this, "Thêm ", Toast.LENGTH_SHORT).show();
        }else if (item.getItemId()==R.id.timkiem){
            Toast.makeText(this, "tìm kiếm ", Toast.LENGTH_SHORT).show();
        }else if (item.getItemId()==R.id.diemdanh){
            Toast.makeText(this, "điểm danh", Toast.LENGTH_SHORT).show();
        }else {
            i=new Intent(Nhan_vien.this,dang_nhap.class);
            startActivity(i);
            finish();
            Toast.makeText(this, "Đăng xuất", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}