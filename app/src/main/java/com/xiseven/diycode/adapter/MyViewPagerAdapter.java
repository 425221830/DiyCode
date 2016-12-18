package com.xiseven.diycode.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by XISEVEN on 2016/12/18.
 */

public class MyViewPagerAdapter extends PagerAdapter {
    private List<View> mViewList;
    private List<String> mTitleList;

    public MyViewPagerAdapter(List<String> mTitleList, List<View> mViewList) {
        this.mTitleList = mTitleList;
        this.mViewList = mViewList;
    }

    @Override
    public int getCount() {
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViewList.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViewList.get(position));
        return mViewList.get(position);
    }
}
