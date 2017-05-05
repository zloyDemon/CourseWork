package com.ulsu.marat.valuter.Controller;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.ulsu.marat.valuter.Model.CurrencyModel;
import com.ulsu.marat.valuter.R;
import com.ulsu.marat.valuter.network.CBRrequest;
import com.ulsu.marat.valuter.utils.Consts;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

@EFragment(R.layout.fragment_list_currency)
public class CurrencyListFragment extends Fragment {


    @ViewById(R.id.get_currency_button_id)
    Button getCurrencyButton;

    @Click(R.id.get_currency_button_id)
    public void GetCurrencyClick(){
        Request();
    }

    @AfterViews
    public void bindViews(){

    }

    public Context getMyContext(){
        return getActivity();
    }

    private void Request(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Consts.CBR_BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        CBRrequest cbRrequest = retrofit.create(CBRrequest.class);

        Call<CurrencyModel> call = cbRrequest.getCurrency(Consts.BASE_DATE);
        call.enqueue(new Callback<CurrencyModel>() {
            @Override
            public void onResponse(Call<CurrencyModel> call, Response<CurrencyModel> response) {
                for (CurrencyModel.Currency currency : response.body().getCurrency()) {
                    Log.d(Consts.TAG, "***" + "\n" + currency.getID() + "\n" + currency.getNumCode() +
                            "\n" + currency.getCharCode() + "\n" + currency.getNominal() +
                            "\n" + currency.getName() + "\n" + currency.getValue());
                }
            }

            @Override
            public void onFailure(Call<CurrencyModel> call, Throwable t) {
                Log.d(Consts.TAG,"FAIL\n " + t);
            }
        });
    }
}
