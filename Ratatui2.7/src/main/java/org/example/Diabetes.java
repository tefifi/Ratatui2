package org.example;

public class Diabetes extends Condicion {
    private final int maxAzucar = 10;
    private final int maxCarbohidratos = 40;

    public Diabetes() {
        super("diabetes");
    }

    @Override
    public int obtenerValor() {
        return maxAzucar;
    }

    public int getMaxCarbohidratos() {
        return maxCarbohidratos;
    }
}
