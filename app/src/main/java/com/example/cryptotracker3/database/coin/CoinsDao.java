package com.example.cryptotracker3.database.coin;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CoinsDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertCoins(Coins coins);

    @Query("DELETE FROM coins_table")
    void deleteAllCoins();

    @Query("SELECT * from coins_table ORDER BY coins ASC")
    LiveData<List<Coins>> getAllCoins();

}
