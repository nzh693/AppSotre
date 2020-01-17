package com.example.appstore.database.Repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.appstore.database.AppDataBase;
import com.example.appstore.database.CallBack;
import com.example.appstore.database.dao.SearchItemsDao;
import com.example.appstore.database.dao.SearchResultsDao;
import com.example.appstore.database.entity.SearchItems;
import com.example.appstore.database.entity.SearchResults;

import java.util.List;


public class SearchResultRepository {



    private LiveData<List<SearchResults>> allLiveData;

    private SearchResultsDao searchResultsDao;

    private CallBack callBackListener;//回调接口返回数据

    public SearchResultRepository(Context context){
        AppDataBase dataBase= AppDataBase.getInstance(context);
        searchResultsDao=dataBase.getSearchResulteDao();

    }

    public LiveData<List<SearchResults>> getAllLiveData() {
        allLiveData=searchResultsDao.QueryAll();
        return allLiveData;
    }

    public void setCallBackListener(CallBack callBackListener) {
        this.callBackListener = callBackListener;
    }

    public void insert(SearchResults...results){
          new InsertAsynctask(searchResultsDao).execute(results);
    }

    public void queryAll(){
        new QueryAllAsynctask(searchResultsDao).execute();
    }

    public void deleteAll(){
      new DeleteAllAsynctask(searchResultsDao).execute();
    }


    //插入
    static class InsertAsynctask extends AsyncTask<SearchResults,Void,Void> {
        private SearchResultsDao resultsDao;
        public InsertAsynctask(SearchResultsDao resultsDao) {
            this.resultsDao=resultsDao ;
        }
        @Override
        protected Void doInBackground(SearchResults... results) {
            resultsDao.insertSearchResult(results);
            return null;
        }
    }

    //查询所有
    static  class QueryAllAsynctask extends AsyncTask<Void, Void, Void> {
        private SearchResultsDao resultsDao;
        public  QueryAllAsynctask( SearchResultsDao resultsDao){
            this.resultsDao=resultsDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            resultsDao.QueryAll();
            return null;
        }
    }

    //删除所有
    static  class DeleteAllAsynctask extends AsyncTask<Void, Void, Void> {
        private SearchResultsDao resultsDao;
        public  DeleteAllAsynctask( SearchResultsDao resultsDao){
            this.resultsDao=resultsDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            resultsDao.deleteAll();
            return null;
        }
    }






}
