import java.util.ArrayList;
import java.util.Scanner;

public class Usuario {
    private int id;
    private String nombre;
    private float altura;
    private float peso;
    private int edad;
    ArrayList<Condicion> condiciones;
    ArrayList<Receta> recetas;

    Scanner scanner = new Scanner(System.in);

    public void agregarCondicion(){
        System.out.println("Que condicion quieres agregar");
        if (scanner.next()=="diabetes"){
            Diabetes diabetes = new Diabetes();
            condiciones.add(diabetes);
        }
    }

    public void agregarReceta(Receta receta){
        recetas.add(receta);
    }

    public float crearIMC(){
       float imc = peso/(altura*altura);
        System.out.println("Tu Indice de Masa Corporal es: "+imc);
       return imc;
    }

    public ArrayList<Condicion> getCondiciones() {
        return condiciones;
    }
}
