package com.example.realbreadbeta.modelo;

public class ProductoCarro extends Producto {
    private int cantidad;

    public ProductoCarro() {
    }

    public ProductoCarro(String nombre, String precio, String descripcion, String urlImagen, int cantidad) {
        super(nombre, precio, descripcion, urlImagen);
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }


}
