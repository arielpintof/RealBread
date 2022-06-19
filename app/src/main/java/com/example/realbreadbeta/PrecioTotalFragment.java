package com.example.realbreadbeta;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class PrecioTotalFragment extends Fragment {
    

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_precio_total,container,false);
        Button botonPagar = view.findViewById(R.id.boton_pagar);
        TextView precioTotal = view.findViewById(R.id.precio_total);


        return view;
    }
}