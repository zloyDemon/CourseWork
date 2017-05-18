package com.ulsu.marat.valuter.controller;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.ulsu.marat.valuter.Model.CurrencyModel;
import com.ulsu.marat.valuter.R;
import com.ulsu.marat.valuter.controller.adapter.SpinnerCurrencyAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EFragment(R.layout.fragment_converter_currency)
public class CurrencyConverterFragment extends Fragment {

//    @ViewById(R.id.from_spinner)
//    MaterialBetterSpinner mSpinnerFrom;
//
//    @ViewById(R.id.to_spinner)
//    MaterialBetterSpinner mSpinnerTo;

    @ViewById(R.id.spinner_from)
    Spinner spinner;

    @ViewById(R.id.progress)
    LinearLayout mProgress;

    @ViewById(R.id.main_layout)
    LinearLayout mMain;

    private List<CurrencyModel.Currency> currencyList;

    @AfterViews
    void bindViews() {
        mProgress.setVisibility(View.VISIBLE);
        mMain.setVisibility(View.INVISIBLE);
    }

    private void initSpinnerList(final List<CurrencyModel.Currency> list){
        final SpinnerCurrencyAdapter adapter = new SpinnerCurrencyAdapter(getActivity(), list);
        spinner.setAdapter(adapter);
        mProgress.setVisibility(View.GONE);
        mMain.setVisibility(View.VISIBLE);
    }

    public void listChange(final List<CurrencyModel.Currency> list, boolean isGet) {
        if (isGet && list!=null) {
            initSpinnerList(list);
        }
    }
}

