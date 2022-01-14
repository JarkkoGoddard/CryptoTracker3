package com.example.cryptotracker3.database.investment;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class InvestmentsViewModel extends AndroidViewModel {

    private InvestmentsRepository mRepository;

    private LiveData<List<Investments>> mAllData;


    public InvestmentsViewModel (Application application) {
        super(application);
        mRepository = new InvestmentsRepository(application);
        mAllData = mRepository.getAllData();
    }
    public LiveData<List<Investments>> getAllData(){return mAllData;}

    public void insert (Investments investments) {mRepository.insert(investments);}
}
