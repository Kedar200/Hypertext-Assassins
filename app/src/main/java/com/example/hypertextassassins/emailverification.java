package com.example.hypertextassassins;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class emailverification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emailverification);

        Button sendemail=findViewById(R.id.button2);
        Button logout=findViewById(R.id.button3);
        sendemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(emailverification.this,"Check your mailbox Or Spam Folder",Toast.LENGTH_SHORT).show();
                        Log.d("Hello", String.valueOf(FirebaseAuth.getInstance().getCurrentUser().isEmailVerified()));
                        FirebaseAuth.getInstance().signOut();

                        startActivity(new Intent(emailverification.this,Signup.class));
                        finish();
                    }
                });

            }
        });
    }
}