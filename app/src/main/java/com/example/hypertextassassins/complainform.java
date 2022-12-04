package com.example.hypertextassassins;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class complainform extends AppCompatActivity {

    EditText room,complaint_dec;
    String a;
    private SimpleDateFormat dateFormat;
    private String date;
    TextView done;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hostel_form);
        getSupportActionBar().hide();
        a=getIntent().getStringExtra("type");
        Log.d("Hello",a);
        room=findViewById(R.id.Room);
        complaint_dec=findViewById(R.id.compalint);
        done = findViewById(R.id.done);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });
    }
    public void submit(){

        final AlertDialog.Builder alert = new AlertDialog.Builder(complainform.this);
        View mview = getLayoutInflater().inflate(R.layout.dialog_finish,null);
        TextView done = mview.findViewById(R.id.done);
        alert.setView(mview);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        Toast.makeText(complainform.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                back();
            }
        });
        alertDialog.show();
        String room_no=room.getText().toString();
        String complaint_dec_str=complaint_dec.getText().toString();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date = dateFormat.format(calendar.getTime());
        Log.d("Hello",date);
        Complaint comp=new Complaint(FirebaseAuth.getInstance().getCurrentUser().getEmail(),String.valueOf(System.nanoTime()),complaint_dec_str,a,"Pending",date,room_no);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Complaint").document(String.valueOf(System.nanoTime())).set(comp)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Log.d("Hello", "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(complainform.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                        Log.w("Hello", "Error writing document", e);
                    }
                });
    }

    public void back(){
        Intent intent = new Intent(this,hostelmanagement.class);
        startActivity(intent);
    }
}
