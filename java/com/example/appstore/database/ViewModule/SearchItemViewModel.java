package com.example.appstore.database.ViewModule;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.appstore.database.AppDataBase;
import com.example.appstore.database.Repository.SearchItemRepository;
import com.example.appstore.database.entity.SearchItems;

import java.lang.annotation.Retention;
import java.util.List;

public class SearchItemViewModel extends AndroidViewModel {

    private SearchItemRepository repository;

    public SearchItemViewModel(@NonNull Application application) {
        super(application);
        repository=new SearchItemRepository(application);
    }

    public void inert(SearchItems...items){
        repository.insert(items);
    }

    public LiveData<List<SearchItems>> getLiveData(){
        return repository.getAllLiveData();
    }

    public void queryAll(){
        repository.queryAll();
    }

    public void deleteAll(){
        repository.deleteAll();
    }

}
