package com.example.realbreadbeta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.realbreadbeta.modelo.Producto;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;


public class ProductosActivity extends AppCompatActivity {

    private FirebaseUser unUsuario;
    private Producto unProducto;

    /**
     * Este metodo recibe un nombre y descripcion de tipo String y una url de tipo Integer desde
     * la clase MainActiviy. Luego las setea a los parametros propios de la clase.
     *
     * @param savedInstanceState referencia a un objeto de tipo Bundle, permite guardar instancias
     * de actividades.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        TextView nombre = findViewById(R.id.correo2);
        ImageView imagen = findViewById(R.id.imagen);
        TextView descripcion = findViewById(R.id.productDescription);
        Button añadirAlCarro = findViewById(R.id.buttonCart);


        unProducto = new Producto(getIntent().getStringExtra("nombre"), getIntent().getStringExtra("precio"), getIntent().getStringExtra("descripcion"),getIntent().getStringExtra("imagen"));

        unUsuario = FirebaseAuth.getInstance().getCurrentUser();
        String uid = unUsuario.getUid();
        Toast.makeText(this,"id del usuario: "+uid,Toast.LENGTH_SHORT).show();

        nombre.setText(unProducto.getNombre());
        descripcion.setText(unProducto.getDescripcion());
        Picasso.get().load(unProducto.getUrlImagen()).resize(300,300).into(imagen);

        FirebaseDatabase  unaBaseDeDatos = FirebaseDatabase.getInstance();
        DatabaseReference unaReferencia = unaBaseDeDatos.getReference("cart").child(uid);


        añadirAlCarro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Aqui se debera guardar la información del producto que elegio el cliente
                //dentro de firebase, de acuerdo a la id del cliente.

                Map<String, Object> map = new HashMap<>();
                map.put("nombre", unProducto.getNombre());
                map.put("descripcion", unProducto.getDescripcion());
                map.put("precio", unProducto.getPrecio());
                map.put("urlImagen",unProducto.getUrlImagen());
                unaReferencia.child(unProducto.getNombre()).setValue(map);

                Toast.makeText(ProductosActivity.this,"Se ha añadido el producto a tu carro de compra",Toast.LENGTH_SHORT).show();

            }
        });
    }
}