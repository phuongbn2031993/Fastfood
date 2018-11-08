package com.example.phuongbnse61101.fastfood_prm391.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.phuongbnse61101.fastfood_prm391.R;
import com.example.phuongbnse61101.fastfood_prm391.model.Donhang;

import java.text.DecimalFormat;
import java.util.List;

public class DonHangAdapter extends BaseAdapter {

    private Context context;
    private List<Donhang> donhangList;

    public DonHangAdapter(Context context, List<Donhang> donhangList) {
        this.context = context;
        this.donhangList = donhangList;
    }

    @Override
    public int getCount() {
        return donhangList.size();
    }

    @Override
    public Object getItem(int i) {
        return donhangList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        if(view == null){
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(R.layout.item_donhangcuatoi, null);
            holder.tvTensp = view.findViewById(R.id.tv_tensp);
            holder.tvSoluong = view.findViewById(R.id.tv_soluong);
            holder.tvTongtien = view.findViewById(R.id.tv_tongtien);
            holder.tvNgaydathang = view.findViewById(R.id.tv_ngaymuahang);

            view.setTag(holder);
        }else holder = (Holder) view.getTag();

        Donhang donhang = donhangList.get(i);

        holder.tvTensp.setText(donhang.getTensp());
        holder.tvSoluong.setText(donhang.getSoluongsp());

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvTongtien.setText(decimalFormat.format(Integer.parseInt(donhang.getGiasp()))+" Đ");
        holder.tvNgaydathang.setText(donhang.getNgaydathang());

        return view;
    }

    class Holder{
        TextView tvTensp, tvSoluong, tvTongtien, tvNgaydathang;
    }
}
