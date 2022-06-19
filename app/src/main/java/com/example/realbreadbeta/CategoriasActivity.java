package com.example.realbreadbeta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.realbreadbeta.adaptador.ListaCategoriasAdaptador;
import com.example.realbreadbeta.modelo.Producto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CategoriasActivity extends AppCompatActivity {
    private TextView unTitulo;
    private List<Producto> listaProductos;
    private String nombre, precio, descripcion;
    private String urlImagen;
    private RecyclerView unRecyclerView;
    private ListaCategoriasAdaptador unAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        unTitulo = findViewById(R.id.titulo);
        String tituloString = getIntent().getStringExtra("nombre");
        unTitulo.setText(tituloString);

        FirebaseDatabase unaBaseDeDatos = FirebaseDatabase.getInstance();
        DatabaseReference productosReference = unaBaseDeDatos.getReference("productos");
        DatabaseReference productosChildReference = productosReference.child(tituloString.toLowerCase());

        listaProductos = new ArrayList<>();

        productosChildReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot pan: snapshot.getChildren()){
                    //en este loop deberia pasar el producto al RecyclerView
                    Producto unProducto = pan.getValue(Producto.class);
                    nombre= unProducto.getNombre();
                    precio= unProducto.getPrecio();
                    descripcion= unProducto.getDescripcion();
                    urlImagen=unProducto.getUrlImagen();
                    setProducto(nombre, precio, descripcion, urlImagen);
                }
                setProductosRecycler(listaProductos);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("PRODUCTO", "onCancelled");
                Toast.makeText(CategoriasActivity.this, "Error:"+ error.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

    }

     private void setProducto(String nombre, String precio, String descripcion, String urlImagen){
        listaProductos.add(new Producto(nombre,precio,descripcion,urlImagen));
    }

    private void setProductosRecycler(List<Producto> listaProductos){
        unRecyclerView = findViewById(R.id.lista_de_productos);
        unRecyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        unRecyclerView.setAdapter(new ListaCategoriasAdaptador(this, listaProductos));
    }
}