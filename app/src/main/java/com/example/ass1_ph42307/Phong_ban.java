package com.example.ass1_ph42307;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import com.example.ass1_ph42307.adapter.pbadapter;
import com.example.ass1_ph42307.model.phongban;

import java.util.ArrayList;

public class Phong_ban extends AppCompatActivity {
    Intent i = new Intent();
    private ArrayList<phongban> list = new ArrayList<>();
    SearchView searchView;
    private pbadapter adapter = new pbadapter(this, list);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phong_ban);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);//gán toolbar thay option

        ImageView immui_ten = findViewById(R.id.immui_ten);
        ListView listpb = findViewById(R.id.listpb);
        list.add(new phongban(R.drawable.nha_red, "Nhân sự"));
        list.add(new phongban(R.drawable.nha_red, "Hành chính"));
        list.add(new phongban(R.drawable.nha_red, "Đào tạo"));

        listpb.setAdapter(adapter);

        immui_ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(Phong_ban.this, Trang_chu.class);
                startActivity(i);
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
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.them){
            i=new Intent(Phong_ban.this,them2.class);
            startActivity(i);
            Toast.makeText(this, "Thêm ", Toast.LENGTH_SHORT).show();
        }else if (item.getItemId()==R.id.timkiem){
            Toast.makeText(this, "tìm kiếm ", Toast.LENGTH_SHORT).show();
        }else if (item.getItemId()==R.id.diemdanh){
            Toast.makeText(this, "điểm danh", Toast.LENGTH_SHORT).show();
        }else {
            i=new Intent(Phong_ban.this,dang_nhap.class);
            startActivity(i);
            finish();
            Toast.makeText(this, "Đăng xuất", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}