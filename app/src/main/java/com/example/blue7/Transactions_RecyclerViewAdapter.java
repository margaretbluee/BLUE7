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


class Transactions_RecyclerViewAdapter extends RecyclerView.Adapter<Transactions_RecyclerViewAdapter.MyViewHolder>  {
    //  private ArrayList<Items> items;
    private List<Transactions> transactionsList;
    private Context context;
    private AdapterListenerT adapterListenerT;
    public Transactions_RecyclerViewAdapter(Context context, AdapterListenerT listener, List<Transactions> transactionsList) {
        // this.items = items;
        this.context = context;
        this.adapterListenerT = listener;
        this.transactionsList=transactionsList;

        // this.itemsList=new ArrayList<>();
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View viewTransactions= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row_transactions,parent,false);
        return new MyViewHolder(viewTransactions);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,  int position) {

   Transactions transactions = transactionsList.get(position);

        holder.tridTxt.setText(String.valueOf(transactions.getTr_id()));
        holder.trdateTxt.setText(transactions.getTr_day());
        holder.trcountTxt.setText(String.valueOf(transactions.getTr_item_count()));
        holder.trdiscTxt.setText(String.valueOf(transactions.getTr_item_discount()));
        holder.triidTxt.setText(String.valueOf(transactions.getItems_id()));
        holder.trcidTxt.setText(String.valueOf(transactions.getCompanies_id()));

        holder.promitheftis.setText(String.valueOf(MainActivity.db.myDaoEshop().getCompaniesName(String.valueOf(transactions.getCompanies_id()))));
        holder.proion.setText(String.valueOf(MainActivity.db.myDaoEshop().getItemsName(String.valueOf(transactions.getItems_id()))));



        holder.imgDeleteT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterListenerT.OnDeleteT(transactions , holder.getAdapterPosition());
                //  removeItems( position );
            }
        });

        holder.imgEditT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterListenerT.OnUpdateT(transactions, holder.getAdapterPosition());
                notifyItemChanged(holder.getAdapterPosition());
            }
        });
    }


    @Override
    public int getItemCount() {
        return transactionsList.size();
    }


    public void addTransactions(Transactions transactions) {
        transactionsList.add(transactions);

        notifyItemRangeChanged(0, transactionsList.size());

    }
    public void showTransactions(List<Transactions> newList) {
        if(newList!=transactionsList)    {   transactionsList.addAll(newList);}

        notifyItemRangeChanged(0, transactionsList.size());

    }
    public void removeTransactions(int position) {
        transactionsList.remove(position);

        //  notifyItemRemoved(position);
    }
    public void clearData(){
        transactionsList.clear();
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder  {
        public  TextView tridTxt,trdateTxt,triidTxt, trcidTxt, trcountTxt, trdiscTxt, proion, promitheftis;
        public  ImageView imgEditT,imgDeleteT;

        public  MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tridTxt = itemView.findViewById(R.id.row_sidfb);
            trdateTxt = itemView.findViewById(R.id.row_sdatefb);
            trcountTxt = itemView.findViewById(R.id.row_scountfb);
            trdiscTxt = itemView.findViewById(R.id.row_sprofitfb);
            triidTxt = itemView.findViewById(R.id.laalla);
            trcidTxt = itemView.findViewById(R.id.id);

            proion=itemView.findViewById(R.id.row_triname );
            promitheftis=itemView.findViewById(R.id.row_trcname);


            imgEditT = itemView.findViewById(R.id.imgEditT);
            imgDeleteT = itemView.findViewById(R.id.imgDeleteT);

        }
    }


}
