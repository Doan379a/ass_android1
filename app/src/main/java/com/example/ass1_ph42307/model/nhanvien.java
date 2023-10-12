package com.example.ass1_ph42307.model;

public class nhanvien {
    private String msv;
    private String name;
    private String phongban;

    public nhanvien() {
    }

    public nhanvien(String msv, String name, String phongban) {
        this.msv = msv;
        this.name = name;
        this.phongban = phongban;
    }

    public String getMsv() {
        return msv;
    }

    public void setMsv(String msv) {
        this.msv = msv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getphongban() {
        return phongban;
    }

    public void setphongban(String phongban) {
        this.phongban = phongban;
    }
}
