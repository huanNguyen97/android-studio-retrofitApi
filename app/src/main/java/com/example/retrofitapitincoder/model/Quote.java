package com.example.retrofitapitincoder.model;

import com.google.gson.annotations.SerializedName;

public class Quote {

    @SerializedName("USDVND")
    private float usdVnd;

    public float getusdVnd() {
        return usdVnd;
    }

    public void setusdVnd(float usdVnd) {
        this.usdVnd = usdVnd;
    }
}
