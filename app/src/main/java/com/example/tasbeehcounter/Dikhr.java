package com.example.tasbeehcounter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.lang.annotation.Retention;
import java.util.Objects;

@Entity(tableName = "dikhr_table")
public class Dikhr {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String date;

    private String word;

    private int count;

    public Dikhr(String date, String word,int count){
        this.date=date;
        this.word=word;
        this.count=count;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getWord() {
        return word;
    }


    public int getCount() {
        return count;
    }



}
