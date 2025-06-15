import java.util.ArrayList;

public class Usuario {
    private int id;
    private String nombre;
    private float altura;
    private float peso;
    private int edad;
    ArrayList<Condicion> condiciones;
    ArrayList<Receta> recetas;

    public void agregarCondicion(Condicion condicion){
        condiciones.add(condicion);
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
