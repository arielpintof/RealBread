package com.example.realbreadbeta.modelo;

public class ProductosDeCategoria {
    private String nombre;
    private String precio;
    private String descripcion;
    private Integer primeraImagen, segundaImagen, terceraImagen;

    public ProductosDeCategoria(String nombre, String precio, String descripcion, Integer primeraImagen, Integer segundaImagen, Integer terceraImagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.primeraImagen = primeraImagen;
        this.segundaImagen = segundaImagen;
        this.terceraImagen = terceraImagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPrimeraImagen() {
        return primeraImagen;
    }

    public void setPrimeraImagen(Integer primeraImagen) {
        this.primeraImagen = primeraImagen;
    }

    public Integer getSegundaImagen() {
        return segundaImagen;
    }

    public void setSegundaImagen(Integer segundaImagen) {
        this.segundaImagen = segundaImagen;
    }

    public Integer getTerceraImagen() {
        return terceraImagen;
    }

    public void setTerceraImagen(Integer terceraImagen) {
        this.terceraImagen = terceraImagen;
    }
}
