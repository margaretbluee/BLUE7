package com.example.blue7;

import static com.example.blue7.MainActivity.db;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

public class TransactionsFragment extends Fragment implements AdapterListenerT , DatePickerDialog.OnDateSetListener {
    Spinner spinner_items_namesi;
    Spinner spinner_companies_namesi;
    RecyclerView recviewt;
ImageView clndr;
    EditText trid,   trdate, trcount;
TextView ttriid, trdisc,ttrcid,triname, trcname, pricetv;
    MaterialButton btnAddt;

private static final String  TAG ="TransactionsFragment";
    private EshopDatabase eshopDatabase;
    private MyDaoEshop myDaoEshop;
    private Transactions_RecyclerViewAdapter transactions_recyclerViewAdapter;
    private Items_RecyclerViewAdapter items_recyclerViewAdapter;

    List<Transactions> transactions;
    private EshopDatabase myAppDatabaseEshop;

ImageView plus, minus;
    public TransactionsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_transactions, container, false);

        recviewt = view.findViewById(R.id.transactionsListId);
    db = Room.databaseBuilder(getContext(), EshopDatabase.class, "eshopBD").allowMainThreadQueries().fallbackToDestructiveMigration().build();

      transactions = db.myDaoEshop().getTransactions();


        trid =  view.findViewById(R.id.TrEditTexti);
      ttriid = view.findViewById(R.id.ttriid);
        triname = view.findViewById(R.id.TrEditTextii);

       ttrcid = view.findViewById(R.id.trcid);

              trcname= view.findViewById(R.id.trEditTextco);

        trcount = view.findViewById(R.id.TrEditTextcoun);
       trdate = view.findViewById(R.id.TrEditTextdat);
        trdisc = view.findViewById(R.id.TrEditTextdiscoun);
        clndr=view.findViewById(R.id.imgCalendarT);
        pricetv=view.findViewById(R.id.price);
        btnAddt = view.findViewById(R.id.bn_insertTransactions);

        recviewt = view.findViewById(R.id.transactionsListId);
        recviewt.invalidate();




        spinner_items_namesi=view.findViewById(R.id.spinnerItems);

        List<String> items_namei = new ArrayList<>();
        items_namei= db.myDaoEshop().getItemsNames();
        items_namei.add(0,"ΠΡΟΪΟΝ");

        ArrayAdapter<String> arrayAdapter_i = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, items_namei);
        arrayAdapter_i.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_items_namesi.setAdapter(arrayAdapter_i);


        spinner_items_namesi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("ΠΡΟΪΟΝ")){}
                else{String item1= parent.getItemAtPosition(position).toString();
triname.setText(item1);
                    String name= item1;
                    int iid= db.myDaoEshop().getItemsId(name);
                    ttriid.setText((String.valueOf(iid)));
                    int  Var_triid = Integer.parseInt(ttriid.getText().toString());

                    int price = db.myDaoEshop().getItemsPrice(Var_triid);
                    pricetv.setText(String.valueOf(price));
                    int count= Integer.parseInt(trcount.getText().toString());
                    int kostos = price* count;
                    trdisc.setText(String.valueOf(kostos));
                    //SPINNER WITH NULL OPTION
                    spinner_items_namesi.setSelection(0);

                }}
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//////////////////////////////////////////////////////////////////////////////////////////////////////////////

        spinner_companies_namesi=view.findViewById(R.id.spinnerCompanies);

        List<String> companies_namei = new ArrayList<>();
        companies_namei= db.myDaoEshop().getCompaniesNames();
        companies_namei.add(0,"ΠΡΟΜΗΘΕΥΤΗΣ");

        ArrayAdapter<String> arrayAdapter_ic = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, companies_namei);
        arrayAdapter_ic.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_companies_namesi.setAdapter(arrayAdapter_ic);


        spinner_companies_namesi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("ΠΡΟΜΗΘΕΥΤΗΣ")){}
                else{String itemc= parent.getItemAtPosition(position).toString();


                    trcname.setText(itemc);
                    String namec= itemc;
                    int cid= db.myDaoEshop().getCompaniesId(namec);
                    ttrcid.setText((String.valueOf(cid)));

                    //SPINNER WITH  OPTION
                    spinner_companies_namesi.setSelection(0);

                }}

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

            transactions_recyclerViewAdapter = new Transactions_RecyclerViewAdapter(getContext(), this, transactions);
        recviewt.setAdapter( transactions_recyclerViewAdapter);
        recviewt.setLayoutManager(new LinearLayoutManager(getContext()));


        int count=25;
        trcount.setText(String.valueOf(count));


        plus= view.findViewById(R.id.imgPlusTr);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Var_trcount ;



                Var_trcount = Integer.parseInt(trcount.getText().toString());
                Var_trcount++;
                trcount.setText(String.valueOf(Var_trcount));
                int  Var_triid = Integer.parseInt(ttriid.getText().toString());

                int price = db.myDaoEshop().getItemsPrice(Var_triid);
                int count= Integer.parseInt(trcount.getText().toString());
                int kostos = price* count;
                trdisc.setText(String.valueOf(kostos));


            }
        });

        minus= view.findViewById(R.id.imgMinusTr);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Var_trcount  ;

                    Var_trcount = Integer.parseInt(trcount.getText().toString());
                    Var_trcount--;
                    trcount.setText(String.valueOf(Var_trcount));
              int  Var_triid = Integer.parseInt(ttriid.getText().toString());

