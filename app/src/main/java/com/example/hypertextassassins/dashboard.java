package com.example.hypertextassassins;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;

public class dashboard extends AppCompatActivity {

    Intent Sign_in_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        Sign_in_intent=new Intent(this, Signup.class);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        final DrawerLayout drawerLayout = findViewById(R.id.drawerlayout);

        findViewById(R.id.dehaze).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        LottieAnimationView assassin;
        assassin= findViewById(R.id.lottieAnimationView);
        assassin.animate().setDuration(3000);

        findViewById(R.id.imageView2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(Sign_in_intent);
                finish();
            }
        });
    }

}