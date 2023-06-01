package com.example.zpringles.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.view.View;

import com.example.zpringles.R;
import com.example.zpringles.utalites.Helper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavView;
    FloatingActionButton floatingActionButton;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingActionButton=findViewById(R.id.floatingButton);
        getSupportActionBar().hide();
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
        bottomNavView=findViewById(R.id.bottomNavView);
        bottomNavView.setBackground(null);

        if(Helper.SKIP=="skip"){
            floatingActionButton.setEnabled(false);
            bottomNavView.getMenu().getItem(2).setEnabled(false);
            bottomNavView.getMenu().getItem(3).setEnabled(false);
        }
        else if(!Helper.isNetworkAvailable(this)){
            bottomNavView.getMenu().getItem(4).setEnabled(false);
        }
        new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(@NonNull Network network) {
                super.onAvailable(network);
                bottomNavView.getMenu().getItem(4).setEnabled(true);
                System.out.println("There is internet");

            }

            @Override
            public void onLost(@NonNull Network network) {
                super.onLost(network);
                System.out.println("There is no internet");
                bottomNavView.getMenu().getItem(4).setEnabled(false);
            }

            @Override
            public void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities networkCapabilities) {
                super.onCapabilitiesChanged(network, networkCapabilities);
            }
        };



        floatingActionButton.setOnClickListener(v->{
            NavController navController1 = Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment);
            navController1.navigateUp();
            navController1.navigate(R.id.planMealsFragment);
        });

        NavController navController= Navigation.findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavView,navController);
        bottomNavView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_host_fragment);
            }
        });



    }
    void init(){
        new NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .build();
    }


}
