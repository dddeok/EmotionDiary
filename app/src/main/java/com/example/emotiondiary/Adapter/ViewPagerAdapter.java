package com.example.emotiondiary.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.emotiondiary.ViewUi.AllDiary;
import com.example.emotiondiary.ViewUi.Calendar;
import com.example.emotiondiary.ViewUi.Write;


public class ViewPagerAdapter extends FragmentPagerAdapter {
    private Fragment fragment = new Fragment();
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        if(i<0 || getCount()<=i)
            return null;
        switch (i) {
            case 0:
                fragment = new AllDiary();
                break;
            case 1:
                fragment = new Write();
                break;
            case 2:
                fragment = new Calendar();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }


}