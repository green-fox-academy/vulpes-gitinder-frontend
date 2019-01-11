package com.greenfox.gitinder.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.greenfox.gitinder.model.FragmentAndTitle;

import java.util.ArrayList;
import java.util.List;

public class SectionsPageAdapterFix2 extends FragmentPagerAdapter {

    private final List<FragmentAndTitle> mFragmentList = new ArrayList<>();

    public SectionsPageAdapterFix2(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title){
        FragmentAndTitle fragmentAndTitle = new FragmentAndTitle(fragment, title);
        mFragmentList.add(fragmentAndTitle);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentList.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
