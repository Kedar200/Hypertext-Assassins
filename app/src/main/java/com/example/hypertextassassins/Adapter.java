package com.example.hypertextassassins;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class Adapter extends FragmentStateAdapter {
    private String[] titles = new String[]{"Log In","Sign Up"};

    public Adapter(@NonNull LoginActivity fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
//                LoginTabFragment loginTabFragment = new LoginTabFragment();
                return new LoginTabFragment();

            case 1:
//                SignupTabFragment signupTabFragment = new SignupTabFragment();
                return new SignupTabFragment();

            default:
                return new LoginTabFragment();
        }
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}
