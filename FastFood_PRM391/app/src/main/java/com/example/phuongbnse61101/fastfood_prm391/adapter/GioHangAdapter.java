package com.example.phuongbnse61101.fastfood_prm391.adapter;

import android.app.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phuongbnse61101.fastfood_prm391.R;
import com.example.phuongbnse61101.fastfood_prm391.util.Constan;
import com.example.phuongbnse61101.fastfood_prm391.util.SharedPrefs;
import com.example.phuongbnse61101.fastfood_prm391.view.activity.activity_main_page;
import com.example.phuongbnse61101.fastfood_prm391.model.GioHang;
import com.example.phuongbnse61101.fastfood_prm391.view.fragment.fragment_giohang;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class GioHangAdapter extends BaseAdapter {

    private Activity context;
    private List<GioHang> gioHangList;

    public GioHangAdapter(Activity context, List<GioHang> gioHangList) {
        this.context = context;
        this.gioHangList = gioHangList;
    }

    @Override
    public int getCount() {
        return gioHangList.size();
    }

    @Override
    public Object getItem(int i) {
        return gioHangList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final Holder holder;
        if(view == null){
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(R.layout.item_giohang,null);
            holder.ivGiohang = view.findViewById(R.id.iv_giohang);
            holder.tvTengiohang = view.findViewById(R.id.tv_tengiohang);
            holder.tvGiagiohang = view.findViewById(R.id.tv_giagiohang);
            holder.btnCong = view.findViewById(R.id.btn_cong);
            holder.btnSoluong = view.findViewById(R.id.btn_soluong);
            holder.btnTru = view.findViewById(R.id.btn_tru);
            holder.btnDelete = view.findViewById(R.id.btn_delete);

            view.setTag(holder);

        }else holder = (Holder) view.getTag();

        GioHang gioHang = gioHangList.get(i);
        holder.tvTengiohang.setText(gioHang.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvGiagiohang.setText(decimalFormat.format(gioHang.getGiasp())+" Đ");

        //with

        Picasso.get().load(gioHang.getHinhsp())
                .placeholder(R.drawable.loading)
                .error(R.drawable.no_image)
                .into(holder.ivGiohang);
        holder.btnSoluong.setText(gioHang.getSoluong()+"");

        int sl = Integer.parseInt(holder.btnSoluong.getText().toString());
        if(sl>=10){
            holder.btnCong.setEnabled(false);
        }else
            if(sl==1){
                holder.btnCong.setEnabled(true);
                holder.btnTru.setEnabled(false);
            }else
            if(sl>1){
                holder.btnCong.setEnabled(true);
                holder.btnTru.setEnabled(true);
            }


        holder.btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt(holder.btnSoluong.getText().toString())+1;
                int slhientai = activity_main_page.mangGioHang.get(i).getSoluong();
                long giahientai = activity_main_page.mangGioHang.get(i).getGiasp();
                activity_main_page.mangGioHang.get(i).setSoluong(slmoinhat);
                long giamoinhat = giahientai*slmoinhat/slhientai;
                activity_main_page.mangGioHang.get(i).setGiasp(giamoinhat);

                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                holder.tvGiagiohang.setText(decimalFormat.format(giamoinhat)+" Đ");

                //load lại ui tổng tiền ben fragment?
                SharedPrefs.getInstance().clear();
                SharedPrefs.getInstance().put(Constan.GIOHANG_SHAREPRE, activity_main_page.mangGioHang);
                loadFragmentUI();

                if (slmoinhat>9){
                    holder.btnCong.setEnabled(false);
                    holder.btnTru.setEnabled(true);
                    holder.btnSoluong.setText(String.valueOf(slmoinhat));
                }else {
                    holder.btnCong.setEnabled(true);
                    holder.btnTru.setEnabled(true);
                    holder.btnSoluong.setText(String.valueOf(slmoinhat));
                }
            }
        });
        holder.btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt(holder.btnSoluong.getText().toString())-1;
                int slhientai = activity_main_page.mangGioHang.get(i).getSoluong();
                long giahientai = activity_main_page.mangGioHang.get(i).getGiasp();
                activity_main_page.mangGioHang.get(i).setSoluong(slmoinhat);
                long giamoinhat = giahientai*slmoinhat/slhientai;
                activity_main_page.mangGioHang.get(i).setGiasp(giamoinhat);

                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                holder.tvGiagiohang.setText(decimalFormat.format(giamoinhat)+" Đ");
                SharedPrefs.getInstance().clear();
                SharedPrefs.getInstance().put(Constan.GIOHANG_SHAREPRE, activity_main_page.mangGioHang);
             //load lại ui tổng tiền ben fragment?
                loadFragmentUI();

                if (slmoinhat<2){
                    holder.btnCong.setEnabled(true);
                    holder.btnTru.setEnabled(false);
                    holder.btnSoluong.setText(String.valueOf(slmoinhat));
                }else {
                    holder.btnCong.setEnabled(true);
                    holder.btnTru.setEnabled(true);
                    holder.btnSoluong.setText(String.valueOf(slmoinhat));
                }
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Xóa thành công sản phẩm "+activity_main_page.mangGioHang.get(i).getTensp(), Toast.LENGTH_SHORT).show();
                activity_main_page.mangGioHang.remove(i);
                loadFragmentUI();
        //        activity_main_page.setSoluong(activity_main_page.mangGioHang.size());
                SharedPrefs.getInstance().clear();
                SharedPrefs.getInstance().put(Constan.GIOHANG_SHAREPRE, activity_main_page.mangGioHang);
            }
        });

        return view;
    }

    class Holder{
        ImageView ivGiohang, btnDelete;
        TextView tvTengiohang, tvGiagiohang;
        Button btnCong, btnTru, btnSoluong;
    }

    public void loadFragmentUI(){
        FragmentManager fragmentManager = ((activity_main_page) context).getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.frame_layout,new fragment_giohang());
        ft.commit();
    }
}
