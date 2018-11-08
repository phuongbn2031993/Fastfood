package com.example.phuongbnse61101.fastfood_prm391.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phuongbnse61101.fastfood_prm391.R;
import com.example.phuongbnse61101.fastfood_prm391.model.KhachHang;
import com.example.phuongbnse61101.fastfood_prm391.database.DBManager;
import com.google.android.gms.common.SignInButton;

import java.util.List;

public class fragment_dangki extends Fragment implements View.OnClickListener {

    private EditText edtHoten, edtMail, edtSdt, edtMk1, edtMk2, edtDiachi;
    private TextView btnDangky;
    private CheckBox cbDieukhoan;
    private LinearLayout btnLoginFb;
    private SignInButton btnSignGoogle;
    private DBManager db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dangki, container, false);
        db = new DBManager(getActivity());
        initView(view);

        List<KhachHang> list = db.getAllKhachHang();
        System.out.println(list.size());
        System.out.println("--------------------");
        for(int i =0; i< list.size();i++){
            System.out.println(list.get(i).getId());
            System.out.println(list.get(i).getHoten());
            System.out.println("..................");
        }

        initData();

        initEvent();
        // Inflate the layout for this fragment
        return view;
    }

    private void initEvent() {
        btnDangky.setOnClickListener(this);
        btnLoginFb.setOnClickListener(this);
        btnSignGoogle.setOnClickListener(this);
    }

    private void initData() {
      // do some thing
    }

    private void initView(View view) {
        edtHoten = view.findViewById(R.id.edt_hoten);
        edtMail = view.findViewById(R.id.edt_email);
        edtSdt = view.findViewById(R.id.edt_sdt);
        edtMk1 = view.findViewById(R.id.edt_matkhau);
        edtMk2 = view.findViewById(R.id.edt_xacnhanmk);
        cbDieukhoan = view.findViewById(R.id.cb_dieukhoan);
        edtDiachi = view.findViewById(R.id.edt_diachi);

        btnDangky = view.findViewById(R.id.btn_dangnky);
        btnLoginFb = view.findViewById(R.id.ll_loginfb);
        btnSignGoogle = view.findViewById(R.id.btn_signin);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_dangnky:
                xulyDangky();
                break;
            case R.id.ll_loginfb:
                Toast.makeText(getActivity(), "App đang nâng cấp, vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_signin:
                Toast.makeText(getActivity(), "App đang nâng cấp, vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void xulyDangky() {
        String hoten = edtHoten.getText().toString().trim();
        String sdt = edtSdt.getText().toString().trim();
        String mail = edtMail.getText().toString().trim();
        String mk1 = edtMk1.getText().toString().trim();
        String mk2 = edtMk2.getText().toString().trim();
        String diachi = edtDiachi.getText().toString().trim();

        if (hoten.isEmpty() || sdt.isEmpty() || mail.isEmpty() || mk1.isEmpty() || mk2.isEmpty()) {
            Toast.makeText(getActivity(), "Vui lòng điền đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
        } else {
            if (!mk1.equals(mk2)) {
                Toast.makeText(getActivity(), "Mật khẩu không khớp, Vui lòng nhập lại.", Toast.LENGTH_SHORT).show();
            } else {
                if (!cbDieukhoan.isChecked())
                    Toast.makeText(getActivity(), "Bạn chưa chấp nhận điều khoản của chúng tôi.", Toast.LENGTH_SHORT).show();
                else {
                    KhachHang khachHang = new KhachHang(hoten, sdt, mail, mk1,diachi);

                    boolean result = db.addKhachHang(khachHang);
                    if(result){
                        Toast.makeText(getActivity(), "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "Đăng ký thất bại! Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
                    }
                    getActivity().finish();
                    getActivity().overridePendingTransition(R.anim.left_in,R.anim.right_out);

                }
            }
        }
    }
}
