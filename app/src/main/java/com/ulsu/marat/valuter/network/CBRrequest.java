package com.ulsu.marat.valuter.network;

import com.ulsu.marat.valuter.Model.CurrencyModel;
import com.ulsu.marat.valuter.utils.Consts;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CBRrequest {
    @GET(Consts.CBR_PARAMETER_REQUEST_RUS)
    Call<CurrencyModel> getCurrency(@Query(Consts.CBR_DATE_REQ_PARAMETER) String date );
}
