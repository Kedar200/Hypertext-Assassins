package com.example.hypertextassassins;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.OffsetTime;

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

        TextView greating=findViewById(R.id.greetings);
        OffsetTime offset = OffsetTime.now();
        Log.d("Hello",String.valueOf(offset.getHour()));
        if(offset.getHour()>6&&offset.getHour()<12){
            greating.setText("Good Morning");
        }
        else if(offset.getHour()>=12&&offset.getHour()<=16){
            greating.setText("Good Afternoon");
        }
        else if(offset.getHour()>=16){
            greating.setText("Good Evening");
        }
        else{
            greating.setText("Time To Sleep");
        }

        String email=FirebaseAuth.getInstance().getCurrentUser().getEmail();
        String id=email.substring(0,9);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("User").document(id);

        TextView wc=findViewById(R.id.Welcomeback);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("Hello", "DocumentSnapshot data: "+id + document.getData());

                        wc.setText(wc.getText()+" "+document.getData().get("name"));

                    } else {
                        Log.d("Hello", "No such document");
                    }
                } else {
                    Log.d("Hello", "get failed with ", task.getException());
                }
            }
        });
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