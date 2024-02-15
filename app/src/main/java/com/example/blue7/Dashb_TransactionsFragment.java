package com.example.blue7;

import static com.example.blue7.MainActivity.db;
import static com.example.blue7.MainActivity.myAppDatabaseEshop;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;


public class Dashb_TransactionsFragment extends Fragment implements AdapterListenerT{


    public Dashb_TransactionsFragment() {
     }


RecyclerView recyclerViewtrdash;
    Transactions_RecyclerViewAdapter transactions_recyclerViewAdapter;
List<Transactions> transactions;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashb__transactions,container, false);
 recyclerViewtrdash = view.findViewById(R.id.dashTransactionsListIdfb);
        List<Transactions> transactions= new ArrayList<>();
transactions_recyclerViewAdapter=new Transactions_RecyclerViewAdapter(getContext(), this,transactions);
recyclerViewtrdash.setAdapter(transactions_recyclerViewAdapter);
recyclerViewtrdash.setLayoutManager(new LinearLayoutManager(getContext()) );
transactions_recyclerViewAdapter.showTransactions(transactions);


 return view;
     }

    @Override
    public void OnDeleteT(Transactions transactions, int pos) {
        List<Transactions> transactionsList;
        db = Room.databaseBuilder(getContext(), EshopDatabase.class, "eshopBD").allowMainThreadQueries().build();
        try{
            transactionsList = db.myDaoEshop().getTransactions();
            myAppDatabaseEshop.myDaoEshop().DeleteTransactions(transactions);
            transactions_recyclerViewAdapter.removeTransactions(pos);
            // itemsList.remove(pos);

            recyclerViewtrdash.removeViewAt(pos);
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
        List<Transactions> transactions = myAppDatabaseEshop.myDaoEshop().getTransactions();
        // items_recyclerViewAdapter.clearData();
        transactions_recyclerViewAdapter = new Transactions_RecyclerViewAdapter(getContext(), this, transactions);
        recyclerViewtrdash.setAdapter(transactions_recyclerViewAdapter);
        recyclerViewtrdash.setLayoutManager(new LinearLayoutManager(getContext()));
        transactions_recyclerViewAdapter.showTransactions(transactions);

    }





    @Override
    public void OnUpdateT(Transactions transactions,int pos){

        Intent intent=new Intent(getContext(),edit_transactions.class);
        intent.putExtra("model",transactions);
        startActivity(intent);}}