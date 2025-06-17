public abstract class Condicion {
    private String nombre;
    private int restriccion;

    public abstract int obtenerValor();

    public String getNombre() {
        return nombre;
    }

    public Condicion(String nombre) {
        this.nombre = nombre;
    }
}
