package com.ulsu.marat.valuter.Model;

import java.math.BigDecimal;
import java.util.List;


public class CurrencyConverter {

    private final int scaleRound = 2;
    private List<Currency> currencyList;



    public void setCurrencyList(List<Currency> currencyList) {
        this.currencyList = currencyList;
    }

    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    /**
     * Рубль в доллар
     * @param rubble Значение рубля, которое хотим перевести
     * @return Возвращает значение рубля в доллрах
     */
    public BigDecimal convertRubbleToDollar(BigDecimal rubble){
        BigDecimal dollar = getValueByCode(CurrencyEnum.US_DOLLAR);
        return rubble.divide(dollar,scaleRound,BigDecimal.ROUND_HALF_DOWN);
    }

    public BigDecimal convertDollarToRubble(BigDecimal dollar){
        BigDecimal rubble = getValueByCode(CurrencyEnum.US_DOLLAR);
        return dollar.multiply(rubble).setScale(scaleRound,BigDecimal.ROUND_HALF_DOWN);
    }

    public BigDecimal convertAustralianDollarToRubble(BigDecimal australianDollar){
        BigDecimal rubble = getValueByCode(CurrencyEnum.AUSTRALIAN_DOLLAR);
        return australianDollar.multiply(rubble).setScale(scaleRound,BigDecimal.ROUND_HALF_DOWN);
    }

    public BigDecimal convertRubbleToAustralianDollar(BigDecimal rubble){
        BigDecimal australianDollar = getValueByCode(CurrencyEnum.AUSTRALIAN_DOLLAR);
        int nominal = getNominalByCode(CurrencyEnum.AUSTRALIAN_DOLLAR);
        return rubble.divide(australianDollar,scaleRound,BigDecimal.ROUND_HALF_DOWN);
    }



    private BigDecimal getValueByCode(CurrencyEnum type){
        for (Currency currency : currencyList) {
            if(currency.getID().contains(type.getId()) || type.getId().contains(currency.getID())){
                return currency.getValue();
            }
        }
        return BigDecimal.ZERO;
    }

    private int getNominalByCode(CurrencyEnum type){
        for (Currency currency : currencyList) {
            if(currency.getID().contains(type.getId()) || type.getId().contains(currency.getID())){
                return currency.getNominal();
            }
        }
        return 1;
    }

}
