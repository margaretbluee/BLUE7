package com.example.blue7;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity (tableName = "companies")
public class Companies  implements Serializable {

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @PrimaryKey @NonNull
    @ColumnInfo(name = "companies_id")
    private int  cid;

    @NonNull @ColumnInfo(name = "companies_name")
    private String  name;

    @NonNull @ColumnInfo(name = "companies_afm")
    private int  afm;

    @NonNull @ColumnInfo(name = "companies_tel")
    private String  tel;

    @NonNull @ColumnInfo(name = "companies_adr")
    private String  adr;

    public int getId() {
        return  cid;
    }

    public void setId(@NonNull int id) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public int getAfm() {
        return afm;
    }

    public void setAfm(@NonNull int afm) {
        this.afm = afm;
    }

    @NonNull
    public String getTel() {
        return tel;
    }

    public void setTel(@NonNull String tel) {
        this.tel = tel;
    }

    @NonNull
    public String getAdr() {
        return adr;
    }

    public void setAdr(@NonNull String adr) {
        this.adr = adr;
    }

}
