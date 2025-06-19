import java.util.Random;

public class Receta {

    private String nombre;
    private String cantPorciones;
    private String listoEn;
    private String vegano;
    private String vegetariano;
    private String saludable;
    private String tipoComida;
    private String ingredientes;



    private final DatosNutricionales datosNutricionales = new DatosNutricionales();


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
                this.cantPorciones = datos[1];
                this.listoEn = datos[2];
                this.vegano = datos[21];
                this.vegetariano = datos[22];
                this.saludable = datos[23];
                this.tipoComida = datos[30];
                this.ingredientes = datos[31];
            } else {
                System.out.println("Línea incompleta: " + datosRecetas[num]);
            }
        } catch (Exception e) {
            System.out.println("Error al procesar línea: " + datosRecetas[num]);
        }
    }

    public void mostrarReceta() {
        System.out.println("Tipo de comida: "+this.tipoComida);
        System.out.println("Cantidad de porciones: "+this.cantPorciones);
        System.out.println("Listo en "+this.listoEn+" minutos");
        System.out.println("Vegano: "+this.vegano);
        System.out.println("Vegetariano: "+this.vegetariano);
        System.out.println("Saludable: "+this.saludable);
        System.out.println("Ingredientes: "+this.ingredientes);
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
