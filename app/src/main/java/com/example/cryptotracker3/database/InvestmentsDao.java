package com.example.cryptotracker3.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface InvestmentsDao {

    @Insert
    void insert(Investments investments);

    @Query("DELETE FROM investments_table")
    void deleteAll();

    @Query("SELECT * from investments_table ORDER BY uid ASC")
    LiveData<List<Investments>> getAllData();
}
