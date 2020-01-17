package com.example.appstore.database.entity;

import android.graphics.Paint;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 用户账户表
 */
@Entity
public class Acount {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "acountName")
    private String acountName;

    @ColumnInfo(name = "acountSex")
    private String acountSex;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "acountPhone")
    private String acountPhone;



    public Acount(String acountName, String acountSex, String password, String acountPhone) {
        this.acountName = acountName;
        this.acountSex = acountSex;
        this.password = password;
        this.acountPhone = acountPhone;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAcountName() {
        return acountName;
    }

    public void setAcountName(String acountName) {
        this.acountName = acountName;
    }

    public String getAcountSex() {
        return acountSex;
    }

    public void setAcountSex(String acountSex) {
        this.acountSex = acountSex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAcountPhone() {
        return acountPhone;
    }

    public void setAcountPhone(String acountPhone) {
        this.acountPhone = acountPhone;
    }
}
