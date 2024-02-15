package com.example.blue7;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.example.blue7.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import org.checkerframework.checker.nullness.qual.NonNull;


public class MainActivity extends AppCompatActivity {
    private static String chid;
    private static final String CHANNEL_ID = chid;
    public static EshopDatabase myAppDatabaseEshop;

    public static EshopDatabase db;
    public static FirebaseFirestore fbdb;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private FragmentManager fragmentManager;
    private void createNotificationChannel() {


        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager =  getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragmentManager = getSupportFragmentManager();
        myAppDatabaseEshop = Room.databaseBuilder(getApplicationContext(),EshopDatabase.class,"eshopBD").allowMainThreadQueries().fallbackToDestructiveMigration().build();


        fbdb=FirebaseFirestore.getInstance();

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment_content_main).navigate(R.id.nav_home);

            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_items, R.id.nav_companies,  R.id.nav_transactions, R.id.nav_FBItems, R.id.nav_FBClients, R.id.nav_FBSales)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(item -> {
            drawer.closeDrawer(GravityCompat.START);
            int id = item.getItemId();
            if (id == R.id.nav_companies) {
                Navigation.findNavController(this, R.id.nav_host_fragment_content_main).navigate(R.id.nav_companies);

            } else if (id == R.id.nav_items) {
                Navigation.findNavController(this, R.id.nav_host_fragment_content_main).navigate(R.id.nav_items);

            } else if (id == R.id.nav_transactions) {
                Navigation.findNavController(this, R.id.nav_host_fragment_content_main).navigate(R.id.nav_transactions);

            } else if (id == R.id.nav_FBItems) {
                Navigation.findNavController(this, R.id.nav_host_fragment_content_main).navigate(R.id.nav_FBItems);

            } else if (id == R.id.nav_FBSales) {
                Navigation.findNavController(this, R.id.nav_host_fragment_content_main).navigate(R.id.nav_FBSales);

            } else if (id == R.id.nav_FBClients) {
                Navigation.findNavController(this, R.id.nav_host_fragment_content_main).navigate(R.id.nav_FBClients);

            } else if (id == R.id.nav_home) {
                Navigation.findNavController(this, R.id.nav_host_fragment_content_main).navigate(R.id.nav_home);

            }
            return false;
        });



        //    RecyclerView recyclerView = findViewById(R.id.mRecyclerView);



        //    setUpItemsModels();

        //  Items_RecyclerViewAdapter adapter = new Items_RecyclerViewAdapter(this,itemsModels);

        //  recyclerView.setAdapter(adapter);
        //  recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        NavController navController = Navigation.findNavController(this,
                R.id.nav_host_fragment_content_main);

        // By calling onNavDestinationSelected(), you always get the right behavior
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item);
    }





    protected void allocateActivityTitle(String titleString) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(titleString);
        }
    }}






