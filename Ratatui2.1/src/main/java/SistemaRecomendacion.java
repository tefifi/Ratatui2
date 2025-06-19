
import java.util.Scanner;

public class SistemaRecomendacion {
    Scanner scanner = new Scanner(System.in);
    private boolean aprobado = false;


    private Receta receta = new Receta();
    private Receta recetaAprob;
    private Usuario usuario = new Usuario();


    public void recomendarReceta(){
        recetaAprob = filtrarReceta();
        if (recetaAprob == null){
            System.out.println("Te recomendamos esta receta: "+receta);
        }else {
            System.out.println("Te recomendamos esta receta: " + recetaAprob.getNombre());
            receta.mostrarReceta();

        }

    }

    public void agregarReceta(){
        System.out.println("Desea agregar la receta a su base de datos");
        String resp = scanner.nextLine();
        if (resp.toLowerCase().equals("si")){
            usuario.agregarReceta(recetaAprob);
            System.out.println("Se agrego la receta: "+recetaAprob);
        }else if (resp.toLowerCase().equals("no")){
            System.out.println("No se agrego la receta");
        }else {
            System.out.println("Escribe bien >:(");
        }
    }

    public Receta filtrarReceta(){


        for (int i = 0; i<80; i++){
            receta.atributosReceta();
            if (usuario.condiciones.size()!=0) {

                if (aprobado == false) {


                    switch (usuario.condiciones.get(i).getNombre()) {
                        case "diabetes":
                            if (usuario.condiciones.get(i).obtenerValor() > receta.getDatosNutricionales().getAzucar()) {
                                aprobado = true;
                                System.out.println("Ta funcionando");
                            }
                            break;


                        case "hipertension":
                            if (usuario.condiciones.get(i).obtenerValor() > receta.getDatosNutricionales().getSodio()) {
                                aprobado = true;
                                System.out.println("Ta funcionando?");
                            }
                             break;

                        default:
                            System.out.println("Condición no reconocida: " + usuario.condiciones.get(i).getNombre());

                    }
                }
            }else {
                receta.atributosReceta();
                System.out.println("no hay condiciones");
                return receta;

            }
            break;

        }
        return receta;

    }

    public SistemaRecomendacion(Usuario usuario) {
        this.usuario = usuario;
    }
}
