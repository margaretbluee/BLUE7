package com.example.blue7;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import java.io.Serializable;


@Entity (tableName = "transactions",
        primaryKeys ={"tr_items_id","tr_companies_id","tr_date","trade_id"},
        foreignKeys={
                @ForeignKey(entity = Items.class,
                        parentColumns="items_id",
                        childColumns = "tr_items_id",
                        onDelete=ForeignKey.CASCADE,
                        onUpdate=ForeignKey.CASCADE),
                @ForeignKey(entity = Companies.class,
                        parentColumns="companies_id",
                        childColumns = "tr_companies_id",
                        onDelete=ForeignKey.CASCADE,
                        onUpdate=ForeignKey.CASCADE)

        })

public class Transactions implements Serializable   {
    @ColumnInfo(name = "tr_items_id")
    private int items_id;

    @ColumnInfo(name = "tr_companies_id")
    private int companies_id;


    @ColumnInfo(name="tr_date")
    @NonNull private String tr_day;

    @NonNull  @ColumnInfo(name="trade_id")
    private int tr_id;

    @ColumnInfo(name="tr_count")
    private int tr_item_count;

    @ColumnInfo(name = "tr_discount")
    private int tr_item_discount;

    public int getItems_id() {
        return items_id;
    }

    public void setItems_id(int items_id) {
        this.items_id = items_id;
    }

    public int getCompanies_id() {
        return companies_id;
    }

    public void setCompanies_id(int companies_id) {
        this.companies_id = companies_id;
    }

    public String getTr_day() {
        return tr_day;
    }

    public void setTr_day(String tr_day) {
        this.tr_day = tr_day;
    }


    public int getTr_id() {
        return tr_id;
    }

    public void setTr_id(int tr_id) {
        this.tr_id = tr_id;
    }

    public int getTr_item_count() {
        return tr_item_count;
    }

    public void setTr_item_count(int tr_item_count) {
        this.tr_item_count = tr_item_count;
    }

    public int getTr_item_discount() {
        return tr_item_discount;
    }

    public void setTr_item_discount(int tr_item_discount) {
        this.tr_item_discount = tr_item_discount;
    }
}
