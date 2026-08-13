import java.util.Random;

public class Digievolucion {

    private String nombre;
    private TipoEfecto efecto;
    private int valorEfecto;
    private int probabilidadActivacion;

    public Digievolucion(String nombre, TipoEfecto efecto,
            int valorEfecto, int probabilidadActivacion) {

        this.nombre = nombre;
        this.efecto = efecto;
        this.valorEfecto = valorEfecto;
        this.probabilidadActivacion = probabilidadActivacion;
    }

    public boolean intentarActivar() {

        Random random = new Random();

        int numero = random.nextInt(101);

        return numero <= probabilidadActivacion;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoEfecto getEfecto() {
        return efecto;
    }

    public int getValorEfecto() {
        return valorEfecto;
    }

    public int getProbabilidadActivacion() {
        return probabilidadActivacion;
    }
}