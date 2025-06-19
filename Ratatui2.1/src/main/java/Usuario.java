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

        switch (scanner.next().toLowerCase()){
            case "diabetes":
                Diabetes diabetes = new Diabetes();
                condiciones.add(diabetes);
                break;

            case "hipertension":
                Hipertension hipertension = new Hipertension();
                condiciones.add(hipertension);
                break;
        }
    }

    public void agregarReceta(Receta receta){
        recetas.add(receta);
    }

    public void datosIMC(){
        System.out.println("Vamos a crear un Indice de Masa Corporal");
        System.out.println("Ingresa tu altura");
        this.altura=scanner.nextFloat();
        System.out.println("Ingresa tu peso");
        this.peso=scanner.nextFloat();

    }
    public float calcularIMC(){
       float imc = peso/(altura*altura);
        System.out.println("Tu Indice de Masa Corporal es: "+imc);
       return imc;
    }


    public void mostrarRecetas(){
        for (Receta r: recetas){
            System.out.println(r);
        }
    }


    public Usuario() {
        this.condiciones = new ArrayList<>();
        this.recetas = new ArrayList<>();
    }
}
