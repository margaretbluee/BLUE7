package com.example.blue7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.room.Room;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class edit_transactions extends AppCompatActivity implements   DatePickerDialog.OnDateSetListener {
    EditText   trdate;
TextView trid, items_names, trcount,trdisc, triid, trcid, companies_names;
    MaterialButton buttondialogt;
    private Transactions transactions;
    Spinner spinner_items_names;
    Spinner spinner_companies_names;

    private EshopDatabase db;
    private MyDaoEshop myDaoEshop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_transactions);
        db= Room.databaseBuilder(getApplicationContext(),EshopDatabase.class,"eshopBD").allowMainThreadQueries().fallbackToDestructiveMigration().build();

        myDaoEshop=db.myDaoEshop();

        trid=findViewById(R.id.trid);
        triid=findViewById(R.id.triid);
        trcid=findViewById(R.id.trcid);
        trdate=findViewById(R.id.trdate);
        trcount=findViewById(R.id.trcount);
        trdisc=findViewById(R.id.trdisc);

        items_names=findViewById(R.id.TrEditTextii);
        companies_names=findViewById(R.id.trEditTextco);

        ImageView imgCalendar = findViewById(R.id.imgCalendarT);
        buttondialogt=findViewById(R.id.btnEditdt);

        ImageView close_items= findViewById(R.id.closeAlertdt);



      transactions=(Transactions)getIntent().getSerializableExtra("model");
        trid.setText(String.valueOf(transactions.getTr_id()));
        triid.setText(String.valueOf(transactions.getItems_id()));
        trcid.setText(String.valueOf( transactions.getCompanies_id()));
        trdate.setText( String.valueOf(transactions.getTr_day()));
        trcount.setText(  String.valueOf(transactions.getTr_item_count()));
        trdisc.setText(String.valueOf( transactions.getTr_item_discount()));

        int idi= Integer.parseInt(triid.getText().toString());
        String namei=MainActivity.db.myDaoEshop().getItemsName(String.valueOf(idi));
        items_names.setText(namei);

        int idc= Integer.parseInt(trcid.getText().toString());
        String namec=MainActivity.db.myDaoEshop().getCompaniesName(String.valueOf(idc));
        companies_names.setText(namec);

        spinner_items_names = findViewById(R.id.spinnerItems);


        List<String> items_name = new ArrayList<>();
        items_name=MainActivity.db.myDaoEshop().getItemsNames();
            items_name.add(0,"ΠΡΟΪΟΝ");

       imgCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.getActivity();
                dialogFragment.show(getSupportFragmentManager(),"datePicker");
            }
        });


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items_name);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_items_names.setAdapter(arrayAdapter);


        spinner_items_names.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("ΠΡΟΪΟΝ")){}
                else{String item1= parent.getItemAtPosition(position).toString();


                  //  BAZW NAME STO TV
                    items_names.setText(item1);

                    String name= items_names.getText().toString();
                    int iid=MainActivity.db.myDaoEshop().getItemsId(name);
                    triid.setText((String.valueOf(iid)));

                    //SPINNER WITH NULL OPTION
                    spinner_items_names.setSelection(0);

                }}

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





         spinner_companies_names = findViewById(R.id.spinnerCompanies);

        List<String> companies_name = new ArrayList<>();

        companies_name=MainActivity.db.myDaoEshop().getCompaniesNames();
        companies_name.add(0,"ΠΡΟΜΗΘΕΥΤΗΣ");


        ArrayAdapter<String> arrayAdapter_companies = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, companies_name);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_companies_names.setAdapter(arrayAdapter_companies);

        spinner_companies_names.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("ΠΡΟΜΗΘΕΥΤΗΣ")){}
                else{String item1= parent.getItemAtPosition(position).toString();

                   companies_names.setText(item1);

                    String name= companies_names.getText().toString();
                    int cid=MainActivity.db.myDaoEshop().getItemsId(name);
                    trcid.setText((String.valueOf(cid)));

                    //SPINNER WITH NULL OPTION
                    spinner_companies_names.setSelection(0);
                }}

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        close_items.setOnClickListener(new View.OnClickListener() {  @Override
        public void onClick(View v) {

            finish();
        }});

        buttondialogt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Transactions transactionsmodel = new Transactions();
                    //          (Var_itemsid,Var_itemsname,Var_itemscount, Var_itemsprice,Var_itemsfpa,Var_itemscategory);

                    transactionsmodel.setTr_id(Integer.parseInt(trid.getText().toString()));
                    transactionsmodel.setItems_id(Integer.parseInt(triid.getText().toString()));
                    transactionsmodel.setCompanies_id(Integer.parseInt(trcid.getText().toString()));
                    transactionsmodel.setTr_day(trdate.getText().toString());
                    transactionsmodel.setTr_item_count(Integer.parseInt(trcount.getText().toString()));
                    transactionsmodel.setTr_item_discount(Integer.parseInt(trdisc.getText().toString()));
                    MainActivity.myAppDatabaseEshop.myDaoEshop().UpdateTransactions(transactionsmodel);
                    //  items_recyclerViewAdapter.addItems(items);
                    //  items_recyclerViewAdapter.notifyItemRangeChanged(0, items.getCount());
                    Toast.makeText(getApplicationContext(), "RECORD UPDATED ", Toast.LENGTH_LONG).show();}

                catch (Exception e ){
                    Toast.makeText(getApplicationContext(), "FAIL to access DB", Toast.LENGTH_LONG).show();


                }  finish();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        StringBuilder sb = new StringBuilder().append(dayOfMonth).append("/").append(month);
        String formattedDate =sb.toString();
       trdate.setText(formattedDate);
    }
}







