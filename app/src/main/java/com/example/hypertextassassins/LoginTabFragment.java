package com.example.hypertextassassins;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginTabFragment extends Fragment {
    TextInputLayout studentid,password;
    TextView forgetpasss;
    Button login;
    FirebaseAuth auth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment,container,false);

        studentid = root.findViewById(R.id.email);
        password = root.findViewById(R.id.password);
        login = root.findViewById(R.id.login);
        auth=FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
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
        LoginActivity.a.setVisibility(View.VISIBLE);
        Intent Dashboard_intent=new Intent(getActivity(),dashboard.class);
        email=studentid.getEditText().getText().toString()+"@iiitvadodara.ac.in";
        pass =password.getEditText().getText().toString();
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified()){
                        startActivity(Dashboard_intent);
                    }
                    else{
                        user.sendEmailVerification();
                        Toast.makeText(getActivity(),"Check Your mail box to verify email",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    LoginActivity.a.setVisibility(View.INVISIBLE);
                    Toast.makeText(getActivity(),"Something went wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}