package com.example.blue7;

import static com.example.blue7.MainActivity.db;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ItemsFragment extends Fragment implements AdapterListener, AdapterListenerT {

    RecyclerView recview;

     EditText iid, iname, iprice, ifpa   ;
TextView icount , icategory;
    MaterialButton btnAdd;

    Spinner spinnerc;
      private MyDaoEshop myDaoEshop;
    private Items_RecyclerViewAdapter items_recyclerViewAdapter;
    List<Items> items;
     private EshopDatabase myAppDatabaseEshop;
    private FragmentManager fragmentManager;
    FragmentTransaction transaction;
    private Fragment Edit_Items_Fragment;
    private com.example.blue7.AdapterListener AdapterListener;

    public ItemsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_items, container, false);

        recview = view.findViewById(R.id.itemsListId);
        EshopDatabase db = Room.databaseBuilder(getContext(), EshopDatabase.class, "eshopBD").allowMainThreadQueries().fallbackToDestructiveMigration().build();


       /* Items items1 = new Items(11, "Maria", 4, 4, 4, "FROUTO");
        Items items2 = new Items(12, "JOHN", 4, 4, 4, "LAXANIKA");
        Items items3 = new Items(13, "GEORGE", 4, 4, 4, "LAXANIKA");
        Items items4 = new Items(14, "RITA", 4, 4, 4, "LAXANIKA");


        MainActivity.myAppDatabaseEshop.myDaoEshop().insert(items1);
        MainActivity.myAppDatabaseEshop.myDaoEshop().insert(items2);
        MainActivity.myAppDatabaseEshop.myDaoEshop().insert(items3);
        MainActivity.myAppDatabaseEshop.myDaoEshop().insert(items4);*/


        items = db.myDaoEshop().getItems();


        iid =  view.findViewById(R.id.ItemsEditTexti);
        iname = view.findViewById(R.id.ItemsEditTextnam);

        icount = view.findViewById(R.id.ItemsEditTextcoun);
        iprice = view.findViewById(R.id.ItemsEditTextpric);
        ifpa = view.findViewById(R.id.ItemsEditTextfp);
        icategory = view.findViewById(R.id.ItemsEditTextcategor);
        recview = view.findViewById(R.id.itemsListId);
