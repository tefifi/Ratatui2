package org.example;

public class Celiaquia extends Condicion {
    public Celiaquia() {
        super("celiaquia");  // Cambiado de "celiaco" a "celiaquia"
    }

    @Override
    public int obtenerValor() {
        return 0;
    }
}
