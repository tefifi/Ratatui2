package org.example;

public class Main {
    public static void main(String[] args) {
        FuncionEcencial operaciones = new FuncionEcencial();
        FirebaseCRUD crud = new FirebaseCRUD(operaciones.connetarBaseDeDatos());
        operaciones.crear(crud);
        operaciones.leer(crud);
        operaciones.actualizar(crud);
        operaciones.borrar(crud);
    }
}