//        recview.invalidate();

         items_recyclerViewAdapter = new Items_RecyclerViewAdapter(getContext(), this, items);
        recview.setAdapter(items_recyclerViewAdapter);
        recview.setLayoutManager(new LinearLayoutManager(getContext()));

        spinnerc = view.findViewById(R.id.spinnerCatego);

        List<String> categories = new ArrayList<>();
        categories.add(0, " ΚΑΤΗΓΟΡΙΑ ");
        categories.add("ΖΩΟΤΡΟΦΕΣ");
        categories.add("ΕΙΔΗ ΚΗΠΟΥ");
        categories.add("ΣΙΤΗΣΗ");
        categories.add("ΕΝΔΥΜΑΣΙΑ");
        categories.add("ΥΠΟΔΗΜΑΤA");
        categories.add("ΔΙΑΚΟΣΜΙΣΗ");
        categories.add("ΥΠΗΡΕΣΙΕΣ");
        categories.add("ΨΙΛΙΚΑ");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, categories);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerc.setAdapter(arrayAdapter);

        spinnerc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals(" ΚΑΤΗΓΟΡΙΑ ")){}
                else{String item= parent.getItemAtPosition(position).toString();

                    icategory.setText(String.valueOf(item));
                    spinnerc.setSelection(position);
                    //     CatEtn.setText(categories.);
                    //  test = position+1;
                }}

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    /*    ImageView plus, minus;
        plus= view.findViewById(R.id.imgplusi);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Var_salecount = 0;

                try {

                    Var_salecount = Integer.parseInt(icount.getText().toString());
                    Var_salecount ++;
                     icount.setText(String.valueOf(Var_salecount));
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }

            }
        });

        minus= view.findViewById(R.id.imgminusi);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Var_salecount = 0;
                try {
                    Var_salecount = Integer.parseInt(icount.getText().toString());
                    Var_salecount--;
                    icount.setText(String.valueOf(Var_salecount));
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
            }});


*/

        //   getroomdata();

        btnAdd = view.findViewById(R.id.bn_insertItems);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EshopDatabase db = Room.databaseBuilder(getContext(), EshopDatabase.class, "eshopBD").allowMainThreadQueries().fallbackToDestructiveMigration().build();

                myDaoEshop = db.myDaoEshop();
                int Var_itemsid = 0;
                try {
                    Var_itemsid = Integer.parseInt(iid.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                Boolean check = myDaoEshop.is_exist(Var_itemsid);

                if (!check) {

                    String Var_itemsname = iname.getText().toString();
                    int Var_itemscount = 0;
                    try {
                        Var_itemscount = Integer.parseInt(icount.getText().toString());
                    } catch (NumberFormatException ex) {
                        System.out.println("Could not parse " + ex);
                    }
                    int Var_itemsprice = 0;
                    try {
                        Var_itemsprice = Integer.parseInt(iprice.getText().toString());
                    } catch (NumberFormatException ex) {
                        System.out.println("Could not parse " + ex);
                    }
                    int Var_itemsfpa = 0;
                    try {
                        Var_itemsfpa = Integer.parseInt(ifpa.getText().toString());
                    } catch (NumberFormatException ex) {
                        System.out.println("Could not parse " + ex);
                    }
                    String Var_itemscategory = icategory.getText().toString();


                    try {
                        Items items = new Items();
                        //          (Var_itemsid,Var_itemsname,Var_itemscount, Var_itemsprice,Var_itemsfpa,Var_itemscategory);

                        items.setIid(Var_itemsid);
                        items.setName(Var_itemsname);
                        items.setCount(Var_itemscount);
                        items.setFpa(Var_itemsfpa);
                        items.setPrice(Var_itemsprice);
                        items.setCategory(Var_itemscategory);

                        MainActivity.myAppDatabaseEshop.myDaoEshop().InsertItems(items);

                        items_recyclerViewAdapter.addItems(items);
items_recyclerViewAdapter.notifyItemRangeChanged(0, items.getCount());
Toast.makeText(getActivity(), "RECORD ADDED IN DB", Toast.LENGTH_LONG).show();


                        //getroomdata(getView());

                        iid.setText("");
                        iname.setText("");
                        icount.setText("0");
                        iprice.setText("");
                        icategory.setText("");
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
    public void OnDelete(Items items, int pos) {
        List<Items> itemsList;
             db = Room.databaseBuilder(getContext(), EshopDatabase.class, "eshopBD").allowMainThreadQueries().build();
      try{
          itemsList = db.myDaoEshop().getItems();
        MainActivity.myAppDatabaseEshop.myDaoEshop().DeleteItems(items);
     items_recyclerViewAdapter.removeItems(pos);
        // itemsList.remove(pos);

  recview.removeViewAt(pos);
 items_recyclerViewAdapter.notifyItemRemoved(pos);
  items_recyclerViewAdapter.notifyItemRangeChanged(pos, itemsList.size());
      //    recview.invalidate();

        Toast.makeText(getActivity(), "DELETED FROM DB", Toast.LENGTH_LONG).show();
    }catch(Exception e){
        Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_LONG).show();
    }}



@Override
public void onResume(){
        super.onResume();
    items_recyclerViewAdapter.clearData();
    myAppDatabaseEshop=Room.databaseBuilder(requireContext(),EshopDatabase.class,"eshopBD").allowMainThreadQueries().fallbackToDestructiveMigration().build();
items = myAppDatabaseEshop.myDaoEshop().getItems();
    // items_recyclerViewAdapter.clearData();
    items_recyclerViewAdapter = new Items_RecyclerViewAdapter(getContext(), this, items);
    recview.setAdapter(items_recyclerViewAdapter);
    recview.setLayoutManager(new LinearLayoutManager(getContext()));
    items_recyclerViewAdapter.showItems(items);

        }

@Override
public void OnUpdate(Items items,int pos){

        Intent intent=new Intent(getContext(),edit_items.class);
        intent.putExtra("model",items);
        startActivity(intent);}




    @Override
    public void OnUpdateT(Transactions transactions, int pos) {

    }

    @Override
    public void OnDeleteT(Transactions transactions, int pos) {

    }
}
      /*  AlertDialog.Builder builderObj=new AlertDialog.Builder(getContext());
        View view=LayoutInflater.from(getContext()).inflate(R.layout.edit_items,null);

        EditText IdEtn=view.findViewById(R.id.itemsIdd);
        EditText NameEtn=view.findViewById(R.id.itemsNamed);
        EditText CountEtn=view.findViewById(R.id.itemsCountd);
        EditText PriceEtn=view.findViewById(R.id.itemsPriced);
        EditText FpaEtn=view.findViewById(R.id.itemsFpad);
        EditText CatEtn=view.findViewById(R.id.itemsCatd);
        MaterialButton buttondialog=view.findViewById(R.id.btnEditd);

        IdEtn.setText(items.getIid());
        NameEtn.setText( items.getName());
        CountEtn.setText( items.getCount());
        PriceEtn.setText( items.getPrice());
        FpaEtn.setText( items.getFpa());
        CatEtn.setText( items.getCategory());

        ImageView closeAlert=view.findViewById(R.id.closeAlertd);
        builderObj.setCancelable(false);
        builderObj.setView(view);

        closeAlert.setOnClickListener(v->{
            alertDialog.cancel();
        });

        buttondialog.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View v) {

                int Var_itemsid = 0;
                try {
                    Var_itemsid = Integer.parseInt(IdEtn.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                Boolean check = myDaoEshop.is_exist(Var_itemsid);

                if (check) {

                    String Var_itemsname = NameEtn.getText().toString();
                    int Var_itemscount = 0;
                    try {
                        Var_itemscount = Integer.parseInt(CountEtn.getText().toString());
                    } catch (NumberFormatException ex) {
                        System.out.println("Could not parse " + ex);
                    }
                    int Var_itemsprice = 0;
                    try {
                        Var_itemsprice = Integer.parseInt(PriceEtn.getText().toString());
                    } catch (NumberFormatException ex) {
                        System.out.println("Could not parse " + ex);
                    }
                    int Var_itemsfpa = 0;
                    try {
                        Var_itemsfpa = Integer.parseInt(FpaEtn.getText().toString());
                    } catch (NumberFormatException ex) {
                        System.out.println("Could not parse " + ex);
                    }
                    String Var_itemscategory = CatEtn.getText().toString();


                    try {
                        Items items = new Items();

                        items.setIid(Var_itemsid);
                        items.setName(Var_itemsname);
                        items.setCount(Var_itemscount);
                        items.setFpa(Var_itemsfpa);
                        items.setPrice(Var_itemsprice);
                        items.setCategory(Var_itemscategory);

                        MainActivity.myAppDatabaseEshop.myDaoEshop().UpdateItems(items);

                        //      items_recyclerViewAdapter.update(items);

                   //     MainActivity.myAppDatabaseEshop.myDaoEshop().UpdateItems(itemsmod);
                        Toast.makeText(getActivity(), "SUCCESSFUL UPDATE", Toast.LENGTH_LONG).show();

                        //   getroomdata(v);
                    } catch (Exception e) {
                        String message = e.getMessage();
                        Toast.makeText(getActivity(), "NOT SUCCESSFUL UPDATE", Toast.LENGTH_LONG).show();

                    }



                } else {
                    Toast.makeText(getActivity(), "ID DOES NOT EXISTS", Toast.LENGTH_LONG).show();
                }
            }});
        alertDialog = builderObj.create();
        alertDialog.show();
        }*/


  /*  @Override
    public void ImageClick(ItemsModel itemsModel) {
        EditItemsDialog dialog = new EditItemsDialog(itemsModel);
        dialog.show(getSupportFragmentManager(), "your_dialog_tag");
    }
}






/*
      MyDaoEshop myDaoEshop;
private Items_RecyclerViewAdapter itemsAdapter;
    private RecyclerView recyclerView = getView().findViewById(R.id.mRecyclerView);

    public ItemsFragment(MyDaoEshop myDaoEshop) {
        // Required empty public constructor
        this.myDaoEshop = myDaoEshop;
    }
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_items, container, false);



        EditText ieditTextid, ieditTextname, ieditTextcount, ieditTextprice, ieditTextfpa, ieditTextcat;
        Button iibn , ifbn;


        itemsAdapter=new Items_RecyclerViewAdapter(  getContext());

        RecyclerView recyclerView = view.findViewById(R.id.mRecyclerView);



        itemsAdapter= new Items_RecyclerViewAdapter(getContext());

        recyclerView.setAdapter(itemsAdapter);
recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ieditTextid= view.findViewByIdint Var_itemsid = 0;
                try {
                    Var_itemsid = Integer.parseInt(ieditTextid.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                String Var_itemsname = ieditTextname.getText().toString();
                int Var_itemscount = 0;
                try {
                    Var_itemscount = Integer.parseInt(ieditTextcount.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                int Var_itemsprice = 0;
                try {
                    Var_itemsprice = Integer.parseInt(ieditTextprice.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                int Var_itemsfpa = 0;
                try {
                    Var_itemsfpa = Integer.parseInt(ieditTextfpa.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                String Var_itemscategory = ieditTextcat.getText().toString();


                try {
                    Items items = new Items();

                    items.setIid(Var_itemsid);
                    items.setName(Var_itemsname);
                    items.setCount(Var_itemscount);
                    items.setFpa(Var_itemsfpa);
                    items.setPrice(Var_itemsprice);
                    items.setCategory(Var_itemscategory);
                    MainActivity.myAppDatabaseEshop.myDaoEshop().InsertItems(items);

                MainActivity.myAppDatabaseEshop.myDaoEshop().InsertItems(new Items(Var_itemsid,Var_itemsname,Var_itemsfpa, Var_itemscount, Var_itemsprice, Var_itemscategory));
             (R.id.ItemsEditTextid);
        ieditTextname= view.findViewById(R.id.ItemsEditTextname);
        ieditTextcount = view.findViewById(R.id.ItemsEditTextcount);
        ieditTextprice = view.findViewById(R.id.ItemsEditTextprice);
        ieditTextfpa = view.findViewById(R.id.ItemsEditTextfpa);
        ieditTextcat = view.findViewById(R.id.ItemsEditTextcat);

        iibn = view.findViewById(R.id.insertItemsSubmitButton);
        ifbn = view.findViewById(R.id.FetchItemsSubmitButton);
 //fetchData();

        iibn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EshopDatabase db= Room.databaseBuilder(getContext(),EshopDatabase.class,"EshopBD").allowMainThreadQueries().build();
               MyDaoEshop eshopDao= db.myDaoEshop();

                       itemsAdapter.addItems(items);


                    Toast.makeText(getActivity(), "Record added.", Toast.LENGTH_LONG).show();
                    //itemsAdapter.addItems(items);


                } catch (Exception e) {
                    String message = e.getMessage();
                    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                }
                ieditTextid.setText("");
                ieditTextname.setText("");
                ieditTextcount.setText("");
                ieditTextprice.setText("");
                ieditTextfpa.setText("");
                ieditTextcat.setText("");
            }
        });




        ifbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchData();
            }
        });
        return view;
    }

private void fetchData() {

    Items_RecyclerViewAdapter adapter = new Items_RecyclerViewAdapter(getContext());

    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    EshopDatabase db = Room.databaseBuilder(getContext(),EshopDatabase.class,"eshopBD").allowMainThreadQueries().build();
    MyDaoEshop myDaoEshop = db.myDaoEshop();
    // List<Items> items = MainActivity.myAppDatabaseEshop.myDaoEshop().getItems();

    List<Items> itemsList =MainActivity.myAppDatabaseEshop.myDaoEshop().getItems();
    for (int i = 0; i < itemsList.size(); i++) {
        Items items = itemsList.get(i);
        itemsAdapter.addItems(items);
    }
}



    }



public class ItemsFragment extends Fragment {

    AlertDialog alertDialog;
    EditText ieditText1, ieditText2, ieditText3, ieditText4, ieditText5;
    Button iibn, ifbn;
    private TextView data;
    RecyclerView recyclerView;

      ArrayAdapter<CharSequence> adapter;
    ArrayList<ItemsModel> itemsModels = new ArrayList<>();
    int[] itemsImages = {R.drawable.ic_items_100, R.drawable.ic_items2_dash, R.drawable.ic_menu_items, R.drawable.ic_companies_dash, R.drawable.button_login, R.drawable.ic_items_dash_60, R.drawable.ic_transactions_dash, R.drawable.ic_menu_camera, R.drawable.button_login, R.drawable.ic_customers_dash};

    ArrayList<Items> itemsModel = new ArrayList<>();
    private String result;
    private ArrayList<ItemsModel> itemsModelArrayList;
    private Items_RecyclerViewAdapter items_recyclerViewAdapter;
    private EshopDatabase db;
    private View view;
    private Items_RecyclerViewAdapter adapter1;
    private Items_RecyclerViewAdapter.OnEditListener onEditListener;

    public ItemsFragment() {
        // Required empty public constructor
    }

    private void setUpItemsModels() {
        String[] itemsNames = getResources().getStringArray(R.array.items_name);
        int[] itemsPrices = getResources().getIntArray(R.array.items_price);

        int[] itemsCount = getResources().getIntArray(R.array.items_counter);

        for (int i = 0; i < itemsNames.length; i++) {
            itemsModels.add(new ItemsModel(itemsNames[i], itemsPrices[i], itemsCount[i], itemsImages[i]));
        }


    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_items, container, false);


        //           setUpItemsModels();
        // Find the RecyclerView in the layout
        recyclerView = view.findViewById(R.id.mRecyclerView);
      //  recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // Initialize the database
        db = Room.databaseBuilder(getActivity().getApplicationContext(),
                EshopDatabase.class, "myDB").allowMainThreadQueries().build();

        // Create an instance of the adapter and set it to the RecyclerView
        adapter1 = new Items_RecyclerViewAdapter(getContext(), itemsModels, this::onEditClick);
        recyclerView.setAdapter(adapter1);




// String[] queryArray = getResources().getStringArray(R.array.queries_description_array);


        itemsModelArrayList = new ArrayList<>();
        ieditText1 = view.findViewById(R.id.ItemsEditText1);
        ieditText2 = view.findViewById(R.id.ItemsEditText2);
        ieditText3 = view.findViewById(R.id.ItemsEditText3);
        ieditText4 = view.findViewById(R.id.ItemsEditText4);
        ieditText5 = view.findViewById(R.id.ItemsEditText5);

MyDaoEshop myDaoEshop;

//button insert
        iibn = view.findViewById(R.id.insertItemsSubmitButton);
        recyclerView = view.findViewById(R.id.mRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<Items> items = MainActivity.myAppDatabaseEshop.myDaoEshop().getItems();


        iibn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             int Var_itemsid = 0;
                try {
                  Var_itemsid = Integer.parseInt(ieditText1.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                String Var_itemsname = ieditText2.getText().toString();
                int Var_itemscount = 0;
                try {
                    Var_itemscount = Integer.parseInt(ieditText3.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                int Var_itemsprice = 0;
                try {
                    Var_itemsprice = Integer.parseInt(ieditText4.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                int Var_itemsfpa = 0;
                try {
                    Var_itemsfpa = Integer.parseInt(ieditText5.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }


                try {
                    Items items = new Items();
                    items.setIid(Var_itemsid);
                    items.setName(Var_itemsname);
                    items.setCount(Var_itemscount);
                    items.setFpa(Var_itemsfpa);
                    items.setPrice(Var_itemsprice);
                    MainActivity.myAppDatabaseEshop.myDaoEshop().InsertItems(items);
                    Toast.makeText(getActivity(), "Record added.", Toast.LENGTH_LONG).show();


                } catch (Exception e) {
                    String message = e.getMessage();
                    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                }
                ieditText1.setText("");
                ieditText2.setText("");
                ieditText3.setText("");
                ieditText4.setText("");
                ieditText5.setText("");
                addItems(Var_itemsname, Var_itemsprice, Var_itemscount);
            }
        });
        return view;
    }


    public void addItems(String strItemsName, int strItemsPrice, int strItemsCount) {
        ItemsModel obj = new ItemsModel();

        obj.setItemsName(strItemsName);
        obj.setItemsCount(strItemsCount);
        obj.setItemsPrice(strItemsPrice);

        itemsModelArrayList.add(obj);
        items_recyclerViewAdapter = new Items_RecyclerViewAdapter(getContext(), itemsModelArrayList, this::onEditClick);
        recyclerView.setAdapter(items_recyclerViewAdapter);
    }



      public void onEditClick(ItemsModel listCurrentData,int currentPosition) {


                AlertDialog.Builder builderObj = new AlertDialog.Builder(requireContext());
                 view = LayoutInflater.from(getContext()).inflate(R.layout.edit_items, null);

                EditText itemsNameEtn = view.findViewById(R.id.itemsNamed);
                EditText itemsPricetEtn = view.findViewById(R.id.itemsCountd);
                EditText itemsCountEtn = view.findViewById(R.id.itemsPriced);

                MaterialButton btnEdit = view.findViewById(R.id.btnEditd);

                itemsNameEtn.setText(listCurrentData.getItemsName());
                itemsPricetEtn.setText(listCurrentData.getItemsPrice());
                itemsCountEtn.setText(listCurrentData.getItemsCount());
                ImageView closeAlert = view.findViewById(R.id.closeAlertd);
                builderObj.setCancelable(false);
                builderObj.setView(view);

                closeAlert.setOnClickListener(v -> {
                    alertDialog.cancel();
                });
                btnEdit.setOnClickListener(v -> {

                    int Var_itemsid = 0;
                    try {
                        Var_itemsid = Integer.parseInt(ieditText1.getText().toString());
                    } catch (NumberFormatException ex) {
                        System.out.println("Could not parse " + ex);
                    }
                    String Var_itemsname = ieditText2.getText().toString();
                    int Var_itemscount = 0;
                    try {
                        Var_itemscount = Integer.parseInt(ieditText3.getText().toString());
                    } catch (NumberFormatException ex) {
                        System.out.println("Could not parse " + ex);
                    }
                    int Var_itemsprice = 0;
                    try {
                        Var_itemsprice = Integer.parseInt(ieditText4.getText().toString());
                    } catch (NumberFormatException ex) {
                        System.out.println("Could not parse " + ex);
                    }
                    int Var_itemsfpa = 0;
                    try {
                        Var_itemsfpa = Integer.parseInt(ieditText5.getText().toString());
                    } catch (NumberFormatException ex) {
                        System.out.println("Could not parse " + ex);
                    }


                    try {
                        Items items = new Items();
                        items.setIid(Var_itemsid);
                        items.setName(Var_itemsname);
                        items.setCount(Var_itemscount);
                        items.setFpa(Var_itemsfpa);
                        items.setPrice(Var_itemsprice);
                        MainActivity.myAppDatabaseEshop.myDaoEshop().UpdateItems(items);
                        Toast.makeText(getActivity(), "Record added.", Toast.LENGTH_LONG).show();


                    } catch (Exception e) {
                        String message = e.getMessage();
                        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                    }
                    ieditText1.setText("");
                    ieditText2.setText("");
                    ieditText3.setText("");
                    ieditText4.setText("");
                    ieditText5.setText("");


                });


                alertDialog = builderObj.create();
                alertDialog.show();

                //   data=view.findViewById(R.id.dataholder);
          //           adapter = ArrayAdapter.createFromResource(getContext(), R.array.queries_array, R.layout.support_simple_spinner_dropdown_item);
        //          adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

         ifbn = view.findViewById(R.id.FetchItemsSubmitButton);
ifbn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        setUpItemsModels();

        Items_RecyclerViewAdapter adapter = new Items_RecyclerViewAdapter(getContext(),itemsModels, onEditListener);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

       EshopDatabase db = Room.databaseBuilder(getContext(),EshopDatabase.class,"eshopBD").allowMainThreadQueries().build();
  MyDaoEshop myDaoEshop = db.myDaoEshop();
 recyclerView = view.findViewById(R.id.mRecyclerView);
        List<Items> items = MainActivity.myAppDatabaseEshop.myDaoEshop().getItems();


      String result= "";

         for(Items items1 :items){
          int id = items1.getIid();
           String name = items1.getName();
            String category = items1.getCategory();
           int fpa = items1.getFpa();
          int price = items1.getPrice();
            int count = items1.getCount();


        result = result + "\n Id: " + id + "\n Name: " + name + "\n Fpa: " + fpa + "\n Category: " + category+ name + "\n Price: " + price    + "\n Count: " + count   ;}
//data.setText(result);

    }});}

   
        
    public void editItems(String strItemsName, int strItemsPrice, int strItemsCount, int currentPosition) {
        ItemsModel obj = new ItemsModel();

        obj.setItemsName(strItemsName);
        obj.setItemsCount(strItemsCount);
        obj.setItemsPrice(strItemsPrice);

        items_recyclerViewAdapter.editData(obj, currentPosition);
        items_recyclerViewAdapter = new Items_RecyclerViewAdapter(getContext(), itemsModelArrayList, this::onEditClick);
        recyclerView.setAdapter(items_recyclerViewAdapter);


    }
} */