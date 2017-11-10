package com.yunzhou.advertswitcher.demo.impl.jd;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yunzhou.advertswitcher.IAdvertAdapter;
import com.yunzhou.advertswitcher.demo.R;

import java.util.List;

/**
 * Created by huayunzhou on 2017/11/9.
 */

public class JDAdvertAdapter implements IAdvertAdapter<JDAdvert> {

    private Context mContext;
    private List<JDAdvert> mDatas;

    public JDAdvertAdapter(Context mContext, List<JDAdvert> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    @Override
    public JDAdvert getItem(int position) {
        return mDatas.get(position);
    }


    @Override
    public View makeView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.advert_jd, null);
        return view;
    }

    @Override
    public void bindView(View view, final JDAdvert data) {
        TextView tvTag = (TextView) view.findViewById(R.id.tag);
        if(!TextUtils.isEmpty(data.getTag())){
            tvTag.setVisibility(View.VISIBLE);
            tvTag.setText(data.getTag());
        }else{
            tvTag.setVisibility(View.GONE);
        }
        TextView tvContent = (TextView) view.findViewById(R.id.content);
        tvContent.setText(data.getContent());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, data.getContent(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
