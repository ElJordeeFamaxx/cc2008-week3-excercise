public class Digimon {

        private String nombre;
        private TipoDigimon tipo;
        private int ataque;
        private int defensa;
        private Digievolucion digievolucion;

        public Digimon(String nombre, TipoDigimon tipo,
                        int ataque, int defensa,
                        Digievolucion digievolucion) {

                this.nombre = nombre;
                this.tipo = tipo;
                this.ataque = ataque;
                this.defensa = defensa;
                this.digievolucion = digievolucion;
        }

        public int calcularEfectoTipo(Digimon enemigo) {

                TipoDigimon tipoEnemigo = enemigo.getTipo();

                // ATAQUES EFECTIVOS: +20
                if (tipo == TipoDigimon.FUEGO
                                && tipoEnemigo == TipoDigimon.PLANTA) {
                        return 20;
                }

                if (tipo == TipoDigimon.PLANTA
                                && tipoEnemigo == TipoDigimon.AGUA) {
                        return 20;
                }

                if (tipo == TipoDigimon.AGUA
                                && tipoEnemigo == TipoDigimon.FUEGO) {
                        return 20;
                }

                if (tipo == TipoDigimon.ELECTRICO
                                && tipoEnemigo == TipoDigimon.AGUA) {
                        return 20;
                }

                // ATAQUES DEBILES: -10
                if (tipo == TipoDigimon.PLANTA
                                && tipoEnemigo == TipoDigimon.FUEGO) {
                        return -10;
                }

                if (tipo == TipoDigimon.AGUA
                                && tipoEnemigo == TipoDigimon.PLANTA) {
                        return -10;
                }

                if (tipo == TipoDigimon.FUEGO
                                && tipoEnemigo == TipoDigimon.AGUA) {
                        return -10;
                }

                if (tipo == TipoDigimon.AGUA
                                && tipoEnemigo == TipoDigimon.ELECTRICO) {
                        return -10;
                }

                // NEUTRAL
                return 0;
        }

        public int calcularAtaqueTotal(Digimon enemigo) {
                return ataque + calcularEfectoTipo(enemigo);
        }

        public String getNombre() {
                return nombre;
        }

        public TipoDigimon getTipo() {
                return tipo;
        }

        public int getAtaque() {
                return ataque;
        }

        public int getDefensa() {
                return defensa;
        }

        public Digievolucion getDigievolucion() {
                return digievolucion;
        }
}