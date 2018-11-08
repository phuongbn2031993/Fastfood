package com.example.phuongbnse61101.fastfood_prm391.model;

import java.io.Serializable;

public class Sanpham implements Serializable{
    public String id;
    public String tensp;
    public String giasp;
    public String hinhanhsp;
    public String motasp;

    public void setId(String id) {
        this.id = id;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public void setGiasp(String giasp) {
        this.giasp = giasp;
    }

    public Sanpham() {
    }

    public Sanpham(String tensp, String giasp, String hinhanhsp, String motasp, String idLoaisp) {
        this.tensp = tensp;
        this.giasp = giasp;
        this.hinhanhsp = hinhanhsp;
        this.motasp = motasp;
        this.idLoaisp = idLoaisp;
    }

    public void setHinhanhsp(String hinhanhsp) {

        this.hinhanhsp = hinhanhsp;
    }

    public void setMotasp(String motasp) {
        this.motasp = motasp;
    }

    public void setIdLoaisp(String idLoaisp) {
        this.idLoaisp = idLoaisp;
    }

    public String idLoaisp;

    public String getId() {
        return id;
    }

    public String getTensp() {
        return tensp;
    }

    public String getGiasp() {
        return giasp;
    }

    public String getHinhanhsp() {
        return hinhanhsp;
    }

    public String getMotasp() {
        return motasp;
    }

    public String getIdLoaisp() {
        return idLoaisp;
    }
}
