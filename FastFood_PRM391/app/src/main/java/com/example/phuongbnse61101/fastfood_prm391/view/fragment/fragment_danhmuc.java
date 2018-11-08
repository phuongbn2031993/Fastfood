package com.example.phuongbnse61101.fastfood_prm391.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.phuongbnse61101.fastfood_prm391.R;
import com.example.phuongbnse61101.fastfood_prm391.adapter.LoaiSpAdapter;
import com.example.phuongbnse61101.fastfood_prm391.adapter.SanPhamAdapter;
import com.example.phuongbnse61101.fastfood_prm391.model.Loaisanpham;
import com.example.phuongbnse61101.fastfood_prm391.model.Sanpham;
import com.example.phuongbnse61101.fastfood_prm391.database.DBManager;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_danhmuc extends Fragment {


    private ListView lvLoaiSp;
    private RecyclerView rvChitietLoaiSP;

    private List<Loaisanpham> loaiSPList;
    private LoaiSpAdapter loaiSpAdapter;

    private List<Sanpham> sanphamList;
    private SanPhamAdapter sanPhamAdapter;

    private String idLoaiSP = "1";

    private EditText edtTimkiem;
    private ImageView ivTimkiem;
    public final int NORMAL_MODE = 0;
    public final int SEACH_MODE = 1;
    private int currenMode;

    public fragment_danhmuc() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_danhmuc, container, false);

        initView(view);

        initData();
        initEvent();
        // Inflate the layout for this fragment
        return view;
    }

    private void initEvent() {

        lvLoaiSp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                idLoaiSP = loaiSPList.get(i).getIdLoaisp();
                loadSPtheoLoai(idLoaiSP);
            }
        });
        edtTimkiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                timSanPham(edtTimkiem.getText().toString());
            }
        });
    }


    private void initData() {
        DBManager db = new DBManager(getActivity());
        List<Loaisanpham> list = db.getAllLoaiSp();
        if(list.size()==0){
            db.addLoaiSp(new Loaisanpham("Thức uống","https://images.foody.vn/res/g14/132210/prof/s640x400/foody-mobile-tiktak-mb-jpg-431-635863978257443374.jpg"));
            db.addLoaiSp(new Loaisanpham("Trái cây","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_RF-VE13XlF0nUzjoazpfGSg8NthpObwdFFMpkftJgjagtKZfcg"));
            db.addLoaiSp(new Loaisanpham("Chiên giòn","https://hellobacsi.com/wp-content/uploads/2017/08/th%E1%BB%A9c-%C4%83n-nhanh.jpg"));
            db.addLoaiSp(new Loaisanpham("Bánh ngọt","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQT4b7Hd8qIrcucmiX-eLw73DQaDGf7Lilc6t-kxlLoE_OCuczUjw"));
            db.addLoaiSp(new Loaisanpham("Ăn nhanh","https://i.doanhnhansaigon.vn/2016/01/29/rau-cu-qua-dong-goi-1508425358.jpg"));
        }
        rvChitietLoaiSP.setLayoutManager(new GridLayoutManager(getActivity(),2));
       // list loại sản phẩm
        loadLoaiSP();
        // load loai sp so 1
        loadSPtheoLoai(idLoaiSP);

    }

    private void initView(View view) {
        lvLoaiSp = view.findViewById(R.id.lv_loaisp);
        rvChitietLoaiSP = view.findViewById(R.id.rv_chitietloaisp);
        edtTimkiem = view.findViewById(R.id.edt_timkiem);
        ivTimkiem = view.findViewById(R.id.iv_tiemkiem);
    }

    private void loadLoaiSP(){
        DBManager db = new DBManager(getActivity());
        loaiSPList = db.getAllLoaiSp();
        loaiSpAdapter = new LoaiSpAdapter(getActivity(),loaiSPList);
        lvLoaiSp.setAdapter(loaiSpAdapter);
    }

    private void loadSPtheoLoai(String idLoaiSP){
        DBManager db = new DBManager(getActivity());
        sanphamList = db.getAllSanPham();
        List<Sanpham> sanphams = new ArrayList<Sanpham>();
        for(Sanpham sanpham : sanphamList){
            if(idLoaiSP.equals(sanpham.getIdLoaisp())){
                sanphams.add(sanpham);
            }
        }
        sanPhamAdapter = new SanPhamAdapter(getActivity(),sanphams);
        rvChitietLoaiSP.setAdapter(sanPhamAdapter);
    }

    private void timSanPham(String s) {
        if(s.trim().length()>0){
            //search
            //clear list

            ArrayList<Sanpham> listSeach = new ArrayList<>();
            listSeach.clear();
            //search item
            for (int i = 0; i< sanphamList.size(); i++){
                //kiểm tra input có nằm trong danh sách không?
                if (sanphamList.get(i).getTensp().trim().toLowerCase().contains(s.trim().toLowerCase())){
                    listSeach.add(sanphamList.get(i));
                }
            }
            //change mode view
            currenMode = SEACH_MODE;
            //updet ui
            sanPhamAdapter = new SanPhamAdapter(getActivity(),listSeach);
            rvChitietLoaiSP.setAdapter(sanPhamAdapter);
        }else {
            //show normal listview
            //change mode view
            currenMode = NORMAL_MODE;

            sanPhamAdapter = new SanPhamAdapter(getActivity(),sanphamList);
            rvChitietLoaiSP.setAdapter(sanPhamAdapter);
        }
    }
}
