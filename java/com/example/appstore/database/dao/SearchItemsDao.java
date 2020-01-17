package com.example.appstore.database.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.appstore.database.entity.SearchItems;

import java.util.List;

@Dao
public interface SearchItemsDao {

    @Insert
    public void inertItem(SearchItems...searchItems);

    @Query("SELECT * FROM SearchItems")
    public LiveData<List<SearchItems>> queryAll();

    @Query("delete  from searchitems")
    public void deleteAll();





}
