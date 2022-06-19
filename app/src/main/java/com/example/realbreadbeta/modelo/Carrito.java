package com.example.realbreadbeta.modelo;

import java.util.Objects;

public class Carrito {

    private Producto unProducto;
    private int cantidad;

    public Carrito (){
    }

    public Carrito(Producto unProducto, int cantidad) {
        this.unProducto = unProducto;
        this.cantidad = cantidad;
    }

    public Producto getUnProducto() {
        return unProducto;
    }

    public void setUnProducto(Producto unProducto) {
        this.unProducto = unProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carrito carrito = (Carrito) o;
        return getCantidad() == carrito.getCantidad() &&
                getUnProducto().equals(carrito.getUnProducto());
    }


    @Override
    public String toString() {
        return "Carrito{" +
                "unProducto=" + unProducto +
                ", cantidad=" + cantidad +
                '}';
    }
}
