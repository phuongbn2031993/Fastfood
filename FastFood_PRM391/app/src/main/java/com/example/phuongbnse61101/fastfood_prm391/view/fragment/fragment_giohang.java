package com.example.phuongbnse61101.fastfood_prm391.view.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phuongbnse61101.fastfood_prm391.R;
import com.example.phuongbnse61101.fastfood_prm391.adapter.GioHangAdapter;
import com.example.phuongbnse61101.fastfood_prm391.model.ListGioHang;
import com.example.phuongbnse61101.fastfood_prm391.model.Sanpham;
import com.example.phuongbnse61101.fastfood_prm391.model.GioHang;
import com.example.phuongbnse61101.fastfood_prm391.util.Constan;
import com.example.phuongbnse61101.fastfood_prm391.util.SharedPrefs;
import com.example.phuongbnse61101.fastfood_prm391.view.activity.activity_dathang;
import com.example.phuongbnse61101.fastfood_prm391.view.activity.activity_login;
import com.example.phuongbnse61101.fastfood_prm391.view.activity.activity_main_page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_giohang extends Fragment  implements View.OnClickListener{

    private TextView tvDiachi, tvTiepkiem, tvTieuchuan, tvTongtien, btnThanhtoan, tvTrong;
    private LinearLayout llThongtin;
    private RelativeLayout rlTieuchuan, rlTiepkiem;
    private ImageView ivTickTc, ivTickTk;
    private ListView lvSPGiohang;

    private List<Sanpham> giohangList;

    private int phiVc = 15000;
    public fragment_giohang() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_giohang, container, false);
        initView(view);
        initData();
        initEvent();
        // Inflate the layout for this fragment
        return view;
    }
    private void initEvent() {
        btnThanhtoan.setOnClickListener(this);
        rlTieuchuan.setOnClickListener(this);
        rlTiepkiem.setOnClickListener(this);
    }

    private void initData() {
        if(activity_main_page.mangGioHang==null){
            activity_main_page.mangGioHang = new ArrayList<GioHang>();
            if(SharedPrefs.getInstance().get(Constan.GIOHANG_SHAREPRE, new ArrayList<GioHang>().getClass())!=null){

                List<GioHang> list = new ArrayList<>();
                GioHang[] aa = SharedPrefs.getInstance().get(Constan.GIOHANG_SHAREPRE, GioHang[].class);
                for (GioHang aaa : aa){
                    activity_main_page.mangGioHang.add(aaa);
                }
            }
        }

        if (activity_main_page.mangGioHang.size() <= 0) {
            tvTrong.setVisibility(View.VISIBLE);
            llThongtin.setVisibility(View.GONE);
            tvTongtien.setText("0 Đ");
        } else {
            tvTrong.setVisibility(View.GONE);
            llThongtin.setVisibility(View.VISIBLE);

            GioHangAdapter adapter = new GioHangAdapter(getActivity(), activity_main_page.mangGioHang);
            lvSPGiohang.setAdapter(adapter);
            tvTongtien.setText(loadTongCong(activity_main_page.mangGioHang));
            tvTiepkiem.setText(ngayNhanHang(60));
            tvTieuchuan.setText(ngayNhanHang(30));
        }
        if(activity_main_page.isDangNhap) tvDiachi.setText(activity_main_page.khachHang.getDiachi());
    }

    private String loadTongCong(List<GioHang> list) {
        long tongtien = 0;
        for (int i = 0; i < list.size(); i++) {
            tongtien += list.get(i).getGiasp();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        return decimalFormat.format(tongtien) + " Đ";
    }

    private void initView(View view) {
        tvDiachi = view.findViewById(R.id.tv_diachi);
        tvTiepkiem = view.findViewById(R.id.tv_thoigian_tiepkiem);
        tvTieuchuan = view.findViewById(R.id.tv_thoigian_tieuchuan);
        tvTongtien = view.findViewById(R.id.tv_tongtien);
        btnThanhtoan = view.findViewById(R.id.btn_thanhtoan);
        llThongtin = view.findViewById(R.id.ll_thongtin);
        lvSPGiohang = view.findViewById(R.id.lv_sp_giohang);
        tvTrong = view.findViewById(R.id.tv_trong);
        rlTieuchuan = view.findViewById(R.id.rl_tieuchuan);
        rlTiepkiem = view.findViewById(R.id.rl_tiepkiem);

        ivTickTc = view.findViewById(R.id.iv_tick_tc);
        ivTickTk = view.findViewById(R.id.iv_tick_tk);
    }

    private String ngayNhanHang(int thoigiangiaohang) {
        Date date1 = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        calendar.set(Calendar.MINUTE, thoigiangiaohang+date1.getMinutes());
        String timeStamp1 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(calendar.getTime());

        Date date = convertStringToDate(timeStamp1);
        String nam = (String) DateFormat.format("yyyy", date);
        String thang = (String) DateFormat.format("MM", date);
        String ngay = (String) DateFormat.format("dd", date);
        String gio = (String) DateFormat.format("HH", date);
        String phut = (String) DateFormat.format("mm", date);
        String giay = (String) DateFormat.format("ss", date);


        String st = "Nhận trước " + gio + " giờ " + phut + " phút "  + " ngày " +
                ngay + " tháng " + thang + " năm " + nam;
        return st;
    }

    //chuyển đổi string lại thành ngày tháng
    public static Date convertStringToDate(String datetime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        try {
            Date date = dateFormat.parse(datetime);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onClick(View view) {
        if (activity_main_page.mangGioHang.size() <= 0) {
            Toast.makeText(getActivity(), "Giỏ hàng đang trống, vui lòng quay lại mua hàng.", Toast.LENGTH_SHORT).show();
        } else {
            switch (view.getId()) {
                case R.id.btn_thanhtoan:
                    xulyThanhtoan();
                    break;
                case R.id.rl_tieuchuan:
                    ivTickTc.setVisibility(View.VISIBLE);
                    ivTickTk.setVisibility(View.GONE);
                    phiVc = 25000;
                    break;
                case R.id.rl_tiepkiem:
                    ivTickTc.setVisibility(View.GONE);
                    ivTickTk.setVisibility(View.VISIBLE);
                    phiVc = 15000;
                    break;
            }
        }
    }

    private void xulyThanhtoan() {
        //chưa đăng nhập
        if (!activity_main_page.isDangNhap) {
            //chưa đăng nhập thì mở activity đăng ký / đăng nhập
            final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
            dialog.setTitle("Hãy đăng nhập tài khoản của bạn");
            dialog.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(getActivity(), activity_login.class);
                    intent.putExtra(Constan.KEY_GIOHANG_DANGNHAP,true);
                    getActivity().startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.right_in,R.anim.left_out);
                }
            });
            dialog.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            dialog.show();
        } else {
            //thanh toán
            Intent intent = new Intent(getActivity(), activity_dathang.class);
            intent.putExtra(Constan.KEY_PHI_VC,phiVc);
            getActivity().startActivity(intent);
            getActivity().overridePendingTransition(R.anim.right_in,R.anim.left_out);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }
}
