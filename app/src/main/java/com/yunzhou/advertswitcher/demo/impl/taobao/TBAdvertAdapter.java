package com.yunzhou.advertswitcher.demo.impl.taobao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yunzhou.advertswitcher.IAdvertAdapter;
import com.yunzhou.advertswitcher.demo.R;

import java.util.List;

/**
 * Created by huayunzhou on 2017/11/10.
 */

public class TBAdvertAdapter implements IAdvertAdapter<TBAdvert>, View.OnClickListener {

    private Context mContext;
    private List<TBAdvert> mDatas;

    public TBAdvertAdapter(Context mContext, List<TBAdvert> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public TBAdvert getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public View makeView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.advert_tb, null);
        return view;
    }

    @Override
    public void bindView(View view, TBAdvert data) {
        TextView content1 = view.findViewById(R.id.content1);
        TextView content2 = view.findViewById(R.id.content2);
        ImageView img = view.findViewById(R.id.tb_img);
        content1.setText(data.getContent1());
        content2.setText(data.getContent2());
        content1.setOnClickListener(this);
        content2.setOnClickListener(this);
        img.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.content1:
                Toast.makeText(mContext, "Click:" + ((TextView)view).getText().toString(), Toast.LENGTH_LONG).show();
                break;
            case R.id.content2:
                Toast.makeText(mContext, "Click:" + ((TextView)view).getText().toString(), Toast.LENGTH_LONG).show();
                break;
            case R.id.tb_img:
                Toast.makeText(mContext, "Click the Image", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
