package com.example.phuongbnse61101.fastfood_prm391.util;

import android.content.SharedPreferences;

import com.example.phuongbnse61101.fastfood_prm391.R;
import com.example.phuongbnse61101.fastfood_prm391.model.ItemTK;

import java.util.ArrayList;
import java.util.List;

public class Constan {
    public static final String data = "data";
    public static final String SANPHAM = "sanpham";
    public static final String LOAI_SANPHAM = "loaisanpham";
    public static final String DONHANG = "donhang";
    public static final String CHITIET_DONHANG = "chitietdonhang";
    public static final String THONGTIN_SP = "thongtinsanpham";
    public static final String KHACHHANG_SHAREPRE = "khachhang_sharepre";
    public static final String GIOHANG_SHAREPRE = "giohang_sharepre";
    public static final String KEY_PHI_VC = "phivanchuyen";

    public static final String KEY_GIOHANG_DANGNHAP = "giohang_dangnhap";

    //Chức năng trong tài khoản
    public static List<ItemTK> getListChucnang() {
        List<ItemTK> list = new ArrayList<>();
        list.add(new ItemTK(R.drawable.ic_tk_tinnhan, "Tin nhắn"));
        list.add(new ItemTK(R.drawable.ic_tk_donhangcuatoi, "Đơn hàng của tôi"));
        return list;
    }
    //Chức năng trong tài khoản
    public static List<ItemTK> getListCaidat() {
        List<ItemTK> list = new ArrayList<>();
        list.add(new ItemTK(R.drawable.ic_tk_caidat, "Cài đặt"));
        list.add(new ItemTK(R.drawable.ic_tk_chinhsach, "Chính sách"));
        list.add(new ItemTK(R.drawable.ic_tk_trogiup, "Trợ giúp"));
        return list;
    }

    public static List<String> getQuangcao() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://images.foody.vn/res/g14/132210/prof/s640x400/foody-mobile-tiktak-mb-jpg-431-635863978257443374.jpg");
        mangquangcao.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQT4b7Hd8qIrcucmiX-eLw73DQaDGf7Lilc6t-kxlLoE_OCuczUjw");
        mangquangcao.add("https://i.doanhnhansaigon.vn/2016/01/29/rau-cu-qua-dong-goi-1508425358.jpg");
        mangquangcao.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_RF-VE13XlF0nUzjoazpfGSg8NthpObwdFFMpkftJgjagtKZfcg");
        mangquangcao.add("https://hellobacsi.com/wp-content/uploads/2017/08/th%E1%BB%A9c-%C4%83n-nhanh.jpg");
        return mangquangcao;
    }
}
