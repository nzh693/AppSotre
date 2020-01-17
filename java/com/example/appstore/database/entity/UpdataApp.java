package com.example.appstore.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.security.PrivateKey;
import java.util.jar.Attributes;


/**
 * 待更新App
 */
@Entity
public class UpdataApp {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="appName")
    private String appName; //app名称

    @ColumnInfo(name = "imagePath")
    private String imagePath;//app图片路径

    @ColumnInfo(name = "content")
    private String content;//app简述

    @ColumnInfo(name = "Date")
    private String Date;//更新app

    @ColumnInfo(name = "isUpdate")
    private int isUpdate;//是否已经刚刚更新过


    public UpdataApp(String appName, String imagePath, String content, String date, int isUpdate) {
        this.appName = appName;
        this.imagePath = imagePath;
        this.content = content;
        Date = date;
        this.isUpdate = isUpdate;
    }

    public UpdataApp() {
    }





    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getId() {
        return id;
    }

    public int getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(int isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
