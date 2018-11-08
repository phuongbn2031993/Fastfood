package com.example.phuongbnse61101.fastfood_prm391.view.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phuongbnse61101.fastfood_prm391.R;
import com.example.phuongbnse61101.fastfood_prm391.adapter.ItemTKAdapter;
import com.example.phuongbnse61101.fastfood_prm391.model.KhachHang;
import com.example.phuongbnse61101.fastfood_prm391.util.Constan;
import com.example.phuongbnse61101.fastfood_prm391.util.SharedPrefKhachhang;
import com.example.phuongbnse61101.fastfood_prm391.util.SharedPrefs;
import com.example.phuongbnse61101.fastfood_prm391.view.activity.activity_login;
import com.example.phuongbnse61101.fastfood_prm391.view.activity.activity_main_page;
import com.example.phuongbnse61101.fastfood_prm391.view.activity.activity_taikhoan.activity_caidat;
import com.example.phuongbnse61101.fastfood_prm391.view.activity.activity_taikhoan.activity_donhang;


public class fragment_taikhoan extends Fragment {
    private ListView lvChucnang, lvCaidat;
    private TextView tvDangNhapDk, tvChaomung;
    private ItemTKAdapter adapter;

    public fragment_taikhoan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_taikhoan, container, false);

        initView(view);
        initEvent();
        initData();
        // Inflate the layout for this fragment
        return view;
    }

    private void initData() {
        KhachHang khachHangz = SharedPrefKhachhang.getInstance().get(Constan.KHACHHANG_SHAREPRE, KhachHang.class);
        if(khachHangz!=null){
            activity_main_page.khachHang = khachHangz;
            activity_main_page.isDangNhap = true;
        }
        if (activity_main_page.isDangNhap) {
            tvDangNhapDk.setVisibility(View.GONE);
            adapter = new ItemTKAdapter(getActivity(), Constan.getListChucnang());
            lvChucnang.setAdapter(adapter);
            tvChaomung.setText("Xin chào " + activity_main_page.khachHang.getHoten());
        } else {
            tvDangNhapDk.setVisibility(View.VISIBLE);
        }

        adapter = new ItemTKAdapter(getActivity(), Constan.getListCaidat());
        lvCaidat.setAdapter(adapter);
    }

    private void initEvent() {
        tvDangNhapDk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), activity_login.class);
                getActivity().startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_in,R.anim.left_out);
            }
        });
        lvCaidat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        //cài đặt
                        if (activity_main_page.khachHang != null) {
                            getActivity().startActivity(new Intent(getActivity(), activity_caidat.class));
                            getActivity().overridePendingTransition(R.anim.right_in,R.anim.left_out);
                        } else
                            Toast.makeText(getActivity(), "Vui lòng đăng nhập", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        //chính sách
                        //show dialog thông báo
                        showChinhSach();
                        break;

                    case 2:
                        //trợ giúp
                        //show dialog thông báo
                        showTroGiup();
                        break;
                }
            }
        });

        lvChucnang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        //tin nhắn
                        //show dialog thông báo
                        Toast.makeText(getActivity(), "Chức năng đang được nâng cấp, bạn vui lòng quay lại sau", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        //đơn hàng của tôi
                        startActivity(new Intent(getActivity(), activity_donhang.class));
                        getActivity().overridePendingTransition(R.anim.right_in,R.anim.left_out);
                        break;
                }
            }
        });
    }

    private void showTroGiup() {

        final Dialog dialog = new Dialog(getActivity());
        dialog.setTitle("Trợ giúp");
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_trogiup);
        TextView btnOk = dialog.findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void showChinhSach() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setTitle("Chính sách của SHOPEESM");
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_chinhsach);
        TextView btnOk = dialog.findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void initView(View view) {
        lvChucnang = view.findViewById(R.id.lv_chucnang);
        lvCaidat = view.findViewById(R.id.lv_caidat);
        tvDangNhapDk = view.findViewById(R.id.tv_dangnhap_dangky);
        tvChaomung = view.findViewById(R.id.tv_chaomung);
    }
}
