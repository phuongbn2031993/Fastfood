package com.example.phuongbnse61101.fastfood_prm391.view.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.phuongbnse61101.fastfood_prm391.R;
import com.example.phuongbnse61101.fastfood_prm391.adapter.ViewPagerAdapter;
import com.example.phuongbnse61101.fastfood_prm391.database.DBManager;

public class activity_login extends AppCompatActivity {

    private ImageView btnThoat;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private DBManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DBManager(this);
        initView();

        initEvent();

        initData();
    }

    private void initData() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initEvent() {
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.left_in,R.anim.right_out);
            }
        });
    }

    private void initView() {
        btnThoat = findViewById(R.id.btn_thoat);
        viewPager = findViewById(R.id.vp_view_pager);
        tabLayout = findViewById(R.id.tb_tab_layout);
    }
}
