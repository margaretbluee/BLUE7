package com.example.blue7;

import static com.example.blue7.MainActivity.db;
import static com.example.blue7.MainActivity.myAppDatabaseEshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;
import java.util.Random;

public class Dashb_CompaniesFragment extends Fragment implements AdapterListenerC{


public Dashb_CompaniesFragment(){ }
    RecyclerView recviewc;
Companies_RecyclerViewAdapter companies_recyclerViewAdapter;
ChipGroup chipgroup;
Chip onoma, id;
    List<Companies> companies;
    AdapterListenerC adapterListenerC;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashb_companies, container, false);

        ImageView filter = view.findViewById(R.id.FILTER);
        recviewc = view.findViewById(R.id.dashCompaniesListIdfb);
        EshopDatabase db = Room.databaseBuilder(getContext(), EshopDatabase.class, "eshopBD").allowMainThreadQueries().fallbackToDestructiveMigration().build();


        companies = db.myDaoEshop().getCompanies();
        companies_recyclerViewAdapter = new Companies_RecyclerViewAdapter(getContext(), this, companies);
        recviewc.setAdapter(companies_recyclerViewAdapter);
        recviewc.setLayoutManager(new LinearLayoutManager(getContext()));
        String selectedKey = "";




        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    List<Companies> asc0;
                    asc0 = db.myDaoEshop().getCompaniesSortByAscLastName();
                    companies_recyclerViewAdapter = new Companies_RecyclerViewAdapter(getContext(), adapterListenerC , asc0);

                    recviewc.setAdapter(companies_recyclerViewAdapter);
                    recviewc.setLayoutManager(new LinearLayoutManager(getContext()));

                }


});        return view;
    }
        @Override
    public void OnDeleteC(Companies companies, int pos) {
        List<Companies> companiesList;
        db = Room.databaseBuilder(getContext(), EshopDatabase.class, "eshopBD").allowMainThreadQueries().build();
        try{
            companiesList = db.myDaoEshop().getCompanies();
            myAppDatabaseEshop.myDaoEshop().DeleteCompanies(companies);
            companies_recyclerViewAdapter.removeCompanies(pos);
            // itemsList.remove(pos);

            recviewc.removeViewAt(pos);
            companies_recyclerViewAdapter.notifyItemRemoved(pos);
            companies_recyclerViewAdapter.notifyItemRangeChanged(pos,   companiesList.size());
            //    recview.invalidate();

            Toast.makeText(getActivity(), "DELETED FROM DB", Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_LONG).show();
        }}



    @Override
    public void onResume(){
        super.onResume();
        companies_recyclerViewAdapter.clearData();
        myAppDatabaseEshop=Room.databaseBuilder(requireContext(),EshopDatabase.class,"eshopBD").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        companies = myAppDatabaseEshop.myDaoEshop().getCompanies();
        // items_recyclerViewAdapter.clearData();
        companies_recyclerViewAdapter = new Companies_RecyclerViewAdapter(getContext(), this,  companies);
        recviewc.setAdapter( companies_recyclerViewAdapter);
        recviewc.setLayoutManager(new LinearLayoutManager(getContext()));
        companies_recyclerViewAdapter.showCompanies(companies);

    }

    @Override
    public void OnUpdateC(Companies companies,int pos){

        Intent intent=new Intent(getContext(), edit_companies.class);
        intent.putExtra("model",   companies);
        startActivity(intent);}}