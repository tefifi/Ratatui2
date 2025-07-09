package org.example;

import java.util.List;
import java.util.Random;

public class Receta {

    private String nombre;
    private String cantPorciones;
    private String listoEn;
    private boolean lactosa;
    private boolean gluten;
    private boolean vegano;
    private boolean vegetariano;
    private String saludable;
    private String tipoComida;
    private String ingredientes;



    private DatosNutricionales datosNutricionales = new DatosNutricionales();
    Random random = new Random();

    public void atributosReceta() {
        String recetasFile = GestorArchivo.leerArchivo("Data/recetas.csv");
        String[] datosRecetas = recetasFile.split("\n");

        int maxIntentos = 10;
        int intentos = 0;
        boolean recetaValida = false;

        while (intentos < maxIntentos && !recetaValida) {
            int num = random.nextInt(datosRecetas.length - 1) + 1;
            String linea = datosRecetas[num];

            try {
                List<String> datos = GestorArchivo.parseCsvLine(linea);

                if (datos.size() >= 28) {
                    this.datosNutricionales = new DatosNutricionales();
                    this.nombre = datos.get(0);
                    this.cantPorciones = datos.get(1);
                    this.listoEn = datos.get(2);
                    lactosa = Boolean.parseBoolean(datos.get(18));
                    gluten = Boolean.parseBoolean(datos.get(19));
                    this.vegano = Boolean.parseBoolean(datos.get(21));
                    this.vegetariano = Boolean.parseBoolean(datos.get(22));
                    this.saludable = datos.get(23);

                    // Manejar campos entrecomillados
                    this.tipoComida = datos.get(26).replaceAll("^\"|\"$", "");
                    this.ingredientes = datos.get(27).replaceAll("^\"|\"$", "");

                    // Obtener datos nutricionales
                    datosNutricionales.infoNutricional(num);

                    // Verificar campos esenciales
                    if (!nombre.isEmpty() && !cantPorciones.isEmpty() &&
                            !listoEn.isEmpty() && !tipoComida.isEmpty() &&
                            !ingredientes.isEmpty()) {
                        recetaValida = true;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error al procesar línea: " + linea);
            }

            intentos++;
        }

        if (!recetaValida && intentos >= maxIntentos) {
            System.out.println("No se encontró receta válida después de " + maxIntentos + " intentos");
            this.nombre = "Receta inválida";
            this.cantPorciones = "0";
            this.listoEn = "0";
            this.vegano = false;
            vegetariano = false;
            this.saludable = "false";
            this.tipoComida = "";
            this.ingredientes = "";
        }
    }


    public void mostrarReceta() {
        System.out.println("Tipo de comida: "+this.tipoComida);
        System.out.println("Cantidad de porciones: "+this.cantPorciones);
        System.out.println("Listo en "+this.listoEn+" minutos");
        System.out.println("Lactosa: "+lactosa);
        System.out.println("Gluten: "+gluten);
        System.out.println("Vegano: "+this.vegano);
        System.out.println("Vegetariano: "+this.vegetariano);
        System.out.println("Saludable: "+this.saludable);
        System.out.println("Ingredientes: "+this.ingredientes);
        System.out.println(datosNutricionales.toString());

    }
    public String getNombre() {
        return nombre;
    }

    public String getCantPorciones() {
        return cantPorciones;
    }

    public String getListoEn() {
        return listoEn;
    }

    public boolean getVegano() {
        return vegano;
    }

    public boolean getVegetariano() {
        return vegetariano;
    }

    public String getSaludable() {
        return saludable;
    }

    public String getTipoComida() {
        return tipoComida;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public DatosNutricionales getDatosNutricionales() {
        return datosNutricionales;
    }

    public boolean isVegan() {
        return vegano; // Devuelve el valor de la propiedad vegana
    }
    public boolean isVegetarian() {
        return vegetariano; // Devuelve el valor de la propiedad vegetariana
    }

    public boolean isLactosa() {
        return lactosa;
    }

    public boolean isGluten() {
        return gluten;
    }

    public void setDatosNutricionales(DatosNutricionales datosNutricionales) {
        this.datosNutricionales = datosNutricionales;
    }

    public void setVegano(boolean vegano) {
        this.vegano = vegano;
    }

    public void setVegetariano(boolean vegetariano) {
        this.vegetariano = vegetariano;
    }

    public void setGluten(boolean gluten) {
        this.gluten = gluten;
    }

    public void setLactosa(boolean lactosa) {
        this.lactosa = lactosa;
    }

    @Override
    public String toString() {
        return "Receta{" +
                "nombre='" + nombre + '\'' +
                ", cantPorciones='" + cantPorciones + '\'' +
                ", listoEn='" + listoEn + '\'' +
                ", vegano='" + vegano + '\'' +
                ", vegetariano='" + vegetariano + '\'' +
                ", saludable='" + saludable + '\'' +
                ", tipoComida='" + tipoComida + '\'' +
                ", ingredientes='" + ingredientes + '\'' +
                '}';
    }
}
