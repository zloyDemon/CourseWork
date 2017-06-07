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

    @Attribute(name = "Date")
    String date;

    @Attribute(name = "name")
    String name;

    @ElementList(inline = true)
    List<Currency> currency;

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


        public String getName() {
            return name;
        }

        public String getNumCode() {
            return numCode;
        }

        public String getCharCode() {
            return charCode;
        }

        public String getNominal() {
            return nominal;
        }

        public String getValue() {
            String resValue = value;
            resValue = resValue.replace(",",".").replace("�","").replace(" ","");
            return resValue;
        }

        public String getID() {
            return ID;
        }

        public void setNumCode(String numCode) {
            this.numCode = numCode;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setCharCode(String charCode) {
            this.charCode = charCode;
        }

        public void setNominal(String nominal) {
            this.nominal = nominal;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public void setID(String ID) {
            this.ID = ID;
        }
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public List<Currency> getCurrency() {
        return currency;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrency(List<Currency> currency) {
        this.currency = currency;
    }

}
