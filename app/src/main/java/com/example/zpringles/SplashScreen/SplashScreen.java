package com.example.zpringles.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.zpringles.R;
import com.example.zpringles.Authantication.signup.view.SignupActivity;
import com.example.zpringles.utalites.Helper;
import com.example.zpringles.view.MainActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();

//        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(Utalites.SHARDPREFERENCE,getApplicationContext().MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(Utalites.USERNAME,null);
//        editor.putString(Utalites.EMAIL,null);
//        editor.putString(Utalites.PASSWORD,null);
//        editor.commit();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences(Helper.SHARDPREFERENCE,getApplicationContext().MODE_PRIVATE);
                if(sharedPreferences.getString(Helper.EMAIL,null) == null) {
                    Intent intent = new Intent(SplashScreen.this, SignupActivity.class);
                    startActivity(intent);
                }else{
                    System.out.println(sharedPreferences.getString(Helper.EMAIL,null));
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        },6000);
    }
}