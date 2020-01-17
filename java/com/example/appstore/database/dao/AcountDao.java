package com.example.appstore.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.appstore.database.entity.Acount;

import java.util.List;


@Dao
public interface AcountDao {

    @Insert
    public void insert(Acount...acounts);//添加用户

    @Query("select * from Acount")
    public LiveData<List<Acount>> QueryAllCount();//获取全部用户

    @Query("DELETE FROM Acount")
    public void deleteAllAcount();//删除全部用户

    @Query("SELECT * FROM Acount WHERE acountName=:acountName and password=:passord ")
    public List<Acount> queryAcountByName(String acountName,String passord);//根据账户名查询账户

}