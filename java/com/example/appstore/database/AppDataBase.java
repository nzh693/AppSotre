package com.example.appstore.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.appstore.database.dao.AcountDao;
import com.example.appstore.database.dao.UpdateAppDao;
import com.example.appstore.database.dao.SearchItemsDao;
import com.example.appstore.database.dao.SearchResultsDao;
import com.example.appstore.database.entity.Acount;
import com.example.appstore.database.entity.UpdataApp;
import com.example.appstore.database.entity.SearchItems;
import com.example.appstore.database.entity.SearchResults;


//单例模式
@Database(entities={UpdataApp.class, Acount.class, SearchResults.class, SearchItems.class},version = 6,exportSchema=false)
public abstract class AppDataBase extends RoomDatabase {


    static AppDataBase instance;
    public static synchronized AppDataBase getInstance(Context context){
        if (instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class,"appStore")
                    .fallbackToDestructiveMigration()//删库跑了算了
                    .build();
        }
        return instance;
    }

    public abstract UpdateAppDao getMyAppDao();
    public abstract AcountDao getAcountDao();
    public abstract SearchItemsDao getSearchItemsDao();
    public abstract SearchResultsDao getSearchResulteDao();

}
