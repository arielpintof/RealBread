package com.example.realbreadbeta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.realbreadbeta.adaptador.CategoriasAdaptador;
import com.example.realbreadbeta.adaptador.OfertasAdaptador;
import com.example.realbreadbeta.adaptador.ProductosDestacadosAdaptador;
import com.example.realbreadbeta.modelo.Categoria;
import com.example.realbreadbeta.modelo.Oferta;
import com.example.realbreadbeta.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private List<Oferta> ofertaList;
    private List<Producto> productoList;
    private List<Categoria> categoriaList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializar();
        añadirElementosListas();
        setearRecyclers();

    }

    private void setearRecyclers(){
        setDestacadosRecycler(productoList);
        setOfertasRecycler(ofertaList);
        setCategoriasRecycler(categoriaList);
    }

    private void añadirElementosListas(){
        setOfertasList();
        setProductosList();
        setCategoriasList();
    }

    private void inicializar(){
         //ViewHolder para el titulo, boton del carro de compra y boton para perfil.
        TextView unTitulo = findViewById(R.id.homeTitle);
        ImageView botonDelCarro = findViewById(R.id.boton_carro);
        ImageView botonDelPerfil = findViewById(R.id.boton_perfil);
        //Listener para los botones de carro y perfil, para poder iniciar sus actividades
        botonDelCarro.setOnClickListener(this);
        botonDelPerfil.setOnClickListener(this);
        //Inicializando Arraylists
        ofertaList = new ArrayList<>();
        productoList = new ArrayList<>();
        categoriaList = new ArrayList<>();
        //seteo del titulo
        unTitulo.setText("REAL BREAD");

    }

    /*
     * Esta funcion define un Intent a partir del id de un boton. Para luego iniciar una actividad
     * CarroActivity o PerfilActivity
     */
    @Override
    public void onClick(View unBoton) {
        switch(unBoton.getId()){
            case R.id.boton_carro:
                Intent unIntent = new Intent(MainActivity.this, CarroActivity.class);
                MainActivity.this.startActivity(unIntent);
                break;

            case R.id.boton_perfil:
                Intent otroIntent = new Intent(MainActivity.this, PerfilActivity.class);
                MainActivity.this.startActivity(otroIntent);
                break;
        }

    }

    /*
     * Esta funcion agrega objetos de tipo Categoria a un ArrayList
     */
    private void setCategoriasList(){
        categoriaList.add(new Categoria("Pan", R.drawable.pan_icono));
        categoriaList.add(new Categoria("Bolleria", R.drawable.bolleria_icono));
        categoriaList.add(new Categoria("Hojaldres", R.drawable.hojaldre_icono));
        categoriaList.add(new Categoria("Pizza", R.drawable.pizza_icono));
    }

    /**
     * Esta funcion agrega objetos de tipo Oferta a un Arraylist
     */
    private void setOfertasList() {
        ofertaList.add(new Oferta(R.drawable.descuento_2500));
        ofertaList.add(new Oferta(R.drawable.descuento_2500));
        ofertaList.add(new Oferta(R.drawable.descuento_2500));
        ofertaList.add(new Oferta(R.drawable.descuento_2500));
    }

    /*
     * Esta funcion agrega objetos de tipo Producto a un Arraylist
     */
    private void setProductosList(){
        productoList.add(new Producto("Pan de Masa Madre","$2500","Descripcion", "https://firebasestorage.googleapis.com/v0/b/real-bread-app.appspot.com/o/boulebread.png?alt=media&token=8023ebbb-10cd-44b5-9fbf-c612d2d2df88"));
        productoList.add(new Producto("Rollos de Canela", "$700","Descripcion", "https://firebasestorage.googleapis.com/v0/b/real-bread-app.appspot.com/o/cinnamon_rolls_2.png?alt=media&token=157ca32f-ff9c-4066-9320-cf41a66f78ef"));
        productoList.add(new Producto("Pan Ciabatta Italiano", "$1200","Descripcion", "https://firebasestorage.googleapis.com/v0/b/real-bread-app.appspot.com/o/ciabatta.png?alt=media&token=10457281-b068-4b32-b384-756762e41ec0"));
        productoList.add(new Producto("Pan de Masa Madre","$2500","Descripcion", "https://firebasestorage.googleapis.com/v0/b/real-bread-app.appspot.com/o/boulebread.png?alt=media&token=8023ebbb-10cd-44b5-9fbf-c612d2d2df88"));
        productoList.add(new Producto("Rollos de Canela", "$700","Descripcion", "https://firebasestorage.googleapis.com/v0/b/real-bread-app.appspot.com/o/cinnamon_rolls_2.png?alt=media&token=157ca32f-ff9c-4066-9320-cf41a66f78ef"));
        productoList.add(new Producto("Pan Ciabatta Italiano", "$1200","Descripcion", "https://firebasestorage.googleapis.com/v0/b/real-bread-app.appspot.com/o/ciabatta.png?alt=media&token=10457281-b068-4b32-b384-756762e41ec0"));
    }

    /*
     * Esta funcion identifica el id del RecyclerView de la seccion Destacados e inicializa sus funciones llamando al Adaptador correspondiente
     * @param productoList
     */
    private void setDestacadosRecycler(List<Producto> productoList){
        RecyclerView destacadosRecycler;
        destacadosRecycler = findViewById(R.id.destacados_recycler);
        destacadosRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false));
        destacadosRecycler.setAdapter(new ProductosDestacadosAdaptador(this, productoList));
    }

    //Setea la lista de productos en RecyclerView
    private void setOfertasRecycler(List<Oferta> ofertaList) {
        RecyclerView ofertasRecycler;
        ofertasRecycler = findViewById(R.id.ofertas_recycler);
        ofertasRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,true));
        ofertasRecycler.setAdapter(new OfertasAdaptador(this, ofertaList));
    }

    //setea la lista de categorias en RecyclerView
    private void setCategoriasRecycler(List<Categoria> categoriaList){
        RecyclerView categoriasRecycler;
        categoriasRecycler = findViewById(R.id.categorias_recycler);
        categoriasRecycler.setLayoutManager(new GridLayoutManager(this, 4,GridLayoutManager.VERTICAL,false));
        categoriasRecycler.setAdapter(new CategoriasAdaptador(this, categoriaList));
    }


}

