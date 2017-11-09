package com.yunzhou.advertswitcher;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ViewSwitcher;


/**
 * Created by huayunzhou on 2017/11/9.
 */

public class AdvertSwitcher extends ViewSwitcher implements ViewSwitcher.ViewFactory{

    private static final int FLAG_START_SCROLL = 0;
    private static final int FLAG_STOP_SCROLL = 1;

    /**
     * 广告滚动的时间间隔
     */
    private long mTimeSpan = 1000;

    /**
     * 当前角标
     */
    private int currentIndex;

    private Handler mHandler;

    private IAdvertAdapter mAdapter;
    public AdvertSwitcher(Context context) {
        this(context, null);
    }

    public AdvertSwitcher(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        currentIndex = 0;
        mHandler = new ScrollHandler();
    }

    /**
     * 启动广告翻滚
     * 建议在onResume中调用
     */
    public void start(){
        setFactory(this);
        mHandler.sendEmptyMessage(FLAG_START_SCROLL);
    }

    /**
     * 暂停广告翻滚
     * 建议在onPause中调用
     */
    public void stop(){
        mHandler.sendEmptyMessage(FLAG_STOP_SCROLL);
    }

    @Override
    public View makeView() {
        return mAdapter.makeView();
    }


    public IAdvertAdapter getmAdapter() {
        return mAdapter;
    }

    public void setmAdapter(IAdvertAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    /**
     * 重新置位
     */
    public void reset(){
        mHandler.removeMessages(FLAG_START_SCROLL);
        currentIndex = 0;
    }

    private class ScrollHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case FLAG_START_SCROLL:
                    View view = getNextView();
                    mAdapter.bindView(view, mAdapter.getItem(currentIndex));
                    showNext();
                    currentIndex = ++currentIndex % mAdapter.getCount();
                    mHandler.sendEmptyMessageDelayed(FLAG_START_SCROLL, mTimeSpan);
                    break;
                case FLAG_STOP_SCROLL:
                    mHandler.removeMessages(FLAG_START_SCROLL);
                    break;
            }
        }
    }
}
