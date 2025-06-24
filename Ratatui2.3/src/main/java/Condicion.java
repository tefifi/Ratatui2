public abstract class Condicion {
    private String nombre;



    public abstract int obtenerValor();

    public String getNombre() {
        return nombre;
    }

    public Condicion(String nombre) {
        this.nombre = nombre;
    }

}
