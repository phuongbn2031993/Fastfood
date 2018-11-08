package com.example.phuongbnse61101.fastfood_prm391.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.phuongbnse61101.fastfood_prm391.R;
import com.example.phuongbnse61101.fastfood_prm391.adapter.SanPhamMoiAdapter;
import com.example.phuongbnse61101.fastfood_prm391.model.Sanpham;
import com.example.phuongbnse61101.fastfood_prm391.database.DBManager;
import com.example.phuongbnse61101.fastfood_prm391.util.Constan;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_home extends Fragment {


    private ViewFlipper viewFlipper;
    private RecyclerView rvSpMoi;

    private List<Sanpham> spMoinhat;
    private SanPhamMoiAdapter sanPhamMoiAdapter;

    //private ApiService apiService;

    public fragment_home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initView(view);

        //chạy ảnh quảng cáo
        ActionViewFlipper(getActivity());

        initData();

        return view;
    }

    private void initData() {
        //webservice
      //  apiService = RetrofitClient.getClient(Constan.API_URL).create(ApiService.class);

        loadSanphammoinhat();

    }

    private void initView(View view) {
        viewFlipper = view.findViewById(R.id.viewflipper);

        rvSpMoi = view.findViewById(R.id.rv_spmoi);
    }

    //ảnh chạy qc trên app
    private void ActionViewFlipper(Context context) {

        for (int i = 0; i<Constan.getQuangcao().size(); i++){
            ImageView imageView = new ImageView(context);
            Picasso.get().load(Constan.getQuangcao().get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getActivity(),R.anim.right_in);
        viewFlipper.setAnimation(animation_slide_in);
    }

    private void loadSanphammoinhat(){
        DBManager db = new DBManager(getActivity());

        try {
            List<Sanpham> sanphamList = db.getAllSanPham();
            if(sanphamList.size()==0){
                db.addSanPham(new Sanpham("Cafe đen","15000","https://images.foody.vn/res/g79/785722/prof/s576x330/foody-upload-api-foody-mobile-22-gif-181013135643.jpg","MÔ TẢ SẢN PHẨM CAFE ĐEN","1"));
                db.addSanPham(new Sanpham("Cafe sữa","18000","https://znews-photo-td.zadn.vn/w1024/Uploaded/ngtmns/2014_11_25/travel_for_children__coffee__1.jpg","MÔ TẢ SẢN PHẨM CAFE SỮA","1"));
                db.addSanPham(new Sanpham("Trà sữa trân châu","20000","http://gongcha.com.vn/wp-content/uploads/2018/02/Tr%C3%A0-s%E1%BB%AFa-Tr%C3%A2n-ch%C3%A2u-%C4%91en.png","MÔ TẢ SẢN PHẨM TRÀ SỮA TRÂN CHÂU","1"));
                db.addSanPham(new Sanpham("Trà sữa thạch","22000","https://blogcachlam.com/wp-content/uploads/2016/07/cach-lam-tra-sua-thach-trai-cay-4.jpg","MÔ TẢ SẢN PHẨM TRÀ SỮA THẠCH","1"));
                db.addSanPham(new Sanpham("Trái cây tô Size L","22000","http://cdn01.diadiemanuong.com//ddau/640x/diadiemanuong-com-nulle1906476635925532879274159.jpg","MÔ TẢ SẢN PHẢM TRÁI CÂY TÔ SIZE L","2"));
                db.addSanPham(new Sanpham("Trái cây tô Size M","19000","https://images.foody.vn/res/g20/196957/prof/s640x400/foody-mobile-gs-jpg-128-635862881844369125.jpg","MÔ TẢ SẢN PHẢM TRÁI CÂY TÔ SIZE M","2"));
                db.addSanPham(new Sanpham("Bưởi da xanh","18888","https://tea-2.lozi.vn/v1/images/resized/buoi-da-xanh-ben-tre-xin-day-ca-nha-oi-1521215311-1-5738326-1521215311?w=480&type=o","MÔ TẢ SẢN PHẨM BƯỞI DA XANH","2"));
                db.addSanPham(new Sanpham("Dâu tây ĐL","24444","https://didalat.net/wp-content/uploads/2015/09/dau-da-lat.jpg","MÔ TẢ SẢN PHẨM DÂU TÂY ĐL","2"));
                db.addSanPham(new Sanpham("Tôm chiên bột","33333","https://cdn.huongnghiepaau.com/images/nau-an/cong-thuc/Tom-lan-bot-chien-xu-an-kem-voi-tuong-ca-tuong-ot.jpg","MÔ TẢ SẢN PHẨM TÔM CHIỆN BỘT","3"));
                db.addSanPham(new Sanpham("Mực chiên giòn","32222","https://i.ytimg.com/vi/fTznLLgkp-s/hqdefault.jpg","MÔ TẢ SẢN PHẨM MỰC CHIÊN GIỜN","3"));
                db.addSanPham(new Sanpham("Cánh gà chiên bột","31111","http://s2.webbnc.vn/uploadv2/web/62/6259/product/2017/04/24/04/11/1493049532_canh-ga-chien-xu-324x235.jpg","MÔ TẢ SẢN PHẨM CÁNH GÀ CHIÊN BỘT","3"));
                db.addSanPham(new Sanpham("Đậu hủ chiên giòn","14444","https://cudat.vn/wp-content/uploads/2017/05/dau-hu-luot-van.jpg","https://cudat.vn/wp-content/uploads/2017/05/dau-hu-luot-van.jpg","3"));
                db.addSanPham(new Sanpham("Bánh mì chà bông","15555","http://www.savourydays.com/wp-content/uploads/2014/12/Flossbun1.jpg","MÔ TẢ SẢN PHẨM Bánh mì chà bông","4"));
                db.addSanPham(new Sanpham("Bánh su kem","18888","http://lambanh365.com/wp-content/uploads/2015/05/cach-lam-banh-su-kem-bang-lo-vi-song-1.jpg","MÔ TẢ SẢN PHẨM http://lambanh365.com/wp-content/uploads/2015/05/cach-lam-banh-su-kem-bang-lo-vi-song-1.jpg","4"));
                db.addSanPham(new Sanpham("Bánh mì Pháp","21111","http://phunu24h.com.vn/images/detailed/2/2-banh-mi-hoa-cuc-harrys-brioche-515g[1].jpg?t=1499152716","MÔ TẢ SẢN PHẨM Bánh mì Pháp","4"));
                db.addSanPham(new Sanpham("Bánh plan","19999","https://blog.beemart.vn/wp-content/uploads/2017/05/Cong-thuc-lam-banh-flan-sieu-ngon-sieu-don-gian-bang-sua-dac-111.jpg","MÔ TẢ SẢN PHẨM BÁNH PLANNN","4"));
                db.addSanPham(new Sanpham("Cơm chiên dương châu","29999","http://sinhcondaulong.com/wp-content/uploads/2017/08/com-chien-duong-chau-.png","MÔ TẢ SẢN PHẨM Cơm chiên dương châuzz","5"));
                db.addSanPham(new Sanpham("Mỳ trộn Ý","27777","http://mcspiedoboston.com/wp-content/uploads/2018/06/a5.jpg","MÔ TẢ SẢN PHẨM Mỳ trộn Ýzz","5"));
                db.addSanPham(new Sanpham("Bánh ướt","22222","https://znews-photo-td.zadn.vn/w1/Uploaded/pgi_cuhpguvau/2018_07_13/Moon_Moon.jpg","MÔ TẢ SẢN PHẨM BÁNH UỐT Zz","5"));
                db.addSanPham(new Sanpham("Bánh bột lọc bình dương","27777","https://cdn.daylambanh.edu.vn/images/congthuclambanh/banh-bot-loc-nhan-tom-hap-dan.jpg","MÔ TẢ SẢN PHẨM Bánh bột lọc bình dương","5"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }



        spMoinhat = db.getAllSanPham();
        sanPhamMoiAdapter = new SanPhamMoiAdapter(getActivity(),spMoinhat);
        rvSpMoi.setLayoutManager(new GridLayoutManager(getActivity(),4));
        rvSpMoi.setAdapter(sanPhamMoiAdapter);

    }

}
