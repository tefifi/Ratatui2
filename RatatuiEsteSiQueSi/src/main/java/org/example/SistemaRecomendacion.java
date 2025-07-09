package org.example;


import java.util.Scanner;

public class SistemaRecomendacion {
    Scanner scanner = new Scanner(System.in);

    private Receta receta = new Receta();
    private Receta recetaAprob;
    private Usuario usuario = new Usuario();


    public void recomendarReceta() {
        int maxIntentos = 10;
        int intentos = 0;
        Receta recetaFiltrada = null;

        while (intentos < maxIntentos && recetaFiltrada == null) {
            Receta candidata = new Receta();
            candidata.atributosReceta();
            recetaFiltrada = filtrarReceta(candidata);
            intentos++;
        }

        if (recetaFiltrada == null) {
            System.out.println("No se encontró una receta adecuada. Intente de nuevo.");
            return;
        }

        System.out.println("Te recomendamos esta receta: " + recetaFiltrada.getNombre());
        recetaFiltrada.mostrarReceta();
        this.recetaAprob = recetaFiltrada;
    }


    public Receta filtrarReceta(Receta receta) {
        return usuario.getCondiciones().stream()
                .noneMatch(condicion -> {
                    switch (condicion.getNombre()) {
                        case "diabetes":
                            Diabetes diabetes = (Diabetes) condicion;
                            return receta.getDatosNutricionales().getAzucar() > diabetes.obtenerValor() ||
                                    receta.getDatosNutricionales().getCarbohidratos() > diabetes.getMaxCarbohidratos();
                        case "hipertension":
                            Hipertension hipertension = (Hipertension) condicion;
                            return receta.getDatosNutricionales().getSodio() > hipertension.obtenerValor();
                        case "vegano":
                            return !receta.isVegan();
                        case "vegetariano":
                            return !receta.isVegetarian();
                        case "hipolactasia":
                            return receta.isLactosa();
                        case "celiaquia":
                            return receta.isGluten();
                        default:
                            return false;
                    }
                }) ? receta : null;
    }

    public void agregarReceta() {
        System.out.println("Desea agregar la receta a su base de datos");
        String resp = scanner.nextLine();

        if (resp.equalsIgnoreCase("si") && recetaAprob != null) {
            usuario.agregarReceta(recetaAprob);
            System.out.println("Se agrego la receta: " + recetaAprob);
        } else {
            System.out.println("No se agrego la receta");
        }
    }



    public SistemaRecomendacion(Usuario usuario) {
        this.usuario = usuario;
    }
}
