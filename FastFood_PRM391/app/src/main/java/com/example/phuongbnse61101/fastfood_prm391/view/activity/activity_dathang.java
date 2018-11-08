package com.example.phuongbnse61101.fastfood_prm391.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phuongbnse61101.fastfood_prm391.R;
import com.example.phuongbnse61101.fastfood_prm391.adapter.DatHangAdapter;
import com.example.phuongbnse61101.fastfood_prm391.adapter.GioHangAdapter;
import com.example.phuongbnse61101.fastfood_prm391.model.Donhang;
import com.example.phuongbnse61101.fastfood_prm391.database.DBManager;
import com.example.phuongbnse61101.fastfood_prm391.model.GioHang;
import com.example.phuongbnse61101.fastfood_prm391.util.Constan;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class activity_dathang extends AppCompatActivity implements View.OnClickListener {

    private TextView tvDiachinhanhang, tvSoluongsp, tvTong, tvPhivc, tvTongtien, btnDathang;
    private ImageView btnThoat;
    public static List<GioHang> mangGioHang;

    private ListView lvSanpham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dathang);

        initView();
        initData();
        initEvent();
    }

    private void initEvent() {

        btnDathang.setOnClickListener(this);
        btnThoat.setOnClickListener(this);
    }

    private void initData() {
        DatHangAdapter adapter = new DatHangAdapter(this, activity_dathang.mangGioHang);
        lvSanpham.setAdapter(adapter);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvDiachinhanhang.setText("Khách hàng: "+ activity_main_page.khachHang.getHoten()+", Địa chỉ: "+activity_main_page.khachHang.getDiachi());
        int phivc = getIntent().getIntExtra(Constan.KEY_PHI_VC,0);
        tvPhivc.setText(decimalFormat.format(phivc)+" Đ");
        tvSoluongsp.setText("Tạm tính ("+activity_main_page.mangGioHang.size()+" sản phẩm)");
        long tongtien = 0;
        for (int i = 0; i < activity_main_page.mangGioHang.size(); i++) {
            tongtien += activity_main_page.mangGioHang.get(i).getGiasp();
        }
        tvTong.setText(decimalFormat.format(tongtien)+" Đ");
        tvTongtien.setText(decimalFormat.format(tongtien+phivc)+" Đ");
    }

    private void initView() {
        tvDiachinhanhang = findViewById(R.id.tv_diachi_giaohang);
        tvSoluongsp = findViewById(R.id.tv_soluongsp);
        tvTong = findViewById(R.id.tv_tongtien_tt);
        tvPhivc = findViewById(R.id.tv_phivc);
        tvTongtien = findViewById(R.id.tv_tongtien);
        btnDathang = findViewById(R.id.btn_dathang);
        btnThoat = findViewById(R.id.btn_thoat);
        lvSanpham = findViewById(R.id.lv_dathang);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_thoat:
                finish();
                break;
            case R.id.btn_dathang:
                xulyDathang();
                break;
        }
    }

    private void xulyDathang() {
        DBManager db = new DBManager(this);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ngaydathang = dateFormat.format(new Date());


        List<Donhang> donhangList = new ArrayList<Donhang>();
        for (int i = 0; i<activity_main_page.mangGioHang.size();i++){

            Donhang donhang = new Donhang();
            try {
                donhang.setMakh(""+ activity_main_page.khachHang.getId());
                donhang.setMasp("" + activity_main_page.mangGioHang.get(i).getIdsp());
                donhang.setTensp("" + activity_main_page.mangGioHang.get(i).getTensp());
                donhang.setGiasp("" + activity_main_page.mangGioHang.get(i).getGiasp());
                donhang.setSoluongsp("" + activity_main_page.mangGioHang.get(i).getSoluong());
                donhang.setNgaydathang("" + ngaydathang);

            } catch (Exception e) {
                e.printStackTrace();
            }
            donhangList.add(donhang);
        }
        for (Donhang donhang : donhangList) {
            db.addDonHang(donhang);
        }
        Toast.makeText(activity_dathang.this, "Đặt hàng thành công!", Toast.LENGTH_SHORT).show();
        activity_main_page.mangGioHang.clear();
        finish();
        overridePendingTransition(R.anim.left_in,R.anim.right_out);
    }
}
