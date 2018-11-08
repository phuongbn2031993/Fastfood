package com.example.phuongbnse61101.fastfood_prm391.model;

public class Donhang {
    public String id;

    public String makh;

    public String masp;

    public String tensp;

    public Donhang() {
    }

    public Donhang(String makh, String masp, String tensp, String giasp, String soluongsp, String ngaydathang) {
        this.makh = makh;
        this.masp = masp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.soluongsp = soluongsp;
        this.ngaydathang = ngaydathang;
    }

    public String giasp;

    public String soluongsp;

    public String ngaydathang;

    public void setId(String id) {
        this.id = id;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public void setGiasp(String giasp) {
        this.giasp = giasp;
    }

    public void setSoluongsp(String soluongsp) {
        this.soluongsp = soluongsp;
    }

    public void setNgaydathang(String ngaydathang) {
        this.ngaydathang = ngaydathang;
    }

    public String getId() {
        return id;
    }

    public String getMakh() {
        return makh;
    }

    public String getMasp() {
        return masp;
    }

    public String getTensp() {
        return tensp;
    }

    public String getGiasp() {
        return giasp;
    }

    public String getSoluongsp() {
        return soluongsp;
    }

    public String getNgaydathang() {
        return ngaydathang;
    }
}
