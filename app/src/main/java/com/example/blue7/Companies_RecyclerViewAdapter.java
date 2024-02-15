package com.example.blue7;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class Companies_RecyclerViewAdapter extends RecyclerView.Adapter<Companies_RecyclerViewAdapter.MyViewHolder>{
    //  private ArrayList<Items> items;
    private List<Companies> companiesList;
    private Context context;
     private AdapterListenerC adapterListenerC;


    public Companies_RecyclerViewAdapter(Context context, AdapterListenerC listener, List<Companies> companiesList) {
        // this.items = items;
        this.context = context;
        this.adapterListenerC = listener;
        this.companiesList=companiesList;

        // this.itemsList=new ArrayList<>();
    }



    @NonNull
    @Override
    public MyViewHolder  onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View viewCompanies= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row_companies,parent,false);
        return new MyViewHolder(viewCompanies);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder  holder,  int position) {
Companies companies = companiesList.get(position);

        holder.cIdTxt.setText(String.valueOf(companies.getCid()));
        holder.cNameTxt.setText(companies.getName());
        holder.cAfmTxt.setText(String.valueOf(companies.getAfm()));
         holder.cTelTxt.setText(String.valueOf(companies.getTel()));
        holder.cAdrTxt.setText(companies.getAdr());

        holder.imgDeleteCom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterListenerC.OnDeleteC(companies , holder.getAdapterPosition());
                //  removeItems( position );
            }
        });

        holder.imgEditCom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterListenerC.OnUpdateC(companies, holder.getAdapterPosition());
                notifyItemChanged(holder.getAdapterPosition());
            }
        });
    }


    @Override
    public int getItemCount() {
        return companiesList.size();
    }


    public void addCompanies(Companies companies) {
        companiesList.add(companies);

        notifyItemRangeChanged(0, companiesList.size());

    }
    public void showCompanies(List<Companies> newList) {
        if(newList!=companiesList)    {   companiesList.addAll(newList);}

        notifyItemRangeChanged(0, companiesList.size());

    }
    public void removeCompanies(int position) {
        companiesList.remove(position);

        //  notifyItemRemoved(position);
    }
    public void clearData(){
        companiesList.clear();
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder  {
        public  TextView cIdTxt,cNameTxt, cAfmTxt, cTelTxt, cAdrTxt ;
        public  ImageView imgEditCom,imgDeleteCom;

        public  MyViewHolder(@NonNull View companiesView) {
            super(companiesView);

            cIdTxt = companiesView.findViewById(R.id.row_coid);
            cNameTxt = companiesView.findViewById(R.id.row_coname);
            cAfmTxt = companiesView.findViewById(R.id.row_coafm);
            cTelTxt = companiesView.findViewById(R.id.row_telc);
            cAdrTxt = companiesView.findViewById(R.id.row_adrc);

            imgEditCom = companiesView.findViewById(R.id.imgEditCom);
            imgDeleteCom = companiesView.findViewById(R.id.imgDeleteCom);

        }
    }


}
