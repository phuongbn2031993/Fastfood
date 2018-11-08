package com.example.phuongbnse61101.fastfood_prm391.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phuongbnse61101.fastfood_prm391.R;
import com.example.phuongbnse61101.fastfood_prm391.adapter.GioHangAdapter;
import com.example.phuongbnse61101.fastfood_prm391.model.ListGioHang;
import com.example.phuongbnse61101.fastfood_prm391.model.Sanpham;
import com.example.phuongbnse61101.fastfood_prm391.model.GioHang;
import com.example.phuongbnse61101.fastfood_prm391.util.Constan;
import com.example.phuongbnse61101.fastfood_prm391.util.SharedPrefs;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class activity_chitiet_san_pham extends Activity {

    private ImageView ivChitiet, btnThoat, btnGiohang;
    private TextView tvTenchitiet, tvGiachitiet, tv_motachitietsp;
    private Spinner spinner;
    private TextView btnThemGioHang, soluongtronggiohang;

    int id = 0;
    String tenchitiet = "";
    int giachitiet = 0;
    String hinhanhchitiet = "";
    String motachitiet = "";
    int idloaisp = 0;
    int soluongsp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_san_pham);

        initView();
        getInfo();
        catEventSpinner();
        initEvient();
    }

    private void initEvient() {
        btnThemGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activity_main_page.mangGioHang == null) {
                    activity_main_page.mangGioHang = new ArrayList<GioHang>();
                }
                if(SharedPrefs.getInstance().get(Constan.GIOHANG_SHAREPRE, new ArrayList<GioHang>().getClass())!=null){

                    List<GioHang> list = new ArrayList<>();
                    GioHang[] aa = SharedPrefs.getInstance().get(Constan.GIOHANG_SHAREPRE, GioHang[].class);
                    activity_main_page.mangGioHang.clear();
                    for (GioHang aaa : aa){
                        activity_main_page.mangGioHang.add(aaa);
                    }
                    int sl = Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exit = false;
                    for (int i = 0; i < activity_main_page.mangGioHang.size(); i++) {
                        if (activity_main_page.mangGioHang.get(i).getIdsp() == id) {
                            activity_main_page.mangGioHang.get(i).setSoluong(activity_main_page.mangGioHang.get(i).getSoluong() + sl);
                            if (activity_main_page.mangGioHang.get(i).getSoluong() >= 10) {
                                activity_main_page.mangGioHang.get(i).setSoluong(10);
                            }
                            activity_main_page.mangGioHang.get(i).setGiasp(giachitiet * activity_main_page.mangGioHang.get(i).getSoluong());
                            exit = true;
                        }
                    }
                    if (!exit) {
                        int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                        long giamoi = soluong * giachitiet;
                        activity_main_page.mangGioHang.add(new GioHang(id, tenchitiet, giamoi, hinhanhchitiet, soluong));
                    }
                } else {
                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    long giamoi = soluong * giachitiet;
                    activity_main_page.mangGioHang.add(new GioHang(id, tenchitiet, giamoi, hinhanhchitiet, soluong));
                }


                Toast.makeText(activity_chitiet_san_pham.this, "Thêm vào giỏ hàng thành công!", Toast.LENGTH_SHORT).show();
                SharedPrefs.getInstance().clear();
                SharedPrefs.getInstance().put(Constan.GIOHANG_SHAREPRE, activity_main_page.mangGioHang);
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnGiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //so luong tren spiner
    private void catEventSpinner() {
        Integer[] soluong = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, soluong);
        spinner.setAdapter(arrayAdapter);
    }

    private void getInfo() {
        Sanpham sanPham = (Sanpham) getIntent().getSerializableExtra(Constan.THONGTIN_SP);
        id = Integer.parseInt(sanPham.getId());
        tenchitiet = sanPham.getTensp();
        giachitiet = Integer.parseInt(sanPham.getGiasp());
        hinhanhchitiet = sanPham.getHinhanhsp();
        motachitiet = sanPham.getMotasp();
        idloaisp = Integer.parseInt(sanPham.getIdLoaisp());

        tvTenchitiet.setText(tenchitiet);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvGiachitiet.setText("Giá: " + decimalFormat.format(giachitiet) + " Đ");
        tv_motachitietsp.setText(motachitiet);
        Picasso.get().load(hinhanhchitiet)
                .placeholder(R.drawable.loading)
                .error(R.drawable.no_image)
                .into(ivChitiet);
    }

    private void initView() {

        ivChitiet = findViewById(R.id.iv_chitietsp);
        tvTenchitiet = findViewById(R.id.tv_tenchitietsp);
        tvGiachitiet = findViewById(R.id.tv_giachitietsp);
        tv_motachitietsp = findViewById(R.id.tv_motachitietsp);
        spinner = findViewById(R.id.spinner);
        btnThemGioHang = findViewById(R.id.btn_datmua);
        btnGiohang = findViewById(R.id.btn_giohang);
        btnThoat = findViewById(R.id.btn_thoat);
        soluongtronggiohang = findViewById(R.id.soluongtronggiohang);
    }

    @Override
    public void finish() {
        super.finish();
        setResult(RESULT_OK);
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }
}
