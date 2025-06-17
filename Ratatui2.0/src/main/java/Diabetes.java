public class Diabetes extends Condicion{
    private int maxAzucar;

    public Diabetes(){
        super("diabetes");
    }

    public int obtenerValor(){
        this.maxAzucar = 10;
        return maxAzucar;
    }


}
