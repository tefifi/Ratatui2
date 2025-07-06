import java.util.ArrayList;
import java.util.Scanner;

public class Usuario {
    private int id;
    private String nombre;
    private float altura;
    private float peso;
    private int edad = 0;
    ArrayList<Condicion> condiciones;
    ArrayList<Receta> recetas;

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
                Error error = new Error();
                error.edad();
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

            case "celiaquia":  // Cambiado de "celiaco" a "celiaquia"
                condiciones.add(new Celiaquia());
                break;

            default:
                System.out.println("Opciones disponibles: Diabetes, Hipertensión, Vegetariano, Vegano, Hipolactasia");
        }
    }


    public void mostrarCondiciones(){
        System.out.println("Tus condiciones son: ");
        for (int i = 0; i<condiciones.size(); i++){
            System.out.println("-"+condiciones.get(i).getNombre());
        }
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
