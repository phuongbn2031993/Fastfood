package com.example.phuongbnse61101.fastfood_prm391.model;

import java.util.List;

public class ListGioHang {
    public static List<GioHang> gioHangList;

    public static List<GioHang> getGioHangList() {
        return gioHangList;
    }

    public void setGioHangList(List<GioHang> gioHangList) {
        this.gioHangList = gioHangList;
    }

    public ListGioHang(List<GioHang> gioHangList) {
        this.gioHangList = gioHangList;
    }
    public ListGioHang() {
    }
}
