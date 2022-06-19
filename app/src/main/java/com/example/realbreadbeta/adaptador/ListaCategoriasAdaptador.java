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

import com.example.realbreadbeta.ProductosActivity;
import com.example.realbreadbeta.R;
import com.example.realbreadbeta.modelo.Producto;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListaCategoriasAdaptador extends RecyclerView.Adapter<ListaCategoriasAdaptador.ListaCategoriasViewHolder> {
    Context context;
    List<Producto> listaDeProductos;


    public ListaCategoriasAdaptador(Context context, List<Producto> listaDeProductos) {
        this.context = context;
        this.listaDeProductos = listaDeProductos;

    }

    @NonNull
    @Override
    public ListaCategoriasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_categoria_items_categoria,parent,false);
        return new ListaCategoriasAdaptador.ListaCategoriasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaCategoriasViewHolder holder, int position) {

        holder.precio.setText(listaDeProductos.get(position).getPrecio());
        holder.nombre.setText(listaDeProductos.get(position).getNombre());
        holder.descripcion.setText(listaDeProductos.get(position).getDescripcion());
        Picasso.get().load(listaDeProductos.get(position).getUrlImagen()).resize(150,150).into(holder.imagen);

        holder.itemView.setOnClickListener(new View.OnClickListener(){

            /**
             * Inicia un intent para la clase ProductosActivity
             * se almacena el precio, nombre, imagen y descripción de un item de RecyclerView
             * para entregarselo a ProductosActivity
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductosActivity.class);
                intent.putExtra("precio",listaDeProductos.get(position).getPrecio());
                intent.putExtra("nombre",listaDeProductos.get(position).getNombre());
                intent.putExtra("imagen",listaDeProductos.get(position).getUrlImagen());
                intent.putExtra("descripcion",listaDeProductos.get(position).getDescripcion());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaDeProductos.size();
    }

    public class ListaCategoriasViewHolder extends RecyclerView.ViewHolder {

        private ImageView imagen;
        private TextView precio;
        private TextView nombre;
        private TextView descripcion;
        public ListaCategoriasViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.titulo_producto);
            precio = itemView.findViewById(R.id.precio_del_producto);
            descripcion = itemView.findViewById(R.id.descripcion_del_producto);
            imagen = itemView.findViewById(R.id.imagen_del_producto);
        }
    }
}
