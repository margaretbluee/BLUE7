package com.example.blue7;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.google.android.material.button.MaterialButton;

public class edit_companies extends AppCompatActivity {
    EditText NameEtnc,AfmEtnc ,TelEtnc ,AdrEtnc  ;
    MaterialButton buttondialogc;
    private Companies companies;
    TextView  IdEtnc;
    private EshopDatabase db;
    private MyDaoEshop myDaoEshop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_companies);
        db= Room.databaseBuilder(getApplicationContext(),EshopDatabase.class,"eshopBD").allowMainThreadQueries().fallbackToDestructiveMigration().build();

        myDaoEshop=db.myDaoEshop();

        IdEtnc=findViewById(R.id.companiesIdc);
        NameEtnc=findViewById(R.id.companiesNamedc);
        AfmEtnc=findViewById(R.id.companiesAfmdc);
        TelEtnc=findViewById(R.id.companiesTeldc);
        AdrEtnc=findViewById(R.id.companiesAdrdc);
         buttondialogc=findViewById(R.id.btnEditdc);
ImageView close = findViewById(R.id.closeAlertdc);


        companies=(Companies)getIntent().getSerializableExtra("model");
        IdEtnc.setText(String.valueOf(companies.getCid()));
        NameEtnc.setText(String.valueOf(companies.getName()));
        AfmEtnc.setText(String.valueOf( companies.getAfm()));
        TelEtnc.setText( String.valueOf(companies.getTel()));
        AdrEtnc.setText(  String.valueOf(companies.getAdr()));


      close.setOnClickListener(new View.OnClickListener() {  @Override
      public void onClick(View v) {

          finish();
      }});
        buttondialogc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Companies companiesmodel = new Companies();

                    companiesmodel.setCid(Integer.parseInt(IdEtnc.getText().toString()));
                    companiesmodel.setName(NameEtnc.getText().toString());
                    companiesmodel.setAfm(Integer.parseInt(AfmEtnc.getText().toString()));
                    companiesmodel.setTel( TelEtnc.getText().toString() );
                     companiesmodel.setAdr(AdrEtnc.getText().toString());

                    MainActivity.myAppDatabaseEshop.myDaoEshop().UpdateCompanies(companiesmodel);

                    Toast.makeText(getApplicationContext(), "RECORD UPDATED ", Toast.LENGTH_LONG).show();}

                catch (Exception e ){
                    Toast.makeText(getApplicationContext(), "FAIL to access DB", Toast.LENGTH_LONG).show();


                }  finish();
            }
        });
    }}







