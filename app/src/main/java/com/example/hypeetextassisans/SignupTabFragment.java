package com.example.hypeetextassisans;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;

//Fragment is a subactivity
public class SignupTabFragment extends Fragment {
    EditText email,password,confirmPassword;
    Button signup;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment,container,false);

        email = root.findViewById(R.id.email);
        password = root.findViewById(R.id.password);
        confirmPassword = root.findViewById(R.id.Cnfpassword);
        signup = root.findViewById(R.id.signup);

        return root;
    }


}
