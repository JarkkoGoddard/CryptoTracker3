package com.example.cryptotracker3.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Investments.class, version = 1, exportSchema = false)
public abstract class InvestmentsRoomDatabase extends RoomDatabase{

    public abstract InvestmentsDao investmentsDao();
    private static InvestmentsRoomDatabase INSTANCE;

    static InvestmentsRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (InvestmentsRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), InvestmentsRoomDatabase.class, "investments_database").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}
