package com.ulsu.marat.valuter.controller.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ulsu.marat.valuter.Model.CurrencyModel;
import com.ulsu.marat.valuter.R;

import java.util.List;


public class SpinnerCurrencyAdapter extends BaseAdapter {

    enum CurrencyName{
        AUSTRALIAN_DOLLAR("R01010","Австралийский доллар"),
        AZERBAIJAN_MANAT("R01020A","Азербайджанский манат"),
        BRITISH_POUND_STERLING("R01035","Фунт стерлингов Соединенного королевства"),
        ARMENIA_DRAM("R01060","Армянских драмов"),
        BELARUSSIAN_RUBLE("R01090","Белорусских рублей"),
        BULGARIAN_LEV("R01100","Болгарский лев"),
        BRAZIL_REAL("R01115","Бразильский реал"),
        HUNGARIAN_FORINT("R01135","Венгерских форинтов"),
        HONG_KONG_DOLLAR("R01200","Гонконгских долларов"),
        DANISH_KRONE("R01215","Датских крон"),
        US_DOLLAR("R01235","Доллар США"),
        EURO("R01239","Евро"),
        INDIAN_RUPEE("R01270","Индийских рупий"),
        ICELAND_KRONA("R01310","Исландских крон"),
        KAZAKHSTAN_TENGE("R01335","Казахстанских тенге"),
        CANADIAN_DOLLAR("R01350","Канадский доллар"),
        KYRGYZSTAN_SOM("R01370","Киргизских сомов"),
        CHINA_YUAN("R01375","Китайских юаней"),
        MOLDOVA_LEI("R01500","Молдавских леев"),
        NORWEGIAN_KRONE("R01535","Норвежских крон"),
        POLISH_ZLOTY("R01565","Польский злотый"),
        ROMANIAN_LEU("R01585F","Румынский лей"),
        SDR("R01589","СДР (специальные права заимствования)"),
        SINGAPORE_DOLLAR("R01625","Сингапурский доллар"),
        TAJIKISTAN_RUBLE("R01670","Таджикских сомони"),
        TURKISH_LIRA("R01700J","Турецкая лира"),
        NEW_TURKMENISTAN_MANAT("R01710A","Новый туркменский манат"),
        UZBEKISTAN_SUM("R01717","Узбекских сумов"),
        UKRAINIAN_HRYVNIA("R01720","Украинских гривен"),
        CZECH_KORUNA("R01760","Чешских крон"),
        SWEDISH_KRONA("R01770","Шведских крон"),
        SWISS_FRANC("R01775","Швейцарский франк"),
        S_AFRICAN_RAND("R01810","Южноафриканских рэндов"),
        SOUTH_KOREAN_WON("R01815","Вон Республики Корея"),
        JAPANESE_YEN("R01820","Японских иен"),
        NONE("","Нет данных");

        private String id_;
        private String name_;

        CurrencyName(String id, String name) {
            this.id_ = id;
            this.name_ = name;
        }

        String getNameById(String id){
            for (SpinnerCurrencyAdapter.CurrencyName currencyName : values()) {
                if(currencyName.id_.contains(id) || id.contains(currencyName.id_)){
                    return currencyName.name_;
                }
            }
            return NONE.name_;
        }
    }

    Context context;
    List<CurrencyModel.Currency> currencies;

    public SpinnerCurrencyAdapter(Context context, List<CurrencyModel.Currency> currencies) {
        super();

        this.context = context;
        this.currencies = currencies;
    }

    @Override
    public int getCount() {
        return currencies.size();
    }

    @Override
    public Object getItem(int position) {
        return currencies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_spinner_currency_list, parent, false);
        }
        TextView charcode = (TextView) view.findViewById(R.id.charcode);
        TextView name = (TextView) view.findViewById(R.id.name);

        charcode.setText(currencies.get(position).getCharCode());
        name.setText(getNameByCode(currencies.get(position).getID()));
        return view;
    }

    private String getNameByCode(String code){
        CurrencyAdapter.CurrencyName name = CurrencyAdapter.CurrencyName.NONE;
        return name.getNameById(code);
    }
}
