package com.yunzhou.advertswitcher.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yunzhou.advertswitcher.AdvertSwitcher;
import com.yunzhou.advertswitcher.demo.impl.jd.JDAdvert;
import com.yunzhou.advertswitcher.demo.impl.jd.JDAdvertAdapter;
import com.yunzhou.advertswitcher.demo.impl.taobao.TBAdvert;
import com.yunzhou.advertswitcher.demo.impl.taobao.TBAdvertAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AdvertSwitcher mJDAdvert = null;
    private AdvertSwitcher mTBAdvert = null;

    List<JDAdvert> jdAdverts = new ArrayList<>();
    List<TBAdvert> tbAdverts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mJDAdvert = findViewById(R.id.advert_switcher_jd);
        mTBAdvert = findViewById(R.id.advert_switcher_tb);

        initForJD();
        initForTB();
    }

    private void initForJD() {
        jdAdverts.add(new JDAdvert("HOT", "1.双十一啦啦啦啦啦"));
        jdAdverts.add(new JDAdvert("爆", "2.双十一啦啦啦啦啦"));
        jdAdverts.add(new JDAdvert("爆", "3.双十一啦啦啦啦啦"));
        jdAdverts.add(new JDAdvert("HOT", "4.双十一啦啦啦啦啦"));
        jdAdverts.add(new JDAdvert("爆", "5.双十一啦啦啦啦啦"));

        mJDAdvert.setAdapter(new JDAdvertAdapter(this, jdAdverts));
    }

    private void initForTB() {
        tbAdverts.add(new TBAdvert("1.lalalalala", "2.mimimimi"));
        tbAdverts.add(new TBAdvert("3.lalalalala", "4.mimimimi"));
        tbAdverts.add(new TBAdvert("5.lalalalala", "6.mimimimi"));
        tbAdverts.add(new TBAdvert("7.lalalalala", "8.mimimimi"));

        mTBAdvert.setAdapter(new TBAdvertAdapter(this, tbAdverts));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mJDAdvert.start();
        mTBAdvert.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mJDAdvert.stop();
        mTBAdvert.start();
    }

    public void refresh(View view) {
        jdAdverts.add(0, new JDAdvert("COLD", "PRE1.双十一啦啦啦啦啦"));
        jdAdverts.add(0, new JDAdvert("COLD", "PRE2.双十一啦啦啦啦啦"));
        jdAdverts.add(0, new JDAdvert("COLD", "PRE3.双十一啦啦啦啦啦"));
        mJDAdvert.refresh();
    }
}
