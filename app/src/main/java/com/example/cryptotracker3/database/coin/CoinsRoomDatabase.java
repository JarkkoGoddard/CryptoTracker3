package com.example.cryptotracker3.database.coin;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Coins.class, version = 1, exportSchema = false)
public abstract class CoinsRoomDatabase extends RoomDatabase {

    public abstract CoinsDao coinsDao();
    private static CoinsRoomDatabase INSTANCE;

    static CoinsRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (CoinsRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), CoinsRoomDatabase.class, "coins_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private CoinsDao coinsDao;
        private PopulateDbAsyncTask(CoinsRoomDatabase db){
            coinsDao = db.coinsDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            coinsDao.insertCoins(new Coins("Pound Sterling", "gbp", 1.0));
            return null;
        }
    }
}
