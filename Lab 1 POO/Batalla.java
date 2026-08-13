public class Batalla {

    private Entrenador entrenador1;
    private Entrenador entrenador2;
    private int rondaActual;

    private int efectoSiguiente1;
    private int efectoSiguiente2;

    public Batalla(Entrenador entrenador1, Entrenador entrenador2) {

        this.entrenador1 = entrenador1;
        this.entrenador2 = entrenador2;

        rondaActual = 1;

        efectoSiguiente1 = 0;
        efectoSiguiente2 = 0;
    }

    public int calcularAtaqueTotal(Digimon atacante,
            Digimon enemigo) {

        return atacante.getAtaque()
                + atacante.calcularEfectoTipo(enemigo);
    }

    public int aplicarEfectoAnterior(int entrenador,
            int ataqueTotal) {

        if (entrenador == 1) {

            ataqueTotal += efectoSiguiente1;
            efectoSiguiente1 = 0;

        } else {

            ataqueTotal += efectoSiguiente2;
            efectoSiguiente2 = 0;
        }

        return ataqueTotal;
    }

    public int usarDigievolucion(Digimon digimon,
            int entrenador,
            int ataqueTotal) {

        Digievolucion digi = digimon.getDigievolucion();

        int valor = digi.getValorEfecto();

        // AUMENTAR ATAQUE
        if (digi.getEfecto() == TipoEfecto.AUMENTAR_ATAQUE) {

            ataqueTotal += valor;

            if (entrenador == 1) {
                efectoSiguiente1 = valor;
            } else {
                efectoSiguiente2 = valor;
            }
        }

        // AUMENTAR DEFENSA
        if (digi.getEfecto() == TipoEfecto.AUMENTAR_DEFENSA) {

            ataqueTotal += valor;

            if (entrenador == 1) {
                efectoSiguiente1 = valor;
            } else {
                efectoSiguiente2 = valor;
            }
        }

        return ataqueTotal;
    }

    public int aplicarDanoEnemigo(Digimon digimon,
            int ataqueEnemigo) {

        int valor = digimon.getDigievolucion().getValorEfecto();

        ataqueEnemigo -= valor;

        return ataqueEnemigo;
    }

    public int determinarGanadorRonda(int ataque1,
            int ataque2) {

        if (ataque1 > ataque2) {

            entrenador1.registrarVictoria();
            return 1;

        } else if (ataque2 > ataque1) {

            entrenador2.registrarVictoria();
            return 2;
        }

        return 0;
    }

    public Entrenador determinarGanadorBatalla() {

        if (entrenador1.getRondasGanadas() > entrenador2.getRondasGanadas()) {

            return entrenador1;
        }

        if (entrenador2.getRondasGanadas() > entrenador1.getRondasGanadas()) {

            return entrenador2;
        }

        return null;
    }

    public void avanzarRonda() {
        rondaActual++;
    }

    public int getRondaActual() {
        return rondaActual;
    }
}