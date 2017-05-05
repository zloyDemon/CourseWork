package com.ulsu.marat.valuter.Model;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.res.ResourcesCompat;

import com.ulsu.marat.valuter.R;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "ValCurs")
public class CurrencyModel {

    enum CurrencyName{
        AUSTRALIAN_DOLLAR("R01010","Австралийский доллар"),
        AZERBAIJAN_MANAT("R01020A",""),
        BRITISH_POUND_STERLING("R01035","Фунт стерлингов Соединенного королевства"),
        ARMENIA_DRAM("R01060",""),
        BELARUSSIAN_RUBLE("R01090","Белорусских рублей"),
        BULGARIAN_LEV("R01100",""),
        BRAZIL_REAL("R01115",""),
        HUNGARIAN_FORINT("R01135",""),
        HONG_KONG_DOLLAR("R01200",""),
        DANISH_KRONE("R01215","Датских крон"),
        US_DOLLAR("R01235","Доллар США"),
        EURO("R01239","Евро"),
        INDIAN_RUPEE("R01270",""),
        ICELAND_KRONA("R01310","Исландских крон"),
        KAZAKHSTAN_TENGE("R01335","Казахстанских тенге"),
        CANADIAN_DOLLAR("R01350","Канадский доллар"),
        KYRGYZSTAN_SOM("R01370","Киргизских сомов"),
        CHINA_YUAN("R01375","Китайских юаней"),
        MOLDOVA_LEI("R01500","Молдавских леев"),
        NORWEGIAN_KRONE("R01535","Норвежских крон"),
        POLISH_ZLOTY("R01565","Польский злотый"),
        ROMANIAN_LEU("R01585F","Румынский лей"),
        SDR("R01589","СДР (специальные права заимствования)"),
        SINGAPORE_DOLLAR("R01625","Сингапурский доллар"),
        TAJIKISTAN_RUBLE("R01670","Таджикских сомони"),
        TURKISH_LIRA("R01700J","Турецкая лира"),
        NEW_TURKMENISTAN_MANAT("R01710A","Новый туркменский манат"),
        UZBEKISTAN_SUM("R01717","Узбекских сумов"),
        UKRAINIAN_HRYVNIA("R01720","Украинских гривен"),
        CZECH_KORUNA("R01760","Чешских крон"),
        SWEDISH_KRONA("R01770","Шведских крон"),
        SWISS_FRANC("R01775","Швейцарский франк"),
        S_AFRICAN_RAND("R01810","Южноафриканских рэндов"),
        SOUTH_KOREAN_WON("R01815","Вон Республики Корея"),
        JAPANESE_YEN("R01820","Японских иен"),
        NONE("","Нет данных");

        private String id_;
        private String name_;

        CurrencyName(String id, String name) {
            this.id_ = id;
            this.name_ = name;
        }

        String getNameById(String id){
            for (CurrencyName currencyName : values()) {
                if(currencyName.id_.equals(id)){
                    return currencyName.name_;
                }
            }
            return NONE.name_;
        }
    }

    @Attribute(name = "Date")
    String date;

    @Attribute(name = "name")
    String name;

    @ElementList(inline = true)
    List<Currency> currency;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrency(List<Currency> currency) {
        this.currency = currency;
    }

    public List<Currency> getCurrency() {
        return currency;
    }

    @Root(name = "Valute")
    public static class Currency {

        @Attribute(name = "ID")
        String ID;

        @Element(name = "NumCode")
        String numCode;

        @Element(name = "CharCode")
        String charCode;

        @Element(name = "Nominal")
        String nominal;

        @Element(name = "Name")
        String name;

        @Element(name = "Value")
        String value;

        public String getNumCode() {
            return numCode;
        }

        public void setNumCode(String numCode) {
            this.numCode = numCode;
        }

        public String getName() {
            CurrencyName currencyName = CurrencyName.NONE;
            return currencyName.getNameById(getID());
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCharCode() {
            return charCode;
        }

        public void setCharCode(String charCode) {
            this.charCode = charCode;
        }

        public String getNominal() {
            return nominal;
        }

        public void setNominal(String nominal) {
            this.nominal = nominal;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }
    }
}
