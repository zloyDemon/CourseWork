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

import com.ulsu.marat.valuter.Model.Currency;
import com.ulsu.marat.valuter.Model.CurrencyEnum;
import com.ulsu.marat.valuter.Model.CurrencyModel;
import com.ulsu.marat.valuter.R;

import java.util.List;


public class SpinnerCurrencyAdapter extends BaseAdapter {

    Context context;
    List<Currency> currencies;

    public SpinnerCurrencyAdapter(Context context, List<Currency> currencies) {
        super();

        this.context = context;
        this.currencies = currencies;
    }

    @Override
    public int getCount() {
        return currencies.size();
    }

    @Override
    public Currency getItem(int position) {
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

    private String getNameByCode(String code) {
        CurrencyEnum name = CurrencyEnum.NONE;
        return name.getNameById(code);
    }
}
