package org.example;

import java.util.List;
import java.util.Random;

public class DatosNutricionales {
    private int calorias;
    private int carbohidratros;
    private int grasas;
    private int proteina;
    private int sodio;
    private int azucar;
    private int colesterol;
    private int fibra;

    public void infoNutricional(int num) {
        String recetasFile = GestorArchivo.leerArchivo("Data/recetas.csv");
        String[] datosRecetas = recetasFile.split("\n");

        if (num < 1 || num >= datosRecetas.length) return;

        try {
            List<String> datos = GestorArchivo.parseCsvLine(datosRecetas[num]);

            if (datos.size() >= 17) {
                this.calorias = (int) Double.parseDouble(datos.get(8));
                this.carbohidratros = (int) Double.parseDouble(datos.get(9));
                this.grasas = (int) Double.parseDouble(datos.get(10));
                this.proteina = (int) Double.parseDouble(datos.get(11));
                this.sodio = (int) Double.parseDouble(datos.get(13));
                this.azucar = (int) Double.parseDouble(datos.get(14));
                this.colesterol = (int) Double.parseDouble(datos.get(15));
                this.fibra = (int) Double.parseDouble(datos.get(16));
            }
        } catch (Exception e) {
            System.out.println("Error nutricional en línea: " + datosRecetas[num]);
        }
    }



    @Override
    public String toString() {
        return "DatosNutricionales{" +
                "calorias=" + calorias +
                ", carbohidratros=" + carbohidratros +
                ", grasas=" + grasas +
                ", proteina=" + proteina +
                ", sodio=" + sodio +
                ", azucar=" + azucar +
                ", colesterol=" + colesterol +
                ", fibra=" + fibra +
                '}';
    }



    public int getCalorias() { return calorias; }
    public int getCarbohidratos() { return carbohidratros; }
    public int getGrasas() { return grasas; }
    public int getProteina() { return proteina; }
    public int getSodio() { return sodio; }
    public int getAzucar() { return azucar; }
    public int getColesterol() { return colesterol; }
    public int getFibra() { return fibra; }

}

