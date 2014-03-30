package info.jackrex.androidcachefoundation.app;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Jackrex on 3/30/14.
 */
public class MyViewPager extends ViewPager {

    private boolean mSwipeEnabled;

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewPager(Context context) {
       this(context,null);
    }


    public boolean isSwipeEnabled() {
        return mSwipeEnabled;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!mSwipeEnabled) {
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!mSwipeEnabled) {
            return false;
        }
        return super.onTouchEvent(ev);
    }

    public void setSwipeEnabled(boolean swipeEnabled) {
        if (mSwipeEnabled == swipeEnabled) {
            return;
        }
        mSwipeEnabled = swipeEnabled;
        if (!swipeEnabled) {
            MotionEvent event = MotionEvent.obtain(0, 0, MotionEvent.ACTION_CANCEL, 0, 0, 0);
            super.onTouchEvent(event);
            event.recycle();
        }
    }




}
