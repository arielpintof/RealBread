package com.example.realbreadbeta.modelo;

import android.widget.ImageView;

public class Categoria {
    private  String nombre;
    private Integer imagen;

    public Categoria(String nombre, Integer imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getImagen() {
        return imagen;
    }

    public void setImagen(Integer imagen) {
        this.imagen = imagen;
    }
}
