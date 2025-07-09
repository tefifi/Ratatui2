package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Usuario {
    private int id;
    private String nombre;
    private float altura;
    private float peso;
    private int edad = 0;
    private ArrayList<Condicion> condiciones;
    private ArrayList<Receta> recetas;

    Scanner scanner = new Scanner(System.in);

    public void ingresarDatos(){
        System.out.println("Ingresa tu nombre");
        nombre =scanner.next();

        System.out.println("Ingresa tu edad");
        while (edad <= 0) {
            try {
                edad = scanner.nextInt();
                scanner.nextLine();

                if (edad <= 0) {
                    System.out.println("La edad debe ser mayor que 0.");
                }

            } catch (Exception e) {
                System.out.println("Entrada no válida. Por favor, ingresa un número entero.");
                scanner.nextLine();
            }
        }
    }

    public void agregarCondicion() {
        System.out.println("¿Qué condición quieres agregar? (Diabetes/Hipertension/Vegetariano/Vegano/Hipolactasia/Celiaquia)");

        switch (scanner.next().toLowerCase()) {
            case "diabetes":
                condiciones.add(new Diabetes());
                break;

            case "hipertension":
                condiciones.add(new Hipertension());
                break;

            case "vegetariano":
                condiciones.add(new Vegetariano());
                break;

            case "vegano":
                condiciones.add(new Vegano());
                break;

            case "hipolactasia":
                condiciones.add(new Hipolactasia());
                break;

            case "celiaquia":
                condiciones.add(new Celiaquia());
                break;

            default:
                System.out.println("Opciones disponibles: Diabetes, Hipertensión, Vegetariano, Vegano, Hipolactasia");
        }
    }


    public void mostrarCondiciones() {
        System.out.println("Tus condiciones son: ");
        condiciones.forEach(condicion -> System.out.println("- " + condicion.getNombre()));
    }

    public void mostrarRecetas() {
        recetas.forEach(receta -> System.out.println("- " + receta));
    }

    public List<Condicion> getCondiciones() {
        return this.condiciones;
    }


    public void agregarReceta(Receta receta){
        recetas.add(receta);
    }

    public void datosIMC(){
        System.out.println("Vamos a crear un Indice de Masa Corporal");
        System.out.println("Ingresa tu altura (Metros)");
        try {
            altura=scanner.nextFloat();
        }catch (Exception e){
            System.out.println("Ingresaste mal los datos");
        }
        System.out.println("Ingresa tu peso (Kilogramos)");
        try {
            peso=scanner.nextFloat();
        }catch (Exception e ){
            System.out.println("Ingresaste mal los datos");
        }
    }

    public String calcularIMC() {
        float imc = peso / (altura * altura);
        String clasificacion;
        if (imc < 18.5) {
            clasificacion = "Tienes un peso bajo";
        } else if (imc < 25) {
            clasificacion = "Tienes un peso normal";
        } else if (imc < 30) {
            clasificacion = "Tienes sobrepeso";
        } else if (imc < 35) {
            clasificacion = "Tienes obesidad leve";
        } else if (imc < 40) {
            clasificacion = "Tienes obesidad media";
        } else {
            clasificacion = "Tienes obesidad mórbida";
        }
        return "Tu Índice de Masa Corporal es: " + String.format("%.2f", imc) + ". " + clasificacion + "\n" +
                "El índice de masa corporal no es del todo preciso, por favor acudir a un profesional para mayor información.";
    }





    public Usuario() {
        this.condiciones = new ArrayList<>();
        this.recetas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public float getAltura() {
        return altura;
    }

    public int getEdad() {
        return edad;
    }

    public float getPeso() {
        return peso;
    }


    public ArrayList<Receta> getRecetas() {
        return recetas;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }



}
