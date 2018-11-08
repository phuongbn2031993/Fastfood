package com.example.phuongbnse61101.fastfood_prm391.view.activity.activity_taikhoan;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phuongbnse61101.fastfood_prm391.R;
import com.example.phuongbnse61101.fastfood_prm391.model.KhachHang;
import com.example.phuongbnse61101.fastfood_prm391.database.DBManager;

import java.util.List;

public class activity_quenpass extends Activity {

    private EditText edtHoten, edtMail, edtSdt;
    private TextView btnLayMK, tvMatkhau;
    private LinearLayout llMatkhaucu;
    private ImageView btnThoat;
    private List<KhachHang> userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quenpass);

        initView();

        btnLayMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layMatKhau(edtHoten.getText().toString().trim(),
                        edtSdt.getText().toString().trim(),
                        edtMail.getText().toString().trim());
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.left_in,R.anim.right_out);
            }
        });
    }

    private void layMatKhau(final String hoten, final String sdt, final String mail) {
       DBManager db = new DBManager(this);
        userList = db.getAllKhachHang();
        boolean check = false;
        KhachHang khachHang = null;
        for (KhachHang kh : userList){
            if(kh.getHoten().equalsIgnoreCase(hoten)
                    && kh.getMail().equalsIgnoreCase(mail)
                    && kh.getSdt().equals(sdt)){
                check = true;
                khachHang = kh;
                continue;
            }
        }
        if(check){
            llMatkhaucu.setVisibility(View.VISIBLE);
            tvMatkhau.setText(khachHang.getPass());
        }else {
            Toast.makeText(activity_quenpass.this, "Thông tin bạn nhập không chinh xác, vui lòng thử lại", Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        edtHoten = findViewById(R.id.edt_hoten);
        edtMail = findViewById(R.id.edt_email);
        edtSdt = findViewById(R.id.edt_sdt);

        btnLayMK = findViewById(R.id.btn_laylaimk);
        tvMatkhau = findViewById(R.id.tv_matkhau);
        llMatkhaucu = findViewById(R.id.ll_mkcu);
        btnThoat = findViewById(R.id.btn_thoat);
    }
}