int price = db.myDaoEshop().getItemsPrice(Var_triid);
int count= Integer.parseInt(trcount.getText().toString());
int kostos = price* count;
trdisc.setText(String.valueOf(kostos));


            }
        });



        clndr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
DialogFragment dialogFragment = new DatePickerFragment();
dialogFragment.setTargetFragment(TransactionsFragment.this, 0);
dialogFragment.show(getFragmentManager(),"datePicker");
            }
        });

        //   getroomdata();

        btnAddt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EshopDatabase db = Room.databaseBuilder(getContext(), EshopDatabase.class, "eshopBD").allowMainThreadQueries().fallbackToDestructiveMigration().build();

                myDaoEshop = db.myDaoEshop();
                int Var_trid = 0;
                try {
                    Var_trid = Integer.parseInt(trid.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                Boolean check = myDaoEshop.is_existt(Var_trid);

                if (!check) {

                    String Var_trdate = trdate.getText().toString();
                    int Var_triid = 0;
                    try {
                        Var_triid = Integer.parseInt(ttriid.getText().toString());
                    } catch (NumberFormatException ex) {
                        System.out.println("Could not parse " + ex);
                    }
                    int Var_trcid = 0;
                    try {
                        Var_trcid = Integer.parseInt(ttrcid.getText().toString());
                    } catch (NumberFormatException ex) {
                        System.out.println("Could not parse " + ex);
                    }
                    int Var_trcount = 0;
                    try {
                        Var_trcount = Integer.parseInt(trcount.getText().toString());
                    } catch (NumberFormatException ex) {
                        System.out.println("Could not parse " + ex);
                    }
                    int Var_trdisc = 0;
                    try {
                        int price = db.myDaoEshop().getItemsPrice(Var_triid);
                        int count = Integer.parseInt(trcount.getText().toString());
                        int kostos = price * count;
                        trdisc.setText(String.valueOf(kostos));
                        Var_trdisc = Integer.parseInt(trdisc.getText().toString());
                    } catch (NumberFormatException ex) {
                        System.out.println("Could not parse " + ex);


                    }


                    try {
                        Transactions transactions = new Transactions();

                        transactions.setTr_id(Var_trid);
                        transactions.setItems_id(Var_triid);
                        transactions.setCompanies_id(Var_trcid);
                        transactions.setTr_day(Var_trdate);
                        transactions.setTr_item_count(Var_trcount);
                        transactions.setTr_item_discount(Var_trdisc);
                        MainActivity.myAppDatabaseEshop.myDaoEshop().InsertTransactions(transactions);
 transactions_recyclerViewAdapter.addTransactions(transactions);


                        /////////////ENIMERWSI TIS POSOTITAS TOU ITEM(ROOM) POU PROMIITHEFITKA
                       int posotita_sinallagis = Integer.parseInt(trcount.getText().toString());
int id= Integer.parseInt(ttriid.getText().toString());
                        int items_previous_count = MainActivity.myAppDatabaseEshop.myDaoEshop().getItemsCount(id);
                        int items_new_count = items_previous_count + posotita_sinallagis ;
                        MainActivity.myAppDatabaseEshop.myDaoEshop().UpdateItemsCount(id, items_new_count);
                        Toast.makeText(getActivity(), "ENHMEΡΩΘΗΚΕ ΕΠΙΤΥΧΩΣ Η ΠΟΣΟΣΗΤΑ ΠΡΟΙΟΝΤΩΝ ΑΠΟΘΗΚΗΣ.", Toast.LENGTH_SHORT).show();

                        //////ENIMERWSI POSOTITAS TOU ITEM POU PROMITHEFTIKA STA ITEMS POLISIS (FIREBASE)
                CollectionReference collectionReference1 = MainActivity.fbdb.collection("FB_Items");


                        String onoma_proiontos = MainActivity.db.myDaoEshop().getItemsName_withId_JOIN( id);

                        collectionReference1.
                                get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                    @Override
                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                        String fb_item_name;
                                        Integer fb_item_id;
                                       Integer fb_item_price;
                                        float fb_item_rating;
                                        String fb_item_cat;
                                       Integer fb_item_previous_posotita;
                                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                            FB_Items fb_items = documentSnapshot.toObject(FB_Items.class);
                                            fb_item_id = fb_items.getFb_item_id();//krataw to id gia na kanw upd to sigkekrimeno document
                                            fb_item_name = fb_items.getFb_item_name();//krataw onoma gia na to sigkrinw me to onoma tis topikis vasis
                                            fb_item_price = fb_items.getFb_item_price();
fb_item_rating=fb_items.getFb_item_rating();
fb_item_cat=fb_items.getFb_item_cat();
                                            if (onoma_proiontos.equals(fb_item_name)&& id==fb_item_id) {
                                                 //UPDATE POSOTITAS ITEM (FB)
                                         //       FB_Items fb_items_upd = new FB_Items();
                                                fb_items.setFb_item_count(items_new_count);
                                              fb_items.setFb_item_id(fb_item_id);
                                                fb_items.setFb_item_rating(fb_item_rating);
                                                 fb_items.setFb_item_price(fb_item_price);
                                               fb_items.setFb_item_cat(fb_item_cat);
                                                MainActivity.fbdb.collection("FB_Items").document("" + id).set(fb_items).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Toast.makeText(getActivity(), "ENHMEΡΩΘΗΚΕ ΕΠΙΤΥΧΩΣ Η ΠΟΣΟΣΗΤΑ ΠΡΟΙΟΝΤΩΝ ΠΟΛΗΣΗΣ..", Toast.LENGTH_SHORT).show();

                                                    }


                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(getActivity(), "query operation failed.", Toast.LENGTH_LONG).show();
                                                    }
                                                });




                        try {
                            ///ENIMWRESI SINALLAGIS
                            transactions_recyclerViewAdapter.addTransactions(transactions);
                            // transactions_recyclerViewAdapter.notifyItemRangeChanged(0, transactions.getCount());
                            Toast.makeText(getActivity(), "RECORD ADDED IN DB", Toast.LENGTH_LONG).show();


                            //getroomdata(getView());

                            trid.setText("");
                            ttriid.setText("");
                            ttrcid.setText(" ");
                            trdate.setText("");
                            trcount.setText("");
                            trdisc.setText("");
                            Toast.makeText(getContext(), "RECORD ADDED IN DB", Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Toast.makeText(getActivity(), "FAIL to access DB", Toast.LENGTH_LONG).show();
                        }
                            } else {
                              Toast.makeText(getActivity(), "ALREADY EXISTS", Toast.LENGTH_LONG).show();

                               }
                         }
                                 }
                              });
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), "FAIL to access DB", Toast.LENGTH_LONG).show();
                         }
                         }
                    }
                });

        return view;
    }




    @Override
    public void OnDeleteT(Transactions transactions, int pos) {
        List<Transactions> transactionsList;
        db = Room.databaseBuilder(getContext(), EshopDatabase.class, "eshopBD").allowMainThreadQueries().build();
        try{
            transactionsList = db.myDaoEshop().getTransactions();
            MainActivity.myAppDatabaseEshop.myDaoEshop().DeleteTransactions(transactions);
            transactions_recyclerViewAdapter.removeTransactions(pos);
            // itemsList.remove(pos);

            recviewt.removeViewAt(pos);
            transactions_recyclerViewAdapter.notifyItemRemoved(pos);
            transactions_recyclerViewAdapter.notifyItemRangeChanged(pos, transactionsList.size());
            //    recview.invalidate();

            Toast.makeText(getActivity(), "DELETED FROM DB", Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_LONG).show();
        }}



    @Override
    public void onResume(){
        super.onResume();
        transactions_recyclerViewAdapter.clearData();
        myAppDatabaseEshop=Room.databaseBuilder(requireContext(),EshopDatabase.class,"eshopBD").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        transactions = myAppDatabaseEshop.myDaoEshop().getTransactions();
        // items_recyclerViewAdapter.clearData();
        transactions_recyclerViewAdapter = new Transactions_RecyclerViewAdapter(getContext(), this, transactions);
        recviewt.setAdapter(transactions_recyclerViewAdapter);
        recviewt.setLayoutManager(new LinearLayoutManager(getContext()));
        transactions_recyclerViewAdapter.showTransactions(transactions);

    }





    @Override
    public void OnUpdateT(Transactions transactions,int pos){

        Intent intent=new Intent(getContext(),edit_transactions.class);
        intent.putExtra("model",transactions);
        startActivity(intent);}

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        StringBuilder sb = new StringBuilder().append(dayOfMonth).append("/").append(month);
        String formattedDate =sb.toString();
        trdate.setText(formattedDate);
    }
}
