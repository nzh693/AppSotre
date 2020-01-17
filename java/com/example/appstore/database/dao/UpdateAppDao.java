package com.example.appstore.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.appstore.database.entity.UpdataApp;

import java.util.List;

@Dao
public interface UpdateAppDao {

    @Insert
    public void insertApp(UpdataApp... apps);

    @Query("select * from UpdataApp ")
    public LiveData<List<UpdataApp>> queryAllApp();

    @Query("select * from UpdataApp where isUpdate=0")
    public List<UpdataApp> queryNotUpdateApp();//未更新的APP

    @Query("select * from UpdataApp where isUpdate=1")
    public List<UpdataApp> queryIsUpdateApp();//已经更新的APP

    @Query("delete  from UpdataApp")
    public void delete();

    @Update()
    public void update(UpdataApp... updataApps);

    @Delete()
    public void deleteByApp(UpdataApp... updataApps);



}
