package com.example.appstore.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SearchItems {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "itemName")
    private String itemName;//搜索词条内容

    @ColumnInfo(name = "type")
    private String type;//该词条所属类型


    public SearchItems(String itemName, String type) {
        this.itemName = itemName;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
