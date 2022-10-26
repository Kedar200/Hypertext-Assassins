package com.example.hypertextassassins;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class IntroductoryActivity extends AppCompatActivity {
    ImageView bg,logo;
    TextView appname;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory);

        bg = findViewById(R.id.bg);
        logo = findViewById(R.id.logo);
        appname = findViewById(R.id.appname);
        button = findViewById(R.id.button);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        bg.animate().translationY(-1600).setDuration(1000).setStartDelay(4000);
        logo.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
        appname.animate().translationY(1400).setDuration(1000).setStartDelay(4000);

        LottieAnimationView v=findViewById(R.id.animationView);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v.cancelAnimation();
            }
        });

    }
    public void openActivity(View v){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


}