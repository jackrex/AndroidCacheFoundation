package info.jackrex.androidcachefoundation.app;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jackrex on 3/30/14.
 */
public class TabAdapter extends FragmentPagerAdapter implements
        ViewPager.OnPageChangeListener {
    private final Context mContext;
    private final List<TabInfo> mTabs;
    private ArrayList<Fragment> fragments;

    public TabAdapter(FragmentActivity activity, ViewPager pager, List<TabInfo> tabs) {
        super(activity.getSupportFragmentManager());
        mContext = activity;
        mTabs = tabs;
        fragments = new ArrayList<Fragment>();
    }

    @Override
    public int getCount() {
        return mTabs.size();
    }

    @Override
    public Fragment getItem(int position) {
        TabInfo info = mTabs.get(position);
        return Fragment.instantiate(mContext, info.getFragmentClazz());
    }

    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
