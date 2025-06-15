import java.util.Random;

public class Receta {

    private String nombre;
    private String ingredientes;
    private String tipoComida;

    private DatosNutricionales datosNutricionales = new DatosNutricionales();


    Random random = new Random();

    public void atributosReceta() {
        String recetasFile = GestorArchivo.leerArchivo("Data/recetas.csv");
        String[] datosRecetas = recetasFile.split("\n");

        int num = random.nextInt(datosRecetas.length - 1) + 1;

        datosNutricionales.infoNutricional(num); // usa el mismo número

        String[] datos = datosRecetas[num].split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        try {
            if (datos.length >= 28) {
                this.nombre = datos[0];
                this.tipoComida = datos[25];
                this.ingredientes = datos[27];
            } else {
                System.out.println("Línea incompleta: " + datosRecetas[num]);
            }
        } catch (Exception e) {
            System.out.println("Error al procesar línea: " + datosRecetas[num]);
        }
    }

    public void mostrarReceta() {
        System.out.println(this.tipoComida);
        System.out.println(this.ingredientes);
        System.out.println(datosNutricionales.toString());

    }

    public String getNombre() {
        return nombre;
    }

    public DatosNutricionales getDatosNutricionales() {
        return datosNutricionales;
    }

    @Override
    public String toString() {
        return "Receta{" +
                "nombre='" + nombre + '\'' +
                ", ingredientes='" + ingredientes + '\'' +
                ", tipoComida='" + tipoComida + '\'' +
                '}';
    }
}
