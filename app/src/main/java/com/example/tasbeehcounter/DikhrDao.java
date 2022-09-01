package com.example.tasbeehcounter;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DikhrDao {

    @Insert
    void insert(Dikhr dikhr);

    @Delete
    void delete(Dikhr dikhr);

    @Update
    void update(Dikhr dikhr);


    @Query("SELECT * FROM dikhr_table")
    LiveData<List<Dikhr>> getAllDikhr();



}
