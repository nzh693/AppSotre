package com.example.appstore.database.ViewModule;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.appstore.database.CallBack;
import com.example.appstore.database.Repository.UpdataAppRepository;
import com.example.appstore.database.entity.UpdataApp;

import java.util.List;

public class UpdataAppViewModule extends AndroidViewModel {


    private UpdataAppRepository updataAppRepository;
    CallBack dataListener;
    CallBack isDataListener;


    public UpdataAppViewModule(@NonNull Application application) {
        super(application);
        updataAppRepository =new UpdataAppRepository(application);
    }


    public void setIsDataListener(CallBack isDataListener) {
        this.isDataListener = isDataListener;
    }

    public void setDataListener(CallBack dataListener) {
        this.dataListener = dataListener;
    }

    public LiveData<List<UpdataApp>> getLiveData(){
        return updataAppRepository.getAllLiveData();
    }

    public void insert(UpdataApp... updataApps){
        updataAppRepository.insert(updataApps);
    }

    public void update(UpdataApp... updataApps){
        updataAppRepository.update(updataApps);
    }

    public void deleteAll(){
        updataAppRepository.deleteAll();
    }

    public void deleteByApp(UpdataApp... updataApps){
        updataAppRepository.deleteByApp(updataApps);
    }

    public void queryIsUpdate(){
         updataAppRepository.queryIsUpdateApp();
         updataAppRepository.setIsDataListener(new CallBack() {
             @Override
             public void onDataSuccessfully(Object object) {
                 isDataListener.onDataSuccessfully(object);
             }

             @Override
             public void onDataFailed() {
                 isDataListener.onDataFailed();
             }
         });

    }

    public void queryNotUpdate(){
        updataAppRepository.queryNotUpdateApp();
        updataAppRepository.setDatalListener(new CallBack() {
            @Override
            public void onDataSuccessfully(Object object) {
                dataListener.onDataSuccessfully(object);
            }

            @Override
            public void onDataFailed() {
                dataListener.onDataFailed();
            }
        });
    }





}
