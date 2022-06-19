package com.example.realbreadbeta.adaptador;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realbreadbeta.R;
import com.example.realbreadbeta.modelo.Producto;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductosCarritoAdaptador extends RecyclerView.Adapter<ProductosCarritoAdaptador.ProductosCarritoViewHolder> implements ValueEventListener {
    private Context context;
    private List<Producto> listaProductoCarro;
    private static Integer contador;
    private final String DEFAULT = "1";



    public ProductosCarritoAdaptador(Context context, List<Producto> listaProductoCarro) {
        this.context = context;
        this.listaProductoCarro = listaProductoCarro;
    }

    @NonNull
    @Override
    public ProductosCarritoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_carrito,parent,false);
        return new ProductosCarritoAdaptador.ProductosCarritoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductosCarritoViewHolder holder, int position) {
        FirebaseUser unUsuario = FirebaseAuth.getInstance().getCurrentUser();
        String id = unUsuario.getUid();
        FirebaseDatabase unaBaseDeDatos = FirebaseDatabase.getInstance();
        DatabaseReference unaReferencia = unaBaseDeDatos.getReference("cart").child(id).child(listaProductoCarro.get(position).getNombre());

        if(contador == null){
            holder.contador.setText(DEFAULT);
            contador = 1;
        }
        else{
            holder.contador.setText(Integer.toString(contador));
        }

        holder.precio.setText(listaProductoCarro.get(position).getPrecio());
        holder.nombre.setText(listaProductoCarro.get(position).getNombre());
        holder.descripcion.setText(listaProductoCarro.get(position).getDescripcion());
        Picasso.get().load(listaProductoCarro.get(position).getUrlImagen()).resize(100,100).into(holder.imagen);
        holder.boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Producto: "+listaProductoCarro.get(position).getNombre()+" seleccionado", Toast.LENGTH_SHORT).show();

            }
        });
        holder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser unUsuario = FirebaseAuth.getInstance().getCurrentUser();
                String id = unUsuario.getUid();
                FirebaseDatabase unaBaseDeDatos = FirebaseDatabase.getInstance();
                DatabaseReference unaReferencia = unaBaseDeDatos.getReference("cart").child(id).child(listaProductoCarro.get(position).getNombre());
                unaReferencia.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        snapshot.getRef().removeValue();
                        eliminarItem(holder, position);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.w("PRODUCTO", "onCancelled");
                    }
                });
            }
        });

        holder.añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contador = contador + 1;
                holder.contador.setText(Integer.toString(contador));
                //Map<String, Object> userUpdates = new HashMap<>();
                //userUpdates.put("cantidad", Integer.toString(contador));
                //unaReferencia.updateChildren(userUpdates);
            }
        });

        holder.restar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(contador > 1){
                    contador = contador - 1;
                    holder.contador.setText(Integer.toString(contador));
                    //Map<String, Object> userUpdates = new HashMap<>();
                    //userUpdates.put("cantidad", Integer.toString(contador));
                   // unaReferencia.updateChildren(userUpdates);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return listaProductoCarro.size();
    }

    public void eliminarItem(@NonNull ProductosCarritoViewHolder holder, int position){
        listaProductoCarro.remove(position);
        notifyItemRemoved(holder.getAdapterPosition());
        notifyItemRangeRemoved(holder.getAdapterPosition(),1);
        notifyItemRangeChanged(holder.getAdapterPosition(), listaProductoCarro.size());
       // holder.itemView.setVisibility(View.GONE);

    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        for(DataSnapshot pan: snapshot.getChildren()){
            Map<String, Object> userUpdates = new HashMap<>();
            userUpdates.put("alanisawesome/nickname", "Alan The Machine");

        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }


    public class ProductosCarritoViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, descripcion, precio, contador;
        ImageView imagen, eliminar, añadir, restar;
        CheckBox boton;

        public ProductosCarritoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre_producto_carrito);
            descripcion = itemView.findViewById(R.id.descripcion_producto_carrito);
            precio = itemView.findViewById(R.id.precio_producto_carrito);
            imagen = itemView.findViewById(R.id.imagen_producto_carrito);
            boton = itemView.findViewById(R.id.radio_producto_carrito);
            eliminar = itemView.findViewById(R.id.boton_eliminar);
            añadir = itemView.findViewById(R.id.add);
            restar = itemView.findViewById(R.id.res);
            contador = itemView.findViewById(R.id.contador);
        }
    }

    public class ProductosCarritoDiffUtil extends DiffUtil.ItemCallback<Producto>{

        @Override
        public boolean areItemsTheSame(@NonNull Producto oldItem, @NonNull Producto newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Producto oldItem, @NonNull Producto newItem) {
            return false;
        }
    }
}
