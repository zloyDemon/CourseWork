package com.ulsu.marat.valuter.Model;

import android.content.Context;

import java.util.List;

public final class CurrencyListSingleton {

    private static CurrencyListSingleton mCurrencyListSingleton;
    private Context context;
    private List<CurrencyModel.Currency> currencyModelList;
    private CurrencyListSingleton(Context context){
        this.context = context;
    }

    public static CurrencyListSingleton get(Context context){
        if(mCurrencyListSingleton == null){
            mCurrencyListSingleton = new CurrencyListSingleton(context);
        }
        return mCurrencyListSingleton;
    }

    public void setList(List<CurrencyModel.Currency> list){
       this.currencyModelList = list;
    }

    public List<CurrencyModel.Currency> getList(){
        return currencyModelList;
    }
}
