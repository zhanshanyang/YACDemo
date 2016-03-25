package com.yangaiche.yackeeper.adapter;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mr_yang on 16-3-17.
 */
public class TabFragmentPagerAdapter extends FragmentPagerAdapter{
    private List<Fragment> mFragmentList = new ArrayList<>();
    private List<CharSequence> mTitleList = new ArrayList<>();

    public TabFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addTab(Fragment fragment, String title){
        mFragmentList.add(fragment);
        mTitleList.add(title);
    }

    @Override
    public Fragment getItem(int position) {

        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }

    public void setTabTitle(TabLayout tabLayout, int position, CharSequence title){
        setTabTitle(position, title);
        if(tabLayout != null && position < tabLayout.getTabCount()){
            tabLayout.getTabAt(position).setText(title);
        }
    }

    public void setTabTitle(int position, CharSequence title){
        mTitleList.set(position, title);
    }


}
