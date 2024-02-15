package com.example.blue7;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;



@Database(entities = {Items.class, Companies.class, Transactions.class}, version = 5, exportSchema = false)
public abstract class EshopDatabase extends RoomDatabase{


    public abstract MyDaoEshop myDaoEshop();



    }





