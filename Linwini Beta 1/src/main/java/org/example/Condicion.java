package org.example;

public abstract class Condicion {
    protected String nombre;



    public abstract int obtenerValor();

    public String getNombre() {
        return nombre;
    }

    public Condicion(String nombre) {
        this.nombre = nombre;
    }

}
