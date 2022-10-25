package com.example.hypeetextassisans;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class LoginActivity extends AppCompatActivity {
    PagerAdapter pagerAdapter;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    FloatingActionButton google;

    private String[] titles = new String[]{"Log In","Sign Up"};
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.viewpage);
        google = findViewById(R.id.google);

        pagerAdapter = new PagerAdapter(this);
        viewPager2.setAdapter(pagerAdapter);

        new TabLayoutMediator(tabLayout,viewPager2,((tab, position) -> tab.setText(titles[position]))).attach();
    }
}