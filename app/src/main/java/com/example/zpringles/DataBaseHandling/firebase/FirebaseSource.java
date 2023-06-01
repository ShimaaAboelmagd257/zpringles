package com.example.zpringles.DataBaseHandling.firebase;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.zpringles.DataBaseHandling.sharedpreference.SharedPreferenceSource;
import com.example.zpringles.home.home.view.HomePageFragment;
import com.example.zpringles.model.modelFirebase.UserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FirebaseSource implements FirebaseSourseInterface  {

    private static FirebaseSource firebaseSource = null;
    private DatabaseReference databaseReference;

    private boolean exists ;
    private boolean  isSucced=false;

    private FirebaseSource(Context context) {
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public static FirebaseSource getInstance(Context context) {

        if (firebaseSource == null) {
            firebaseSource = new FirebaseSource(context);
        }
        return firebaseSource;
    }

    @Override
    public void insertUser(UserModel userModel) {
        List<String> route = Arrays.asList(userModel.getEmail().split("\\."));
        databaseReference.child("User").child(route.get(0)).setValue(userModel);
    }


    @Override
    public boolean isUserExists(UserModel userModel) {

        List<String> route = Arrays.asList(userModel.getEmail().split("\\."));
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("User");


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                UserModel userModel1= dataSnapshot.child(route.get(0)).getValue(UserModel.class);
                if (userModel1 != null) {
                    if (userModel1.getEmail().equals(userModel.getEmail())) {
                        exists = false;
                    }
                }else {

                    System.out.println(exists);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        return exists;
    }

    @Override
    public boolean isLoginSuccessed(Context context,String email, String pass) {
        String[]splitEmail=email.split("\\.");
        String root=splitEmail[0];

        DatabaseReference databaseReference1 =FirebaseDatabase.getInstance()
                .getReference("User");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                UserModel userModel1=snapshot.child(root).getValue(UserModel.class);
                if(userModel1!=null){
                    if(userModel1.getPassWord().equals(pass)
                            &&userModel1.getEmail().equals(email)){
                        isSucced = true;

                        showdataFromFirebase(userModel1);
                        SharedPreferenceSource.getInstance(context).saveUserData(userModel1);
                        System.out.println("Successed");

                    }else {
                        isSucced=false;
                    }
                }else {
                    System.out.println("Data is invalid ");
                    isSucced=false;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Data is invalid OnCancelled");
                isSucced=false;
            }
        });
        return isSucced;
    }

    public void showdataFromFirebase(UserModel userModel1){
        if(userModel1.getFavorites()!=null) {
            HomePageFragment.favorite = userModel1.getFavorites();
        }
        int i=0;
        HomePageFragment.plans = new ArrayList<>();
        if(userModel1.getFriday()!= null) {
            HomePageFragment.plans.add(userModel1.getFriday());
        }
        if (userModel1.getSaturday() !=null) {
            HomePageFragment.plans.add(userModel1.getSaturday());
        }
        if (userModel1.getSunday() !=null) {
            HomePageFragment.plans.add(userModel1.getSunday());
        }
        if (userModel1.getMonday() !=null) {
            HomePageFragment.plans.add(userModel1.getMonday());
        }
        if (userModel1.getThursday() !=null ) {
            HomePageFragment.plans.add(userModel1.getThursday());
        }
        if (userModel1.getWednesday() !=null ) {
            HomePageFragment.plans.add(userModel1.getWednesday());
        }
        if (userModel1.getThursday() !=null) {
            HomePageFragment.plans.add(userModel1.getThursday());
        }
    }

}