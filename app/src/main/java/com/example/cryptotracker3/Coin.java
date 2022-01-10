package com.example.cryptotracker3;

public class Coin {
    String mCoin;
    String mCurrency;
    Double mValue;

    public Coin(String Name, String Currency, Double Value){
        mCoin = Name;
        mCurrency = Currency;
        mValue = Value;
    }

    public String getCoinName(){
        return mCoin;
    }

    public Double getValue(){
        return mValue;
    }
}
