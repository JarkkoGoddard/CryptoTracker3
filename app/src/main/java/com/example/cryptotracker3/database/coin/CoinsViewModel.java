package com.example.cryptotracker3.database.coin;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CoinsViewModel extends AndroidViewModel {

    private CoinsRepository mRepository;

    private LiveData<List<Coins>> mAllCoins;


    public CoinsViewModel(Application application) {
        super(application);
        mRepository = new CoinsRepository(application);
        mAllCoins = mRepository.getAllCoins();
    }

    public LiveData<List<Coins>> getAllCoins() {
        return mAllCoins;
    }

    public void insertCoins (Coins coins) {
        mRepository.insertCoins(coins);
    }
}
