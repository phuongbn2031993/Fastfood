package com.example.phuongbnse61101.fastfood_prm391.util;

import android.app.Application;
import com.google.gson.Gson;

public class Appz extends Application {
    private static Appz mSelf;
    private Gson mGSon;

    public static Appz self() {
        return mSelf;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mSelf = this;
        mGSon = new Gson();
    }

    public Gson getGSon() {
        return mGSon;
    }
}