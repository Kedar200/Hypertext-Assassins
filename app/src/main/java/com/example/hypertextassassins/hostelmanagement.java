package com.example.hypertextassassins;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class hostelmanagement extends AppCompatActivity {
    private ImageButton carpenterbutton;
    private ImageButton elctricianbutton;
    private ImageButton cleanerbutton;
    private ImageButton plumberbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hm_mainpage);
//        getSupportActionBar().hide();
        carpenterbutton=findViewById(R.id.carpenterbutton);
        plumberbutton=findViewById(R.id.plumberbutton);
        elctricianbutton=findViewById(R.id.electricianbutton);
        cleanerbutton=findViewById(R.id.cleanerbutton);

        getSupportActionBar().hide();
        carpenterbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openform("Carpenter");
            }

        });
        plumberbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openform("Plumber");
            }

        });
        cleanerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openform("Cleaner");
            }

        });
        elctricianbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openform("Electrician");
            }

        });
    }

    public void openform(String a)
    {
        Intent intent=new Intent(this,complainform.class);
        intent.putExtra("type",a);
        startActivity(intent);
    }



}
