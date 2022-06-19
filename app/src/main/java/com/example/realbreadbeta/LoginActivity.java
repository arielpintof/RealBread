package com.example.realbreadbeta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.realbreadbeta.adaptador.LoginAdapter;
import com.google.android.material.tabs.TabLayout;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TabLayout unTabLayout = findViewById(R.id.tab_layout);
        ViewPager unViewPager = findViewById(R.id.viewpager2);


        unTabLayout.addTab(unTabLayout.newTab().setText("Iniciar Sesión"));
        unTabLayout.addTab(unTabLayout.newTab().setText("Registrarse"));
        unTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final LoginAdapter unAdaptador = new LoginAdapter(getSupportFragmentManager(),this, unTabLayout.getTabCount());
        unTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(unViewPager));
        unViewPager.setAdapter(unAdaptador);
        unViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(unTabLayout));

    }

}