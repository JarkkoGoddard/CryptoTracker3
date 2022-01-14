package com.example.cryptotracker3.database.investment;

import android.app.Application;

import androidx.lifecycle.LiveData;

import android.os.AsyncTask;

import java.util.List;

public class InvestmentsRepository {
    private InvestmentsDao mInvestmentsDao;
    private LiveData<List<Investments>> mAllData;

    InvestmentsRepository(Application application){
        InvestmentsRoomDatabase db = InvestmentsRoomDatabase.getDatabase(application);
        mInvestmentsDao = db.investmentsDao();
        mAllData = mInvestmentsDao.getAllData();
    }

    LiveData<List<Investments>> getAllData(){
        return mAllData;
    }

    public void insert (Investments investments){
        new insertAsyncTask(mInvestmentsDao).execute(investments);
    }

    private class insertAsyncTask extends AsyncTask<Investments, Void, Void>{
        private InvestmentsDao mAsyncTaskDao;
        public insertAsyncTask(InvestmentsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Investments... params){
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
