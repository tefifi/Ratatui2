package org.example;

public class Error extends Exception {
    // Constructor privado
    private Error(String mensaje) {
        super(mensaje);
    }

    // Factory method con el nombre deseado
    public static Error ingrDatos(String mensaje) {
        return new Error(mensaje);
    }
    public static Error datosIMC(String mensaje) {
        return new Error("Error en IMC: " + mensaje);
    }

    // Método para errores de formato numérico
    public static Error formatoNum() {
        return new Error("Valores inválidos. Ingresa números.");
    }
}