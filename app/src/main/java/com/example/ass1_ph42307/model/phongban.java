package com.example.ass1_ph42307.model;

public class phongban {
    private int hinh;
    private String tencs;

    public phongban() {
    }

    public phongban(int hinh, String tencs) {
        this.hinh = hinh;
        this.tencs = tencs;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    public String getTencs() {
        return tencs;
    }

    public void setTencs(String tencs) {
        this.tencs = tencs;
    }
}
