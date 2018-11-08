package com.example.phuongbnse61101.fastfood_prm391.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefKhachhang {
    private static final String PREFS_NAME_KHACHHANG = "share_prefs_khachhang_";
    private static final String PREFS_NAME = "share_prefsKHACHHANG";
    private static SharedPrefKhachhang mInstance;
    private SharedPreferences mSharedPreferences;

    private SharedPrefKhachhang(String tenkhachhang) {
        mSharedPreferences = Appz.self().getSharedPreferences(PREFS_NAME_KHACHHANG+tenkhachhang, Context.MODE_PRIVATE);
    }
    private SharedPrefKhachhang() {
        mSharedPreferences = Appz.self().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }


    public static SharedPrefKhachhang getInstance(String tenkhachhang) {
        if (mInstance == null) {
            mInstance = new SharedPrefKhachhang(tenkhachhang);

        }
        return mInstance;
    }

    public static SharedPrefKhachhang getInstance() {
        if (mInstance == null) {
            mInstance = new SharedPrefKhachhang();

        }
        return mInstance;
    }


    public <T> T get(String key, Class<T> anonymousClass) {
        if (anonymousClass == String.class) {
            return (T) mSharedPreferences.getString(key, "");
        } else if (anonymousClass == Boolean.class) {
            return (T) Boolean.valueOf(mSharedPreferences.getBoolean(key, false));
        } else if (anonymousClass == Float.class) {
            return (T) Float.valueOf(mSharedPreferences.getFloat(key, 0));
        } else if (anonymousClass == Integer.class) {
            return (T) Integer.valueOf(mSharedPreferences.getInt(key, 0));
        } else if (anonymousClass == Long.class) {
            return (T) Long.valueOf(mSharedPreferences.getLong(key, 0));
        } else {
            return (T) Appz.self().getGSon().fromJson(mSharedPreferences.getString(key, ""), anonymousClass);
        }
    }

    public <T> T get(String key, Class<T> anonymousClass, T defaultValue) {
        if (anonymousClass == String.class) {
            return (T) mSharedPreferences.getString(key, (String) defaultValue);
        } else if (anonymousClass == Boolean.class) {
            return (T) Boolean.valueOf(mSharedPreferences.getBoolean(key, (Boolean) defaultValue));
        } else if (anonymousClass == Float.class) {
            return (T) Float.valueOf(mSharedPreferences.getFloat(key, (Float) defaultValue));
        } else if (anonymousClass == Integer.class) {
            return (T) Integer.valueOf(mSharedPreferences.getInt(key, (Integer) defaultValue));
        } else if (anonymousClass == Long.class) {
            return (T) Long.valueOf(mSharedPreferences.getLong(key, (Long) defaultValue));
        } else {
            return (T) Appz.self()
                    .getGSon()
                    .fromJson(mSharedPreferences.getString(key, ""), anonymousClass);
        }
    }
    public <T> void put(String key, T data) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        if (data instanceof String) {
            editor.putString(key, (String) data);
        } else if (data instanceof Boolean) {
            editor.putBoolean(key, (Boolean) data);
        } else if (data instanceof Float) {
            editor.putFloat(key, (Float) data);
        } else if (data instanceof Integer) {
            editor.putInt(key, (Integer) data);
        } else if (data instanceof Long) {
            editor.putLong(key, (Long) data);
        } else {
            editor.putString(key, Appz.self().getGSon().toJson(data));
        }
        editor.apply();
    }

    public void clear() {
        mSharedPreferences.edit().clear().apply();
    }
}
