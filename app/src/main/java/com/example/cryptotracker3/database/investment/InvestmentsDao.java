package com.example.cryptotracker3.database.investment;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface InvestmentsDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insert(Investments investments);

    @Query("DELETE FROM investments_table")
    void deleteAll();

    @Query("SELECT * from investments_table ORDER BY coin ASC")
    LiveData<List<Investments>> getAllData();

    @Query("SELECT SUM(investment) as total FROM investments_table")
    LiveData<Double> getTotalInvestments();

}
