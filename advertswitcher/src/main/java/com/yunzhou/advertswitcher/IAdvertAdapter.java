package com.yunzhou.advertswitcher;

import android.view.View;

/**
 * Created by huayunzhou on 2017/11/9.
 */
public interface IAdvertAdapter<T> {

    /**
     * 获取当前数据个数
     * @return
     */
    public int getCount();

    /**
     * 获取position位置对应的数据
     * @param position
     * @return
     */
    public T getItem(int position);

    /**
     * 创建View
     * 这边仅仅是创建View，不要给View绑定数据，或者添加事件监听之类
     * Only new View or inflate layout
     * @return
     */
    public View makeView();

    /**
     * 为View绑定数据，同时添加对应的事件监听
     * @param view
     * @param data
     */
    public void bindView(View view, T data);
}
