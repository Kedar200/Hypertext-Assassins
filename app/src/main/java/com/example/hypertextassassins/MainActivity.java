package com.example.hypertextassassins;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();
        Handler handler = new Handler();
        Intent Dashboard_intent = new Intent(this, dashboard.class);
        Intent Sign_in_intent=new Intent(this, LoginActivity.class);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        getSupportActionBar().hide();

        handler.postDelayed(new Runnable() {
            public void run() {
                if (currentUser!= null) {
                    Log.d("Hello", "Logged in");
                    startActivity(Dashboard_intent);
                    finish();
                } else {
                    Log.d("Hello", "Not Logged in");
                    startActivity(Sign_in_intent);
                    finish();
                }

            }
        }, 2000);

    }
}