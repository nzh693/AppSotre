package com.example.appstore.database.Repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.PrimaryKey;

import com.example.appstore.R;
import com.example.appstore.database.AppDataBase;
import com.example.appstore.database.CallBack;
import com.example.appstore.database.dao.AcountDao;
import com.example.appstore.database.entity.Acount;
import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class AcountRepository  {



    private LiveData<List<Acount>> allLiveData;

    private  List<Acount> acounts;

    private AcountDao acountDao;

    private CallBack callBackListener;

    public AcountRepository(Context context){
        AppDataBase dataBase= AppDataBase.getInstance(context);
        acountDao=dataBase.getAcountDao();

    }

    public LiveData<List<Acount>> getAllLiveData() {
        allLiveData=acountDao.QueryAllCount();
        return allLiveData;
    }

    public List<Acount> getAcounts() {
        return acounts;
    }

    public void setCallBackListener(CallBack callBackListener) {
        this.callBackListener = callBackListener;
    }

    public LiveData<List<Acount>> QueryAllCount() {
        new findAllAsynctask(acountDao).execute();
        return null;
    }

    public void insert(Acount...acounts){
          new InsertAsynctask(acountDao).execute(acounts);
    }

    public void deleteAllAcount(){
        new DeteleAllAcount(acountDao).execute();
    }

    public List<Acount> queryByName(String... strings){
        acounts=new ArrayList<>();
        QueryAcountByName task=new QueryAcountByName(acountDao);
        task.execute(strings);

        task.setListener(new CallBack() {
            @Override
            public void onDataSuccessfully(Object object) {

                if (object!=null){
                    callBackListener.onDataSuccessfully(object);
                }
                else {
                    callBackListener.onDataFailed();
                }
            }
            @Override
            public void onDataFailed() {
                callBackListener.onDataFailed();
            }
        });


        return acounts;
    }





      //副线程插入
    static class InsertAsynctask extends AsyncTask<Acount,Void,Void> {
        private AcountDao acountDao;

        public InsertAsynctask(AcountDao acountDao) {
            this.acountDao= acountDao;
        }
        @Override
        protected Void doInBackground(Acount... acounts) {
            acountDao.insert(acounts);
            return null;
        }
    }

    //副线程查询所有账户
    static class findAllAsynctask extends AsyncTask<Acount,Void,Void> {
        private AcountDao acountDao;

        public findAllAsynctask(AcountDao acountDao) {
            this.acountDao=  acountDao;
        }
        @Override
        protected Void doInBackground(Acount... acounts) {
            acountDao.QueryAllCount();
            return null;
        }
    }

    //删除所有用户
    static class  DeteleAllAcount extends AsyncTask<Void,Void,Void>{

        AcountDao acountDao;

        public  DeteleAllAcount(AcountDao acountDao){
            this.acountDao=acountDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            acountDao.deleteAllAcount();
            return null;
        }
    }

    //根据账户名查询
    static  class QueryAcountByName extends  AsyncTask<String,Void,List<Acount>>{

        AcountDao acountDao;
        CallBack listener;

        public  QueryAcountByName(AcountDao acountDao){
            this.acountDao=acountDao;
        }

        public void setListener(CallBack listener) {
            this.listener = listener;
        }

        @Override
        protected List<Acount> doInBackground(String... strings) {
            List<Acount> result=acountDao.queryAcountByName(strings[0],strings[1]);
            return result;
        }

        @Override
        protected void onPostExecute(List<Acount> acounts) {
            super.onPostExecute(acounts);
            if (acounts!=null){
                listener.onDataSuccessfully(acounts);//回调函数拿数据
            }
            else {
                listener.onDataFailed();
            }


        }


    }




}
