package com.example.hypertextassassins;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

//Fragment is a subactivity
public class SignupTabFragment extends Fragment {
    private static String TAG = "Hello";

    Button signup;
    FirebaseAuth auth;
    TextInputLayout Name, student_id, password, phone_number;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment, container, false);

        Name = root.findViewById(R.id.name);
        student_id = root.findViewById(R.id.email);
        password = root.findViewById(R.id.password);
        phone_number = root.findViewById(R.id.phone);
        signup = root.findViewById(R.id.signup);

        auth = FirebaseAuth.getInstance();


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sign_up();
                Log.d("Hello", Name.getEditText().getText().toString());
            }

        });


        return root;

    }

    public void sign_up() {
        String email = new String();
        String pass = new String();
        Intent Dashboard_intent = new Intent(getActivity(), dashboard.class);
        email = student_id.getEditText().getText().toString() + "@iiitvadodara.ac.in";
        Log.d("Hello", email);
        pass = password.getEditText().getText().toString();
        LoginActivity.a.setVisibility(View.VISIBLE);
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success");
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    User localuser = new User(student_id.getEditText().getText().toString(), Name.getEditText().getText().toString(), phone_number.getEditText().getText().toString());

                    db.collection("User").document(localuser.getName())
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
                    LoginActivity.a.setVisibility(View.INVISIBLE);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(Dashboard_intent);
                        }
                    }, 2000);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    LoginActivity.a.setVisibility(View.INVISIBLE);
                    Toast.makeText(getActivity(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}

