package com.example.phuongbnse61101.fastfood_prm391.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.phuongbnse61101.fastfood_prm391.R;
import com.example.phuongbnse61101.fastfood_prm391.model.KhachHang;
import com.example.phuongbnse61101.fastfood_prm391.model.GioHang;
import com.example.phuongbnse61101.fastfood_prm391.view.fragment.fragment_danhmuc;
import com.example.phuongbnse61101.fastfood_prm391.view.fragment.fragment_giohang;
import com.example.phuongbnse61101.fastfood_prm391.view.fragment.fragment_home;
import com.example.phuongbnse61101.fastfood_prm391.view.fragment.fragment_taikhoan;

import java.util.List;

public class activity_main_page extends AppCompatActivity {

    public static boolean isDangNhap = false;
    public static KhachHang khachHang;
    private TextView mTextMessage, soluongtronggiohang;
    public static List<GioHang> mangGioHang;
    private static TextView tvSoluong;
    public static void setSoluong(int sl) {
        tvSoluong.setText(sl + " ");
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                  //  mTextMessage.setText(R.string.title_home);
                    replaceFragment(new fragment_home());
                    return true;
                case R.id.navigation_catagory:
                  //  mTextMessage.setText(R.string.title_catagory);
                    replaceFragment(new fragment_danhmuc());
                    return true;
                case R.id.navigation_cart:
                 //  mTextMessage.setText(R.string.title_cart);
                    replaceFragment(new fragment_giohang());
                    return true;
                case R.id.navigation_account:
                  //+0 12222222222222222222222222222222222222222222222222222 mTextMessage.setText(R.string.title_account);
                    replaceFragment(new fragment_taikhoan());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        replaceFragment(new fragment_home());

    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager =  getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.frame_layout, fragment);
        ft.commit();
    }

}
