package com.ulsu.marat.valuter.controller;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.ulsu.marat.valuter.Model.Currency;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.ulsu.marat.valuter.Model.CurrencyEnum.*;


@EFragment(R.layout.fragment_converter_currency)
public class CurrencyConverterFragment extends Fragment {

    public static final String TAG = "CONVERTER";

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
    public void ConvertButtonClick() {
        String input = mInputCurrency.getText().toString();

        if (input.isEmpty()) {
            mInputCurrency.setError(getString(R.string.input_empty_error));
            return;
        }

        String from = adapter.getItem(mSpinnerFrom.getSelectedItemPosition()).getID();
        String to = adapter.getItem(mSpinnerTo.getSelectedItemPosition()).getID();
        Convert(from, to, new BigDecimal(input));
    }

    @AfterViews
    void bindViews() {
        mProgress.setVisibility(View.VISIBLE);
        mMain.setVisibility(View.INVISIBLE);
        converter = new CurrencyConverter();
    }

    private void initSpinnerList(final List<Currency> list) {
        adapter = new SpinnerCurrencyAdapter(getActivity(), list);
        mSpinnerFrom.setAdapter(adapter);
        mSpinnerTo.setAdapter(adapter);
        mProgress.setVisibility(View.GONE);
        mMain.setVisibility(View.VISIBLE);
    }

    public void listChange(final List<CurrencyModel.Currency> list, boolean isGet) {
        if (isGet && list != null) {
            List<Currency> currencies = getCurrencyListFirstFive(list);
            converter.setCurrencyList(currencies);
            initSpinnerList(currencies);
        }
    }

    private List<Currency> getCurrencyList(List<CurrencyModel.Currency> list) {
        List<Currency> currencies = new ArrayList<>();
        for (CurrencyModel.Currency currency : list) {
            currencies.add(new Currency(currency.getID(),
                    currency.getNumCode(),
                    currency.getCharCode(),
                    Integer.parseInt(currency.getNominal()),
                    currency.getName(),
                    new BigDecimal(currency.getValue())));
        }
        currencies.add(new Currency(RUBLE.getId(), "032", "RUB", 1, RUBLE.getNameById(RUBLE.getId()), BigDecimal.ZERO));
        return currencies;
    }

