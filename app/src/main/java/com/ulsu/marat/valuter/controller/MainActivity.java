package com.ulsu.marat.valuter.controller;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ulsu.marat.valuter.R;
import com.ulsu.marat.valuter.controller.adapter.ViewPagerAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @ViewById(R.id.tab_layout)
    TabLayout mTabLayout;

    @ViewById(R.id.view_pager)
    ViewPager mViewPager;

    ViewPagerAdapter mViewPagerAdapter;

    @AfterViews
    public void bindViews(){
        setSupportActionBar(toolbar);
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPagerAdapter.addFragments(new CurrencyListFragment_(),"");
        mViewPagerAdapter.addFragments(new CurrencyConverterFragment_(),"");

        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setIcon(R.drawable.tab_list_icon);
        mTabLayout.getTabAt(1).setIcon(R.drawable.tab_list_icon);
    }
}
