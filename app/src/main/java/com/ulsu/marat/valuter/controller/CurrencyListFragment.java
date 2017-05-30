package com.ulsu.marat.valuter.controller;

import android.app.Activity;
import android.app.DatePickerDialog;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;

import com.ulsu.marat.valuter.Model.CurrencyListSingleton;
import com.ulsu.marat.valuter.Model.CurrencyModel;
import com.ulsu.marat.valuter.R;
import com.ulsu.marat.valuter.controller.adapter.CurrencyAdapter;
import com.ulsu.marat.valuter.network.CBRrequest;
import com.ulsu.marat.valuter.utils.Consts;
import com.ulsu.marat.valuter.utils.DialogUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

@EFragment(R.layout.fragment_list_currency)
public class CurrencyListFragment extends Fragment {

    public interface OnCurrencyConverterFragment {
         void ListChange(List<CurrencyModel.Currency> list, boolean isGet);
    }

    @ViewById(R.id.date_button)
    Button mDateButton;

    @ViewById(R.id.currency_list)
    RecyclerView currencyList;

    @Click(R.id.date_button)
    public void ShowDateClick() {
        showDatePickerDialog();
    }

    @InstanceState
    Calendar calendar = Calendar.getInstance();

    CurrencyAdapter adapter;
    OnCurrencyConverterFragment listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnCurrencyConverterFragment) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @AfterViews
    public void bindViews() {
        mDateButton.setText(getString(R.string.date_button_title).replace("{date}",
                DateFormat(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))));
        Request(DateFormat(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)));
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
                listener.ListChange(response.body().getCurrency(),true);
                CurrencyListSingleton.get(getActivity()).setList(response.body().getCurrency());
                adapter = new CurrencyAdapter(getActivity(), response.body().getCurrency());
                currencyList.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<CurrencyModel> call, Throwable t) {
                Log.d(Consts.TAG, "FAIL\n " + t.toString());
                if (t.toString().contains("UnknownHostException")) {
                    DialogUtils.DialogMessage(getActivity(), R.string.error_title, R.string.internet_work_error);
                }
                if (t.toString().contains("ValueRequiredException")) {
                    DialogUtils.DialogMessage(getActivity(), R.string.error_title, R.string.no_currency_data_error);
                }
                listener.ListChange(null,false);
            }
        });
    }


    private void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                if (view.isShown()) {
                    Request(DateFormat(year, month, dayOfMonth));
                    calendar.set(year, month, dayOfMonth);
                    mDateButton.setText(getString(R.string.date_button_title).replace("{date}",
                            DateFormat(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))));
                }
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private String DateFormat(int year, int month, int day) {
        String dayString = String.valueOf(day);
        String monthString = String.valueOf(month + 1);

        if (monthString.length() == 1) {
            monthString = "0" + monthString;
        }
        if (dayString.length() == 1) {
            dayString = "0" + dayString;
        }

        return new StringBuilder().append(dayString).append(".").append(monthString).append(".").append(year).toString();
    }

}
