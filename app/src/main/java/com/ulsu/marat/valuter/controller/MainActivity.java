package com.ulsu.marat.valuter.controller;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.ulsu.marat.valuter.Model.CurrencyModel;
import com.ulsu.marat.valuter.R;
import com.ulsu.marat.valuter.controller.adapter.ViewPagerAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements CurrencyListFragment.OnCurrencyConverterFragment{

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @ViewById(R.id.tab_layout)
    TabLayout mTabLayout;

    @ViewById(R.id.view_pager)
    ViewPager mViewPager;

    ViewPagerAdapter mViewPagerAdapter;

    @AfterViews
    public void bindViews() {

        //Установка Шрифта
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build());

        setSupportActionBar(toolbar);

        //Устанавливаем фрагменты для табов
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPagerAdapter.addFragments(new CurrencyListFragment_(), "");
        mViewPagerAdapter.addFragments(new CurrencyConverterFragment_(), "");

        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setIcon(R.drawable.tab_currency_list_icon);
        mTabLayout.getTabAt(1).setIcon(R.drawable.tab_converter_icon);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("О приложении");
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void ListChange(List<CurrencyModel.Currency> list, boolean isGet) {
        CurrencyConverterFragment fragment  = (CurrencyConverterFragment) mViewPagerAdapter.getItem(1);
        fragment.listChange(list,isGet);
    }
}
