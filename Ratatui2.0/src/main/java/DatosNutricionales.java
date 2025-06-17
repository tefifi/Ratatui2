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
    private int indiceGlucemico;






        public void infoNutricional(int num){
            String recetasFile = GestorArchivo.leerArchivo("Data/recetas.csv");
            String[] datosRecetas = recetasFile.split("\n");

            String[] datos = datosRecetas[num].split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            try {
                this.calorias = (int) Double.parseDouble(datos[9]);
                this.carbohidratros = (int) Double.parseDouble(datos[10]);
                this.grasas = (int) Double.parseDouble(datos[11]);
                this.proteina = (int) Double.parseDouble(datos[12]);
                this.sodio = (int) Double.parseDouble(datos[13]);
                this.azucar = (int) Double.parseDouble(datos[14]);
                this.colesterol = (int) Double.parseDouble(datos[15]);
                this.fibra = (int) Double.parseDouble(datos[16]);
            } catch (Exception e) {
                System.out.println("Error al procesar línea: " + datosRecetas[num]);
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
                ", indiceGlucemico=" + indiceGlucemico +
                '}';
    }

    public int getSodio() {
        return sodio;
    }

    public int getAzucar() {
        return azucar;
    }
}
