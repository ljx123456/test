package com.huayou.acc.acc.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by alu on 2017/5/8.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private ImageView[] views;

    public ViewPagerAdapter(ImageView[] views) {
        this.views = views;
    }

    @Override
    public int getCount() {
        return views.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
    //生成要滑动的页面及其左右的页面
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views[position]);
        return views[position];
    }
    //销毁除以上页面之外的其他页面
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views[position]);
    }
}
