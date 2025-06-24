
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
            System.out.println("No se encontró una receta adecuada.");
            return; // Salir si no se encuentra una receta
        }

        System.out.println("Te recomendamos esta receta: " + recetaFiltrada.getNombre());
        recetaFiltrada.mostrarReceta();
        this.recetaAprob = recetaFiltrada;
    }


    public Receta filtrarReceta(Receta receta) {
        boolean aprobada = true;

        for (Condicion condicion : usuario.condiciones) {
            switch (condicion.getNombre()) {
                case "diabetes":
                    Diabetes diabetes = (Diabetes) condicion;
                    if (receta.getDatosNutricionales().getAzucar() > diabetes.obtenerValor() ||
                            receta.getDatosNutricionales().getCarbohidratos() > diabetes.getMaxCarbohidratos()) {
                        aprobada = false;
                    }
                    break;
                case "hipertension":
                    Hipertension hipertension = (Hipertension) condicion;
                    if (receta.getDatosNutricionales().getSodio() > hipertension.obtenerValor()) {
                        aprobada = false;
                    }
                    break;
            }
            if (!aprobada) break;
        }
        return aprobada ? receta : null;
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
