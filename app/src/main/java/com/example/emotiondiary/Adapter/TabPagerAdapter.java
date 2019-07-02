package com.example.emotiondiary.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.emotiondiary.ViewUi.AllDiary;
import com.example.emotiondiary.ViewUi.Calendar;
import com.example.emotiondiary.ViewUi.Write;

public class TabPagerAdapter extends FragmentStatePagerAdapter {
    private Fragment fragment = new Fragment();
    public TabPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position){
        if(position<0 || getCount()<=position)
            return null;
        switch (position) {
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
