package com.example.tasbeehcounter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Dikhr.class,PreLoadedDikhr.class},version = 1)
public abstract class DikhrDatabase extends RoomDatabase {

    private static DikhrDatabase INSTANCE;

    public abstract DikhrDao dikhrDao();

    public abstract PreLoadedDikhrDao pldDao();

    public static synchronized DikhrDatabase getInstance(Context context){
        if(INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),DikhrDatabase.class,"dikhr_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }

        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallback= new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbExecutor(INSTANCE).getService1();
        }
    };

private static class PopulateDbExecutor{
    private PreLoadedDikhrDao PldDao;
    private DikhrDao dikhrDao;
    private PopulateDbExecutor(DikhrDatabase db){
        dikhrDao=db.dikhrDao();
        PldDao=db.pldDao();
    }

    ExecutorService service1= Executors.newSingleThreadExecutor();

private void getService1(){
    service1.execute(new Runnable() {
        @Override
        public void run() {
            dikhrDao.insert(new Dikhr("June,10,2022","Alhamdulillah",33));
            dikhrDao.insert(new Dikhr("May,11,2022","Allahuakbar",23));
            dikhrDao.insert(new Dikhr("January,23,2022","Alhamdulillah",44));
            dikhrDao.insert(new Dikhr("December,11,2022","Subhanallah",56));
            PldDao.insert(new PreLoadedDikhr("سبحان الله",33));
            PldDao.insert(new PreLoadedDikhr(" ٱلْحَمْدُ لِلَّٰهِ",23));
        }
    });
}

}

}
