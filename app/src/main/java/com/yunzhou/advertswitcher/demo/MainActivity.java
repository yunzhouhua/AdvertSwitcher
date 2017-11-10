package com.yunzhou.advertswitcher.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yunzhou.advertswitcher.AdvertSwitcher;
import com.yunzhou.advertswitcher.demo.impl.jd.JDAdvert;
import com.yunzhou.advertswitcher.demo.impl.jd.JDAdvertAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AdvertSwitcher mAdvertSwitcher = null;

    List<JDAdvert> jdAdverts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdvertSwitcher = findViewById(R.id.advert_switcher);

        initForJD();
    }

    private void initForJD() {
        jdAdverts.add(new JDAdvert("HOT", "1.双十一啦啦啦啦啦"));
        jdAdverts.add(new JDAdvert("爆", "2.双十一啦啦啦啦啦"));
        jdAdverts.add(new JDAdvert("爆", "3.双十一啦啦啦啦啦"));
        jdAdverts.add(new JDAdvert("HOT", "4.双十一啦啦啦啦啦"));
        jdAdverts.add(new JDAdvert("爆", "5.双十一啦啦啦啦啦"));

        mAdvertSwitcher.setmAdapter(new JDAdvertAdapter(this, jdAdverts));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdvertSwitcher.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAdvertSwitcher.stop();
    }

    public void refresh(View view) {
        jdAdverts.add(0, new JDAdvert("COLD", "PRE1.双十一啦啦啦啦啦"));
        jdAdverts.add(0, new JDAdvert("COLD", "PRE2.双十一啦啦啦啦啦"));
        jdAdverts.add(0, new JDAdvert("COLD", "PRE3.双十一啦啦啦啦啦"));
        mAdvertSwitcher.refresh();

    }
}
