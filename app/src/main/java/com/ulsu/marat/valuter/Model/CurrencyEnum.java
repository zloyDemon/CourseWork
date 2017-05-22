package com.ulsu.marat.valuter.Model;

public enum CurrencyEnum {
    AUSTRALIAN_DOLLAR("R01010","Австралийский доллар"),
    AZERBAIJAN_MANAT("R01020A","Азербайджанский манат"),
    BRITISH_POUND_STERLING("R01035","Фунт стерлингов Соединенного королевства"),
    ARMENIA_DRAM("R01060","Армянских драмов"),
    BELARUSSIAN_RUBLE("R01090","Белорусских рублей"),
    BULGARIAN_LEV("R01100","Болгарский лев"),
    BRAZIL_REAL("R01115","Бразильский реал"),
    HUNGARIAN_FORINT("R01135","Венгерских форинтов"),
    HONG_KONG_DOLLAR("R01200","Гонконгских долларов"),
    DANISH_KRONE("R01215","Датских крон"),
    US_DOLLAR("R01235","Доллар США"),
    EURO("R01239","Евро"),
    INDIAN_RUPEE("R01270","Индийских рупий"),
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
    RUBLE("R00000","Российский рубль"),
    NONE("000000","Нет данных");

    private String id_;
    private String name_;

    CurrencyEnum(String id, String name) {
        this.id_ = id;
        this.name_ = name;
    }

    public String getId(){
        return id_;
    }

    public String getNameById(String id){
        for (CurrencyEnum currencyName : values()) {
            if(currencyName.id_.contains(id) || id.contains(currencyName.id_)){
                return currencyName.name_;
            }
        }
        return NONE.name_;
    }

    public CurrencyEnum getCurrencyById(String id){
        for (CurrencyEnum currencyName : values()) {
            if(currencyName.id_.contains(id) || id.contains(currencyName.id_)){
                return currencyName;
            }
        }
        return NONE;
    }
}
