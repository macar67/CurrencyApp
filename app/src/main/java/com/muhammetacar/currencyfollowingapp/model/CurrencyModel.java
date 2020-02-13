package com.muhammetacar.currencyfollowingapp.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;


public class CurrencyModel {

    @SerializedName("rates")
    public String rates;

    @SerializedName("succes")
    public String success;

    @SerializedName("timestamp")
    public String timestamp;

    @SerializedName("base")
    public String base;

    @SerializedName("date")
    public String date;


}
