package com.ulsu.marat.valuter.controller;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.ulsu.marat.valuter.Model.CurrencyConverter;
import com.ulsu.marat.valuter.Model.CurrencyEnum;
import com.ulsu.marat.valuter.Model.CurrencyModel;
import com.ulsu.marat.valuter.R;
import com.ulsu.marat.valuter.controller.adapter.SpinnerCurrencyAdapter;
import com.ulsu.marat.valuter.utils.Consts;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.math.BigDecimal;
import java.util.List;

@EFragment(R.layout.fragment_converter_currency)
public class CurrencyConverterFragment extends Fragment {

    @ViewById(R.id.spinner_to)
    Spinner mSpinnerTo;

    @ViewById(R.id.spinner_from)
    Spinner mSpinnerFrom;

    @ViewById(R.id.result_text)
    TextView mResultText;

    @ViewById(R.id.progress)
    LinearLayout mProgress;

    @ViewById(R.id.main_layout)
    LinearLayout mMain;

    @ViewById(R.id.input_currency)
    EditText mInputCurrency;

    CurrencyConverter converter;
    SpinnerCurrencyAdapter adapter;

    private List<CurrencyModel.Currency> currencyList;

    @Click(R.id.convert_button)
    public void ConvertButtonClick(){
        String input = mInputCurrency.getText().toString();
        if (!input.isEmpty()) {
            BigDecimal decimal = new BigDecimal(input);
            String result = converter.convertRubbleToDollar(decimal).toString();
            mResultText.setText(getString(R.string.ruble_icon).replace("{value}",result));
            Log.d("Currency", result);
        }
        String from = adapter.getItem(mSpinnerFrom.getSelectedItemPosition()).getID();
        String to = adapter.getItem(mSpinnerTo.getSelectedItemPosition()).getID();
        Convert(from,to,input);
    }

    @AfterViews
    void bindViews() {
        mProgress.setVisibility(View.VISIBLE);
        mMain.setVisibility(View.INVISIBLE);
        converter = new CurrencyConverter();
    }

    private void initSpinnerList(final List<CurrencyModel.Currency> list) {
        adapter = new SpinnerCurrencyAdapter(getActivity(), list);
        mSpinnerFrom.setAdapter(adapter);
        mSpinnerTo.setAdapter(adapter);
        mProgress.setVisibility(View.GONE);
        mMain.setVisibility(View.VISIBLE);
    }

    public void listChange(final List<CurrencyModel.Currency> list, boolean isGet) {
        if (isGet && list != null) {
            initSpinnerList(list);
            converter.setCurrencyList(list);
        }
    }

    private void Convert(String convertFrom, String convertTo, String value){
        CurrencyEnum currencyEnum = CurrencyEnum.NONE;
        CurrencyEnum currencyFrom =  currencyEnum.getCurrencyById(convertFrom);
        CurrencyEnum currencyTo = currencyEnum.getCurrencyById(convertTo);

    }
}

