package com.yunzhou.advertswitcher;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewSwitcher;


/**
 * 广告上下轮播组件
 * 参考京东首页-京东日报，淘宝首页-淘宝头条
 * Created by huayunzhou on 2017/11/9.
 */

public class AdvertSwitcher extends ViewSwitcher implements ViewSwitcher.ViewFactory{

    private static final String TAG = "AdvertSwitcher";

    private static final int FLAG_START_SCROLL = 0;
    private static final int FLAG_STOP_SCROLL = 1;
    private static final int FLAG_REFRESH = 2;

    //默认时间间隔
    private static final int DEFAULT_TIME_SPAN = 3000;
    private static final int DEFAULT_IN_ANIM_ID = R.anim.advert_scroll_in;
    private static final int DEFAULT_OUT_ANIM_ID = R.anim.advert_scroll_out;
    private static final int DEFAULT_INTERPOLATOR = android.R.interpolator.accelerate_cubic;

    private Context mContext;
    /**
     * 广告滚动的时间间隔
     */
    private long mTimeSpan;
    /**
     * view进入动画
     */
    private int mInAnimId;
    /**
     * view离开动画
     */
    private int mOutAnimId;
    /**
     *
     */
    private int interpolator;

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
        this.mContext = context;
        initAttribute(attrs);
        init();
    }

    private void initAttribute(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.AdvertSwitcher);
        mTimeSpan = typedArray.getInteger(R.styleable.AdvertSwitcher_timeSpan, DEFAULT_TIME_SPAN);
        mInAnimId = typedArray.getResourceId(R.styleable.AdvertSwitcher_inAnim, DEFAULT_IN_ANIM_ID);
        mOutAnimId = typedArray.getResourceId(R.styleable.AdvertSwitcher_outAnim, DEFAULT_OUT_ANIM_ID);
        interpolator = typedArray.getResourceId(R.styleable.AdvertSwitcher_interpolator, DEFAULT_INTERPOLATOR);
        typedArray.recycle();
        typedArray = null;
    }

    private void init() {
        currentIndex = 0;
        mHandler = new ScrollHandler();
        Animation inAnim = AnimationUtils.loadAnimation(mContext, mInAnimId);
        Animation outAnim = AnimationUtils.loadAnimation(mContext, mOutAnimId);
//        inAnim.setInterpolator(mContext, interpolator);
//        outAnim.setInterpolator(mContext, interpolator);
        //设置View进入离开动画
        setInAnimation(inAnim);
        setOutAnimation(outAnim);
    }

    /**
     * 启动广告翻滚
     * 建议在onResume中调用
     */
    public void start(){
        if(getChildCount() == 0) {
            setFactory(this);
        }
        mHandler.sendEmptyMessage(FLAG_START_SCROLL);
    }

    /**
     * 暂停广告翻滚
     * 建议在onPause中调用
     */
    public void stop(){
        mHandler.sendEmptyMessage(FLAG_STOP_SCROLL);
    }

    /**
     * 数据源发生变化后，从列表头开始重新播放
     */
    public void refresh(){
        mHandler.removeMessages(FLAG_START_SCROLL);
        currentIndex = 0;
        start();
    }

    @Override
    public View makeView() {
        return mAdapter.makeView();
    }


    public IAdvertAdapter getmAdapter() {
        return mAdapter;
    }

    public void setAdapter(IAdvertAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    /**
     * 重新置位
     */
    public void reset(){
        mHandler.removeMessages(FLAG_START_SCROLL);
        currentIndex = 0;
        removeAllViews();
    }

    private class ScrollHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case FLAG_START_SCROLL:
                    View view = getNextView();
                    mAdapter.bindView(view, mAdapter.getItem(currentIndex));
                    Log.e(TAG, "Index:" + currentIndex + " //" + mAdapter.getItem(currentIndex).toString());
                    showNext();
                    currentIndex = ++currentIndex % mAdapter.getCount();
                    mHandler.sendEmptyMessageDelayed(FLAG_START_SCROLL, mTimeSpan);
                    break;
                case FLAG_STOP_SCROLL:
                    mHandler.removeMessages(FLAG_START_SCROLL);
                    break;
                case FLAG_REFRESH:
                    currentIndex = 0;
                    removeAllViews();
                    start();
                    break;
            }
        }
    }

}
