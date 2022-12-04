package com.example.hypertextassassins;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;


public class complaints extends AppCompatActivity implements CustomAdapter.ItemClickListener {

    CustomAdapter adapter;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);

        // data to populate the RecyclerView with
        ArrayList<String> Date = new ArrayList<>();
        ArrayList<String> Type = new ArrayList<>();
        ArrayList<String> Description = new ArrayList<>();

        progressBar=findViewById(R.id.progressBar2);
        FirebaseUser mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String email = mFirebaseUser.getEmail(); //Do what you need to do with the id

        db.collection("Complaint").whereEqualTo("email",email)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d("Hello", document.getId() + " => " + document.getData());
                                Type.add(document.getData().get("type").toString());
                                Date.add(document.getData().get("date_of_complaint").toString());
                                Description.add(document.getData().get("description").toString());
                                Log.d("Hello",Type.toString());
                            }
                        } else {
                            Log.w("Hello", "Error getting documents.", task.getException());
                        }
                    }
                });

        // set up the
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                RecyclerView recyclerView = findViewById(R.id.rvAnimals);
                recyclerView.setLayoutManager(new LinearLayoutManager(complaints.this));



                adapter = new CustomAdapter(complaints.this, Type,Date,Description);
                adapter.setClickListener(complaints.this);
                progressBar.setVisibility(View.INVISIBLE);
                recyclerView.setAdapter(adapter);
            }
        },1000);

    }

    @Override
    public void onItemClick(View view, int position) {
//        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}