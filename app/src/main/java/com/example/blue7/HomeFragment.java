package com.example.blue7;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment implements View.OnClickListener  {

       public HomeFragment() {
        // Required empty public constructor
    }


    private sharedPreferenceConfig sharedPreferenceConfig;
TextView sales,tr, fbitems, companies,fbclients;
ImageView img_clients, img_tr,   img_fbitems, img_fbsales, img_companies;
AdapterListenerFBClients adapterListenerFBClients;
FBClients_RecyclerViewAdapter fbClients_recyclerViewAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
     //    sharedPreferenceConfig = new sharedPreferenceConfig(getActivity().getApplicationContext());
img_clients=view.findViewById(R.id.img_clients);
        img_tr=view.findViewById(R.id.img_tr);
       // img_items=view.findViewById(R.id.img_items);
        img_fbitems=view.findViewById(R.id.img_fbitems);
        img_fbsales=view.findViewById(R.id.img_fbsales);
        img_companies=view.findViewById(R.id.img_companies);



        tr=view.findViewById(R.id.num_tr);
        sales=view.findViewById(R.id.num_fbsales);
        fbclients=view.findViewById(R.id.num_fbclients);
        fbitems=view.findViewById(R.id.num_fbitems);
        companies=view.findViewById(R.id.num_companies);



        img_clients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dashb_ClientsFragment dashb_clientsFragment = new Dashb_ClientsFragment();
                FragmentManager fragmentManager = getFragmentManager();
                 FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_content_main,dashb_clientsFragment );

                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.commit();
            }
        });
img_tr.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Dashb_TransactionsFragment dashb_trFragment = new Dashb_TransactionsFragment();
        FragmentManager fragmentManager = getFragmentManager();
        assert fragmentManager != null;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment_content_main,dashb_trFragment );
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }
});
        img_fbsales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dashb_SalesFragment dashb_salesFragment = new Dashb_SalesFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_content_main,dashb_salesFragment );
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.commit();
            }
        });
        img_companies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dashb_CompaniesFragment dashb_companiesFragment = new Dashb_CompaniesFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_content_main,dashb_companiesFragment );
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.commit();
            }
        });
        img_fbitems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dashb_FbitemsFragment dashb_fbitemsFragment = new Dashb_FbitemsFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_content_main,dashb_fbitemsFragment );
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.commit();
            }

        });
        return view;
    }

    @Override
    public void onClick(View v) {

    }
}