package com.example.phuongbnse61101.fastfood_prm391.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.phuongbnse61101.fastfood_prm391.model.Donhang;
import com.example.phuongbnse61101.fastfood_prm391.model.KhachHang;
import com.example.phuongbnse61101.fastfood_prm391.model.Loaisanpham;
import com.example.phuongbnse61101.fastfood_prm391.model.Sanpham;

import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "SQLiteDBFastFood.db";
    public static final int DATABASE_VERSION = 3;


    private static final String KHACHHANG_TABLE = "khachhang";
    private static final String KHACHHANG_ID = "khachhang_id";
    private static final String HOTEN = "hoten";
    private static final String SDT = "sdt";
    private static final String MAIL = "mail";
    private static final String PASS = "pass";
    private static final String DIACHI = "diachi";

    private static final String LOAISP_TABLE = "loaisp";
    private static final String LOAISP_ID = "loaisp_id";
    private static final String LOAISP_TEN = "loaisp_ten";
    private static final String LOAISP_HINHANH = "loaisp_hinhanh";

    private static final String SANPHAM_TABLE = "sanpham";
    private static final String SANPHAM_ID = "sanpham_id";
    private static final String SANPHAM_TEN = "sanpham_ten";
    private static final String SANPHAM_GIA = "sanpham_gia";
    private static final String SANPHAM_HINHANH = "sanpham_hinhanh";
    private static final String SANPHAM_MOTA = "sanpham_mota";
    private static final String SANPHAM_LOAISP = "sanpham_loaisp";

    private static final String DONHANG_TABLE = "donhang";
    private static final String DONHANG_ID = "donhang_id";
    private static final String DONHANG_MAKH = "donhang_makh";
    private static final String DONHANG_MASP = "donhang_masp";
    private static final String DONHANG_TENSP = "donhang_tensp";
    private static final String DONHANG_GIASP = "donhang_giasp";
    private static final String DONHANG_SOLUONGSP = "donhang_soluongsp";
    private static final String DONHANG_NGAYDATHANG = "donhang_ngaydathang";


    private Context context;

    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public static final String CREATE_DATABASE_SANPHAM = "CREATE TABLE " + SANPHAM_TABLE + " (" +
            SANPHAM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            SANPHAM_TEN + " TEXT, " +
            SANPHAM_GIA + " TEXT, " +
            SANPHAM_HINHANH + " TEXT, " +
            SANPHAM_MOTA + " TEXT, " +
            SANPHAM_LOAISP + " TEXT)";

    public static final String CREATE_DATABASE_KHACHHANG = "CREATE TABLE " + KHACHHANG_TABLE + " (" +
            KHACHHANG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            HOTEN + " TEXT, " +
            SDT + " TEXT, " +
            MAIL + " TEXT, " +
            PASS + " TEXT, " +
            DIACHI + " TEXT)";


    public static final String CREATE_DATABASE_LOAISP = "CREATE TABLE " + LOAISP_TABLE + " (" +
            LOAISP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            LOAISP_TEN + " TEXT, " +
            LOAISP_HINHANH + " TEXT)";

    public static final String CREATE_DATABASE_DONHANG = "CREATE TABLE " + DONHANG_TABLE + " (" +
            DONHANG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            DONHANG_MAKH + " TEXT, " +
            DONHANG_MASP + " TEXT, " +
            DONHANG_TENSP + " TEXT, " +
            DONHANG_GIASP + " TEXT, " +
            DONHANG_SOLUONGSP + " TEXT, " +
            DONHANG_NGAYDATHANG + " TEXT)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DATABASE_KHACHHANG);
        db.execSQL(CREATE_DATABASE_LOAISP);
        db.execSQL(CREATE_DATABASE_SANPHAM);
        db.execSQL(CREATE_DATABASE_DONHANG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + KHACHHANG_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + LOAISP_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SANPHAM_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DONHANG_TABLE);
        onCreate(db);
    }

    public boolean addKhachHang(KhachHang khachhang) {
        boolean istrue = false;
        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(HOTEN, khachhang.getHoten());
            values.put(SDT, khachhang.getSdt());
            values.put(MAIL, khachhang.getMail());
            values.put(PASS, khachhang.getPass());
            values.put(DIACHI, khachhang.getDiachi());
            db.insert(KHACHHANG_TABLE, null, values);
            istrue = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return istrue;
    }

    public List<KhachHang> getAllKhachHang() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<KhachHang> khachHangList = new ArrayList<KhachHang>();
        String selectQuery = "SELECT  * FROM " + KHACHHANG_TABLE;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                KhachHang khachHang = new KhachHang();
                khachHang.setId(Integer.parseInt(cursor.getString(0)));
                khachHang.setHoten(cursor.getString(1));
                khachHang.setSdt(cursor.getString(2));
                khachHang.setMail(cursor.getString(3));
                khachHang.setPass(cursor.getString(4));
                khachHang.setDiachi(cursor.getString(5));
                khachHangList.add(khachHang);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return khachHangList;
    }

    public KhachHang getKhachHangById(String id) {
        KhachHang result = null;
        List<KhachHang> khachHangList = getAllKhachHang();
        if (khachHangList != null) {
            for (KhachHang khachHang : khachHangList) {
                if (Integer.parseInt(id) == khachHang.getId()) {
                    result = khachHang;
                }
            }
        }
        return result;
    }

    public int updateKhachhang(KhachHang khachHang) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HOTEN, khachHang.getHoten());
        values.put(SDT, khachHang.getSdt());
        values.put(MAIL, khachHang.getMail());
        values.put(PASS, khachHang.getPass());
        values.put(DIACHI, khachHang.getDiachi());

        String whereClause = KHACHHANG_ID + " LIKE ?";
        String[] whereArgs = {String.valueOf(khachHang.getId())};
        return db.update(KHACHHANG_TABLE, values, whereClause, whereArgs);
    }

    public void deleteKhachHang(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = id + " LIKE ?";
        String[] whereArgs = {id};
        db.delete(KHACHHANG_TABLE, whereClause, whereArgs);
        db.close();
    }

    public boolean addLoaiSp(Loaisanpham loaisanpham) {
        boolean istrue = false;
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(LOAISP_TEN, loaisanpham.getTenLoaisp());
            values.put(LOAISP_HINHANH, loaisanpham.getHinhanhLoaisp());
            db.insert(LOAISP_TABLE, null, values);
            istrue = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return istrue;
    }

    public List<Loaisanpham> getAllLoaiSp() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Loaisanpham> loaisanphamList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + LOAISP_TABLE;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Loaisanpham loaisanpham = new Loaisanpham();
                loaisanpham.setIdLoaisp(cursor.getString(0));
                loaisanpham.setTenLoaisp(cursor.getString(1));
                loaisanpham.setHinhanhLoaisp(cursor.getString(2));
                loaisanphamList.add(loaisanpham);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return loaisanphamList;
    }

    public int updateLoaisp(Loaisanpham loaisanpham) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LOAISP_TEN, loaisanpham.getTenLoaisp());
        values.put(LOAISP_HINHANH, loaisanpham.getHinhanhLoaisp());
        String whereClause = LOAISP_ID + " LIKE ?";
        String[] whereArgs = {String.valueOf(loaisanpham.getIdLoaisp())};
        return db.update(LOAISP_TABLE, values, whereClause, whereArgs);
    }

    public void deleteLoaisp(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = LOAISP_ID + " LIKE ?";
        String[] whereArgs = {id};
        db.delete(LOAISP_TABLE, whereClause, whereArgs);
        db.close();
    }

    public boolean addSanPham(Sanpham sp) {
        boolean istrue = false;
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(SANPHAM_TEN, sp.getTensp());
            values.put(SANPHAM_GIA, sp.getGiasp());
            values.put(SANPHAM_HINHANH, sp.getHinhanhsp());
            values.put(SANPHAM_MOTA, sp.getMotasp());
            values.put(SANPHAM_LOAISP, sp.getIdLoaisp());
            db.insert(SANPHAM_TABLE, null, values);
            istrue = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return istrue;
    }

    public List<Sanpham> getAllSanPham() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Sanpham> sanphamList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + SANPHAM_TABLE;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Sanpham sanpham = new Sanpham();
                sanpham.setId(cursor.getString(0));
                sanpham.setTensp(cursor.getString(1));
                sanpham.setGiasp(cursor.getString(2));
                sanpham.setHinhanhsp(cursor.getString(3));
                sanpham.setMotasp(cursor.getString(4));
                sanpham.setIdLoaisp(cursor.getString(5));
                sanphamList.add(sanpham);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return sanphamList;
    }

    public int updateSanPham(Sanpham sanpham) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SANPHAM_TEN, sanpham.getTensp());
        values.put(SANPHAM_GIA, sanpham.getGiasp());
        values.put(SANPHAM_HINHANH, sanpham.getHinhanhsp());
        values.put(SANPHAM_MOTA, sanpham.getMotasp());

        String whereClause = SANPHAM_ID + " LIKE ?";
        String[] whereArgs = {String.valueOf(sanpham.getId())};
        return db.update(SANPHAM_TABLE, values, whereClause, whereArgs);
    }

    public void deleteSanPham(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = SANPHAM_ID + " LIKE ?";
        String[] whereArgs = {id};
        db.delete(SANPHAM_TABLE, whereClause, whereArgs);
        db.close();
    }

    public boolean addDonHang(Donhang donhang) {
        boolean istrue = false;
        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DONHANG_MAKH, donhang.getMakh());
            values.put(DONHANG_MASP, donhang.getMasp());
            values.put(DONHANG_TENSP, donhang.getTensp());
            values.put(DONHANG_GIASP, donhang.getGiasp());
            values.put(DONHANG_SOLUONGSP, donhang.getSoluongsp());
            values.put(DONHANG_NGAYDATHANG , donhang.getNgaydathang());
            db.insert(DONHANG_TABLE, null, values);
            istrue = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return istrue;
    }

    public List<Donhang> listDonhangByMaKh(String maKH){
        List<Donhang> donhangList = new ArrayList<Donhang>();


        SQLiteDatabase db = this.getReadableDatabase();
      //  String selectQuery = "SELECT  * FROM " + DONHANG_TABLE + " WHERE " + DONHANG_MAKH + " LIKE ?";
        String sortByDate = DONHANG_NGAYDATHANG + " ASC";
        String[] columns = {DONHANG_ID, DONHANG_MAKH, DONHANG_MASP, DONHANG_TENSP, DONHANG_GIASP, DONHANG_SOLUONGSP, DONHANG_NGAYDATHANG};
        String selection = DONHANG_MAKH + "=?";
        String[] selectionArgs = {maKH};
        Cursor cursor = db.query(DONHANG_TABLE, columns, selection, selectionArgs, null, null, sortByDate, null);

      //  Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                Donhang donhang = new Donhang();
                donhang.setId(cursor.getString(0));
                donhang.setMakh(cursor.getString(1));
                donhang.setMasp(cursor.getString(2));
                donhang.setTensp(cursor.getString(3));
                donhang.setGiasp(cursor.getString(4));
                donhang.setSoluongsp(cursor.getString(5));
                donhang.setNgaydathang(cursor.getString(6));
                donhangList.add(donhang);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return donhangList;
    }
}