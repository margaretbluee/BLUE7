package com.example.blue7;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;

@Entity (tableName = "items")
public  class Items implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "items_id")
    private int  iid;

    @ColumnInfo(name = "items_name")
    private String name;

    @ColumnInfo(name = "items_count")
    private int count;

    @ColumnInfo(name = "items_price")
    private int price;

    @ColumnInfo(name = "items_fpa")
    private int fpa;
    @ColumnInfo(name = "items_category")
    private String category;

    public Items(int var_itemsid, String var_itemsname, int var_itemsfpa, int var_itemscount, int var_itemsprice, String var_itemscategory) {
  this.iid=var_itemsid;
  this.name=var_itemsname;
  this.fpa=var_itemsfpa;
  this.count=var_itemscount;
  this.price=var_itemsprice;
  this.category=var_itemscategory;
    }

    public Items( String var_itemsname, int var_itemsfpa, int var_itemscount, int var_itemsprice, String var_itemscategory) {

         this.name=var_itemsname;
        this.fpa=var_itemsfpa;
        this.count=var_itemscount;
        this.price=var_itemsprice;
        this.category=var_itemscategory;
    }

    public Items() {

    }

    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    public int getPrice() {
        return price;
    }




    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    public void setPrice(int price) {
        this.price = price;
    }

    public int getFpa() {
        return fpa;
    }

    public void setFpa(int fpa) {
        this.fpa = fpa;
    }



}
