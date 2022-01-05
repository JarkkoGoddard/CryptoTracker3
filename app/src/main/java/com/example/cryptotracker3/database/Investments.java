package com.example.cryptotracker3.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity (tableName = "investments_table")
public class Investments {

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo (name = "uid")
    @NonNull
    private int uId;

    @ColumnInfo (name = "coin")
    private String coin;

    @ColumnInfo (name = "investment")
    private double investment;

    @ColumnInfo (name = "low_sell_price")
    private double lowSellPrice;

    @ColumnInfo (name = "high_sell_price")
    private double highSellPrice;

    @ColumnInfo (name = "sell_percentage")
    private int sellPercentage;

    @ColumnInfo (name = "days_to_hold")
    private int daysToHold;

    @ColumnInfo (name = "initial_purchase_price")
    private double initialPurchasePrice;

    @ColumnInfo (name = "notes")
    private String notes;

    public int getUId() {
        return uId;
    }

    public void setUId(int uId) {
        this.uId = uId;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public double getInvestment() {
        return investment;
    }

    public void setInvestment(double investment) {
        this.investment = investment;
    }

    public double getLowSellPrice() {
        return lowSellPrice;
    }

    public void setLowSellPrice(double lowSellPrice) {
        this.lowSellPrice = lowSellPrice;
    }

    public double getHighSellPrice() {
        return highSellPrice;
    }

    public void setHighSellPrice(double highSellPrice) {
        this.highSellPrice = highSellPrice;
    }

    public int getSellPercentage() {
        return sellPercentage;
    }

    public void setSellPercentage(int sellPercentage) {
        this.sellPercentage = sellPercentage;
    }

    public int getDaysToHold() {
        return daysToHold;
    }

    public void setDaysToHold(int daysToHold) {
        this.daysToHold = daysToHold;
    }

    public double getInitialPurchasePrice() {
        return initialPurchasePrice;
    }

    public void setInitialPurchasePrice(double initialPurchasePrice) {
        this.initialPurchasePrice = initialPurchasePrice;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Investments(int uId, String coin, double investment, double lowSellPrice, double highSellPrice, int sellPercentage, int daysToHold, double initialPurchasePrice, String notes) {
        this.uId = uId;
        this.coin = coin;
        this.investment = investment;
        this.lowSellPrice = lowSellPrice;
        this.highSellPrice = highSellPrice;
        this.sellPercentage = sellPercentage;
        this.daysToHold = daysToHold;
        this.initialPurchasePrice = initialPurchasePrice;
        this.notes = notes;
    }
}
