package com.example.tasbeehcounter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "preloaded_table")
public class PreLoadedDikhr {

    @PrimaryKey(autoGenerate = true)
    private int Pid;

    private String Pdikhr;

    private int Pcount;

    public PreLoadedDikhr(String Pdikhr,int Pcount){
        this.Pdikhr=Pdikhr;
        this.Pcount=Pcount;
    }

    public int getPid() {
        return Pid;
    }

    public void setPid(int pid) {
        Pid = pid;
    }

    public String getPdikhr() {
        return Pdikhr;
    }


    public int getPcount() {
        return Pcount;
    }

    @Override
    public String toString() {
        return "PreLoadedDikhr{" +
                "Pid='" + Pid + '\'' +
                ", Pdikhr='" + Pdikhr + '\'' +
                ", Pcount=" + Pcount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PreLoadedDikhr that = (PreLoadedDikhr) o;
        return Pcount == that.Pcount && Objects.equals(Pid, that.Pid) && Objects.equals(Pdikhr, that.Pdikhr);
    }


    @Ignore
   public static DiffUtil.ItemCallback<PreLoadedDikhr> itemCallback=new DiffUtil.ItemCallback<PreLoadedDikhr>() {
       @Override
       public boolean areItemsTheSame(@NonNull PreLoadedDikhr oldItem, @NonNull PreLoadedDikhr newItem) {
           return oldItem.getPid()== newItem.getPid();
       }

       @Override
       public boolean areContentsTheSame(@NonNull PreLoadedDikhr oldItem, @NonNull PreLoadedDikhr newItem) {
           return oldItem.equals(newItem);
       }
   };

}
