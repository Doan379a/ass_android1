package com.example.ass1_ph42307.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(@Nullable Context context){
        super(context,"QLDL",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String dnNguoiDung="CREATE TABLE NGUOIDUNG(tendangnhap TEXT PRIMARY KEY,matkhau TEXT)";
   db.execSQL(dnNguoiDung);

        //////
        String tkNguoiDung="INSERT INTO NGUOIDUNG VALUES('doandt','1234')";
        db.execSQL(tkNguoiDung);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
if (oldVersion!=newVersion){
    db.execSQL("DROP TABLE IF EXISTS NGUOIDUNG");
    onCreate(db);
}
    }
}
