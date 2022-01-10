package com.example.cryptotracker3.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Investments.class, version = 1, exportSchema = false)
public abstract class InvestmentsRoomDatabase extends RoomDatabase{

    public abstract InvestmentsDao investmentsDao();
    private static InvestmentsRoomDatabase INSTANCE;

    static InvestmentsRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (InvestmentsRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), InvestmentsRoomDatabase.class, "investments_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    //Old code used to test the database

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private InvestmentsDao investmentsDao;
        private PopulateDbAsyncTask(InvestmentsRoomDatabase db){
            investmentsDao = db.investmentsDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            investmentsDao.insert(new Investments("Bitcoin", 500.00, 31000.00, 35000.00, 50, 30, 31500.00, "test"));
            return null;
        }
    }
}
