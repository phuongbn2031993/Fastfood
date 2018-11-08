package com.example.phuongbnse61101.fastfood_prm391;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.phuongbnse61101.fastfood_prm391.model.KhachHang;
import com.example.phuongbnse61101.fastfood_prm391.database.DBManager;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.phuongbnse61101.fastfood_prm391", appContext.getPackageName());
    }
    Context instrumentationCtx = InstrumentationRegistry.getContext();
    DBManager db = new DBManager(instrumentationCtx);
    List<KhachHang> khachHangList = db.getAllKhachHang();

}
