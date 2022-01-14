package com.example.cryptotracker3.database.coin;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CoinsRepository {
    private CoinsDao mCoinsDao;
    private LiveData<List<Coins>> mAllCoins;

    CoinsRepository(Application application){
        CoinsRoomDatabase db = CoinsRoomDatabase.getDatabase(application);
        mCoinsDao = db.coinsDao();
        mAllCoins = mCoinsDao.getAllCoins();
    }

    LiveData<List<Coins>> getAllCoins() {
        return mAllCoins;
    }

    public void insertCoins(Coins coins){
        new insertCoinsAsyncTask(mCoinsDao).execute(coins);
    }

    private class insertCoinsAsyncTask extends AsyncTask<Coins, Void, Void>{
        private CoinsDao coinsAsyncTaskDao;
        public insertCoinsAsyncTask(CoinsDao dao){
            coinsAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Coins... params) {
            coinsAsyncTaskDao.insertCoins(params[0]);
            return null;
        }
    }
}
