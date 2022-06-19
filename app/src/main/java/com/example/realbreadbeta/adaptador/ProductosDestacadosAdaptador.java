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

public class ProductosDestacadosAdaptador extends RecyclerView.Adapter<ProductosDestacadosAdaptador.ProductosDestacadosViewHolder>  {
    Context context;
    List<Producto> listaProductos;

    public ProductosDestacadosAdaptador(Context context, List<Producto> listaProductos) {
        this.context = context;
        this.listaProductos = listaProductos;
    }

    @NonNull
    @Override
    public ProductosDestacadosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_destacados_lista_items,parent, false);
        return new ProductosDestacadosViewHolder(view);
    }

    /**
     * Setea la imagen, precio, descripcion y nombre de la lista de productos en el ViewHolder
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ProductosDestacadosViewHolder holder, int position) {

        holder.precio.setText(listaProductos.get(position).getPrecio());
        holder.nombre.setText(listaProductos.get(position).getNombre());
        holder.descripcion.setText(listaProductos.get(position).getDescripcion());
        Picasso.get().load(listaProductos.get(position).getUrlImagen()).resize(150,150).into(holder.imagen);

        holder.itemView.setOnClickListener(new View.OnClickListener(){

            /**
             * Inicia un intent para la clase ProductosActivity
             * se almacena el precio, nombre, imagen y descripción de un item de RecyclerView
             * para entregarselo a ProductosACtivity
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductosActivity.class);
                intent.putExtra("precio",listaProductos.get(position).getPrecio());
                intent.putExtra("nombre",listaProductos.get(position).getNombre());
                intent.putExtra("imagen",listaProductos.get(position).getUrlImagen());
                intent.putExtra("descripcion",listaProductos.get(position).getDescripcion());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public static final class ProductosDestacadosViewHolder extends RecyclerView.ViewHolder{
        ImageView imagen;
        TextView precio;
        TextView nombre;
        TextView descripcion;


        public ProductosDestacadosViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.imagen);
            precio = itemView.findViewById(R.id.precio_producto);
            nombre = itemView.findViewById(R.id.correo2);
            descripcion= itemView.findViewById(R.id.descripcion_producto);

        }
    }

}
