package com.example.realbreadbeta.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realbreadbeta.R;
import com.example.realbreadbeta.modelo.Oferta;

import java.util.List;

public class OfertasAdaptador extends RecyclerView.Adapter<OfertasAdaptador.OfertasViewHolder>  {

    Context context;
    List<Oferta> listaOfertas;

    public OfertasAdaptador(Context context, List<Oferta> listaOfertas) {
        this.context = context;
        this.listaOfertas = listaOfertas;
    }

    @NonNull
    @Override
    public OfertasAdaptador.OfertasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ofertas_items,parent,false);
        return new OfertasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OfertasAdaptador.OfertasViewHolder holder, int position) {
        holder.imagen.setImageResource(listaOfertas.get(position).getUrlImagen());
    }

    @Override
    public int getItemCount() {
        return listaOfertas.size();
    }

    public static final class OfertasViewHolder extends RecyclerView.ViewHolder {
        ImageView imagen;
        public OfertasViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.imagen_oferta);
        }
    }
}
