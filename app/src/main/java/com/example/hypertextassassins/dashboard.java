package com.example.hypertextassassins;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.OffsetTime;

import io.grpc.okhttp.internal.framed.Header;

public class dashboard extends AppCompatActivity{

    Intent Sign_in_intent;
    DrawerLayout drawer_layout;
    NavigationView navigationView;

    TextView greating,user,student_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);
        Sign_in_intent=new Intent(this, Signup.class);

        drawer_layout=findViewById(R.id.drawerlayout);
        user=findViewById(R.id.username);
        student_id=findViewById(R.id.student_id);


       getSupportActionBar().hide();

        navigationView =(NavigationView) findViewById(R.id.navigation);
        View headerView=navigationView.getHeaderView(0);

        user=(TextView)headerView.findViewById(R.id.username);
        student_id=(TextView)headerView.findViewById(R.id.student_id);
        findViewById(R.id.toogle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!drawer_layout.isDrawerOpen(GravityCompat.START)){
                drawer_layout.openDrawer(GravityCompat.START);
                }
                else{
                    drawer_layout.openDrawer(GravityCompat.START);
                }
            }
        });
        greating=findViewById(R.id.greetings);
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

        FirebaseUser mFirebaseUser =FirebaseAuth.getInstance().getCurrentUser();
        String email = mFirebaseUser.getEmail(); //Do what you need to do with the id
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
                        Log.d("Hello", "DocumentSnapshot data: "+id + document.getData().get("name").toString());
                        user.setText(document.getData().get("name").toString());
                        student_id.setText(document.getData().get("student_id").toString());
                    } else {
                        Log.d("Hello", "No such document");
                    }
                } else {
                    Log.d("Hello", "get failed with ", task.getException());
                }
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.hostelmanagement:
                        startActivity(new Intent(dashboard.this , hostelmanagement.class));
                        break;

                    case R.id.home:
                        drawer_layout.close();
                        break;


                    case R.id.your_complaint:
                        startActivity(new Intent(dashboard.this,complaints.class));
                        break;

                    case R.id.logout:
                        Log.d("Hello","Signed out");
                        FirebaseAuth.getInstance().signOut();
                        startActivity(Sign_in_intent);
                        finish();
                        break;
                }
                drawer_layout.close();
                return false;
            }
        });

    }




}