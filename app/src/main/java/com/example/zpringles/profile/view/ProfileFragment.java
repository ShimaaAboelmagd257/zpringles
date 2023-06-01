package com.example.zpringles.profile.view;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.zpringles.R;
import com.example.zpringles.Authantication.signup.view.SignupActivity;
import com.example.zpringles.DataBaseHandling.firebase.FirebaseSource;
import com.example.zpringles.DataBaseHandling.room.ConceretLocalSource;
import com.example.zpringles.DataBaseHandling.sharedpreference.SharedPreferenceSource;
import com.example.zpringles.model.modelFirebase.RepositoryFirebase;
import com.example.zpringles.model.modelFirebase.UserModel;
import com.example.zpringles.model.Repository;
import com.example.zpringles.NetworkConnection.APIClient;
import com.example.zpringles.profile.presenter.profilePresenter;
import com.example.zpringles.profile.presenter.profilePresenterInterface;
import com.example.zpringles.utalites.Helper;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment implements ProfileViewInterface , ProfileOnclickListener {

    CircleImageView profileImage, editImage;
    TextView userName, email, editProfile;
    EditText editUserName, currentPassword, newPassword, confirmPassword;
    ImageButton logout, editName, editPassword;

    Group group;

    String image = null;

    profilePresenterInterface profilePresenterInterface;

    boolean edit = false;

    UserModel userModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);

        profilePresenterInterface = new profilePresenter(RepositoryFirebase.getInstance(FirebaseSource.getInstance(getContext())
                , SharedPreferenceSource.getInstance(getContext()), getContext()), Repository.getInstance(APIClient.getInstance(getContext()),
                        ConceretLocalSource.getInstance(getContext()),getContext()));

        editProfile.setOnClickListener(event -> editProfile());

        editImage.setOnClickListener(event -> editImageOnClick());

        logout.setOnClickListener(event -> logoutOnClick());

        editName.setOnClickListener(event -> {
            editNameOnClick();
        });

        editPassword.setOnClickListener(event -> editPasswordOnClick());

        showData(getSavedUserData());

    }

    public void init(View view) {
        profileImage = view.findViewById(R.id.profileUserImage);
        editImage = view.findViewById(R.id.editProfileImage);
        userName = view.findViewById(R.id.profileName);
        email = view.findViewById(R.id.profileEmail);
        editUserName = view.findViewById(R.id.editTextPersonName);
        currentPassword = view.findViewById(R.id.current_password_edt);
        newPassword = view.findViewById(R.id.new_password_edt);
        confirmPassword = view.findViewById(R.id.confirm_new_password);
        logout = view.findViewById(R.id.log_out_btn);
        editName = view.findViewById(R.id.edit_name_Imagebutton);
        editPassword = view.findViewById(R.id.sava_change_password);
        editProfile = view.findViewById(R.id.editProfile);
        group = view.findViewById(R.id.groupEdit);

        userModel = new UserModel();
    }

    @Override
    public UserModel getSavedUserData() {
        return profilePresenterInterface.getSavedUserData();
    }

    @Override
    public void updateUserData(UserModel userModel) {
        profilePresenterInterface.updateUserData(userModel);
    }

    @Override
    public void updateUserFirebaseData(UserModel userModel) {
        profilePresenterInterface.updateUserFirebaseData(userModel);
    }

    @Override
    public void showData(UserModel userModel) {
        userName.setText(userModel.getUserName());
        email.setText(userModel.getEmail());
        editUserName.setText(userModel.getUserName());
        image = userModel.getImage();
        if (image.equals("null")) {
            profileImage.setImageResource(R.drawable.dfuser);
        } else {
            Glide.with(getContext()).load(Uri.parse(userModel.getImage())).into(profileImage);
        }
    }

    public void imageChooser() {

        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(i, "Select Picture"), 200);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == 200) {
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    profileImage.setImageURI(selectedImageUri);
                    image = selectedImageUri.toString();
                    userModel = getSavedUserData();
                    userModel.setImage(image);
                    updateUserData(userModel);
                    updateUserFirebaseData(userModel);
                    Glide.with(getView()).load(Uri.parse(userModel.getImage())).into(profileImage);
                    Toast.makeText(getContext(), "Your picture is updated", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }


    @Override
    public void logoutOnClick() {
        UserModel userModel = new UserModel();
        userModel.setUserName(null);
        userModel.setEmail(null);
        userModel.setPassWord(null);
        userModel.setImage(null);
        updateUserData(userModel);

        profilePresenterInterface.deleteAllMeals();

        Intent intent = new Intent(getContext(), SignupActivity.class);
        startActivity(intent);
        Toast.makeText(getContext(), "you logged out", Toast.LENGTH_SHORT).show();


        getActivity().finish();

    }
    @Override
    public void editNameOnClick() {
        userModel = getSavedUserData();
        userModel.setUserName(editUserName.getText().toString());
        userName.setText(editUserName.getText().toString());
        updateUserData(userModel);
        updateUserFirebaseData(userModel);
        Toast.makeText(getContext(), "Your userName is updated", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void editPasswordOnClick() {
        userModel = getSavedUserData();
        if(!currentPassword.getText().toString().equals(userModel.getPassWord())){
            Toast.makeText(getContext(), "You password is wrong", Toast.LENGTH_SHORT).show();
        }else if (!newPassword.getText().toString().matches(Helper.PASSWORD_PATTERN)){
            Toast.makeText(getContext(), "New password is invalid", Toast.LENGTH_SHORT).show();
        }else if (!newPassword.getText().toString().equals(confirmPassword.getText().toString())){
            Toast.makeText(getContext(), "New password and confirm password are not matched", Toast.LENGTH_SHORT).show();
        }else{
            userModel.setPassWord(confirmPassword.getText().toString());
            updateUserData(userModel);
            updateUserFirebaseData(userModel);
            Toast.makeText(getContext(), "Your password is updated", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void editImageOnClick() {
        if (Helper.SKIP == "skip") {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("Do you want to signup in application?");
            builder.setTitle("Alert !");
            builder.setCancelable(false);
            builder.setPositiveButton("yes, Signup", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(getContext(), SignupActivity.class);
                    getContext().startActivity(intent);
                    ((Activity) getContext()).finish();
                }
            });

            builder.setNegativeButton("No, thanks", (DialogInterface.OnClickListener) (dialog, which) -> {
                dialog.cancel();
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        } else {
            ArrayList<String> MemberList = new ArrayList<>();
            MemberList.add("Upload Image");
            MemberList.add("Delete Image");
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setItems(MemberList.toArray(new String[2]), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    if (i == 0) {
                        imageChooser();

                    } else {
                        image = null;
                        userModel = getSavedUserData();
                        userModel.setImage(image);
                        updateUserData(userModel);
                        updateUserFirebaseData(userModel);
                        profileImage.setImageResource(R.drawable.dfuser);
                        Toast.makeText(getContext(), "Your picture is deleted", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }
    }

    @Override
    public void editProfile() {
        if(Helper.SKIP == "skip"){
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("Do you want to signup in application?");
            builder.setTitle("Alert !");
            builder.setCancelable(false);
            builder.setPositiveButton("yes, Signup", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(getContext(), SignupActivity.class);
                    getContext().startActivity(intent);
                    ((Activity)getContext()).finish();
                }
            });

            builder.setNegativeButton("No, thanks", (DialogInterface.OnClickListener) (dialog, which) -> {
                dialog.cancel();
            });

            AlertDialog alertDialog = builder.create();
                alertDialog.show();
        }else {
            if (edit) {
                edit = false;
                group.setVisibility(View.GONE);
            } else {
                edit = true;
                group.setVisibility(View.VISIBLE);
            }
        }
    }
}