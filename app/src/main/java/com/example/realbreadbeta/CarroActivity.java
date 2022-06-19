package com.example.realbreadbeta;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realbreadbeta.adaptador.ProductosCarritoAdaptador;
import com.example.realbreadbeta.adaptador.ProductosDestacadosAdaptador;
import com.example.realbreadbeta.modelo.Producto;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CarroActivity extends AppCompatActivity implements ValueEventListener {
    private RecyclerView carroRecycler;
    private List<Producto> listaDeProductos;
    private String nombre, precio, descripcion, urlImagen;;

    //Esta actividad debera recuperar la información de firebase de acuerdo
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //variables

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        TextView title = findViewById(R.id.titulo_carro);
        TextView precioTotal = findViewById(R.id.textView5);
        Button botonPagar = findViewById(R.id.boton_pagar_carro);
        listaDeProductos = new ArrayList<>();

        getPrecioTotal(carroRecycler);
        title.setText("Carro de Compra");

        FirebaseUser unUsuario = FirebaseAuth.getInstance().getCurrentUser();
        String id = unUsuario.getUid();
        FirebaseDatabase unaBaseDeDatos = FirebaseDatabase.getInstance();
        DatabaseReference unaReferencia = unaBaseDeDatos.getReference("cart").child(id);
        unaReferencia.addValueEventListener(this);

    }

    private String getPrecioTotal (RecyclerView carroRecycler){
        String precioTotal = null;
        int unPrecio;

        //

        return precioTotal;
    }
    private void setProducto(String nombre, String precio, String descripcion, String urlImagen){
        listaDeProductos.add(new Producto(nombre,precio,descripcion,urlImagen));
    }
    private void setProductosRecycler(List<Producto> listaDeProductos){
        carroRecycler = findViewById(R.id.carrito);
        carroRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
        carroRecycler.setAdapter(new ProductosCarritoAdaptador(this, listaDeProductos));
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        for(DataSnapshot pan: snapshot.getChildren()){
            //en este loop deberia pasar el producto al RecyclerView
            Producto unProducto = pan.getValue(Producto.class);
            nombre= unProducto.getNombre();
            precio= unProducto.getPrecio();
            descripcion= unProducto.getDescripcion();
            urlImagen=unProducto.getUrlImagen();
            setProducto(nombre,precio,descripcion, urlImagen);
        }
        setProductosRecycler(listaDeProductos);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {
        Log.w("PRODUCTO", "onCancelled");
        Toast.makeText(CarroActivity.this, "Error:"+ error.getCode(), Toast.LENGTH_SHORT).show();
    }


}