package com.example.hypertextassassins;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class Signup extends AppCompatActivity {
    private static String  TAG = "Hello";

    Button signup;
    FirebaseAuth auth;
    TextInputLayout Name,student_id,password,phone_number;
    boolean para;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        getSupportActionBar().hide();

        Name = findViewById(R.id.name);
        student_id =findViewById(R.id.email);
        password = findViewById(R.id.password);
        phone_number=findViewById(R.id.phone);
        signup = findViewById(R.id.signup);

        auth = FirebaseAuth.getInstance();

        student_id.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(student_id.getEditText().getText().length()<9){

                    student_id.setError("Student Id Can't be less than 9");
                    signup.setEnabled(false);
                }
                else if(student_id.getEditText().getText().length()==9){
                    student_id.setErrorEnabled(false);
                    password.getEditText().addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            if(password.getEditText().getText().length()<6){

                                password.setError("Password Can't be less than 6");
                                signup.setEnabled(false);
                            }
                            else{
                                password.setErrorEnabled(false);
                                signup.setEnabled(true);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {

                        }
                    });

                }
                else{
                    student_id.setError("Student Id Can't be greater than 9");
                    signup.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        findViewById(R.id.sign_in).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Signup.this,Login.class));
                finish();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Name.getEditText().getText()!=null && password.getEditText().getText().length()>=6 && student_id.getEditText().getText().length()==9 && phone_number.getEditText().getText().length()==10)
                sign_up();
                else{
                    Toast.makeText(Signup.this,"Please Fill Form Correctly",Toast.LENGTH_SHORT).show();
                }
                Log.d("Hello",Name.getEditText().getText().toString());
            }

        });


    }

    public void sign_up(){
        String email = new String();
        String pass = new String();
        Intent Dashboard_intent=new Intent(this,dashboard.class);
        email =student_id.getEditText().getText().toString()+"@iiitvadodara.ac.in";
        Log.d("Hello",email);
        pass =password.getEditText().getText().toString();

        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success");
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Log.d(TAG, "Hello");
                                Toast.makeText(Signup.this,"Check Your Mail ",Toast.LENGTH_SHORT).show();
                            }
                        });

                    User localuser = new User(student_id.getEditText().getText().toString(),Name.getEditText().getText().toString(),phone_number.getEditText().getText().toString());

                    db.collection("User").document(localuser.getStudent_id())
                            .set(localuser)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    Log.d(TAG, "DocumentSnapshot successfully written!");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(Exception e) {
                                    Log.w(TAG, "Error writing document", e);
                                }
                            });
                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(Signup.this,emailverify.class));
                            finish();
                        }
                    },2000);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(Signup.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
