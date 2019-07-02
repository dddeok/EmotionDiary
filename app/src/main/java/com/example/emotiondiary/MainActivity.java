package com.example.emotiondiary;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.emotiondiary.Adapter.TabPagerAdapter;
import com.example.emotiondiary.Adapter.ViewPagerAdapter;


public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    ViewPagerAdapter adapter;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        viewPager = (ViewPager)findViewById(R.id.viewPager);

        tabLayout = (TabLayout)findViewById(R.id.layout_tab);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());

        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        int[] imageResId = {
                R.drawable.home,
                R.drawable.write,
                R.drawable.calendar,
        };

        for(int i = 0; i < imageResId.length; ++i) {
            tabLayout.getTabAt(i).setIcon(imageResId[i]);
        }

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition())
                {
                    case(0):
                        tab.setIcon(R.drawable.home);
                        break;
                    case(1):
                        tab.setIcon(R.drawable.write);
                        break;
                    case(2):
                        tab.setIcon(R.drawable.calendar);
                        break;
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch (tab.getPosition())
                {
                    case(0):
                        tab.setIcon(R.drawable.home);
                        break;
                    case(1):
                        tab.setIcon(R.drawable.write);
                        break;
                    case(2):
                        tab.setIcon(R.drawable.calendar);
                        break;
                }
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                switch (tab.getPosition())
                {
                    case(0):
                        tab.setIcon(R.drawable.home);
                        break;
                    case(1):
                        tab.setIcon(R.drawable.write);
                        break;
                    case(2):
                        tab.setIcon(R.drawable.calendar);
                        break;
                }
            }
        });
    }
}

