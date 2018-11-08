package com.example.phuongbnse61101.fastfood_prm391.model;

public class KhachHang {
    public int id;
    public String hoten;
    public String sdt;
    public String mail;
    public String pass;
    public String diachi;

    public int getId() {
        return id;
    }
    public KhachHang(){

    }
    public KhachHang(int id, String hoten, String sdt, String mail, String pass, String diachi) {
        this.id = id;
        this.hoten = hoten;
        this.sdt = sdt;
        this.mail = mail;
        this.pass = pass;
        this.diachi = diachi;
    }
    public KhachHang(String hoten, String sdt, String mail, String pass, String diachi) {
        this.hoten = hoten;
        this.sdt = sdt;
        this.mail = mail;
        this.pass = pass;
        this.diachi = diachi;
    }

    public String getHoten() {
        return hoten;
    }

    public String getMail() {
        return mail;
    }

    public String getPass() {
        return pass;
    }

    public String getDiachi() {
        return diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
}
