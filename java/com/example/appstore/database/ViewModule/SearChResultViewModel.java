package com.example.appstore.database.ViewModule;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.appstore.database.Repository.SearchItemRepository;
import com.example.appstore.database.Repository.SearchResultRepository;
import com.example.appstore.database.entity.SearchResults;

import java.util.List;

public class SearChResultViewModel extends AndroidViewModel {

    private SearchResultRepository repository;

    public SearChResultViewModel(@NonNull Application application) {
        super(application);
        repository=new SearchResultRepository(application);
    }

    public LiveData<List<SearchResults>> getLiveData(){
        return repository.getAllLiveData();
    }

    public void insert(SearchResults...results){
        repository.insert(results);
    }

    public void queryAll(){
        repository.queryAll();
    }

    public void deleteAll(){
        repository.deleteAll();
    }
}
