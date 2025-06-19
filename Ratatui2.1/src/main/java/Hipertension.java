public class Hipertension extends Condicion{
    private int maxSodio;

    public Hipertension(){
        super("hipertension");
    }

    public int obtenerValor(){
        this.maxSodio = 110;
        return maxSodio;
    }
}