    //todo Временная реализация. Исправить!
    private List<Currency> getCurrencyListFirstFive(List<CurrencyModel.Currency> list) {
        List<Currency> currencies = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            CurrencyModel.Currency currency = list.get(i);
            currencies.add(new Currency(currency.getID(),
                    currency.getNumCode(),
                    currency.getCharCode(),
                    Integer.parseInt(currency.getNominal()),
                    currency.getName(),
                    new BigDecimal(currency.getValue())));
        }
        currencies.add(new Currency(RUBLE.getId(), "032", "RUB", 1, RUBLE.getNameById(RUBLE.getId()), BigDecimal.ZERO));
        return currencies;
    }

    private void Convert(String convertFrom, String convertTo, BigDecimal value) {

        CurrencyEnum currencyEnum = CurrencyEnum.NONE;
        CurrencyEnum currencyFrom = currencyEnum.getCurrencyById(convertFrom);
        CurrencyEnum currencyTo = currencyEnum.getCurrencyById(convertTo);
        BigDecimal result = BigDecimal.ZERO;
        String resultString = "";
        switch (currencyFrom) {
            case AUSTRALIAN_DOLLAR:
                switch (currencyTo) {
                    case AUSTRALIAN_DOLLAR:
                        Log.d(TAG, "AUSTRALIAN_DOLLAR - > AUSTRALIAN_DOLLAR");

                        break;

                    case AZERBAIJAN_MANAT:
                        Log.d(TAG, "AUSTRALIAN_DOLLAR - > AZERBAIJAN_MANAT");
                        break;

                    case BRITISH_POUND_STERLING:
                        Log.d(TAG, "AUSTRALIAN_DOLLAR - > BRITISH_POUND_STERLING");
                        break;

                    case ARMENIA_DRAM:
                        Log.d(TAG, "AUSTRALIAN_DOLLAR - > ARMENIA_DRAM");
                        break;

                    case BELARUSSIAN_RUBLE:

                        break;

                    case BULGARIAN_LEV:

                        break;

                    case BRAZIL_REAL:

                        break;

                    case HUNGARIAN_FORINT:

                        break;

                    case HONG_KONG_DOLLAR:

                        break;

                    case DANISH_KRONE:

                        break;

                    case US_DOLLAR:

                        break;

                    case EURO:

                        break;

                    case INDIAN_RUPEE:

                        break;

                    case ICELAND_KRONA:

                        break;

                    case KAZAKHSTAN_TENGE:

                        break;

                    case CANADIAN_DOLLAR:

                        break;

                    case KYRGYZSTAN_SOM:

                        break;

                    case CHINA_YUAN:

                        break;

                    case MOLDOVA_LEI:

                        break;

                    case NORWEGIAN_KRONE:

                        break;

                    case POLISH_ZLOTY:

                        break;

                    case ROMANIAN_LEU:

                        break;

                    case SDR:

                        break;

                    case SINGAPORE_DOLLAR:

                        break;

                    case TAJIKISTAN_RUBLE:

                        break;

                    case TURKISH_LIRA:

                        break;

                    case NEW_TURKMENISTAN_MANAT:

                        break;

                    case UZBEKISTAN_SUM:

                        break;

                    case UKRAINIAN_HRYVNIA:

                        break;

                    case CZECH_KORUNA:

                        break;

                    case SWEDISH_KRONA:

                        break;

                    case SWISS_FRANC:

                        break;

                    case S_AFRICAN_RAND:

                        break;

                    case SOUTH_KOREAN_WON:

                        break;

                    case JAPANESE_YEN:

                        break;

                    case RUBLE:
                        result = converter.convertAustralianDollarToRubble(value);
                        resultString = getString(R.string.russian_ruble_icon).replace("{value}", result.toString());
                        break;
                }
                break;

            case AZERBAIJAN_MANAT:
                switch (currencyTo) {
                    case AUSTRALIAN_DOLLAR:
                        Log.d(TAG, "AZERBAIJAN_MANAT - > AUSTRALIAN_DOLLAR");
                        break;

                    case AZERBAIJAN_MANAT:

                        break;

                    case BRITISH_POUND_STERLING:

                        break;

                    case ARMENIA_DRAM:

                        break;

                    case BELARUSSIAN_RUBLE:

                        break;

                    case BULGARIAN_LEV:

                        break;

                    case BRAZIL_REAL:

                        break;

                    case HUNGARIAN_FORINT:

                        break;

                    case HONG_KONG_DOLLAR:

                        break;

                    case DANISH_KRONE:

                        break;

                    case US_DOLLAR:

                        break;

                    case EURO:

                        break;

                    case INDIAN_RUPEE:

                        break;

                    case ICELAND_KRONA:

                        break;

                    case KAZAKHSTAN_TENGE:

                        break;

                    case CANADIAN_DOLLAR:

                        break;

                    case KYRGYZSTAN_SOM:

                        break;

                    case CHINA_YUAN:

                        break;

                    case MOLDOVA_LEI:

                        break;

                    case NORWEGIAN_KRONE:

                        break;

                    case POLISH_ZLOTY:

                        break;

                    case ROMANIAN_LEU:

                        break;

                    case SDR:

                        break;

                    case SINGAPORE_DOLLAR:

                        break;

                    case TAJIKISTAN_RUBLE:

                        break;

                    case TURKISH_LIRA:

                        break;

                    case NEW_TURKMENISTAN_MANAT:

                        break;

                    case UZBEKISTAN_SUM:

                        break;

                    case UKRAINIAN_HRYVNIA:

                        break;

                    case CZECH_KORUNA:

                        break;

                    case SWEDISH_KRONA:

                        break;

                    case SWISS_FRANC:

                        break;

                    case S_AFRICAN_RAND:

                        break;

                    case SOUTH_KOREAN_WON:

                        break;

                    case JAPANESE_YEN:

                        break;

                    case RUBLE:
                        resultString = getString(R.string.russian_ruble_icon).replace("{value}", converter.convertAzerbaijanManatToRubble(value).toString());
                        break;
                }
                break;

            case BRITISH_POUND_STERLING:
                switch (currencyTo) {
                    case AUSTRALIAN_DOLLAR:

                        break;

                    case AZERBAIJAN_MANAT:

                        break;

                    case BRITISH_POUND_STERLING:

                        break;

                    case ARMENIA_DRAM:

                        break;

                    case BELARUSSIAN_RUBLE:

                        break;

                    case BULGARIAN_LEV:

                        break;

                    case BRAZIL_REAL:

                        break;

                    case HUNGARIAN_FORINT:

                        break;

                    case HONG_KONG_DOLLAR:

                        break;

                    case DANISH_KRONE:

                        break;

                    case US_DOLLAR:

                        break;

                    case EURO:

                        break;

                    case INDIAN_RUPEE:

                        break;

                    case ICELAND_KRONA:

                        break;

                    case KAZAKHSTAN_TENGE:

                        break;

                    case CANADIAN_DOLLAR:

                        break;

                    case KYRGYZSTAN_SOM:

                        break;

                    case CHINA_YUAN:

                        break;

                    case MOLDOVA_LEI:

                        break;

                    case NORWEGIAN_KRONE:

                        break;

                    case POLISH_ZLOTY:

                        break;

                    case ROMANIAN_LEU:

                        break;

                    case SDR:

                        break;

                    case SINGAPORE_DOLLAR:

                        break;

                    case TAJIKISTAN_RUBLE:

                        break;

                    case TURKISH_LIRA:

                        break;

                    case NEW_TURKMENISTAN_MANAT:

                        break;

                    case UZBEKISTAN_SUM:

                        break;

                    case UKRAINIAN_HRYVNIA:

                        break;

                    case CZECH_KORUNA:

                        break;

                    case SWEDISH_KRONA:

                        break;

                    case SWISS_FRANC:

                        break;

                    case S_AFRICAN_RAND:

                        break;

                    case SOUTH_KOREAN_WON:

                        break;

                    case JAPANESE_YEN:

                        break;

                    case RUBLE:
                        resultString = getString(R.string.russian_ruble_icon).replace("{value}", converter.convertBritishPoundSterlingToRubble(value).toString());
                        break;
                }
                break;

            case ARMENIA_DRAM:
                switch (currencyTo) {
                    case AUSTRALIAN_DOLLAR:

                        break;

                    case AZERBAIJAN_MANAT:

                        break;

                    case BRITISH_POUND_STERLING:

                        break;

                    case ARMENIA_DRAM:

                        break;

                    case BELARUSSIAN_RUBLE:

                        break;

                    case BULGARIAN_LEV:

                        break;

                    case BRAZIL_REAL:

                        break;

                    case HUNGARIAN_FORINT:

                        break;

                    case HONG_KONG_DOLLAR:

                        break;

                    case DANISH_KRONE:

                        break;

                    case US_DOLLAR:

                        break;

                    case EURO:

                        break;

                    case INDIAN_RUPEE:

                        break;

                    case ICELAND_KRONA:

                        break;

                    case KAZAKHSTAN_TENGE:

                        break;

                    case CANADIAN_DOLLAR:

                        break;

                    case KYRGYZSTAN_SOM:

                        break;

                    case CHINA_YUAN:

                        break;

                    case MOLDOVA_LEI:

                        break;

                    case NORWEGIAN_KRONE:

                        break;

                    case POLISH_ZLOTY:

                        break;

                    case ROMANIAN_LEU:

                        break;

                    case SDR:

                        break;

                    case SINGAPORE_DOLLAR:

                        break;

                    case TAJIKISTAN_RUBLE:

                        break;

                    case TURKISH_LIRA:

                        break;

                    case NEW_TURKMENISTAN_MANAT:

                        break;

                    case UZBEKISTAN_SUM:

                        break;

                    case UKRAINIAN_HRYVNIA:

                        break;

                    case CZECH_KORUNA:

                        break;

                    case SWEDISH_KRONA:

                        break;

                    case SWISS_FRANC:

                        break;

                    case S_AFRICAN_RAND:

                        break;

                    case SOUTH_KOREAN_WON:

                        break;

                    case JAPANESE_YEN:

                        break;

                    case RUBLE:
                        resultString = getString(R.string.russian_ruble_icon).replace("{value}", converter.convertArmeniaDramToRubble(value).toString());
                        break;
                }
                break;

            case BELARUSSIAN_RUBLE:
                switch (currencyTo) {
                    case AUSTRALIAN_DOLLAR:

                        break;

                    case AZERBAIJAN_MANAT:

                        break;

                    case BRITISH_POUND_STERLING:

                        break;

                    case ARMENIA_DRAM:

                        break;

                    case BELARUSSIAN_RUBLE:

                        break;

                    case BULGARIAN_LEV:

                        break;

                    case BRAZIL_REAL:

                        break;

                    case HUNGARIAN_FORINT:

                        break;

                    case HONG_KONG_DOLLAR:

                        break;

                    case DANISH_KRONE:

                        break;

                    case US_DOLLAR:

                        break;

                    case EURO:

                        break;

                    case INDIAN_RUPEE:

                        break;

                    case ICELAND_KRONA:

                        break;

                    case KAZAKHSTAN_TENGE:

                        break;

                    case CANADIAN_DOLLAR:

                        break;

                    case KYRGYZSTAN_SOM:

                        break;

                    case CHINA_YUAN:

                        break;

                    case MOLDOVA_LEI:

                        break;

                    case NORWEGIAN_KRONE:

                        break;

                    case POLISH_ZLOTY:

                        break;

                    case ROMANIAN_LEU:

                        break;

                    case SDR:

                        break;

                    case SINGAPORE_DOLLAR:

                        break;

                    case TAJIKISTAN_RUBLE:

                        break;

                    case TURKISH_LIRA:

                        break;

                    case NEW_TURKMENISTAN_MANAT:

                        break;

                    case UZBEKISTAN_SUM:

                        break;

                    case UKRAINIAN_HRYVNIA:

                        break;

                    case CZECH_KORUNA:

                        break;

                    case SWEDISH_KRONA:

                        break;

                    case SWISS_FRANC:

                        break;

                    case S_AFRICAN_RAND:

                        break;

                    case SOUTH_KOREAN_WON:

                        break;

                    case JAPANESE_YEN:

                        break;

                    case RUBLE:

                        break;
                }
                break;

            case BULGARIAN_LEV:
                switch (currencyTo) {
                    case AUSTRALIAN_DOLLAR:

                        break;

                    case AZERBAIJAN_MANAT:

                        break;

                    case BRITISH_POUND_STERLING:

                        break;

                    case ARMENIA_DRAM:

                        break;

                    case BELARUSSIAN_RUBLE:

                        break;

                    case BULGARIAN_LEV:

                        break;

                    case BRAZIL_REAL:

                        break;

                    case HUNGARIAN_FORINT:

                        break;

                    case HONG_KONG_DOLLAR:

                        break;

                    case DANISH_KRONE:

                        break;

                    case US_DOLLAR:

                        break;

                    case EURO:

                        break;

                    case INDIAN_RUPEE:

                        break;

                    case ICELAND_KRONA:

                        break;

                    case KAZAKHSTAN_TENGE:

                        break;

                    case CANADIAN_DOLLAR:

                        break;

                    case KYRGYZSTAN_SOM:

                        break;

                    case CHINA_YUAN:

                        break;

                    case MOLDOVA_LEI:

                        break;

                    case NORWEGIAN_KRONE:

                        break;

                    case POLISH_ZLOTY:

                        break;

                    case ROMANIAN_LEU:

                        break;

                    case SDR:

                        break;

                    case SINGAPORE_DOLLAR:

                        break;

                    case TAJIKISTAN_RUBLE:

                        break;

                    case TURKISH_LIRA:

                        break;

                    case NEW_TURKMENISTAN_MANAT:

                        break;

                    case UZBEKISTAN_SUM:

                        break;

                    case UKRAINIAN_HRYVNIA:

                        break;

                    case CZECH_KORUNA:

                        break;

                    case SWEDISH_KRONA:

                        break;

                    case SWISS_FRANC:

                        break;

                    case S_AFRICAN_RAND:

                        break;

                    case SOUTH_KOREAN_WON:

                        break;

                    case JAPANESE_YEN:

                        break;

                    case RUBLE:

                        break;
                }
                break;

            case BRAZIL_REAL:
                switch (currencyTo) {
                    case AUSTRALIAN_DOLLAR:

                        break;

                    case AZERBAIJAN_MANAT:

                        break;

                    case BRITISH_POUND_STERLING:

                        break;

                    case ARMENIA_DRAM:

                        break;

                    case BELARUSSIAN_RUBLE:

                        break;

                    case BULGARIAN_LEV:

                        break;

                    case BRAZIL_REAL:

                        break;

                    case HUNGARIAN_FORINT:

                        break;

                    case HONG_KONG_DOLLAR:

                        break;

                    case DANISH_KRONE:

                        break;

                    case US_DOLLAR:

                        break;

                    case EURO:

                        break;

                    case INDIAN_RUPEE:

                        break;

                    case ICELAND_KRONA:

                        break;

                    case KAZAKHSTAN_TENGE:

                        break;

                    case CANADIAN_DOLLAR:

                        break;

                    case KYRGYZSTAN_SOM:

                        break;

                    case CHINA_YUAN:

                        break;

                    case MOLDOVA_LEI:

                        break;

                    case NORWEGIAN_KRONE:

                        break;

                    case POLISH_ZLOTY:

                        break;

                    case ROMANIAN_LEU:

                        break;

                    case SDR:

                        break;

                    case SINGAPORE_DOLLAR:

                        break;

                    case TAJIKISTAN_RUBLE:

                        break;

                    case TURKISH_LIRA:

                        break;

                    case NEW_TURKMENISTAN_MANAT:

                        break;

                    case UZBEKISTAN_SUM:

                        break;

                    case UKRAINIAN_HRYVNIA:

                        break;

                    case CZECH_KORUNA:

                        break;

                    case SWEDISH_KRONA:

                        break;

                    case SWISS_FRANC:

                        break;

                    case S_AFRICAN_RAND:

                        break;

                    case SOUTH_KOREAN_WON:

                        break;

                    case JAPANESE_YEN:

                        break;

                    case RUBLE:

                        break;
                }
                break;

            case HUNGARIAN_FORINT:
                switch (currencyTo) {
                    case AUSTRALIAN_DOLLAR:

                        break;

                    case AZERBAIJAN_MANAT:

                        break;

                    case BRITISH_POUND_STERLING:

                        break;

                    case ARMENIA_DRAM:

                        break;

                    case BELARUSSIAN_RUBLE:

                        break;

                    case BULGARIAN_LEV:

                        break;

                    case BRAZIL_REAL:

                        break;

                    case HUNGARIAN_FORINT:

                        break;

                    case HONG_KONG_DOLLAR:

                        break;

                    case DANISH_KRONE:

                        break;

                    case US_DOLLAR:

                        break;

                    case EURO:

                        break;

                    case INDIAN_RUPEE:

                        break;

                    case ICELAND_KRONA:

                        break;

                    case KAZAKHSTAN_TENGE:

                        break;

                    case CANADIAN_DOLLAR:

                        break;

                    case KYRGYZSTAN_SOM:

                        break;

                    case CHINA_YUAN:

                        break;

                    case MOLDOVA_LEI:

                        break;

                    case NORWEGIAN_KRONE:

                        break;

                    case POLISH_ZLOTY:

                        break;

                    case ROMANIAN_LEU:

                        break;

                    case SDR:

                        break;

                    case SINGAPORE_DOLLAR:

                        break;

                    case TAJIKISTAN_RUBLE:

                        break;

                    case TURKISH_LIRA:

                        break;

                    case NEW_TURKMENISTAN_MANAT:

                        break;

                    case UZBEKISTAN_SUM:

                        break;

                    case UKRAINIAN_HRYVNIA:

                        break;

                    case CZECH_KORUNA:

                        break;

                    case SWEDISH_KRONA:

                        break;

                    case SWISS_FRANC:

                        break;

                    case S_AFRICAN_RAND:

                        break;

                    case SOUTH_KOREAN_WON:

                        break;

                    case JAPANESE_YEN:

                        break;

                    case RUBLE:

                        break;
                }
                break;

            case HONG_KONG_DOLLAR:
                switch (currencyTo) {
                    case AUSTRALIAN_DOLLAR:

                        break;

                    case AZERBAIJAN_MANAT:

                        break;

                    case BRITISH_POUND_STERLING:

                        break;

                    case ARMENIA_DRAM:

                        break;

                    case BELARUSSIAN_RUBLE:

                        break;

                    case BULGARIAN_LEV:

                        break;

                    case BRAZIL_REAL:

                        break;

                    case HUNGARIAN_FORINT:

                        break;

                    case HONG_KONG_DOLLAR:

                        break;

                    case DANISH_KRONE:

                        break;

                    case US_DOLLAR:

                        break;

                    case EURO:

                        break;

                    case INDIAN_RUPEE:

                        break;

                    case ICELAND_KRONA:

                        break;

                    case KAZAKHSTAN_TENGE:

                        break;

                    case CANADIAN_DOLLAR:

                        break;

                    case KYRGYZSTAN_SOM:

                        break;

                    case CHINA_YUAN:

                        break;

                    case MOLDOVA_LEI:

                        break;

                    case NORWEGIAN_KRONE:

                        break;

                    case POLISH_ZLOTY:

                        break;

                    case ROMANIAN_LEU:

                        break;

                    case SDR:

                        break;

                    case SINGAPORE_DOLLAR:

                        break;

                    case TAJIKISTAN_RUBLE:

                        break;

                    case TURKISH_LIRA:

                        break;

                    case NEW_TURKMENISTAN_MANAT:

                        break;

                    case UZBEKISTAN_SUM:

                        break;

                    case UKRAINIAN_HRYVNIA:

                        break;

                    case CZECH_KORUNA:

                        break;

                    case SWEDISH_KRONA:

                        break;

                    case SWISS_FRANC:

                        break;

                    case S_AFRICAN_RAND:

                        break;

                    case SOUTH_KOREAN_WON:

                        break;

                    case JAPANESE_YEN:

                        break;

                    case RUBLE:

                        break;
                }
                break;

            case DANISH_KRONE:
                switch (currencyTo) {
                    case AUSTRALIAN_DOLLAR:

                        break;

                    case AZERBAIJAN_MANAT:

                        break;

                    case BRITISH_POUND_STERLING:

                        break;

                    case ARMENIA_DRAM:

                        break;

                    case BELARUSSIAN_RUBLE:

                        break;

                    case BULGARIAN_LEV:

                        break;

                    case BRAZIL_REAL:

                        break;

                    case HUNGARIAN_FORINT:

                        break;

                    case HONG_KONG_DOLLAR:

                        break;

                    case DANISH_KRONE:

                        break;

                    case US_DOLLAR:

                        break;

                    case EURO:

                        break;

                    case INDIAN_RUPEE:

                        break;

                    case ICELAND_KRONA:

                        break;

                    case KAZAKHSTAN_TENGE:

                        break;

                    case CANADIAN_DOLLAR:

                        break;

                    case KYRGYZSTAN_SOM:

                        break;

                    case CHINA_YUAN:

                        break;

                    case MOLDOVA_LEI:

                        break;

                    case NORWEGIAN_KRONE:

                        break;

                    case POLISH_ZLOTY:

                        break;

                    case ROMANIAN_LEU:

                        break;

                    case SDR:

                        break;

                    case SINGAPORE_DOLLAR:

                        break;

                    case TAJIKISTAN_RUBLE:

                        break;

                    case TURKISH_LIRA:

                        break;

                    case NEW_TURKMENISTAN_MANAT:

                        break;

                    case UZBEKISTAN_SUM:

                        break;

                    case UKRAINIAN_HRYVNIA:

                        break;

                    case CZECH_KORUNA:

                        break;

                    case SWEDISH_KRONA:

                        break;

                    case SWISS_FRANC:

                        break;

                    case S_AFRICAN_RAND:

                        break;

                    case SOUTH_KOREAN_WON:

                        break;

                    case JAPANESE_YEN:

                        break;

                    case RUBLE:

                        break;
                }
                break;

            case US_DOLLAR:
                switch (currencyTo) {
                    case AUSTRALIAN_DOLLAR:

                        break;

                    case AZERBAIJAN_MANAT:

                        break;

                    case BRITISH_POUND_STERLING:

                        break;

                    case ARMENIA_DRAM:

                        break;

                    case BELARUSSIAN_RUBLE:

                        break;

                    case BULGARIAN_LEV:

                        break;

                    case BRAZIL_REAL:

                        break;

                    case HUNGARIAN_FORINT:

                        break;

                    case HONG_KONG_DOLLAR:

                        break;

                    case DANISH_KRONE:

                        break;

                    case US_DOLLAR:

                        break;

                    case EURO:

                        break;

                    case INDIAN_RUPEE:

                        break;

                    case ICELAND_KRONA:

                        break;

                    case KAZAKHSTAN_TENGE:

                        break;

                    case CANADIAN_DOLLAR:

                        break;

                    case KYRGYZSTAN_SOM:

                        break;

                    case CHINA_YUAN:

                        break;

                    case MOLDOVA_LEI:

                        break;

                    case NORWEGIAN_KRONE:

                        break;

                    case POLISH_ZLOTY:

                        break;

                    case ROMANIAN_LEU:

                        break;

                    case SDR:

                        break;

                    case SINGAPORE_DOLLAR:

                        break;

                    case TAJIKISTAN_RUBLE:

                        break;

                    case TURKISH_LIRA:

                        break;

                    case NEW_TURKMENISTAN_MANAT:

                        break;

                    case UZBEKISTAN_SUM:

                        break;

                    case UKRAINIAN_HRYVNIA:

                        break;

                    case CZECH_KORUNA:

                        break;

                    case SWEDISH_KRONA:

                        break;

                    case SWISS_FRANC:

                        break;

                    case S_AFRICAN_RAND:

                        break;

                    case SOUTH_KOREAN_WON:

                        break;

                    case JAPANESE_YEN:

                        break;

                    case RUBLE:
                        resultString = getString(R.string.russian_ruble_icon).replace("{value}", converter.convertDollarToRubble(value).toString());
                        break;
                }
                break;

            case EURO:
                switch (currencyTo) {
                    case AUSTRALIAN_DOLLAR:

                        break;

                    case AZERBAIJAN_MANAT:

                        break;

                    case BRITISH_POUND_STERLING:

                        break;

                    case ARMENIA_DRAM:

                        break;

                    case BELARUSSIAN_RUBLE:

                        break;

                    case BULGARIAN_LEV:

                        break;

                    case BRAZIL_REAL:

                        break;

                    case HUNGARIAN_FORINT:

                        break;

                    case HONG_KONG_DOLLAR:

                        break;

                    case DANISH_KRONE:

                        break;

                    case US_DOLLAR:

                        break;

                    case EURO:

                        break;

                    case INDIAN_RUPEE:

                        break;

                    case ICELAND_KRONA:

                        break;

                    case KAZAKHSTAN_TENGE:

                        break;

                    case CANADIAN_DOLLAR:

                        break;

                    case KYRGYZSTAN_SOM:

                        break;

                    case CHINA_YUAN:

                        break;

                    case MOLDOVA_LEI:

                        break;

                    case NORWEGIAN_KRONE:

                        break;

                    case POLISH_ZLOTY:

                        break;

                    case ROMANIAN_LEU:

                        break;

                    case SDR:

                        break;

                    case SINGAPORE_DOLLAR:

                        break;

                    case TAJIKISTAN_RUBLE:

                        break;

                    case TURKISH_LIRA:

                        break;

                    case NEW_TURKMENISTAN_MANAT:

                        break;

                    case UZBEKISTAN_SUM:

                        break;

                    case UKRAINIAN_HRYVNIA:

                        break;

                    case CZECH_KORUNA:

                        break;

                    case SWEDISH_KRONA:

                        break;

                    case SWISS_FRANC:

                        break;

                    case S_AFRICAN_RAND:

                        break;

                    case SOUTH_KOREAN_WON:

                        break;

                    case JAPANESE_YEN:

                        break;

                    case RUBLE:

                        break;
                }
                break;

            case INDIAN_RUPEE:
                switch (currencyTo) {
                    case AUSTRALIAN_DOLLAR:

                        break;

                    case AZERBAIJAN_MANAT:

                        break;

                    case BRITISH_POUND_STERLING:

                        break;

                    case ARMENIA_DRAM:

                        break;

                    case BELARUSSIAN_RUBLE:

                        break;

                    case BULGARIAN_LEV:

                        break;

                    case BRAZIL_REAL:

                        break;

                    case HUNGARIAN_FORINT:

                        break;

                    case HONG_KONG_DOLLAR:

                        break;

                    case DANISH_KRONE:

                        break;

                    case US_DOLLAR:

                        break;

                    case EURO:

                        break;

                    case INDIAN_RUPEE:

                        break;

                    case ICELAND_KRONA:

                        break;

                    case KAZAKHSTAN_TENGE:

                        break;

                    case CANADIAN_DOLLAR:

                        break;

                    case KYRGYZSTAN_SOM:

                        break;

                    case CHINA_YUAN:

                        break;

                    case MOLDOVA_LEI:

                        break;

                    case NORWEGIAN_KRONE:

                        break;

                    case POLISH_ZLOTY:

                        break;

                    case ROMANIAN_LEU:

                        break;

                    case SDR:

                        break;

                    case SINGAPORE_DOLLAR:

                        break;

                    case TAJIKISTAN_RUBLE:

                        break;

                    case TURKISH_LIRA:

                        break;

                    case NEW_TURKMENISTAN_MANAT:

                        break;

                    case UZBEKISTAN_SUM:

                        break;

                    case UKRAINIAN_HRYVNIA:

                        break;

                    case CZECH_KORUNA:

                        break;

                    case SWEDISH_KRONA:

                        break;

                    case SWISS_FRANC:

                        break;

                    case S_AFRICAN_RAND:

                        break;

                    case SOUTH_KOREAN_WON:

                        break;

                    case JAPANESE_YEN:

                        break;

                    case RUBLE:

                        break;
                }
                break;

            case ICELAND_KRONA:
                switch (currencyTo) {
                    case AUSTRALIAN_DOLLAR:

                        break;

                    case AZERBAIJAN_MANAT:

                        break;

                    case BRITISH_POUND_STERLING:

                        break;

                    case ARMENIA_DRAM:

                        break;

                    case BELARUSSIAN_RUBLE:

                        break;

                    case BULGARIAN_LEV:

                        break;

                    case BRAZIL_REAL:

                        break;

                    case HUNGARIAN_FORINT:

                        break;

                    case HONG_KONG_DOLLAR:

                        break;

                    case DANISH_KRONE:

                        break;

                    case US_DOLLAR:

                        break;

                    case EURO:

                        break;

                    case INDIAN_RUPEE:

                        break;

                    case ICELAND_KRONA:

                        break;

                    case KAZAKHSTAN_TENGE:

                        break;

                    case CANADIAN_DOLLAR:

                        break;

                    case KYRGYZSTAN_SOM:

                        break;

                    case CHINA_YUAN:

                        break;

                    case MOLDOVA_LEI:

                        break;

                    case NORWEGIAN_KRONE:

                        break;

                    case POLISH_ZLOTY:

                        break;

                    case ROMANIAN_LEU:

                        break;

                    case SDR:

                        break;

                    case SINGAPORE_DOLLAR:

                        break;

                    case TAJIKISTAN_RUBLE:

                        break;

                    case TURKISH_LIRA:

                        break;

                    case NEW_TURKMENISTAN_MANAT:

                        break;

                    case UZBEKISTAN_SUM:

                        break;

                    case UKRAINIAN_HRYVNIA:

                        break;

                    case CZECH_KORUNA:

                        break;

                    case SWEDISH_KRONA:

                        break;

                    case SWISS_FRANC:

                        break;

                    case S_AFRICAN_RAND:

                        break;

                    case SOUTH_KOREAN_WON:

                        break;

                    case JAPANESE_YEN:

                        break;

                    case RUBLE:

                        break;
                }
                break;

            case KAZAKHSTAN_TENGE:
                switch (currencyTo) {
                    case AUSTRALIAN_DOLLAR:

                        break;

                    case AZERBAIJAN_MANAT:

                        break;

                    case BRITISH_POUND_STERLING:

                        break;

                    case ARMENIA_DRAM:

                        break;

                    case BELARUSSIAN_RUBLE:

                        break;

                    case BULGARIAN_LEV:

                        break;

                    case BRAZIL_REAL:

                        break;

                    case HUNGARIAN_FORINT:

                        break;

                    case HONG_KONG_DOLLAR:

                        break;

                    case DANISH_KRONE:

                        break;

                    case US_DOLLAR:

                        break;

                    case EURO:

                        break;

                    case INDIAN_RUPEE:

                        break;

                    case ICELAND_KRONA:

                        break;

                    case KAZAKHSTAN_TENGE:

                        break;

                    case CANADIAN_DOLLAR:

                        break;

                    case KYRGYZSTAN_SOM:

                        break;

                    case CHINA_YUAN:

                        break;

                    case MOLDOVA_LEI:

                        break;

                    case NORWEGIAN_KRONE:

                        break;

                    case POLISH_ZLOTY:

                        break;

                    case ROMANIAN_LEU:

                        break;

                    case SDR:

                        break;

                    case SINGAPORE_DOLLAR:

                        break;

                    case TAJIKISTAN_RUBLE:

                        break;

                    case TURKISH_LIRA:

                        break;

                    case NEW_TURKMENISTAN_MANAT:

                        break;

                    case UZBEKISTAN_SUM:

                        break;

                    case UKRAINIAN_HRYVNIA:

                        break;

                    case CZECH_KORUNA:

                        break;

                    case SWEDISH_KRONA:

                        break;

                    case SWISS_FRANC:

                        break;

                    case S_AFRICAN_RAND:

                        break;

                    case SOUTH_KOREAN_WON:

                        break;

                    case JAPANESE_YEN:

                        break;

                    case RUBLE:

                        break;
                }
                break;

            case CANADIAN_DOLLAR:
                switch (currencyTo) {
                    case AUSTRALIAN_DOLLAR:

                        break;

                    case AZERBAIJAN_MANAT:

                        break;

                    case BRITISH_POUND_STERLING:

                        break;

                    case ARMENIA_DRAM:

                        break;

                    case BELARUSSIAN_RUBLE:

                        break;

                    case BULGARIAN_LEV:

                        break;

                    case BRAZIL_REAL:

                        break;

                    case HUNGARIAN_FORINT:

                        break;

                    case HONG_KONG_DOLLAR:

                        break;

                    case DANISH_KRONE:

                        break;

                    case US_DOLLAR:

                        break;

                    case EURO:

                        break;

                    case INDIAN_RUPEE:

                        break;

                    case ICELAND_KRONA:

                        break;

                    case KAZAKHSTAN_TENGE:

                        break;

                    case CANADIAN_DOLLAR:

                        break;

                    case KYRGYZSTAN_SOM:

                        break;

                    case CHINA_YUAN:

                        break;

                    case MOLDOVA_LEI:

                        break;

                    case NORWEGIAN_KRONE:

                        break;

                    case POLISH_ZLOTY:

                        break;

                    case ROMANIAN_LEU:

                        break;

                    case SDR:

                        break;

                    case SINGAPORE_DOLLAR:

                        break;

                    case TAJIKISTAN_RUBLE:

                        break;

                    case TURKISH_LIRA:

                        break;

                    case NEW_TURKMENISTAN_MANAT:

                        break;

                    case UZBEKISTAN_SUM:

                        break;

                    case UKRAINIAN_HRYVNIA:

                        break;

                    case CZECH_KORUNA:

                        break;

                    case SWEDISH_KRONA:

                        break;

                    case SWISS_FRANC:

                        break;

                    case S_AFRICAN_RAND:

                        break;

                    case SOUTH_KOREAN_WON:

                        break;

                    case JAPANESE_YEN:

                        break;

                    case RUBLE:

                        break;
                }
                break;

            case KYRGYZSTAN_SOM:
                switch (currencyTo) {
                    case AUSTRALIAN_DOLLAR:

                        break;

                    case AZERBAIJAN_MANAT:

                        break;

                    case BRITISH_POUND_STERLING:

                        break;

                    case ARMENIA_DRAM:

                        break;

                    case BELARUSSIAN_RUBLE:

                        break;

                    case BULGARIAN_LEV:

                        break;

                    case BRAZIL_REAL:

                        break;

                    case HUNGARIAN_FORINT:

                        break;

                    case HONG_KONG_DOLLAR:

                        break;

                    case DANISH_KRONE:

                        break;

                    case US_DOLLAR:

                        break;

                    case EURO:

                        break;

                    case INDIAN_RUPEE:

                        break;

                    case ICELAND_KRONA:

                        break;

                    case KAZAKHSTAN_TENGE:

                        break;

                    case CANADIAN_DOLLAR:

                        break;

                    case KYRGYZSTAN_SOM:

                        break;

                    case CHINA_YUAN:

                        break;

                    case MOLDOVA_LEI:

                        break;

                    case NORWEGIAN_KRONE:

                        break;

                    case POLISH_ZLOTY:

                        break;

                    case ROMANIAN_LEU:

                        break;

                    case SDR:

                        break;

                    case SINGAPORE_DOLLAR:

                        break;

                    case TAJIKISTAN_RUBLE:

                        break;

                    case TURKISH_LIRA:

                        break;

                    case NEW_TURKMENISTAN_MANAT:

                        break;

                    case UZBEKISTAN_SUM:

                        break;

                    case UKRAINIAN_HRYVNIA:

                        break;

                    case CZECH_KORUNA:

                        break;

                    case SWEDISH_KRONA:

                        break;

                    case SWISS_FRANC:

                        break;

                    case S_AFRICAN_RAND:

                        break;

                    case SOUTH_KOREAN_WON:

                        break;

                    case JAPANESE_YEN:

                        break;

                    case RUBLE:

                        break;
                }
                break;

            case CHINA_YUAN:
                switch (currencyTo) {
                    case AUSTRALIAN_DOLLAR:

                        break;

                    case AZERBAIJAN_MANAT:

                        break;

                    case BRITISH_POUND_STERLING:

                        break;

                    case ARMENIA_DRAM:

                        break;

                    case BELARUSSIAN_RUBLE:

                        break;

                    case BULGARIAN_LEV:

                        break;

                    case BRAZIL_REAL:

                        break;

                    case HUNGARIAN_FORINT:

                        break;

                    case HONG_KONG_DOLLAR:

                        break;

                    case DANISH_KRONE:

                        break;

                    case US_DOLLAR:

                        break;

                    case EURO:

                        break;

                    case INDIAN_RUPEE:

                        break;

                    case ICELAND_KRONA:

                        break;

                    case KAZAKHSTAN_TENGE:

                        break;

                    case CANADIAN_DOLLAR:

                        break;

                    case KYRGYZSTAN_SOM:

                        break;

                    case CHINA_YUAN:

                        break;

                    case MOLDOVA_LEI:

                        break;

                    case NORWEGIAN_KRONE:

                        break;

                    case POLISH_ZLOTY:

                        break;

                    case ROMANIAN_LEU:

                        break;

                    case SDR:

                        break;

                    case SINGAPORE_DOLLAR:

                        break;

                    case TAJIKISTAN_RUBLE:

                        break;

                    case TURKISH_LIRA:

                        break;

                    case NEW_TURKMENISTAN_MANAT:

                        break;

                    case UZBEKISTAN_SUM:

                        break;

                    case UKRAINIAN_HRYVNIA:

                        break;

                    case CZECH_KORUNA:

                        break;

                    case SWEDISH_KRONA:

                        break;

                    case SWISS_FRANC:

                        break;

                    case S_AFRICAN_RAND:

                        break;

                    case SOUTH_KOREAN_WON:

                        break;

                    case JAPANESE_YEN:

                        break;

                    case RUBLE:

                        break;
                }
                break;

            case MOLDOVA_LEI:
                switch (currencyTo) {
                    case AUSTRALIAN_DOLLAR:

                        break;

                    case AZERBAIJAN_MANAT:

                        break;

                    case BRITISH_POUND_STERLING:

                        break;

                    case ARMENIA_DRAM:

                        break;

                    case BELARUSSIAN_RUBLE:

                        break;

                    case BULGARIAN_LEV:

                        break;

                    case BRAZIL_REAL:

                        break;

                    case HUNGARIAN_FORINT:

                        break;

                    case HONG_KONG_DOLLAR:

                        break;

                    case DANISH_KRONE:

                        break;

                    case US_DOLLAR:

                        break;

                    case EURO:

                        break;

                    case INDIAN_RUPEE:

                        break;

                    case ICELAND_KRONA:

                        break;

                    case KAZAKHSTAN_TENGE:

                        break;

                    case CANADIAN_DOLLAR:

                        break;

                    case KYRGYZSTAN_SOM:

                        break;

                    case CHINA_YUAN:

                        break;

                    case MOLDOVA_LEI:

                        break;

                    case NORWEGIAN_KRONE:

                        break;

                    case POLISH_ZLOTY:

                        break;

                    case ROMANIAN_LEU:

                        break;

                    case SDR:

                        break;

                    case SINGAPORE_DOLLAR:

                        break;

                    case TAJIKISTAN_RUBLE:

                        break;

                    case TURKISH_LIRA:

                        break;

                    case NEW_TURKMENISTAN_MANAT:

                        break;

                    case UZBEKISTAN_SUM:

                        break;

                    case UKRAINIAN_HRYVNIA:

                        break;

                    case CZECH_KORUNA:

                        break;

                    case SWEDISH_KRONA:

                        break;

                    case SWISS_FRANC:

                        break;

                    case S_AFRICAN_RAND:

                        break;

                    case SOUTH_KOREAN_WON:

                        break;

                    case JAPANESE_YEN:

                        break;

                    case RUBLE:

                        break;
                }
                break;

            case NORWEGIAN_KRONE:
                switch (currencyTo) {
                    case AUSTRALIAN_DOLLAR:

                        break;

                    case AZERBAIJAN_MANAT:

                        break;

                    case BRITISH_POUND_STERLING:

                        break;

                    case ARMENIA_DRAM:

                        break;

                    case BELARUSSIAN_RUBLE:

                        break;

                    case BULGARIAN_LEV:

                        break;

                    case BRAZIL_REAL:

                        break;

                    case HUNGARIAN_FORINT:

                        break;

                    case HONG_KONG_DOLLAR:

                        break;

                    case DANISH_KRONE:

                        break;

                    case US_DOLLAR:

                        break;

                    case EURO:

                        break;

                    case INDIAN_RUPEE:

                        break;

                    case ICELAND_KRONA:

                        break;

                    case KAZAKHSTAN_TENGE:

                        break;

                    case CANADIAN_DOLLAR:

                        break;

                    case KYRGYZSTAN_SOM:

                        break;

                    case CHINA_YUAN:

                        break;

                    case MOLDOVA_LEI:

                        break;

                    case NORWEGIAN_KRONE:

                        break;

                    case POLISH_ZLOTY:

                        break;

                    case ROMANIAN_LEU:

                        break;

                    case SDR:

                        break;

                    case SINGAPORE_DOLLAR:

                        break;

                    case TAJIKISTAN_RUBLE:

                        break;

                    case TURKISH_LIRA:

                        break;

                    case NEW_TURKMENISTAN_MANAT:

                        break;

                    case UZBEKISTAN_SUM:

                        break;

                    case UKRAINIAN_HRYVNIA:

                        break;

                    case CZECH_KORUNA:

                        break;

                    case SWEDISH_KRONA:

                        break;

                    case SWISS_FRANC:

                        break;

                    case S_AFRICAN_RAND:

                        break;

                    case SOUTH_KOREAN_WON:

                        break;

                    case JAPANESE_YEN:

                        break;

                    case RUBLE:

                        break;
                }
                break;

            case POLISH_ZLOTY:
                switch (currencyTo) {
                    case AUSTRALIAN_DOLLAR:

                        break;

                    case AZERBAIJAN_MANAT:

                        break;

                    case BRITISH_POUND_STERLING:

                        break;

                    case ARMENIA_DRAM:

                        break;

                    case BELARUSSIAN_RUBLE:

                        break;

                    case BULGARIAN_LEV:

                        break;

                    case BRAZIL_REAL:

                        break;

                    case HUNGARIAN_FORINT:

                        break;

                    case HONG_KONG_DOLLAR:

                        break;

                    case DANISH_KRONE:

                        break;

                    case US_DOLLAR:

                        break;

                    case EURO:

                        break;

                    case INDIAN_RUPEE:

                        break;

                    case ICELAND_KRONA:

                        break;

                    case KAZAKHSTAN_TENGE:

                        break;

                    case CANADIAN_DOLLAR:

                        break;

                    case KYRGYZSTAN_SOM:

                        break;

                    case CHINA_YUAN:

                        break;

                    case MOLDOVA_LEI:

                        break;

                    case NORWEGIAN_KRONE:

                        break;

                    case POLISH_ZLOTY:

                        break;

                    case ROMANIAN_LEU:

                        break;

                    case SDR:

                        break;

                    case SINGAPORE_DOLLAR:

                        break;

                    case TAJIKISTAN_RUBLE:

                        break;

                    case TURKISH_LIRA:

                        break;

                    case NEW_TURKMENISTAN_MANAT:

                        break;

                    case UZBEKISTAN_SUM:

                        break;

                    case UKRAINIAN_HRYVNIA:

                        break;

                    case CZECH_KORUNA:

                        break;

                    case SWEDISH_KRONA:

                        break;

                    case SWISS_FRANC:

                        break;

                    case S_AFRICAN_RAND:

                        break;

                    case SOUTH_KOREAN_WON:

                        break;

                    case JAPANESE_YEN:

                        break;

                    case RUBLE:

                        break;
                }
                break;

            case ROMANIAN_LEU:
                switch (currencyTo) {
                    case AUSTRALIAN_DOLLAR:

                        break;

                    case AZERBAIJAN_MANAT:

                        break;

                    case BRITISH_POUND_STERLING:

                        break;

                    case ARMENIA_DRAM:

                        break;

                    case BELARUSSIAN_RUBLE:

                        break;

                    case BULGARIAN_LEV:

                        break;

                    case BRAZIL_REAL:

                        break;

                    case HUNGARIAN_FORINT:

                        break;

                    case HONG_KONG_DOLLAR:

                        break;

                    case DANISH_KRONE:

                        break;

                    case US_DOLLAR:

                        break;

                    case EURO:

                        break;

                    case INDIAN_RUPEE:

                        break;

                    case ICELAND_KRONA:

                        break;

                    case KAZAKHSTAN_TENGE:

                        break;

                    case CANADIAN_DOLLAR:

                        break;

                    case KYRGYZSTAN_SOM:

                        break;

                    case CHINA_YUAN:

                        break;

                    case MOLDOVA_LEI:

                        break;

                    case NORWEGIAN_KRONE:

                        break;

                    case POLISH_ZLOTY:

                        break;

                    case ROMANIAN_LEU:

                        break;

                    case SDR:

                        break;

                    case SINGAPORE_DOLLAR:

                        break;

                    case TAJIKISTAN_RUBLE:

                        break;

                    case TURKISH_LIRA:

                        break;

                    case NEW_TURKMENISTAN_MANAT:

                        break;

                    case UZBEKISTAN_SUM:

                        break;

                    case UKRAINIAN_HRYVNIA:

                        break;

                    case CZECH_KORUNA:

                        break;

                    case SWEDISH_KRONA:

                        break;

                    case SWISS_FRANC:

                        break;

                    case S_AFRICAN_RAND:

                        break;

                    case SOUTH_KOREAN_WON:

                        break;

                    case JAPANESE_YEN:

                        break;

                    case RUBLE:

                        break;
                }
                break;

            case SDR:
                switch (currencyTo) {
                    case AUSTRALIAN_DOLLAR:

                        break;

                    case AZERBAIJAN_MANAT:

                        break;

                    case BRITISH_POUND_STERLING:

                        break;

                    case ARMENIA_DRAM:

                        break;

                    case BELARUSSIAN_RUBLE:

                        break;

                    case BULGARIAN_LEV:

                        break;

                    case BRAZIL_REAL:

                        break;

                    case HUNGARIAN_FORINT:

                        break;

                    case HONG_KONG_DOLLAR:

                        break;

                    case DANISH_KRONE:

                        break;

                    case US_DOLLAR:

                        break;

                    case EURO:

                        break;

                    case INDIAN_RUPEE:

                        break;

                    case ICELAND_KRONA:

                        break;

                    case KAZAKHSTAN_TENGE:

                        break;

                    case CANADIAN_DOLLAR:

                        break;

                    case KYRGYZSTAN_SOM:

                        break;

                    case CHINA_YUAN:

                        break;

                    case MOLDOVA_LEI:

                        break;

                    case NORWEGIAN_KRONE:

                        break;

                    case POLISH_ZLOTY:

                        break;

                    case ROMANIAN_LEU:

                        break;

                    case SDR:

                        break;

                    case SINGAPORE_DOLLAR:

                        break;

                    case TAJIKISTAN_RUBLE:

                        break;

                    case TURKISH_LIRA:

                        break;

                    case NEW_TURKMENISTAN_MANAT:

                        break;

                    case UZBEKISTAN_SUM:

                        break;

                    case UKRAINIAN_HRYVNIA:

                        break;

                    case CZECH_KORUNA:

                        break;

                    case SWEDISH_KRONA:

                        break;

                    case SWISS_FRANC:

                        break;

                    case S_AFRICAN_RAND:

                        break;

                    case SOUTH_KOREAN_WON:

                        break;

                    case JAPANESE_YEN:

                        break;

                    case RUBLE:

                        break;
                }
                break;

            case SINGAPORE_DOLLAR:
                switch (currencyTo) {
                    case AUSTRALIAN_DOLLAR:

                        break;

                    case AZERBAIJAN_MANAT:

                        break;

                    case BRITISH_POUND_STERLING:

                        break;

                    case ARMENIA_DRAM:

                        break;

                    case BELARUSSIAN_RUBLE:

                        break;

                    case BULGARIAN_LEV:

                        break;

                    case BRAZIL_REAL:

                        break;

                    case HUNGARIAN_FORINT:

                        break;

                    case HONG_KONG_DOLLAR:

                        break;

                    case DANISH_KRONE:

                        break;

                    case US_DOLLAR:

                        break;

                    case EURO:

                        break;

                    case INDIAN_RUPEE:

                        break;

                    case ICELAND_KRONA:

                        break;

                    case KAZAKHSTAN_TENGE:

                        break;

                    case CANADIAN_DOLLAR:

                        break;

                    case KYRGYZSTAN_SOM:

                        break;

                    case CHINA_YUAN:

                        break;

                    case MOLDOVA_LEI:

                        break;

                    case NORWEGIAN_KRONE:

                        break;

                    case POLISH_ZLOTY:

                        break;

                    case ROMANIAN_LEU:

                        break;

                    case SDR:

                        break;

                    case SINGAPORE_DOLLAR:

                        break;

                    case TAJIKISTAN_RUBLE:

                        break;

                    case TURKISH_LIRA:

                        break;

                    case NEW_TURKMENISTAN_MANAT:

                        break;

                    case UZBEKISTAN_SUM:

                        break;

                    case UKRAINIAN_HRYVNIA:

                        break;

                    case CZECH_KORUNA:

                        break;

                    case SWEDISH_KRONA:

                        break;

                    case SWISS_FRANC:

                        break;

                    case S_AFRICAN_RAND:

                        break;

                    case SOUTH_KOREAN_WON:

                        break;

                    case JAPANESE_YEN:

                        break;

                    case RUBLE:

                        break;
                }
                break;

            case TAJIKISTAN_RUBLE:
                switch (currencyTo) {
                    case AUSTRALIAN_DOLLAR:

                        break;

                    case AZERBAIJAN_MANAT:

                        break;

                    case BRITISH_POUND_STERLING:

                        break;

                    case ARMENIA_DRAM:

                        break;

                    case BELARUSSIAN_RUBLE:

                        break;

                    case BULGARIAN_LEV:

                        break;

                    case BRAZIL_REAL:

                        break;

                    case HUNGARIAN_FORINT:

                        break;

                    case HONG_KONG_DOLLAR:

                        break;

                    case DANISH_KRONE:

                        break;

                    case US_DOLLAR:

                        break;

                    case EURO:

                        break;

                    case INDIAN_RUPEE:

                        break;

                    case ICELAND_KRONA:

                        break;

                    case KAZAKHSTAN_TENGE:

                        break;

                    case CANADIAN_DOLLAR:

                        break;

                    case KYRGYZSTAN_SOM:

                        break;

                    case CHINA_YUAN:

                        break;

                    case MOLDOVA_LEI:

                        break;

                    case NORWEGIAN_KRONE:

                        break;

                    case POLISH_ZLOTY:

                        break;

                    case ROMANIAN_LEU:

                        break;

                    case SDR:

                        break;

                    case SINGAPORE_DOLLAR:

                        break;

                    case TAJIKISTAN_RUBLE:

                        break;

                    case TURKISH_LIRA:

                        break;

                    case NEW_TURKMENISTAN_MANAT:

                        break;

                    case UZBEKISTAN_SUM:

                        break;

                    case UKRAINIAN_HRYVNIA:

                        break;

                    case CZECH_KORUNA:

                        break;

                    case SWEDISH_KRONA:

                        break;

                    case SWISS_FRANC:

                        break;

                    case S_AFRICAN_RAND:

                        break;

                    case SOUTH_KOREAN_WON:

                        break;

                    case JAPANESE_YEN:

                        break;

                    case RUBLE:

                        break;
                }
                break;

            case TURKISH_LIRA:
                switch (currencyTo) {
                    case AUSTRALIAN_DOLLAR:

                        break;

                    case AZERBAIJAN_MANAT:

                        break;

                    case BRITISH_POUND_STERLING:

                        break;

                    case ARMENIA_DRAM:

                        break;

                    case BELARUSSIAN_RUBLE:

                        break;

                    case BULGARIAN_LEV:

                        break;

                    case BRAZIL_REAL:

                        break;

                    case HUNGARIAN_FORINT:

                        break;

                    case HONG_KONG_DOLLAR:

                        break;

                    case DANISH_KRONE:

                        break;

                    case US_DOLLAR:

                        break;

                    case EURO:

                        break;

                    case INDIAN_RUPEE:

                        break;

                    case ICELAND_KRONA:

                        break;

                    case KAZAKHSTAN_TENGE:

                        break;

                    case CANADIAN_DOLLAR:

                        break;

                    case KYRGYZSTAN_SOM:

                        break;

                    case CHINA_YUAN:

                        break;

                    case MOLDOVA_LEI:

                        break;

                    case NORWEGIAN_KRONE:

                        break;

                    case POLISH_ZLOTY:

                        break;

                    case ROMANIAN_LEU:

                        break;

                    case SDR:

                        break;

                    case SINGAPORE_DOLLAR:

                        break;

                    case TAJIKISTAN_RUBLE:

                        break;

                    case TURKISH_LIRA:

                        break;

                    case NEW_TURKMENISTAN_MANAT:

                        break;

                    case UZBEKISTAN_SUM:

                        break;

                    case UKRAINIAN_HRYVNIA:

                        break;

                    case CZECH_KORUNA:

                        break;

                    case SWEDISH_KRONA:

                        break;

                    case SWISS_FRANC:

                        break;

                    case S_AFRICAN_RAND:

                        break;

                    case SOUTH_KOREAN_WON:

                        break;

                    case JAPANESE_YEN:

                        break;

                    case RUBLE:

                        break;
                }
                break;

            case NEW_TURKMENISTAN_MANAT:
                switch (currencyTo) {
                    case AUSTRALIAN_DOLLAR:

                        break;

                    case AZERBAIJAN_MANAT:

                        break;

                    case BRITISH_POUND_STERLING:

                        break;

                    case ARMENIA_DRAM:

                        break;

                    case BELARUSSIAN_RUBLE:

                        break;

                    case BULGARIAN_LEV:

                        break;

                    case BRAZIL_REAL:

                        break;

                    case HUNGARIAN_FORINT:

                        break;

                    case HONG_KONG_DOLLAR:

                        break;

                    case DANISH_KRONE:

                        break;

                    case US_DOLLAR:

                        break;

                    case EURO:

                        break;

                    case INDIAN_RUPEE:

                        break;

                    case ICELAND_KRONA:

                        break;

                    case KAZAKHSTAN_TENGE:

                        break;

                    case CANADIAN_DOLLAR:

                        break;

                    case KYRGYZSTAN_SOM:

                        break;

                    case CHINA_YUAN:

                        break;

                    case MOLDOVA_LEI:

                        break;

                    case NORWEGIAN_KRONE:

                        break;

                    case POLISH_ZLOTY:

                        break;

                    case ROMANIAN_LEU:

                        break;

                    case SDR:

                        break;

                    case SINGAPORE_DOLLAR:

                        break;

                    case TAJIKISTAN_RUBLE:

                        break;

                    case TURKISH_LIRA:

                        break;

                    case NEW_TURKMENISTAN_MANAT:

                        break;

                    case UZBEKISTAN_SUM:

                        break;

                    case UKRAINIAN_HRYVNIA:

                        break;

                    case CZECH_KORUNA:

                        break;

                    case SWEDISH_KRONA:

                        break;

                    case SWISS_FRANC:

                        break;

                    case S_AFRICAN_RAND:

                        break;

                    case SOUTH_KOREAN_WON:

                        break;

                    case JAPANESE_YEN:

                        break;

                    case RUBLE:

                        break;
                }
                break;

            case UZBEKISTAN_SUM:
                switch (currencyTo) {
                    case AUSTRALIAN_DOLLAR:

                        break;

                    case AZERBAIJAN_MANAT:

                        break;

                    case BRITISH_POUND_STERLING:

                        break;

                    case ARMENIA_DRAM:

                        break;

                    case BELARUSSIAN_RUBLE:

                        break;

                    case BULGARIAN_LEV:

                        break;

                    case BRAZIL_REAL:

                        break;

                    case HUNGARIAN_FORINT:

                        break;

                    case HONG_KONG_DOLLAR:

                        break;

                    case DANISH_KRONE:

                        break;

                    case US_DOLLAR:

                        break;

                    case EURO:

                        break;

                    case INDIAN_RUPEE:

                        break;

                    case ICELAND_KRONA:

                        break;

                    case KAZAKHSTAN_TENGE:

                        break;

                    case CANADIAN_DOLLAR:

                        break;

                    case KYRGYZSTAN_SOM:

                        break;

                    case CHINA_YUAN:

                        break;

                    case MOLDOVA_LEI:

                        break;

                    case NORWEGIAN_KRONE:

                        break;

                    case POLISH_ZLOTY:

                        break;

                    case ROMANIAN_LEU:

                        break;

                    case SDR:

                        break;

                    case SINGAPORE_DOLLAR:

                        break;

                    case TAJIKISTAN_RUBLE:

                        break;

                    case TURKISH_LIRA:

                        break;

                    case NEW_TURKMENISTAN_MANAT:

                        break;

                    case UZBEKISTAN_SUM:

                        break;

                    case UKRAINIAN_HRYVNIA:

                        break;

                    case CZECH_KORUNA:

                        break;

                    case SWEDISH_KRONA:

                        break;

                    case SWISS_FRANC:

                        break;

                    case S_AFRICAN_RAND:

                        break;

                    case SOUTH_KOREAN_WON:

                        break;

                    case JAPANESE_YEN:

                        break;

                    case RUBLE:

                        break;
                }
                break;

            case UKRAINIAN_HRYVNIA:
                switch (currencyTo) {
                    case AUSTRALIAN_DOLLAR:

                        break;

                    case AZERBAIJAN_MANAT:

                        break;

                    case BRITISH_POUND_STERLING:

                        break;

                    case ARMENIA_DRAM:

                        break;

                    case BELARUSSIAN_RUBLE:

                        break;

                    case BULGARIAN_LEV:

                        break;

                    case BRAZIL_REAL:

                        break;

                    case HUNGARIAN_FORINT:

                        break;

                    case HONG_KONG_DOLLAR:

                        break;

                    case DANISH_KRONE:

                        break;

                    case US_DOLLAR:

                        break;

                    case EURO:

                        break;

                    case INDIAN_RUPEE:

                        break;

                    case ICELAND_KRONA:

                        break;

                    case KAZAKHSTAN_TENGE:

                        break;

                    case CANADIAN_DOLLAR:

                        break;

                    case KYRGYZSTAN_SOM:

                        break;

                    case CHINA_YUAN:

                        break;

                    case MOLDOVA_LEI:

                        break;

                    case NORWEGIAN_KRONE:

                        break;

                    case POLISH_ZLOTY:

                        break;

                    case ROMANIAN_LEU:

                        break;

                    case SDR:

                        break;

                    case SINGAPORE_DOLLAR:

                        break;

                    case TAJIKISTAN_RUBLE:

                        break;

                    case TURKISH_LIRA:

                        break;

                    case NEW_TURKMENISTAN_MANAT:

                        break;

                    case UZBEKISTAN_SUM:

                        break;

                    case UKRAINIAN_HRYVNIA:

                        break;

                    case CZECH_KORUNA:

                        break;

                    case SWEDISH_KRONA:

                        break;

                    case SWISS_FRANC:

                        break;

                    case S_AFRICAN_RAND:

                        break;

                    case SOUTH_KOREAN_WON:

                        break;

                    case JAPANESE_YEN:

                        break;

                    case RUBLE:

                        break;
                }
                break;

            case CZECH_KORUNA:
                switch (currencyTo) {
                    case AUSTRALIAN_DOLLAR:

                        break;

                    case AZERBAIJAN_MANAT:

                        break;

                    case BRITISH_POUND_STERLING:

                        break;

                    case ARMENIA_DRAM:

                        break;

                    case BELARUSSIAN_RUBLE:

                        break;

                    case BULGARIAN_LEV:

                        break;

                    case BRAZIL_REAL:

                        break;

                    case HUNGARIAN_FORINT:

                        break;

                    case HONG_KONG_DOLLAR:

                        break;

                    case DANISH_KRONE:

                        break;

                    case US_DOLLAR:

                        break;

                    case EURO:

                        break;

                    case INDIAN_RUPEE:

                        break;

                    case ICELAND_KRONA:

                        break;

                    case KAZAKHSTAN_TENGE:

                        break;

                    case CANADIAN_DOLLAR:

                        break;

                    case KYRGYZSTAN_SOM:

                        break;

                    case CHINA_YUAN:

                        break;

                    case MOLDOVA_LEI:

                        break;

                    case NORWEGIAN_KRONE:

                        break;

                    case POLISH_ZLOTY:

                        break;

                    case ROMANIAN_LEU:

                        break;

                    case SDR:

                        break;

                    case SINGAPORE_DOLLAR:

                        break;

                    case TAJIKISTAN_RUBLE:

                        break;

                    case TURKISH_LIRA:

                        break;

                    case NEW_TURKMENISTAN_MANAT:

                        break;

                    case UZBEKISTAN_SUM:

                        break;

                    case UKRAINIAN_HRYVNIA:

                        break;

                    case CZECH_KORUNA:

                        break;

                    case SWEDISH_KRONA:

                        break;

                    case SWISS_FRANC:

                        break;

                    case S_AFRICAN_RAND:

                        break;

                    case SOUTH_KOREAN_WON:

                        break;

                    case JAPANESE_YEN:

                        break;

                    case RUBLE:

                        break;
                }
                break;

            case SWEDISH_KRONA:
                switch (currencyTo) {
                    case AUSTRALIAN_DOLLAR:

                        break;

                    case AZERBAIJAN_MANAT:

                        break;

                    case BRITISH_POUND_STERLING:

                        break;

                    case ARMENIA_DRAM:

                        break;

                    case BELARUSSIAN_RUBLE:

                        break;

                    case BULGARIAN_LEV:

                        break;

                    case BRAZIL_REAL:

                        break;

                    case HUNGARIAN_FORINT:

                        break;

                    case HONG_KONG_DOLLAR:

                        break;

                    case DANISH_KRONE:

                        break;

                    case US_DOLLAR:

                        break;

                    case EURO:

                        break;

                    case INDIAN_RUPEE:

                        break;

                    case ICELAND_KRONA:

                        break;

                    case KAZAKHSTAN_TENGE:

                        break;

                    case CANADIAN_DOLLAR:

                        break;

                    case KYRGYZSTAN_SOM:

                        break;

                    case CHINA_YUAN:

                        break;

                    case MOLDOVA_LEI:

                        break;

                    case NORWEGIAN_KRONE:

                        break;

                    case POLISH_ZLOTY:

                        break;

                    case ROMANIAN_LEU:

                        break;

                    case SDR:

                        break;

                    case SINGAPORE_DOLLAR:

                        break;

                    case TAJIKISTAN_RUBLE:

                        break;

                    case TURKISH_LIRA:

                        break;

                    case NEW_TURKMENISTAN_MANAT:

                        break;

                    case UZBEKISTAN_SUM:

                        break;

                    case UKRAINIAN_HRYVNIA:

                        break;

                    case CZECH_KORUNA:

                        break;

                    case SWEDISH_KRONA:

                        break;

                    case SWISS_FRANC:

                        break;

                    case S_AFRICAN_RAND:

                        break;

                    case SOUTH_KOREAN_WON:

                        break;

                    case JAPANESE_YEN:

                        break;

                    case RUBLE:

                        break;
                }
                break;

            case SWISS_FRANC:
                switch (currencyTo) {
                    case AUSTRALIAN_DOLLAR:

                        break;

                    case AZERBAIJAN_MANAT:

                        break;

                    case BRITISH_POUND_STERLING:

                        break;

                    case ARMENIA_DRAM:

                        break;

                    case BELARUSSIAN_RUBLE:

                        break;

                    case BULGARIAN_LEV:

                        break;

                    case BRAZIL_REAL:

                        break;

                    case HUNGARIAN_FORINT:

                        break;

                    case HONG_KONG_DOLLAR:

                        break;

                    case DANISH_KRONE:

                        break;

                    case US_DOLLAR:

                        break;

                    case EURO:

                        break;

                    case INDIAN_RUPEE:

                        break;

                    case ICELAND_KRONA:

                        break;

                    case KAZAKHSTAN_TENGE:

                        break;

                    case CANADIAN_DOLLAR:

                        break;

                    case KYRGYZSTAN_SOM:

                        break;

                    case CHINA_YUAN:

                        break;

                    case MOLDOVA_LEI:

                        break;

                    case NORWEGIAN_KRONE:

                        break;

                    case POLISH_ZLOTY:

                        break;

                    case ROMANIAN_LEU:

                        break;

                    case SDR:

                        break;

                    case SINGAPORE_DOLLAR:

                        break;

                    case TAJIKISTAN_RUBLE:

                        break;

                    case TURKISH_LIRA:

                        break;

                    case NEW_TURKMENISTAN_MANAT:

                        break;

                    case UZBEKISTAN_SUM:

                        break;

                    case UKRAINIAN_HRYVNIA:

                        break;

                    case CZECH_KORUNA:

                        break;

                    case SWEDISH_KRONA:

                        break;

                    case SWISS_FRANC:

                        break;

                    case S_AFRICAN_RAND:

                        break;

                    case SOUTH_KOREAN_WON:

                        break;

                    case JAPANESE_YEN:

                        break;

                    case RUBLE:

                        break;
                }
                break;

            case S_AFRICAN_RAND:
                switch (currencyTo) {
                    case AUSTRALIAN_DOLLAR:

                        break;

                    case AZERBAIJAN_MANAT:

                        break;

                    case BRITISH_POUND_STERLING:

                        break;

                    case ARMENIA_DRAM:

                        break;

                    case BELARUSSIAN_RUBLE:

                        break;

                    case BULGARIAN_LEV:

                        break;

                    case BRAZIL_REAL:

                        break;

                    case HUNGARIAN_FORINT:

                        break;

                    case HONG_KONG_DOLLAR:

                        break;

                    case DANISH_KRONE:

                        break;

                    case US_DOLLAR:

                        break;

                    case EURO:

                        break;

                    case INDIAN_RUPEE:

                        break;

                    case ICELAND_KRONA:

                        break;

                    case KAZAKHSTAN_TENGE:

                        break;

                    case CANADIAN_DOLLAR:

                        break;

                    case KYRGYZSTAN_SOM:

                        break;

                    case CHINA_YUAN:

                        break;

                    case MOLDOVA_LEI:

                        break;

                    case NORWEGIAN_KRONE:

                        break;

                    case POLISH_ZLOTY:

                        break;

                    case ROMANIAN_LEU:

                        break;

                    case SDR:

                        break;

                    case SINGAPORE_DOLLAR:

                        break;

                    case TAJIKISTAN_RUBLE:

                        break;

                    case TURKISH_LIRA:

                        break;

                    case NEW_TURKMENISTAN_MANAT:

                        break;

                    case UZBEKISTAN_SUM:

                        break;

                    case UKRAINIAN_HRYVNIA:

                        break;

                    case CZECH_KORUNA:

                        break;

                    case SWEDISH_KRONA:

                        break;

                    case SWISS_FRANC:

                        break;

                    case S_AFRICAN_RAND:

                        break;

                    case SOUTH_KOREAN_WON:

                        break;

                    case JAPANESE_YEN:

                        break;

                    case RUBLE:

                        break;
                }
                break;

            case SOUTH_KOREAN_WON:
                switch (currencyTo) {
                    case AUSTRALIAN_DOLLAR:

                        break;

                    case AZERBAIJAN_MANAT:

                        break;

                    case BRITISH_POUND_STERLING:

                        break;

                    case ARMENIA_DRAM:

                        break;

                    case BELARUSSIAN_RUBLE:

                        break;

                    case BULGARIAN_LEV:

                        break;

                    case BRAZIL_REAL:

                        break;

                    case HUNGARIAN_FORINT:

                        break;

                    case HONG_KONG_DOLLAR:

                        break;

                    case DANISH_KRONE:

                        break;

                    case US_DOLLAR:

                        break;

                    case EURO:

                        break;

                    case INDIAN_RUPEE:

                        break;

                    case ICELAND_KRONA:

                        break;

                    case KAZAKHSTAN_TENGE:

                        break;

                    case CANADIAN_DOLLAR:

                        break;

                    case KYRGYZSTAN_SOM:

                        break;

                    case CHINA_YUAN:

                        break;

                    case MOLDOVA_LEI:

                        break;

                    case NORWEGIAN_KRONE:

                        break;

                    case POLISH_ZLOTY:

                        break;

                    case ROMANIAN_LEU:

                        break;

                    case SDR:

                        break;

                    case SINGAPORE_DOLLAR:

                        break;

                    case TAJIKISTAN_RUBLE:

                        break;

                    case TURKISH_LIRA:

                        break;

                    case NEW_TURKMENISTAN_MANAT:

                        break;

                    case UZBEKISTAN_SUM:

                        break;

                    case UKRAINIAN_HRYVNIA:

                        break;

                    case CZECH_KORUNA:

                        break;

                    case SWEDISH_KRONA:

                        break;

                    case SWISS_FRANC:

                        break;

                    case S_AFRICAN_RAND:

                        break;

                    case SOUTH_KOREAN_WON:

                        break;

                    case JAPANESE_YEN:

                        break;

                    case RUBLE:

                        break;
                }
                break;

            case JAPANESE_YEN:
                switch (currencyTo) {
                    case AUSTRALIAN_DOLLAR:

                        break;

                    case AZERBAIJAN_MANAT:

                        break;

                    case BRITISH_POUND_STERLING:

                        break;

                    case ARMENIA_DRAM:

                        break;

                    case BELARUSSIAN_RUBLE:

                        break;

                    case BULGARIAN_LEV:

                        break;

                    case BRAZIL_REAL:

                        break;

                    case HUNGARIAN_FORINT:

                        break;

                    case HONG_KONG_DOLLAR:

                        break;

                    case DANISH_KRONE:

                        break;

                    case US_DOLLAR:

                        break;

                    case EURO:

                        break;

                    case INDIAN_RUPEE:

                        break;

                    case ICELAND_KRONA:

                        break;

                    case KAZAKHSTAN_TENGE:

                        break;

                    case CANADIAN_DOLLAR:

                        break;

                    case KYRGYZSTAN_SOM:

                        break;

                    case CHINA_YUAN:

                        break;

                    case MOLDOVA_LEI:

                        break;

                    case NORWEGIAN_KRONE:

                        break;

                    case POLISH_ZLOTY:

                        break;

                    case ROMANIAN_LEU:

                        break;

                    case SDR:

                        break;

                    case SINGAPORE_DOLLAR:

                        break;

                    case TAJIKISTAN_RUBLE:

                        break;

                    case TURKISH_LIRA:

                        break;

                    case NEW_TURKMENISTAN_MANAT:

                        break;

                    case UZBEKISTAN_SUM:

                        break;

                    case UKRAINIAN_HRYVNIA:

                        break;

                    case CZECH_KORUNA:

                        break;

                    case SWEDISH_KRONA:

                        break;

                    case SWISS_FRANC:

                        break;

                    case S_AFRICAN_RAND:

                        break;

                    case SOUTH_KOREAN_WON:

                        break;

                    case JAPANESE_YEN:

                        break;

                    case RUBLE:

                        break;
                }
                break;

            case RUBLE:
                switch (currencyTo) {
                    case AUSTRALIAN_DOLLAR:
                        resultString = getString(R.string.australian_dollar_icon).replace("{value}", converter.convertRubbleToAustralianDollar(value).toString());
                        break;

                    case AZERBAIJAN_MANAT:
                        resultString = getString(R.string.azerbaijan_manat_icon).replace("{value}", converter.convertRubbleToAzerbaijanManat(value).toString());
                        break;

                    case BRITISH_POUND_STERLING:
                        resultString = getString(R.string.british_pound_sterling_icon).replace("{value}", converter.convertRubbleToBritishPoundSterling(value).toString());
                        break;

                    case ARMENIA_DRAM:
                        resultString = getString(R.string.armenia_dram_icon).replace("{value}", converter.convertRubbleToArmeniaDram(value).toString());
                        break;

                    case BELARUSSIAN_RUBLE:

                        break;

                    case BULGARIAN_LEV:

                        break;

                    case BRAZIL_REAL:

                        break;

                    case HUNGARIAN_FORINT:

                        break;

                    case HONG_KONG_DOLLAR:

                        break;

                    case DANISH_KRONE:

                        break;

                    case US_DOLLAR:
                        resultString = getString(R.string.us_dollar_icon).replace("{value}", converter.convertRubbleToDollar(value).toString());
                        break;

                    case EURO:

                        break;

                    case INDIAN_RUPEE:

                        break;

                    case ICELAND_KRONA:

                        break;

                    case KAZAKHSTAN_TENGE:

                        break;

                    case CANADIAN_DOLLAR:

                        break;

                    case KYRGYZSTAN_SOM:

                        break;

                    case CHINA_YUAN:

                        break;

                    case MOLDOVA_LEI:

                        break;

                    case NORWEGIAN_KRONE:

                        break;

                    case POLISH_ZLOTY:

                        break;

                    case ROMANIAN_LEU:

                        break;

                    case SDR:

                        break;

                    case SINGAPORE_DOLLAR:

                        break;

                    case TAJIKISTAN_RUBLE:

                        break;

                    case TURKISH_LIRA:

                        break;

                    case NEW_TURKMENISTAN_MANAT:

                        break;

                    case UZBEKISTAN_SUM:

                        break;

                    case UKRAINIAN_HRYVNIA:

                        break;

                    case CZECH_KORUNA:

                        break;

                    case SWEDISH_KRONA:

                        break;

                    case SWISS_FRANC:

                        break;

                    case S_AFRICAN_RAND:

                        break;

                    case SOUTH_KOREAN_WON:

                        break;

                    case JAPANESE_YEN:

                        break;

                    case RUBLE:

                        break;
                }
                break;
        }
        mResultText.setText(resultString);
    }
}

