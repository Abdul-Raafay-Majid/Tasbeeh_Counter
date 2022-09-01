package com.example.tasbeehcounter;

import android.app.Application;
import android.database.Cursor;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DikhrRepository {

    private DikhrDao dikhrDao;

    private LiveData<List<Dikhr>> mAllDikhr;

    private PreLoadedDikhrDao pldDao;

    private LiveData<List<PreLoadedDikhr>> AllPld;



    public DikhrRepository(Application application){
        DikhrDatabase database=DikhrDatabase.getInstance(application);
        dikhrDao=database.dikhrDao();
        pldDao=database.pldDao();
        mAllDikhr=dikhrDao.getAllDikhr();
        AllPld=pldDao.getAllPld();
    }

    public void insertPld(PreLoadedDikhr pld){
        new InsertPldExecutor(pldDao,pld).getService5();
    }

    public void deletePld(PreLoadedDikhr pld){
        new DeletePldExecutor(pldDao,pld).getService6();
    }

    public LiveData<List<PreLoadedDikhr>> getAllPld(){
        return AllPld;
    };

    public void insert(Dikhr dikhr){
        new InsertDhikrExecutor(dikhrDao,dikhr).getService2();
    }

    public void delete(Dikhr dikhr){
        new DeleteDhikrExecutor(dikhrDao,dikhr).getService3();
    }

    public void update(Dikhr dikhr){
        new UpdateDhikrExecutor(dikhrDao,dikhr).getService4();
    }


    public LiveData<List<Dikhr>> getAllDikhr(){
        return mAllDikhr;
    }

    private static class InsertDhikrExecutor{
        private DikhrDao dikhrDao;
        private Dikhr dikhr;

        private InsertDhikrExecutor(DikhrDao dikhrDao, Dikhr dikhr){
            this.dikhrDao=dikhrDao;
            this.dikhr=dikhr;
        }


        ExecutorService service2= Executors.newSingleThreadExecutor();

        private void getService2(){
            service2.execute(new Runnable() {
                @Override
                public void run() {
                    dikhrDao.insert(dikhr);
                }
            });
        }
    }

    private static class DeleteDhikrExecutor{
        private DikhrDao dikhrDao;
        private Dikhr dikhr;

        private DeleteDhikrExecutor(DikhrDao dikhrDao, Dikhr dikhr){
            this.dikhrDao=dikhrDao;
            this.dikhr=dikhr;
        }


        ExecutorService service3= Executors.newSingleThreadExecutor();

        private void getService3(){
            service3.execute(new Runnable() {
                @Override
                public void run() {
                    dikhrDao.delete(dikhr);
                }
            });
        }
    }

    private static class UpdateDhikrExecutor{
        private DikhrDao dikhrDao;
        private Dikhr dikhr;

        private UpdateDhikrExecutor(DikhrDao dikhrDao, Dikhr dikhr){
            this.dikhrDao=dikhrDao;
            this.dikhr=dikhr;
        }


        ExecutorService service4= Executors.newSingleThreadExecutor();

        private void getService4(){
            service4.execute(new Runnable() {
                @Override
                public void run() {
                    dikhrDao.update(dikhr);
                }
            });
        }
    }

    private static class InsertPldExecutor{
        private PreLoadedDikhrDao pldDao;
        private PreLoadedDikhr pld;

        private InsertPldExecutor(PreLoadedDikhrDao pldDao,PreLoadedDikhr pld){
            this.pldDao=pldDao;
            this.pld=pld;
        }

        private ExecutorService service5= Executors.newSingleThreadExecutor();

        private void getService5(){
            service5.execute(new Runnable() {
                @Override
                public void run() {
                    pldDao.insert(pld);
                }
            });
        }
    }

    private static class DeletePldExecutor{
        private PreLoadedDikhrDao pldDao;
        private PreLoadedDikhr pld;

        private DeletePldExecutor(PreLoadedDikhrDao pldDao,PreLoadedDikhr pld){
            this.pldDao=pldDao;
            this.pld=pld;
        }

        private ExecutorService service6= Executors.newSingleThreadExecutor();

        private void getService6(){
            service6.execute(new Runnable() {
                @Override
                public void run() {
                    pldDao.deletePld(pld);
                }
            });
        }
    }

    }
