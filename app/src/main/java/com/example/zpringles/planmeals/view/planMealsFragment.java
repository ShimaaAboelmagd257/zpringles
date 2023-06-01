package com.example.zpringles.planmeals.view;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zpringles.R;
import com.example.zpringles.DataBaseHandling.firebase.FirebaseSource;
import com.example.zpringles.DataBaseHandling.room.ConceretLocalSource;
import com.example.zpringles.DataBaseHandling.sharedpreference.SharedPreferenceSource;
import com.example.zpringles.model.MealModel;
import com.example.zpringles.model.modelFirebase.RepositoryFirebase;
import com.example.zpringles.model.modelFirebase.UserModel;
import com.example.zpringles.model.retrofit.Repository;
import com.example.zpringles.NetworkConnection.APIResponse;
import com.example.zpringles.planmeals.presenter.PlanMealsPresenter;
import com.example.zpringles.planmeals.presenter.PlanPresenterInterface;
import com.example.zpringles.utalites.Helper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class planMealsFragment extends Fragment implements OnPlanClickListner,OnPlanViewListner{

    LinearLayoutManager[] linearLayoutManager=new LinearLayoutManager[7];
    RecyclerView []recyclerView=new RecyclerView[7];

    RecyclePlanAdapter[] recyclePlanAdapter=new RecyclePlanAdapter[7];

    PlanPresenterInterface planPresenterInterface;
    String [] daysWeak;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_plan_meals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         daysWeak=getResources().getStringArray(R.array.weekdays);

        Init(view);

        planPresenterInterface = new PlanMealsPresenter((Repository.getInstance(APIResponse.getInstance(getContext()),
                ConceretLocalSource.getInstance(getContext()),getContext()))
                , RepositoryFirebase.getInstance(FirebaseSource.getInstance(getContext())
                , SharedPreferenceSource.getInstance(getContext()),getContext()));


          getPlan(0);
          getPlan(1);
          getPlan(2);
          getPlan(3);
          getPlan(4);
          getPlan(5);
          getPlan(6);

    }
    void Init(View view){
        recyclerView[0]= view.findViewById(R.id.recyclerSaturday);
        recyclerView[1] = view.findViewById(R.id.recyclerSunday);
        recyclerView[2] = view.findViewById(R.id.recyclerMonday);
        recyclerView[3] = view.findViewById(R.id.recyclerTuesday);
        recyclerView[4] = view.findViewById(R.id.recyclerWednesday);
        recyclerView[5] = view.findViewById(R.id.recyclerThursday);
        recyclerView[6] = view.findViewById(R.id.recyclerFriday);
        linearLayoutManager[0]=new LinearLayoutManager(view.getContext());
        linearLayoutManager[1]=new LinearLayoutManager(view.getContext());
        linearLayoutManager[2]=new LinearLayoutManager(view.getContext());
        linearLayoutManager[3]=new LinearLayoutManager(view.getContext());
        linearLayoutManager[4]=new LinearLayoutManager(view.getContext());
        linearLayoutManager[5]=new LinearLayoutManager(view.getContext());
        linearLayoutManager[6]=new LinearLayoutManager(view.getContext());
        recyclePlanAdapter[0] = new RecyclePlanAdapter(view.getContext(),new ArrayList<>(),this);
        recyclePlanAdapter[1] = new RecyclePlanAdapter(view.getContext(),new ArrayList<>(),this);
        recyclePlanAdapter[2] = new RecyclePlanAdapter(view.getContext(),new ArrayList<>(),this);
        recyclePlanAdapter[3] = new RecyclePlanAdapter(view.getContext(),new ArrayList<>(),this);
        recyclePlanAdapter[4]= new RecyclePlanAdapter(view.getContext(),new ArrayList<>(),this);
        recyclePlanAdapter[5] = new RecyclePlanAdapter(view.getContext(),new ArrayList<>(),this);
        recyclePlanAdapter[6] = new RecyclePlanAdapter(view.getContext(),new ArrayList<>(),this);

    }

    @Override
    public void onRemovePlanClicked(MealModel mealModel) {
        removeMealFromPlaned(mealModel);
    }

    @Override
    public void addToFavoriteOnClick(MealModel mealModel) {
        addToFavorite(mealModel);
    }

    @Override
    public void removeMealFromPlaned(MealModel meal) {
        planPresenterInterface.removeFromPlan(meal);
        Toast.makeText(getContext(), "Meal is added to plan", Toast.LENGTH_SHORT).show();
        updateData(0,meal);
        updateData(1,meal);
        updateData(2,meal);
        updateData(3,meal);
        updateData(4,meal);
        updateData(5,meal);
        updateData(6,meal);
    }

    @Override
    public void ViewData(List<MealModel> Meals) {

    }

    @Override
    public void uploadPlanInFirebase(UserModel userModel) {
        planPresenterInterface.uploadPlanInFirebase(userModel);
    }

    @Override
    public void addToFavorite(MealModel mealModel) {
        planPresenterInterface.addToFavorite(mealModel);
    }

    public void updateData(int i,MealModel meal){
        recyclePlanAdapter[i].removePlan(meal);
        recyclePlanAdapter[i].notifyDataSetChanged();
    }

    public void getPlan(int i){
        planPresenterInterface.getAllPlanedMeals(daysWeak[i]).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorComplete()
                .subscribe(e->{
                    recyclerView[i].setHasFixedSize(true);
                    recyclerView[i].setAdapter(recyclePlanAdapter[i]);
                    linearLayoutManager[i].setOrientation(RecyclerView.HORIZONTAL);
                    recyclerView[i].setLayoutManager(linearLayoutManager[i]);
                    System.out.println("The size of :"+e.size());
                    recyclePlanAdapter[i].setRecyclePlanAdapterList(e);
                    recyclePlanAdapter[i].notifyDataSetChanged();
                    uploadToPlanTFire(daysWeak[i],e);

                });
    }
  void uploadToPlanTFire(String plan,List<MealModel> planList){
      DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
      SharedPreferences sharedPreferences = getContext().getSharedPreferences(Helper.SHARDPREFERENCE,getContext().MODE_PRIVATE);
      SharedPreferences.Editor editor = sharedPreferences.edit();
      UserModel userModel = new UserModel();
      userModel.setUserName(sharedPreferences.getString(Helper.USERNAME,"Null"));
      userModel.setEmail(sharedPreferences.getString(Helper.EMAIL,"Null"));
      userModel.setPassWord(sharedPreferences.getString(Helper.PASSWORD,"Null"));
      userModel.setImage(sharedPreferences.getString(Helper.IMAGE,"null"));
      List<String> route = Arrays.asList(userModel.getEmail().split("\\."));
      databaseReference.child("User").child(route.get(0)).child(plan).setValue(planList);
  }

}