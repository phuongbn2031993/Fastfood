package com.example.phuongbnse61101.fastfood_prm391.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phuongbnse61101.fastfood_prm391.R;
import com.example.phuongbnse61101.fastfood_prm391.model.Sanpham;
import com.example.phuongbnse61101.fastfood_prm391.util.Constan;
import com.example.phuongbnse61101.fastfood_prm391.view.activity.activity_chitiet_san_pham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;



public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.Holder> {
    private Activity context;
    private List<Sanpham> sanPhamList;

    public SanPhamAdapter(Activity context, List<Sanpham> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sanpham,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        Sanpham sanPham = sanPhamList.get(position);
        Picasso.get().load(sanPham.getHinhanhsp())
                .placeholder(R.drawable.loading)
                .error(R.drawable.no_image)
                .into(holder.ivDienthoai);
        holder.tvTenDt.setText(sanPham.getTensp());

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvGiaDt.setText("Giá: "+decimalFormat.format(Integer.parseInt(sanPham.getGiasp()))+" Đ");

        holder.tvMotaDt.setMaxLines(2);
        holder.tvMotaDt.setEllipsize(TextUtils.TruncateAt.END); //kiểu dấu 3 chấm nếu mô tả dài quá.
        holder.tvMotaDt.setText(sanPham.getMotasp());
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivDienthoai;
        TextView tvTenDt, tvGiaDt, tvMotaDt;

        public Holder(View itemView) {
            super(itemView);
            ivDienthoai = itemView.findViewById(R.id.iv_dienthoai);
            tvTenDt = itemView.findViewById(R.id.tv_tendt);
            tvGiaDt = itemView.findViewById(R.id.tv_giadt);
            tvMotaDt = itemView.findViewById(R.id.tv_motadt);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, activity_chitiet_san_pham.class);
            intent.putExtra(Constan.THONGTIN_SP, sanPhamList.get(getLayoutPosition()));
            context.startActivity(intent);
        }
    }
}
