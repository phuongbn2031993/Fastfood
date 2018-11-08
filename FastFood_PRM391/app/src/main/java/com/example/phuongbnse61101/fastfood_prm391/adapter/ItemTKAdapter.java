package com.example.phuongbnse61101.fastfood_prm391.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phuongbnse61101.fastfood_prm391.R;
import com.example.phuongbnse61101.fastfood_prm391.model.ItemTK;

import java.util.List;

public class ItemTKAdapter extends BaseAdapter {

    private Context context;
    private List<ItemTK> itemTKList;

    public ItemTKAdapter(Context context, List<ItemTK> itemTKList) {
        this.context = context;
        this.itemTKList = itemTKList;
    }

    @Override
    public int getCount() {
        return itemTKList.size();
    }

    @Override
    public Object getItem(int i) {
        return itemTKList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.item_taikhoan, null);
        ImageView ivItem = view.findViewById(R.id.iv_chucnang);
        TextView tvChucnang = view.findViewById(R.id.tv_chucnang);

         ItemTK itemTK = itemTKList.get(i);
         ivItem.setImageResource(itemTK.getIcon());
         tvChucnang.setText(itemTK.getTenChucnang());
        return view;
    }

}
