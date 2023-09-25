package com.example.hypertextassassins;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    String TAG="Hello";
    LottieAnimationView assassin;
    TextView logoheader;
    FirebaseDatabase database;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();
        Handler handler = new Handler();
        Intent Dashboard_intent = new Intent(this, dashboard.class);
        Intent Sign_in_intent=new Intent(this, Signup.class);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        getSupportActionBar().hide();
        logoheader=findViewById(R.id.logoheader);
        assassin= findViewById(R.id.assassin);
        assassin.animate().setDuration(3000);
        fadee();
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        Log.d("Hello",token.toString());
                    }
                });
        FirebaseMessaging.getInstance().subscribeToTopic("weather")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Subscribed";
                        if (!task.isSuccessful()) {
                            msg = "Subscribe failed";
                        }
                        Log.d(TAG, msg);
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
        Handler h=new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (currentUser!= null) {
                    if(currentUser.isEmailVerified()){

                        Log.d(TAG, "Logged in");
                        startActivity(new Intent(MainActivity.this,dashboard.class));
                        finish();
                    }else{
                        startActivity(new Intent(MainActivity.this,emailverification.class));
                        finish();

                    }
                }


                else {
                    Log.d(TAG, "Not Logged in");
                    startActivity(new Intent(MainActivity.this,Signup.class));
                    finish();
                }
            }
        },3000);



    }


    private void fadee() {
        logoheader.animate().translationY(100).setDuration(3000);
        logoheader.setVisibility(View.VISIBLE);
    }}