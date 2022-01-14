package com.example.cryptotracker3.database.coin;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "coins_table")
public class Coins {

    @PrimaryKey
    @NonNull
    @ColumnInfo (name = "coins")
    private String coins;

    @ColumnInfo (name = "currency")
    private String currency;

    @ColumnInfo (name = "price")
    private double price;

    @NonNull
    public String getCoins() {
        return coins;
    }

    public void setCoins(@NonNull String coins) {
        this.coins = coins;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Coins(@NonNull String coins, String currency, double price) {
        this.coins = coins;
        this.currency = currency;
        this.price = price;
    }
}
