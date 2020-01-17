package com.example.appstore.database.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.appstore.database.entity.SearchResults;

import java.util.List;

@Dao
public interface SearchResultsDao {

    @Insert
    public void insertSearchResult(SearchResults... searchResults);

    @Query("select * from searchresults")
    public LiveData<List<SearchResults>> QueryAll();

    @Query("delete from searchresults")
    public void deleteAll();


}
