package com.example.appstore.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.IdentityHashMap;

@Entity
public class SearchResults {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "iconPath")
    private String iconPath;//app图标路径

    @ColumnInfo(name = "appName")
    private String appName;//app名称

    @ColumnInfo(name = "appDecs")
    private String appDecs;//app简述

    @ColumnInfo(name = "appLeves")
    private String appLeves;//appp星级评分

    @ColumnInfo(name = "appNums")
    private  String appNums;//app使用人数

    @ColumnInfo(name = "appShowIamge")
    private String appShowIamge;//app展示图片


    public SearchResults(String iconPath, String appName, String appDecs, String appLeves, String appNums, String appShowIamge) {
        this.iconPath = iconPath;
        this.appName = appName;
        this.appDecs = appDecs;
        this.appLeves = appLeves;
        this.appNums = appNums;
        this.appShowIamge = appShowIamge;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAppDecs() {
        return appDecs;
    }

    public void setAppDecs(String appDecs) {
        this.appDecs = appDecs;
    }

    public String getAppLeves() {
        return appLeves;
    }

    public void setAppLeves(String appLeves) {
        this.appLeves = appLeves;
    }

    public String getAppNums() {
        return appNums;
    }

    public void setAppNums(String appNums) {
        this.appNums = appNums;
    }

    public String getAppShowIamge() {
        return appShowIamge;
    }

    public void setAppShowIamge(String appShowIamge) {
        this.appShowIamge = appShowIamge;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
