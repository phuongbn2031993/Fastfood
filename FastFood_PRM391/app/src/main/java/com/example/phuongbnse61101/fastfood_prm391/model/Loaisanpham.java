package com.example.phuongbnse61101.fastfood_prm391.model;

public class Loaisanpham {
    public String idLoaisp;
    public String tenLoaisp;

    public Loaisanpham() {
    }

    public String hinhanhLoaisp;

    public Loaisanpham(String tenLoaisp, String hinhanhLoaisp) {
        this.tenLoaisp = tenLoaisp;
        this.hinhanhLoaisp = hinhanhLoaisp;
    }

    public String getIdLoaisp() {
        return idLoaisp;
    }

    public void setIdLoaisp(String idLoaisp) {
        this.idLoaisp = idLoaisp;
    }

    public void setTenLoaisp(String tenLoaisp) {
        this.tenLoaisp = tenLoaisp;
    }

    public void setHinhanhLoaisp(String hinhanhLoaisp) {
        this.hinhanhLoaisp = hinhanhLoaisp;
    }

    public String getTenLoaisp() {
        return tenLoaisp;

    }

    public String getHinhanhLoaisp() {
        return hinhanhLoaisp;
    }
}
