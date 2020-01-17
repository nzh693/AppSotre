package com.example.appstore.database.Repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.appstore.database.AppDataBase;
import com.example.appstore.database.CallBack;
import com.example.appstore.database.dao.UpdateAppDao;
import com.example.appstore.database.entity.UpdataApp;

import java.util.List;

public class UpdataAppRepository {


    CallBack datalListener;//回调函数接口
    CallBack isDataListener;//回调函数接口

    private LiveData<List<UpdataApp>> allLiveData;
    private UpdateAppDao updateAppDao;


    public UpdataAppRepository(Context context){
        AppDataBase dataBase= AppDataBase.getInstance(context);
        updateAppDao =dataBase.getMyAppDao();
    }

    public void setDatalListener(CallBack datalListener) {
        this.datalListener = datalListener;
    }

    public void setIsDataListener(CallBack isDataListener) {
        this.isDataListener = isDataListener;
    }

    public LiveData<List<UpdataApp>> getAllLiveData() {
        allLiveData= updateAppDao.queryAllApp();
        return allLiveData;
    }

    public void insert(UpdataApp... updataApps){
        new InsertAsynctask(updateAppDao).execute(updataApps);
    }

    public void update(UpdataApp... updataApps){
        new updateAsynctask(updateAppDao).execute(updataApps);
    }
    public void deleteAll(){
        new DeleteAsynctask(updateAppDao).execute();
    }


    public void deleteByApp(UpdataApp... updataApps){
        new deleteByAppAsynctask(updateAppDao).execute(updataApps);
    }

    public void queryIsUpdateApp(){
        QueryIsUpdateAppAsynctask task=new QueryIsUpdateAppAsynctask(updateAppDao);
        task.setIsDataListener(new CallBack() {
            @Override
            public void onDataSuccessfully(Object object) {
                isDataListener.onDataSuccessfully(object);
            }

            @Override
            public void onDataFailed() {
                isDataListener.onDataFailed();
            }
        });
        task.execute();

    }

    public void queryNotUpdateApp(){

        QueryNotUpdateAppAsynctask task=new QueryNotUpdateAppAsynctask(updateAppDao);
        task.setDataListener(new CallBack() {
            @Override
            public void onDataSuccessfully(Object object) {
                datalListener.onDataSuccessfully(object);
            }
            @Override
            public void onDataFailed() {
                datalListener.onDataFailed();
            }
        });
        task.execute();
    }







    //查询未更新app
    static class QueryNotUpdateAppAsynctask extends AsyncTask<UpdataApp,Void,List<UpdataApp>> {

        private UpdateAppDao updateAppDao;
        CallBack dataListener;

        public QueryNotUpdateAppAsynctask(UpdateAppDao updateAppDao) {
            this.updateAppDao = updateAppDao;
        }

        public void setDataListener(CallBack dataListener) {
            this.dataListener = dataListener;
        }

        @Override
        protected List<UpdataApp> doInBackground(UpdataApp... apps) {
            List<UpdataApp>listLiveData= updateAppDao.queryNotUpdateApp();
            return listLiveData;
        }

        @Override
        protected void onPostExecute(List<UpdataApp> updataApps) {
            super.onPostExecute(updataApps);
            if (updataApps!=null){
                dataListener.onDataSuccessfully(updataApps);
            }
            else {
                dataListener.onDataFailed();
            }
        }
    }



    //查询未更新app
    static class QueryIsUpdateAppAsynctask extends AsyncTask<UpdataApp,Void,List<UpdataApp>> {

        private UpdateAppDao updateAppDao;
        CallBack isDataListener;

        public QueryIsUpdateAppAsynctask(UpdateAppDao updateAppDao) {
            this.updateAppDao = updateAppDao;
        }

        public void setIsDataListener(CallBack isDataListener) {
            this.isDataListener = isDataListener;
        }

        @Override
        protected List<UpdataApp> doInBackground(UpdataApp... apps) {
            List<UpdataApp>listLiveData= updateAppDao.queryIsUpdateApp();
            return listLiveData;
        }

        @Override
        protected void onPostExecute(List<UpdataApp> updataApps) {
            super.onPostExecute(updataApps);
            if (updataApps!=null){
                isDataListener.onDataSuccessfully(updataApps);
            }
            else {
                isDataListener.onDataFailed();
            }
        }
    }




    //插入
    static class InsertAsynctask extends AsyncTask<UpdataApp,Void,Void> {
        private UpdateAppDao updateAppDao;

        public InsertAsynctask(UpdateAppDao updateAppDao) {
            this.updateAppDao = updateAppDao;
        }

        @Override
        protected Void doInBackground(UpdataApp... apps) {
            updateAppDao.insertApp(apps);
            return null;
        }
    }

    //删除
    static class DeleteAsynctask extends AsyncTask<Void,Void,Void>{
        private UpdateAppDao updateAppDao;

        public DeleteAsynctask(UpdateAppDao updateAppDao) {
            this.updateAppDao = updateAppDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            updateAppDao.delete();
            return null;
        }
    }

    //修改
    static class updateAsynctask extends AsyncTask<UpdataApp,Void,Void>{
        private UpdateAppDao updateAppDao;

        public updateAsynctask(UpdateAppDao updateAppDao) {
            this.updateAppDao = updateAppDao;
        }

        @Override
        protected Void doInBackground(UpdataApp... apps) {
            updateAppDao.update(apps);
            return null;
        }
    }

    //副线删除一个
    static class deleteByAppAsynctask extends AsyncTask<UpdataApp,Void,Void>{
        private UpdateAppDao updateAppDao;

        public deleteByAppAsynctask(UpdateAppDao updateAppDao) {
            this.updateAppDao = updateAppDao;
        }

        @Override
        protected Void doInBackground(UpdataApp... apps) {
            updateAppDao.deleteByApp(apps);
            return null;
        }
    }
}
