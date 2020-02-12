package com.mealsharing.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;


import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener
{
//    Firebase instance variable
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if (mFirebaseUser == null) {
            // Not signed in, launch the Sign In activity
            startActivity(new Intent(this, SignInActivity.class));
            finish();
            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Toast.makeText(this, "Inside login page", Toast.LENGTH_SHORT).show();

//            mUsername = mFirebaseUser.getDisplayName();
//            if (mFirebaseUser.getPhotoUrl() != null) {
//                mPhotoUrl = mFirebaseUser.getPhotoUrl().toString();
            }
        }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
