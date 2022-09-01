package com.example.tasbeehcounter;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class DikhrViewModel extends AndroidViewModel {

    private DikhrRepository repository;
    private LiveData<List<Dikhr>> mAllDikhrs;
    private LiveData<List<PreLoadedDikhr>> Allpld;



    public DikhrViewModel(@NonNull Application application) {
        super(application);
        repository=new DikhrRepository(application);
        mAllDikhrs=repository.getAllDikhr();
        Allpld=repository.getAllPld();
    }

    public void insert(Dikhr dikhr){
        repository.insert(dikhr);
    }

    public void update(Dikhr dikhr){
        repository.update(dikhr);
    }

    public void delete(Dikhr dikhr){
        repository.delete(dikhr);
    }

    public LiveData<List<Dikhr>> getAllDikhrs(){
        return mAllDikhrs;
    }

    public void insertPld(PreLoadedDikhr pld){
        repository.insertPld(pld);
    }

    public void deletePld(PreLoadedDikhr pld){
        repository.deletePld(pld);
    }

    public LiveData<List<PreLoadedDikhr>> getAllPld(){
        return Allpld;
    };

}
