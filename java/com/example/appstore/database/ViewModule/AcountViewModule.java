package com.example.appstore.database.ViewModule;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Delete;


import com.example.appstore.database.CallBack;
import com.example.appstore.database.Repository.AcountRepository;
import com.example.appstore.database.entity.Acount;

import java.util.List;

public class AcountViewModule extends AndroidViewModel {


    private AcountRepository acountRepository;
    private  CallBack callBackListener;//回调接口取数据

    public AcountViewModule(@NonNull Application application) {
        super(application);
        acountRepository=new AcountRepository(application);
    }

    public LiveData<List<Acount>> getLiveData(){
        return acountRepository.getAllLiveData();
    }

    public void setCallBackListener(CallBack callBackListener) {
        this.callBackListener = callBackListener;
    }

    public void insert(Acount...acounts){
        acountRepository.insert(acounts);
    }

    public void deleteAllAcount(){
        acountRepository.deleteAllAcount();
    }

    public List<Acount> queryByNameAndPassword(String acountName,String passord){
       List<Acount>acounts= acountRepository.queryByName(acountName,passord);
       acountRepository.setCallBackListener(new CallBack() {
           @Override
           public void onDataSuccessfully(Object object) {
               if (object!=null){
                   callBackListener.onDataSuccessfully(object);
               }else {
                   callBackListener.onDataFailed();
               }
           }

           @Override
           public void onDataFailed() {
              //
           }
       });
      return acounts;
    }






}
