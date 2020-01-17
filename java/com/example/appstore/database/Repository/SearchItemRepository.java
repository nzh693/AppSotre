package com.example.appstore.database.Repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.appstore.database.AppDataBase;
import com.example.appstore.database.CallBack;
import com.example.appstore.database.dao.SearchItemsDao;
import com.example.appstore.database.entity.SearchItems;

import java.util.List;


public class SearchItemRepository {



    private LiveData<List<SearchItems>> allLiveData;

    private SearchItemsDao searchItemsDao;

    private CallBack callBackListener;//回调接口返回数据

    public SearchItemRepository(Context context){
        AppDataBase dataBase= AppDataBase.getInstance(context);
        searchItemsDao=dataBase.getSearchItemsDao();

    }

    public LiveData<List<SearchItems>> getAllLiveData() {
        allLiveData=searchItemsDao.queryAll();
        return allLiveData;
    }

    public void setCallBackListener(CallBack callBackListener) {
        this.callBackListener = callBackListener;
    }

    public void insert(SearchItems...items){
          new InsertAsynctask(searchItemsDao).execute(items);
    }

    public void queryAll(){
        new QueryAllAsynctask(searchItemsDao).execute();
    }

    public void deleteAll(){
        new DeleteAllAsynctask(searchItemsDao).execute();
    }




    //插入
    static class InsertAsynctask extends AsyncTask<SearchItems,Void,Void> {
        private SearchItemsDao searchItemsDao;
        public InsertAsynctask(SearchItemsDao searchItemsDao) {
            this.searchItemsDao=searchItemsDao ;
        }
        @Override
        protected Void doInBackground(SearchItems... searchItems) {
            searchItemsDao.inertItem(searchItems);
            return null;
        }
    }

    //查询所有
    static  class QueryAllAsynctask extends AsyncTask<Void, Void, Void> {
        SearchItemsDao searchItemsDao;
        public  QueryAllAsynctask( SearchItemsDao searchItemsDao){
            this.searchItemsDao=searchItemsDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            searchItemsDao.queryAll();
            return null;
        }
    }

    //删除所有
    static  class DeleteAllAsynctask extends AsyncTask<Void, Void, Void> {
        SearchItemsDao searchItemsDao;
        public  DeleteAllAsynctask( SearchItemsDao searchItemsDao){
            this.searchItemsDao=searchItemsDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            searchItemsDao.deleteAll();
            return null;
        }
    }






}
