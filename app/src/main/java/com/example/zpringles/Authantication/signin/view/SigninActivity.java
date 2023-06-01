package com.example.zpringles.Authantication.signin.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zpringles.R;
import com.example.zpringles.Authantication.signin.presenter.SigninPresenter;
import com.example.zpringles.Authantication.signin.presenter.SigninPresenterInterface;
import com.example.zpringles.DataBaseHandling.firebase.FirebaseSource;
import com.example.zpringles.DataBaseHandling.sharedpreference.SharedPreferenceSource;
import com.example.zpringles.model.modelFirebase.RepositoryFirebase;
import com.example.zpringles.model.modelFirebase.UserModel;
import com.example.zpringles.view.MainActivity;

public class SigninActivity extends AppCompatActivity implements SigninOnclickListener,SignInViewInterface {

    EditText email_edt,passWord_edt;
    Button login_btn;
    SigninPresenterInterface signinPresenterInterface;

    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        getSupportActionBar().hide();
        init();
        signinPresenterInterface=new SigninPresenter(this, RepositoryFirebase.getInstance(FirebaseSource.getInstance(this)
                , SharedPreferenceSource.getInstance(this),this));

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userEmail=email_edt.getText().toString();
                if(email_edt.getText().toString().equals("")||email_edt.getText().toString().equals(null)){
                    Toast.makeText(SigninActivity.this, "You should fill all data", Toast.LENGTH_SHORT).show();
                }else if(passWord_edt.getText().toString().equals("")||passWord_edt.getText().toString().equals(null)){
                    Toast.makeText(SigninActivity.this, "You should fill all data", Toast.LENGTH_SHORT).show();
                }else {
                    onLoginClicked();
                }
            }
        });

    }


    private void init(){
        email_edt=findViewById(R.id.editTextEmailLogin);
        passWord_edt=findViewById(R.id.editTextPasswordLogin);
        login_btn=findViewById(R.id.btnLogin);
    }


    @Override
    public void onLoginSuccess(UserModel userModel) {
        signinPresenterInterface.addUserDataToShered(userModel);
    }

    @Override
    public boolean isSuccessed(Context context, String email, String pass) {
        return signinPresenterInterface.checkUserData(context,email,pass);
    }

    @Override
    public void onLoginClicked() {
        if(isSuccessed(getApplicationContext(),email_edt.getText().toString(),passWord_edt.getText().toString())){
            Toast.makeText(SigninActivity.this, "Login succced.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SigninActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        }else{
            Toast.makeText(SigninActivity.this, "Data invalid.", Toast.LENGTH_SHORT).show();
        }
    }
}