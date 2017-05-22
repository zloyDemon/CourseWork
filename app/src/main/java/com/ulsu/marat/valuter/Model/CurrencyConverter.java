package com.ulsu.marat.valuter.Model;

import java.math.BigDecimal;
import java.util.List;


public class CurrencyConverter {

    private List<CurrencyModel.Currency> currencyList;


    public void setCurrencyList(List<CurrencyModel.Currency> currencyList) {
        this.currencyList = currencyList;
    }

    public List<CurrencyModel.Currency> getCurrencyList() {
        return currencyList;
    }

    public BigDecimal convertRubbleToDollar(BigDecimal rubble){
        BigDecimal dollar = getValueByCode(CurrencyEnum.US_DOLLAR);
        return rubble.divide(dollar,4,BigDecimal.ROUND_HALF_DOWN);
    }

    public BigDecimal convertDollarToRubble(BigDecimal dollar){
        BigDecimal rubble = getValueByCode(CurrencyEnum.US_DOLLAR);
        return rubble.multiply(dollar).setScale(4,BigDecimal.ROUND_HALF_DOWN);
    }

    private BigDecimal getValueByCode(CurrencyEnum type){
        for (CurrencyModel.Currency currency : currencyList) {
            if(currency.getID().contains(type.getId()) || type.getId().contains(currency.getID())){
                return new BigDecimal(currency.getValue());
            }
        }
        return BigDecimal.ZERO;
    }
}
