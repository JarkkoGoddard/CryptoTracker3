package com.example.cryptotracker3.database;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class InvestmentsViewModel extends AndroidViewModel {

    private InvestmentsRepository mRepository;

    private LiveData<List<Investments>> mAllData;

    private LiveData<Double> mAllSums;

    public InvestmentsViewModel (Application application) {
        super(application);
        mRepository = new InvestmentsRepository(application);
        mAllData = mRepository.getAllData();
    }
    public LiveData<List<Investments>> getAllData(){return mAllData;}

    public void insert (Investments investments) {mRepository.insert(investments);}

    public LiveData<Double> getAllSums(){return mAllSums;}
}
