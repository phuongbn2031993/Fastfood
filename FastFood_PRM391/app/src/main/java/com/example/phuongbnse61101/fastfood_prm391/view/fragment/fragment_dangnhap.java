package com.example.phuongbnse61101.fastfood_prm391.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phuongbnse61101.fastfood_prm391.R;
import com.example.phuongbnse61101.fastfood_prm391.model.KhachHang;
import com.example.phuongbnse61101.fastfood_prm391.database.DBManager;
import com.example.phuongbnse61101.fastfood_prm391.util.Constan;
import com.example.phuongbnse61101.fastfood_prm391.util.SharedPrefKhachhang;
import com.example.phuongbnse61101.fastfood_prm391.util.SharedPrefs;
import com.example.phuongbnse61101.fastfood_prm391.view.activity.activity_main_page;
import com.example.phuongbnse61101.fastfood_prm391.view.activity.activity_taikhoan.activity_quenpass;
import com.google.android.gms.common.SignInButton;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_dangnhap extends Fragment implements View.OnClickListener {
    private EditText edtMail, edtPass;
    private TextView tvQuenmk, btnDangnhap;
    private LinearLayout btnLoginFb;
    private SignInButton btnSignGoogle;

    private List<KhachHang> khachHangList;

    public fragment_dangnhap() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dangnhap, container, false);

        initView(view);

        initData();

        iniEvent();
        // Inflate the layout for this fragment
        return view;
    }

    private void iniEvent() {
        btnDangnhap.setOnClickListener(this);
        tvQuenmk.setOnClickListener(this);
        btnLoginFb.setOnClickListener(this);
        btnSignGoogle.setOnClickListener(this);
    }

    private void initData() {
        DBManager db = new DBManager(getActivity());
        khachHangList = db.getAllKhachHang();
    }

    private void initView(View view) {
        edtMail = view.findViewById(R.id.edt_email);
        edtPass = view.findViewById(R.id.edt_matkhau);
        tvQuenmk = view.findViewById(R.id.tv_quenmk);
        btnDangnhap = view.findViewById(R.id.btn_dangnhap);
        btnLoginFb = view.findViewById(R.id.ll_loginfb);
        btnSignGoogle = view.findViewById(R.id.btn_signin);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_dangnhap:
                //xử lý đăng nhập
                String user = edtMail.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();

                if(!khachHangList.isEmpty()){
                    for (int i = 0; i<khachHangList.size(); i++){
                        if(khachHangList.get(i).getMail().equalsIgnoreCase(user) && khachHangList.get(i).getPass().equals(pass)){
                            activity_main_page.isDangNhap = true;
                            activity_main_page.khachHang = khachHangList.get(i);
                            continue;
                        }
                    }
                }
            //    SharedPrefKhachhang.getInstance(activity_main_page.khachHang.getMail()).put(Constan.KHACHHANG_SHAREPRE, activity_main_page.khachHang);
                SharedPrefKhachhang.getInstance().put(Constan.KHACHHANG_SHAREPRE, activity_main_page.khachHang);
                if(activity_main_page.isDangNhap){
                Toast.makeText(getActivity(), "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(getActivity(), activity_main_page.class);
                getActivity().startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_in,R.anim.left_out);

            }else{
                Toast.makeText(getActivity(), "Đăng nhập thất bại, sai tên đăng nhập hoặc mật khẩu.\nNếu bạn chưa có tài khoản vui lòng đăng ký.", Toast.LENGTH_SHORT).show();
            }

                break;

            case R.id.tv_quenmk:
                getActivity().startActivity(new Intent(getActivity(), activity_quenpass.class));
                getActivity().overridePendingTransition(R.anim.right_in,R.anim.left_out);
                break;
            case R.id.ll_loginfb:
                Toast.makeText(getActivity(), "App đang nâng cấp, vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_signin:
                Toast.makeText(getActivity(), "App đang nâng cấp, vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
