package com.example.realbreadbeta.adaptador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realbreadbeta.CategoriasActivity;
import com.example.realbreadbeta.R;
import com.example.realbreadbeta.modelo.Categoria;

import java.util.List;

public class CategoriasAdaptador extends RecyclerView.Adapter<CategoriasAdaptador.CategoriasViewHolder>  {

    Context context;
    List<Categoria> listaCategorias;


    public CategoriasAdaptador(Context context, List<Categoria> listaCategorias) {
        this.context = context;
        this.listaCategorias = listaCategorias;

    }

    @NonNull
    @Override
    public CategoriasAdaptador.CategoriasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_categorias_items_main,parent,false);
        return new CategoriasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriasAdaptador.CategoriasViewHolder holder, int position) {
        holder.imagen.setImageResource(listaCategorias.get(position).getImagen());
        holder.nombre.setText(listaCategorias.get(position).getNombre());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CategoriasActivity.class);
                intent.putExtra("nombre", listaCategorias.get(position).getNombre());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaCategorias.size();
    }

    public static final class CategoriasViewHolder extends RecyclerView.ViewHolder {
        ImageView imagen;
        TextView nombre;
        public CategoriasViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.imagen_categoria);
            nombre = itemView.findViewById(R.id.categoriaText);
        }
    }
}
