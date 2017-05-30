package com.ulsu.marat.valuter.controller.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ulsu.marat.valuter.Model.CurrencyEnum;
import com.ulsu.marat.valuter.Model.CurrencyModel;
import com.ulsu.marat.valuter.R;

import java.util.List;
import java.util.Random;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView currencyName;
        TextView currencyCharCode;
        TextView currencyNominal;
        TextView currencyValue;

        public ViewHolder(View view) {
            super(view);

            currencyName = (TextView) view.findViewById(R.id.currency_name);
            currencyCharCode = (TextView) view.findViewById(R.id.currency_charcode);
            currencyNominal = (TextView) view.findViewById(R.id.currency_nominal);
            currencyValue = (TextView) view.findViewById(R.id.currency_value);

        }
    }

    Context context;
    List<CurrencyModel.Currency> currencyList;

    public CurrencyAdapter(Context context, List<CurrencyModel.Currency> currencyList) {
        this.context = context;
        this.currencyList = currencyList;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_currency_list, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CurrencyModel.Currency currency = currencyList.get(position);
        holder.currencyName.setText(getNameByCode(currency.getID()));
        holder.currencyCharCode.setText(currency.getCharCode());
        holder.currencyNominal.setText(context.getString(R.string.nominal_text).replace("{nominal}",currency.getNominal()));
        holder.currencyValue.setText(context.getString(R.string.russian_ruble_icon).replace("{value}",currency.getValue()));
    }

    @Override
    public int getItemCount() {
        return currencyList.size();
    }

    private String getNameByCode(String code){
        CurrencyEnum name = CurrencyEnum.NONE;
        return name.getNameById(code);
    }
}
