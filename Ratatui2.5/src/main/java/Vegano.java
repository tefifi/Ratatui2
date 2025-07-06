public class Vegano extends Condicion {
    public Vegano() {
        super("vegano");
    }
    @Override
    public int obtenerValor() {
        return 0; // No necesitamos un valor numérico para esta condición
    }
}