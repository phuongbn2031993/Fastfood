package com.example.phuongbnse61101.fastfood_prm391.view.activity.activity_taikhoan;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phuongbnse61101.fastfood_prm391.R;
import com.example.phuongbnse61101.fastfood_prm391.model.KhachHang;
import com.example.phuongbnse61101.fastfood_prm391.database.DBManager;
import com.example.phuongbnse61101.fastfood_prm391.util.Constan;
import com.example.phuongbnse61101.fastfood_prm391.util.SharedPrefKhachhang;
import com.example.phuongbnse61101.fastfood_prm391.view.activity.activity_main_page;

public class activity_caidat extends Activity implements View.OnClickListener {

    private TextView tvDoimk, tvDoidiachi, tvDangxuat, btnCapnhapMk, btnCapnhapDc;
    private EditText edtDoidc, edtDoimk, edtXnMk, edtMkCu;
    private LinearLayout llDoimk, llDoidc;
    private ImageView btnThoat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caidat);

        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        btnCapnhapMk.setOnClickListener(this);
        btnCapnhapDc.setOnClickListener(this);
        tvDoidiachi.setOnClickListener(this);
        tvDangxuat.setOnClickListener(this);
        tvDoimk.setOnClickListener(this);
        btnThoat.setOnClickListener(this);
    }

    private void initData() {


    }

    private void initView() {
        tvDoimk = findViewById(R.id.tv_doimk);
        tvDoidiachi = findViewById(R.id.tv_doi_diachi);
        tvDangxuat = findViewById(R.id.tv_dangxuat);

        btnCapnhapMk = findViewById(R.id.btn_capnhap_mk);
        btnCapnhapDc = findViewById(R.id.btn_capnhap_diachi);
        edtDoidc = findViewById(R.id.edt_diachi_moi);
        edtDoimk = findViewById(R.id.edt_mk_moi);
        edtXnMk = findViewById(R.id.edt_xnmk_moi);
        edtMkCu = findViewById(R.id.edt_mk_cu);
        llDoidc = findViewById(R.id.ll_doidiachi);
        llDoimk = findViewById(R.id.ll_doimk);
        btnThoat = findViewById(R.id.btn_thoat);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_capnhap_diachi:
                xulyDodiachi();
                break;

            case R.id.btn_capnhap_mk:
                xulyDoiMk();
                break;

            case R.id.tv_doimk:
                llDoimk.setVisibility(View.VISIBLE);
                llDoidc.setVisibility(View.GONE);
                break;

            case R.id.tv_doi_diachi:
                llDoimk.setVisibility(View.GONE);
                llDoidc.setVisibility(View.VISIBLE);
                edtDoidc.setText(""+activity_main_page.khachHang.getDiachi());
                break;

            case R.id.tv_dangxuat:
                xulyDangXuat(view);

                break;
            case R.id.btn_thoat:
                finish();
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
                break;
        }
    }

    private void xulyDodiachi() {
        String diachimoi = edtDoidc.getText().toString();
        if (!diachimoi.isEmpty()) {
            //update địa chỉ
            KhachHang khachHang = activity_main_page.khachHang;
            khachHang.setDiachi(diachimoi);
            DBManager db = new DBManager(this);
            int result = db.updateKhachhang(khachHang);
            finish();
            overridePendingTransition(R.anim.left_in, R.anim.right_out);
            if (result >= 1)
                Toast.makeText(activity_caidat.this, "Cập nhập thành công!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(activity_caidat.this, "Cập nhập thất bại!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
        }
    }

    private void xulyDoiMk() {
        KhachHang khachHang = activity_main_page.khachHang;
        String mk_moi = edtDoimk.getText().toString();
        String mk_cu = edtMkCu.getText().toString();
        String xn_mk = edtXnMk.getText().toString();
        if(mk_cu.equals(khachHang.getPass())) {
            if (!mk_moi.isEmpty() && !xn_mk.isEmpty()) {
                if (mk_moi.equals(xn_mk)) {
                    //update mật khẩu
                    DBManager db = new DBManager(this);
                    System.out.println("..................");
                    System.out.println(db.getKhachHangById("" + khachHang.getId()).getMail());
                    System.out.println(db.getKhachHangById("" + khachHang.getId()).getPass());
                    khachHang.setPass(mk_moi);
                    System.out.println("mk moi: " + khachHang.getPass());
                    int result = db.updateKhachhang(khachHang);
                    System.out.println("======================================");
                    System.out.println(result);
                    System.out.println(khachHang.mail);
                    System.out.println(db.getKhachHangById("" + khachHang.getId()).getPass());
                    System.out.println("=============================================================");
                    if (result >= 1) {
                        Toast.makeText(activity_caidat.this, "Cập nhập thành công!", Toast.LENGTH_SHORT).show();
                        finish();
                        overridePendingTransition(R.anim.left_in, R.anim.right_out);
                    } else
                        Toast.makeText(activity_caidat.this, "Cập nhập thất bại!", Toast.LENGTH_SHORT).show();

                } else
                    Toast.makeText(this, "Mật khẩu không khớp, vui lòng nhập lại", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Mật khẩu cũ không chính xác, vui lòng thử lại.", Toast.LENGTH_SHORT).show();
        }
    }

    private void xulyDangXuat(View view) {
        llDoimk.setVisibility(View.GONE);
        llDoidc.setVisibility(View.GONE);
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Bạn muốn đăng xuất tài khoản?");
        dialog.setNegativeButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //xử lý đăng xuất
            //    SharedPrefKhachhang.getInstance(activity_main_page.khachHang.getMail()).clear();
                SharedPrefKhachhang.getInstance().clear();
                activity_main_page.khachHang = null;
                activity_main_page.isDangNhap = false;
                Toast.makeText(activity_caidat.this, "Đăng xuất thành công!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity_caidat.this, activity_main_page.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in,R.anim.left_out);

            }
        });
        dialog.setPositiveButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.show();
    }
}
