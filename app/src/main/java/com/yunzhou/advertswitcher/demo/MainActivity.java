package com.yunzhou.advertswitcher.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yunzhou.advertswitcher.AdvertSwitcher;
import com.yunzhou.advertswitcher.impl.jd.JDAdvert;
import com.yunzhou.advertswitcher.impl.jd.JDAdvertAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AdvertSwitcher mAdvertSwitcher = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdvertSwitcher = findViewById(R.id.advert_switcher);

        List<JDAdvert> adverts = new ArrayList<>();
        adverts.add(new JDAdvert("HOT", "1.双十一啦啦啦啦啦"));
        adverts.add(new JDAdvert("爆", "2.双十一啦啦啦啦啦"));
        adverts.add(new JDAdvert("爆", "3.双十一啦啦啦啦啦"));
        adverts.add(new JDAdvert("HOT", "4.双十一啦啦啦啦啦"));
        adverts.add(new JDAdvert("爆", "5.双十一啦啦啦啦啦"));

        mAdvertSwitcher.setmAdapter(new JDAdvertAdapter(this, adverts));
    }

    public void refresh(View view) {
    }
}
