package com.ulsu.marat.valuter.controller;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.ulsu.marat.valuter.R;
import com.ulsu.marat.valuter.utils.Consts;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_converter_currency)
public class CurrencyConverterFragment extends Fragment {

    @ViewById(R.id.from_spinner)
    MaterialBetterSpinner mSpinnerFrom;

    @ViewById(R.id.to_spinner)
    MaterialBetterSpinner mSpinnerTo;

    String[] SPINNERLIST = {"Android Material Design", "Material Design Spinner", "Spinner Using Material Library", "Material Spinner Example"};

    @AfterViews
    void bindViews(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line,SPINNERLIST);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerFrom.setAdapter(adapter);
        mSpinnerTo.setAdapter(adapter);
        mSpinnerFrom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("spinner",""+position);
            }
        });
    }
}
