package com.example.hypertextassassins;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//Fragment is a subactivity
public class SignupTabFragment extends Fragment {
    private static String  TAG = "Hello";
    EditText student_id,password,Name,phone_number;
    Button signup;
    FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment,container,false);

        student_id = root.findViewById(R.id.email);
        password = root.findViewById(R.id.password);
        Name = root.findViewById(R.id.name);
        phone_number=root.findViewById(R.id.phone);
        signup = root.findViewById(R.id.signup);

        auth = FirebaseAuth.getInstance();





        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sign_up();
            }
        });


        return root;

    }

public void sign_up(){
    String email = new String();
    String pass = new String();
    Intent Dashboard_intent=new Intent(getActivity(),dashboard.class);
    email =student_id.getText().toString()+"@iiitvadodaa.ac.in";
    pass =password.getText().toString();
    LoginActivity.a.setVisibility(View.VISIBLE);
    auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success");

                        FirebaseUser user = auth.getCurrentUser();
                        DatabaseReference database=FirebaseDatabase.getInstance().getReference();
                        User localuser = new User(student_id.getText().toString(),Name.getText().toString(),phone_number.getText().toString());
                        database.child("Users").child(user.getUid()).setValue(localuser);
                        LoginActivity.a.setVisibility(View.INVISIBLE);
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                            startActivity(Dashboard_intent);

                            }
                        },2000);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(getActivity(), "Authentication failed.",Toast.LENGTH_SHORT).show();
                    }
                }
            });

}}

