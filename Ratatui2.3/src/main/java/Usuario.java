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

    public void ingresarDatos(){
        System.out.println("Ingresa tu nombre");
        nombre=scanner.next();
        System.out.println("Ingresa tu edad");
        edad= scanner.nextInt();
    }

    public void agregarCondicion(){
        System.out.println("¿Que condicion quieres agregar?");

        switch (scanner.next().toLowerCase()){
            case "diabetes":
                Diabetes diabetes = new Diabetes();
                condiciones.add(diabetes);
                break;

            case "hipertension":
                Hipertension hipertension = new Hipertension();
                condiciones.add(hipertension);
                break;

            default:
                System.out.println("Las opciones disponibles de condiciones son: Diabetes, Hipertension");


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
        if (imc<18.5){
            System.out.println("Tienes un peso bajo");
        }else if (imc<25){
            System.out.println("Tienes un peso normal");
        } else if (imc<30) {
            System.out.println("Tienes sobrepeso");
        } else if (imc<35) {
            System.out.println("Tienes obesidad leve");
        } else if (imc<40) {
            System.out.println("Tienes obesidad media");
        } else if (imc>40) {
            System.out.println("Tienes obesidad morbida");
        }
        System.out.println("El indice de masa corporal no es del todo preciso, por favor acudir a un profesional para mayor informacion");
        return imc;
    }


    public void mostrarRecetas(){
        for (Receta r: recetas){
            System.out.println("-"+r);
        }
    }


    public Usuario() {
        this.condiciones = new ArrayList<>();
        this.recetas = new ArrayList<>();
    }
}
