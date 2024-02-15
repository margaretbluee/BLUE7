package com.example.blue7;


import static android.text.TextUtils.TruncateAt.END;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

@Dao
public interface MyDaoEshop {
    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Items items);

    @Insert
    void InsertItems (Items items);
//  @Insert  void insertItems(int var_itemsid, String var_itemsname, int var_itemsfpa, int var_itemscount, int var_itemsprice, String var_itemscategory);


    @Insert
    void InsertCompanies (Companies companies);

    @Insert
    void InsertTransactions (Transactions transactions);

    @Update
    void UpdateItems (Items items);

    @Update
    void UpdateCompanies (Companies companies);

    @Update
    void UpdateTransactions (Transactions transactions);


    @Delete
    void DeleteItems (Items items);

    @Delete
    void DeleteCompanies (Companies companies);

    @Delete
    void DeleteTransactions (Transactions transactions);



    @Query("select *  from items")
    List<Items> getItems();

    @Query("select items_name  from items")
    List<String> getItemsNames();

    @Query("select *  from companies")
    List<Companies> getCompanies();

    @Query("SELECT EXISTS(SELECT* FROM Items WHERE items_id = :itemId)")
    Boolean is_exist(int itemId);
    @Query("SELECT COUNT(companies_id) FROM companies ")
   int number_of_companies();
    @Query("SELECT EXISTS(SELECT* FROM Companies WHERE companies_id = :companiesId)")
    Boolean is_existc(int companiesId);

    @Query("SELECT EXISTS(SELECT* FROM Transactions WHERE trade_id = :tradeId)")
    Boolean is_existt(int tradeId);


    @Query("select *  from transactions")
    List<Transactions> getTransactions();


    @Query("select *  from items ORDER BY items_name ASC")
    LiveData <List<Items>> getAlphabetizedItems();


    @Query("select *  from companies ORDER BY companies_name ASC")
    LiveData <List<Companies>> getAlphabetizedCompanies();


    @Query("select *  from transactions ORDER BY tr_date ASC")
    LiveData <List<Transactions>> getOreredTransactions();


    @Query("DELETE FROM items WHERE items_id= :iid")
    void deleteById(int iid);

    @Query("select *  from Items  ORDER BY items_id DESC  ")
    List<Items> getAllItems();

    @Query("select companies_name  from companies")
       List<String> getCompaniesNames();

    @Query("select companies_id  from companies")
    List<Integer> getCompaniesID();

    @Query("select items_id  from items")
    List<Integer> getItemsID();

    @Query("SELECT DISTINCT items_id  " +
            "FROM Items   " +
            "WHERE items_name = :inputname ")
    public int getItemsId(String inputname);

    @Query("SELECT DISTINCT items_name  " +
            "FROM Items   " +
            "WHERE items_id = :inputid ")
    public String getItemsName(String inputid);

    @Query("SELECT DISTINCT companies_id  " +
            "FROM Companies   " +
            "WHERE companies_name = :inputname ")
    public int getCompaniesId(String inputname);

    @Query("SELECT DISTINCT companies_name  " +
            "FROM Companies   " +
            "WHERE companies_id = :inputid ")
    public String getCompaniesName(String inputid);

    @Query("SELECT DISTINCT items_price  " +
            "FROM Items   " +
            "WHERE items_id = :inputid ")
    public int getItemsPrice(int inputid);
    @Query("SELECT DISTINCT items_price  " +
            "FROM Items   " +
            "WHERE items_name = :inputname ")
    public int getItemsPrice_withName(String inputname);

    @Query("SELECT DISTINCT items_count  " +
            "FROM Items   " +
            "WHERE items_id = :inputid ")
    public int getItemsCount(int inputid);
    @Query("SELECT DISTINCT items_id  " +
            "FROM Items   " +
            "WHERE items_name = :inputname ")
    public int getItemsId_withName(String inputname);

    ///enimerwsi fb_items_count
    @Query("SELECT DISTINCT items_count  " +
            "FROM Items   " +
            "WHERE items_name = :inputname ")
    public int getItemsCount_withName(String inputname);

    @Query("UPDATE Items  " +
            "SET items_count = :inputcount  " +
            "WHERE items_id = :inputid ")
    public void UpdateItemsCount(int inputid, int inputcount);

    @Query("UPDATE Items  " +
            "SET items_count = :inputcount  " +
            "WHERE items_name = :inputname ")
    public void UpdateItemsCountwithName(String inputname, int inputcount);
    @Query("SELECT DISTINCT items_name  " +
            "FROM Items   INNER JOIN Transactions ON Items.items_id = Transactions.tr_items_id  " +
            "WHERE items_id = :inputid ")
    public String getItemsName_withId_JOIN(int inputid);

    @Query("SELECT DISTINCT items_category  " +
            "FROM Items    " +
            "WHERE items_name = :inputname ")
    public String getItemsCategory_withName(String inputname);



    @Query("SELECT * FROM Companies ORDER BY companies_name ASC")
    List<Companies> getCompaniesSortByAscLastName();
    @Query("SELECT * FROM Companies ORDER BY companies_id ASC")
    List<Companies> getCompaniesSortByAscID();

    @Query("SELECT * FROM Companies ORDER BY companies_name DESC")
    List<Companies> getCompaniesSortByDescLastName();

}