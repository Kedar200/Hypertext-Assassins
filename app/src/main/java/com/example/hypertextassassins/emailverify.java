package com.example.hypertextassassins;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class emailverify extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseAuth.getInstance().getCurrentUser().reload();

        if(FirebaseAuth.getInstance().getCurrentUser().isEmailVerified()){
            startActivity(new Intent(emailverify.this,dashboard.class));
        }
        setContentView(R.layout.activity_emailverify);
    }
}