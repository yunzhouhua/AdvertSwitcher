package com.yunzhou.advertswitcher;

import android.view.View;

/**
 * Created by huayunzhou on 2017/11/9.
 */
public interface IAdvertAdapter<T> {

    public int getCount();
    public T getItem(int position);
    public View makeView();
    public void bindView(View view, T data);
}
