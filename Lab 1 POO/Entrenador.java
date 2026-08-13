public class Entrenador {

    private String nombre;
    private Digimon[] equipo;
    private boolean[] utilizados;
    private int rondasGanadas;

    public Entrenador(String nombre) {

        this.nombre = nombre;
        this.equipo = new Digimon[4];
        this.utilizados = new boolean[4];
        this.rondasGanadas = 0;
    }

    public void agregarDigimon(Digimon digimon, int posicion) {

        if (posicion >= 0 && posicion < equipo.length) {
            equipo[posicion] = digimon;
        }
    }

    public Digimon seleccionarDigimon(int posicion) {

        if (posicion < 0 || posicion >= equipo.length) {
            return null;
        }

        if (utilizados[posicion]) {
            return null;
        }

        if (equipo[posicion] == null) {
            return null;
        }

        utilizados[posicion] = true;

        return equipo[posicion];
    }

    public boolean estaDisponible(int posicion) {

        if (posicion < 0 || posicion >= equipo.length) {
            return false;
        }

        return equipo[posicion] != null && !utilizados[posicion];
    }

    public void registrarVictoria() {
        rondasGanadas++;
    }

    public String getNombre() {
        return nombre;
    }

    public Digimon[] getEquipo() {
        return equipo;
    }

    public int getRondasGanadas() {
        return rondasGanadas;
    }
}