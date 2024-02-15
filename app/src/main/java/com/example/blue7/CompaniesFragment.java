package com.example.blue7;

import static com.example.blue7.MainActivity.db;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.List;

public class CompaniesFragment extends Fragment implements com.example.blue7.AdapterListenerC{

    RecyclerView recviewc;

    EditText cid, cname,cafm, ctel, cadr;

    MaterialButton btnAddc;


    private EshopDatabase eshopDatabase;
    private MyDaoEshop myDaoEshop;
    private Companies_RecyclerViewAdapter companies_recyclerViewAdapter;
    List<Companies> companies;
     private EshopDatabase myAppDatabaseEshop;


    public CompaniesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_companies, container, false);

        recviewc = view.findViewById(R.id.companiesListId);
        EshopDatabase db = Room.databaseBuilder(getContext(), EshopDatabase.class, "eshopBD").allowMainThreadQueries().fallbackToDestructiveMigration().build();


       companies = db.myDaoEshop().getCompanies();


        cid =  view.findViewById(R.id.fbcid);
      cname = view.findViewById(R.id.fbcname);

      cafm = view.findViewById(R.id.fbcage);
       ctel = view.findViewById(R.id.fbcgender);
       cadr = view.findViewById(R.id.fbcrating);
         recviewc.invalidate();

//PROSOXI STO THIS !!!!!
      companies_recyclerViewAdapter = new Companies_RecyclerViewAdapter(getContext(), this, companies);
        recviewc.setAdapter(companies_recyclerViewAdapter);
        recviewc.setLayoutManager(new LinearLayoutManager(getContext()));


        //   getroomdata();

        btnAddc = view.findViewById(R.id.bn_fbinsertc);
        btnAddc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EshopDatabase db = Room.databaseBuilder(getContext(), EshopDatabase.class, "eshopBD").allowMainThreadQueries().fallbackToDestructiveMigration().build();

                myDaoEshop = db.myDaoEshop();
                int Var_cid = 0;
                try {
                    Var_cid = Integer.parseInt(cid.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                Boolean check = myDaoEshop.is_existc(Var_cid);

                if (!check) {

                    String Var_cname = cname.getText().toString();
                    int Var_cafm = 0;
                    try {
                        Var_cafm = Integer.parseInt(cafm.getText().toString());
                    } catch (NumberFormatException ex) {
                        System.out.println("Could not parse " + ex);
                    }


                    String Var_cadr = cadr.getText().toString();
                    String Var_ctel = cadr.getText().toString();


                    try {
                      Companies companies = new Companies();
                        //          (Var_itemsid,Var_itemsname,Var_itemscount, Var_itemsprice,Var_itemsfpa,Var_itemscategory);

                        companies.setCid(Var_cid);
                        companies.setName(Var_cname);
                        companies.setAfm(Var_cafm);
                        companies.setTel(Var_ctel);
                        companies.setAdr(Var_cadr);

                        MainActivity.myAppDatabaseEshop.myDaoEshop().InsertCompanies(companies);

                        companies_recyclerViewAdapter.addCompanies(companies);
                    //    companies_recyclerViewAdapter.notifyItemRangeChanged(0, companies.ge());
                        Toast.makeText(getActivity(), "RECORD ADDED IN DB", Toast.LENGTH_LONG).show();


                        //getroomdata(getView());

                        cid.setText("");
                        cname.setText("");
                       cafm.setText("");
                     ctel.setText("");
                      cadr.setText("");
                        Toast.makeText(getContext(), "RECORD ADDED IN DB", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), "FAIL to access DB", Toast.LENGTH_LONG).show();
                    }
                } else {
         /*       iname.setText("");
          icount.setText("");
          iprice.setText("");
          icategory.setText("");*/
                    Toast.makeText(getActivity(), "ALREADY EXISTS", Toast.LENGTH_LONG).show();

                }
            }
        });

        return view;
    }



    @Override
    public void OnDeleteC(Companies companies, int pos) {
        List<Companies>companiesList;
        db = Room.databaseBuilder(getContext(), EshopDatabase.class, "eshopBD").allowMainThreadQueries().build();
        try{
            companiesList = db.myDaoEshop().getCompanies();
            MainActivity.myAppDatabaseEshop.myDaoEshop().DeleteCompanies(companies);
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