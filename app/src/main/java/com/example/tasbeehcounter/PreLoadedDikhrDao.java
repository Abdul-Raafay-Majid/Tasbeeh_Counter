package com.example.tasbeehcounter;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface PreLoadedDikhrDao {

    @Insert
    void insert(PreLoadedDikhr pld);

    @Delete
    void deletePld(PreLoadedDikhr pld);

    @Query("SELECT * FROM preloaded_table")
    LiveData<List<PreLoadedDikhr>> getAllPld();
}
