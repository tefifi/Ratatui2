public class Vegetariano extends Condicion {
    public Vegetariano() {
        super("vegetariano");
    }
    @Override
    public int obtenerValor() {
        return 0; // No necesitamos un valor numérico para esta condición
    }
}