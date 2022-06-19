package com.example.realbreadbeta.adaptador;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.realbreadbeta.IniciarSesionFragment;
import com.example.realbreadbeta.RegistrarseFragment;

public class LoginAdapter extends FragmentPagerAdapter {

    private Context context;
    int totalTabs;

    public LoginAdapter(FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

    public Fragment getItem(int position) {
       switch(position){
           case 0:
               IniciarSesionFragment unIniciarSesionFragment = new IniciarSesionFragment();
               return unIniciarSesionFragment;
           case 1:
               RegistrarseFragment unRegistrarseFragment = new RegistrarseFragment();
               return unRegistrarseFragment;
           default:
               return null;
       }
    }


}
