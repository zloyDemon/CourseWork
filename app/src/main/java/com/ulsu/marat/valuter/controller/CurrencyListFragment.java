package com.ulsu.marat.valuter.controller;

import android.app.DatePickerDialog;


import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;

import com.ulsu.marat.valuter.Model.CurrencyModel;
import com.ulsu.marat.valuter.R;
import com.ulsu.marat.valuter.controller.adapter.CurrencyAdapter;
import com.ulsu.marat.valuter.network.CBRrequest;
import com.ulsu.marat.valuter.utils.Consts;
import com.ulsu.marat.valuter.utils.DialogUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

@EFragment(R.layout.fragment_list_currency)
public class CurrencyListFragment extends Fragment {


    @ViewById(R.id.date_button)
    Button mDateButton;

    @ViewById(R.id.currency_list)
    RecyclerView currencyList;

    @Click(R.id.date_button)
    public void ShowDateClick() {
        showDatePickerDialog();
    }

    Calendar calendar = Calendar.getInstance();
    CurrencyAdapter adapter;

    @AfterViews
    public void bindViews() {
        mDateButton.setText(getString(R.string.date_button_title).replace("{date}",
                DateFormat(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH))));
    }


    private void Request(String date) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Consts.CBR_BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        CBRrequest cbRrequest = retrofit.create(CBRrequest.class);

        Call<CurrencyModel> call = cbRrequest.getCurrency(date);
        call.enqueue(new Callback<CurrencyModel>() {
            @Override
            public void onResponse(Call<CurrencyModel> call, Response<CurrencyModel> response) {
                Log.d(Consts.TAG,"Дата " + response.body().getDate());
                for (CurrencyModel.Currency currency : response.body().getCurrency()) {
                    Log.d(Consts.TAG, "***" + "\n" + currency.getID() + "\n" + currency.getNumCode() +
                            "\n" + currency.getCharCode() + "\n" + currency.getNominal() +
                            "\n" + currency.getName() + "\n" + currency.getValue());
                    adapter = new CurrencyAdapter(getActivity(),response.body().getCurrency());
                    currencyList.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<CurrencyModel> call, Throwable t) {
                Log.d(Consts.TAG, "FAIL\n " + t.toString());
                if(t.toString().contains("UnknownHostException")){
                    DialogUtils.DialogMessage(getActivity(),R.string.error_title,R.string.internet_work_error);
                }
                if(t.toString().contains("ValueRequiredException")){
                    DialogUtils.DialogMessage(getActivity(),R.string.error_title,R.string.no_currency_data_error);
                }
            }
        });
    }
    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            if(view.isShown()) {
                Request(DateFormat(year,month,dayOfMonth));
                calendar.set(year,month,dayOfMonth);
                mDateButton.setText(getString(R.string.date_button_title).replace("{date}",
                        DateFormat(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH))));
            }
        }
    };

    private void showDatePickerDialog() {

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private String DateFormat(int year, int month, int day) {
        String dayString = String.valueOf(day);
        String monthString = String.valueOf(month+1);

        if (monthString.length() == 1) {
            monthString = "0" + monthString;
        }
        if (dayString.length() == 1) {
            dayString = "0" + dayString;
        }

        return new StringBuilder().append(dayString).append(".").append(monthString).append(".").append(year).toString();
    }

}
