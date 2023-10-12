package com.example.ass1_ph42307.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ass1_ph42307.database.DbHelper;

public class nguoiDungDao {
    private DbHelper dbHelper;

    public nguoiDungDao(Context context) {
        dbHelper =new DbHelper(context);
    }
    public boolean checkLogin(String usename,String password){
        SQLiteDatabase sqLiteDatabase=dbHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT*FROM NGUOIDUNG WHERE tendangnhap=? AND matkhau=?",new String[]{usename,password});
        if (cursor.getCount()>0) {
            return true;
        }
        return false;
    }
    public boolean luumk(String usename,String password){
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("tendangnhap",usename);
        values.put("matkhau",password);
        long check=sqLiteDatabase.insert("NGUOIDUNG",null,values);
        return check !=1;
    }
}
