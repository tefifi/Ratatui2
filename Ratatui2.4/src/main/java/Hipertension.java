public class Hipertension extends Condicion {
    private final int maxSodio;
    public Hipertension() {
        super("hipertension");
        this.maxSodio = 150;}
    @Override
    public int obtenerValor() {
        return maxSodio;
    }
}