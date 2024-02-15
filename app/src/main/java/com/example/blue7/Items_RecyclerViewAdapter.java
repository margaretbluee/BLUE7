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


class Items_RecyclerViewAdapter extends RecyclerView.Adapter<Items_RecyclerViewAdapter.MyViewHolder>  {
  //  private ArrayList<Items> items;
 private List<Items> itemsList;
    private Context context;
    private AdapterListener adapterListener;
   public Items_RecyclerViewAdapter(Context context, AdapterListener listener, List<Items> itemsList) {
       // this.items = items;
        this.context = context;
        this.adapterListener = listener;
       this.itemsList=itemsList;

      // this.itemsList=new ArrayList<>();
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View viewItem= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row,parent,false);
        return new MyViewHolder(viewItem);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,  int position) {

        Items items = itemsList.get(position);

        holder.idTxt.setText(String.valueOf(items.getIid()));
        holder.nameTxt.setText(items.getName());
        holder.countTxt.setText(String.valueOf(items.getCount()));
        holder.priceTxt.setText(String.valueOf(items.getPrice()));
        holder.fpaTxt.setText(String.valueOf(items.getFpa()));
        holder.categoryTxt.setText(items.getCategory());

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 adapterListener.OnDelete(items , holder.getAdapterPosition());
              //  removeItems( position );
            }
        });

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterListener.OnUpdate(items, holder.getAdapterPosition());
                notifyItemChanged(holder.getAdapterPosition());
            }
        });


    }


    @Override
    public int getItemCount() {
        return itemsList.size();
    }


    public void addItems(Items items) {
       itemsList.add(items);

         notifyItemRangeChanged(0, itemsList.size());

    }
    public void showItems(List<Items> newList) {
 if(newList!=itemsList)    {   itemsList.addAll(newList);}

        notifyItemRangeChanged(0, itemsList.size());

    }
    public void removeItems(int position) {
        itemsList.remove(position);

   //  notifyItemRemoved(position);
    }
    public void clearData(){
       itemsList.clear();
       notifyDataSetChanged();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder  {
      public  TextView idTxt,nameTxt, countTxt, priceTxt, fpaTxt, categoryTxt;
       public  ImageView imgEdit,imgDelete;

        public  MyViewHolder(@NonNull View itemView) {
            super(itemView);

            idTxt = itemView.findViewById(R.id.row_sidfb);
            nameTxt = itemView.findViewById(R.id.row_inamefb);
            countTxt = itemView.findViewById(R.id.row_scountfb);
            priceTxt = itemView.findViewById(R.id.titlos);
            fpaTxt = itemView.findViewById(R.id.row_fpa);
            categoryTxt = itemView.findViewById(R.id.row_icategfb);

            imgEdit = itemView.findViewById(R.id.imgEditFBI);
            imgDelete = itemView.findViewById(R.id.imgDeleteFBI);

        }
        }


           }
