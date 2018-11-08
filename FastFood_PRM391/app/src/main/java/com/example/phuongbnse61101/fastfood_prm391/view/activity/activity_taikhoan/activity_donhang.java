package com.example.phuongbnse61101.fastfood_prm391.view.activity.activity_taikhoan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.phuongbnse61101.fastfood_prm391.adapter.DonHangAdapter;
import com.example.phuongbnse61101.fastfood_prm391.model.Donhang;
import com.example.phuongbnse61101.fastfood_prm391.R;
import com.example.phuongbnse61101.fastfood_prm391.database.DBManager;
import com.example.phuongbnse61101.fastfood_prm391.view.activity.activity_main_page;

import java.util.ArrayList;
import java.util.List;

public class activity_donhang extends AppCompatActivity {
    private ListView lvDonhangcuatoi;
    private List<Donhang> donhangList;
    private DonHangAdapter adapter;
    private TextView tvDonhangtrong;
    private ImageView btnThoat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donhang);

        lvDonhangcuatoi = findViewById(R.id.lv_donhangcuatoi);
        tvDonhangtrong = findViewById(R.id.tv_donhang_trong);
        btnThoat = findViewById(R.id.btn_thoat);

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
            }
        });
        donhangList = new ArrayList<>();

        if (activity_main_page.khachHang != null) {
            DBManager db = new DBManager(this);

            List<Donhang> donhangList = db.listDonhangByMaKh("" + activity_main_page.khachHang.getId());

            if(donhangList.size()>0) {
                lvDonhangcuatoi.setVisibility(View.VISIBLE);
                tvDonhangtrong.setVisibility(View.GONE);
            }
            adapter = new DonHangAdapter(activity_donhang.this, donhangList);
            lvDonhangcuatoi.setAdapter(adapter);
        }
    }
}
