package org.example;

public class Error extends Exception {
  private Error(String mensaje) {
    super(mensaje);
  }


  public static Error ingrDatos(String mensaje) {
    return new Error(mensaje);
  }
  public static Error datosIMC(String mensaje) {
    return new Error("Error en IMC: " + mensaje);
  }

  public static Error formatoNum() {
    return new Error("Valores inválidos. Ingresa números.");
  }
}