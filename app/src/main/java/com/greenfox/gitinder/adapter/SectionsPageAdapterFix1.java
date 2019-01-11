package com.greenfox.gitinder.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.greenfox.gitinder.model.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class SectionsPageAdapterFix1 extends FragmentPagerAdapter {

    private final List<BaseFragment> mFragmentList = new ArrayList<>();

    public SectionsPageAdapterFix1(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(BaseFragment fragment, String title){
        fragment.setTitle(title);
        mFragmentList.add(fragment);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentList.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
