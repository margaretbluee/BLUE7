package com.example.blue7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class edit_items extends AppCompatActivity {
    EditText NameEtn  ,PriceEtn ,FpaEtn ,CatEtn ;
    TextView IdEtn, CountEtn;
    MaterialButton buttondialog;
    Spinner spinnerc;
    int test;
    ArrayAdapter<CharSequence> adapterCateg;

    private Items items;
    private EshopDatabase db;
    private MyDaoEshop myDaoEshop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_items);
        db= Room.databaseBuilder(getApplicationContext(),EshopDatabase.class,"eshopBD").allowMainThreadQueries().fallbackToDestructiveMigration().build();

        myDaoEshop=db.myDaoEshop();


        IdEtn=findViewById(R.id.itemsId);
        NameEtn=findViewById(R.id.itemsName);
        CountEtn=findViewById(R.id.itemsCount);
        PriceEtn=findViewById(R.id.itemsPrice);
        FpaEtn=findViewById(R.id.itemsFpa);
        CatEtn=findViewById(R.id.itemsCati);
        buttondialog=findViewById(R.id.btnEditi);
        ImageView close_items= findViewById(R.id.CloseAlerti);


        items=(Items)getIntent().getSerializableExtra("model");
        IdEtn.setText(String.valueOf(items.getIid()));
        NameEtn.setText(String.valueOf(items.getName()));
        CountEtn.setText(String.valueOf( items.getCount()));
        PriceEtn.setText( String.valueOf(items.getPrice()));
        FpaEtn.setText(  String.valueOf(items.getFpa()));
        CatEtn.setText( items.getCategory());

        SeekBar bar = findViewById(R.id.seekBarit);
        bar.setProgress(items.getPrice());
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                PriceEtn.setText(String.valueOf(progress));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    /*    SeekBar barcount = findViewById(R.id.seekBarCount);
        barcount.setProgress(items.getCount());
        barcount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                CountEtn.setText(String.valueOf(progress));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });*/
        spinnerc = findViewById(R.id.spinnerCateg);

        List<String> categories = new ArrayList<>();
        categories.add(0, "---ΔΙΑΛΕΞΕ---");
        categories.add("ΖΩΟΤΡΟΦΕΣ");
        categories.add("ΕΙΔΗ ΚΗΠΟΥ");
        categories.add("ΣΙΤΗΣΗ");
        categories.add("ΕΝΔΥΜΑΣΙΑ");
        categories.add("ΥΠΟΔΗΜΑΤA");
        categories.add("ΔΙΑΚΟΣΜΙΣΗ");
        categories.add("ΥΠΗΡΕΣΙΕΣ");
        categories.add("ΨΙΛΙΚΑ");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerc.setAdapter(arrayAdapter);

        spinnerc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("---ΔΙΑΛΕΞΕ---")){}
                else{String item= parent.getItemAtPosition(position).toString();

                    CatEtn.setText(item);
                    spinnerc.setSelection(0);

                }}

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        close_items.setOnClickListener(new View.OnClickListener() {  @Override
        public void onClick(View v) {

            finish();
        }});

        buttondialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Items itemsmodel = new Items();

                    itemsmodel.setIid(Integer.parseInt(IdEtn.getText().toString()));
                    itemsmodel.setName(NameEtn.getText().toString());
                    itemsmodel.setCount(Integer.parseInt(CountEtn.getText().toString()));
                    itemsmodel.setFpa(Integer.parseInt(FpaEtn.getText().toString()));
                    itemsmodel.setPrice(Integer.parseInt(PriceEtn.getText().toString()));
                    itemsmodel.setCategory(CatEtn.getText().toString());

                    MainActivity.myAppDatabaseEshop.myDaoEshop().UpdateItems(itemsmodel);
                    //  items_recyclerViewAdapter.addItems(items);
                    //  items_recyclerViewAdapter.notifyItemRangeChanged(0, items.getCount());
                    Toast.makeText(getApplicationContext(), "RECORD UPDATED ", Toast.LENGTH_LONG).show();}

                catch (Exception e ){
                    Toast.makeText(getApplicationContext(), "FAIL to access DB", Toast.LENGTH_LONG).show();


                }  finish();
            }
        });
    }}







