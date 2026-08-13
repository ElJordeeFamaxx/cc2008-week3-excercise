import java.util.Scanner;

public class Main {

        public static void main(String[] args) {

                Scanner scanner = new Scanner(System.in);

                System.out.println("=== BATALLA DIGIMON ===");

                System.out.print("Nombre del entrenador 1: ");
                Entrenador entrenador1 = new Entrenador(scanner.nextLine());

                System.out.print("Nombre del entrenador 2: ");
                Entrenador entrenador2 = new Entrenador(scanner.nextLine());

                // Digievoluciones
                Digievolucion adult = new Digievolucion(
                                "Adult", TipoEfecto.AUMENTAR_ATAQUE, 15, 30);

                Digievolucion mega = new Digievolucion(
                                "Mega", TipoEfecto.AUMENTAR_DEFENSA, 20, 30);

                Digievolucion ultimate = new Digievolucion(
                                "Ultimate", TipoEfecto.DANAR_ENEMIGO, 10, 30);

                // Equipo 1
                entrenador1.agregarDigimon(
                                new Digimon("Agumon", TipoDigimon.FUEGO,
                                                50, 30, adult),
                                0);

                entrenador1.agregarDigimon(
                                new Digimon("Divermon", TipoDigimon.AGUA,
                                                45, 40, mega),
                                1);

                entrenador1.agregarDigimon(
                                new Digimon("Palmon", TipoDigimon.PLANTA,
                                                40, 35, ultimate),
                                2);

                entrenador1.agregarDigimon(
                                new Digimon("Thundermon", TipoDigimon.ELECTRICO,
                                                48, 32, adult),
                                3);

                // Equipo 2
                entrenador2.agregarDigimon(
                                new Digimon("Biyomon", TipoDigimon.FUEGO,
                                                46, 32, mega),
                                0);

                entrenador2.agregarDigimon(
                                new Digimon("Zudomon", TipoDigimon.AGUA,
                                                44, 38, adult),
                                1);

                entrenador2.agregarDigimon(
                                new Digimon("Floramon", TipoDigimon.PLANTA,
                                                42, 34, ultimate),
                                2);

                entrenador2.agregarDigimon(
                                new Digimon("Jupitermon", TipoDigimon.ELECTRICO,
                                                47, 31, mega),
                                3);

                Batalla batalla = new Batalla(entrenador1, entrenador2);

                // 4 rondas
                for (int ronda = 1; ronda <= 4; ronda++) {

                        System.out.println("\n=== RONDA " + ronda + " ===");

                        Digimon digimon1 = elegirDigimon(scanner, entrenador1);
                        Digimon digimon2 = elegirDigimon(scanner, entrenador2);

                        System.out.println(
                                        "\n" + digimon1.getNombre()
                                                        + " VS "
                                                        + digimon2.getNombre());

                        System.out.println(
                                        digimon1.getTipo()
                                                        + " VS "
                                                        + digimon2.getTipo());

                        int accion1 = elegirAccion(scanner, entrenador1);
                        int accion2 = elegirAccion(scanner, entrenador2);

                        int ataque1 = batalla.calcularAtaqueTotal(
                                        digimon1, digimon2);

                        int ataque2 = batalla.calcularAtaqueTotal(
                                        digimon2, digimon1);

                        ataque1 = batalla.aplicarEfectoAnterior(1, ataque1);
                        ataque2 = batalla.aplicarEfectoAnterior(2, ataque2);

                        // Digievolución jugador 1
                        if (accion1 == 2) {

                                Digievolucion digi = digimon1.getDigievolucion();

                                System.out.println(
                                                "\n" + digimon1.getNombre()
                                                                + " intenta usar "
                                                                + digi.getNombre());

                                if (digi.intentarActivar()) {

                                        System.out.println("¡La Digievolución se activó!");

                                        if (digi.getEfecto() == TipoEfecto.DANAR_ENEMIGO) {
                                                ataque2 = batalla.aplicarDanoEnemigo(
                                                                digimon1, ataque2);
                                        } else {
                                                ataque1 = batalla.usarDigievolucion(
                                                                digimon1, 1, ataque1);
                                        }

                                } else {
                                        System.out.println("La Digievolución no se activó.");
                                }
                        }

                        // Digievolución jugador 2
                        if (accion2 == 2) {

                                Digievolucion digi = digimon2.getDigievolucion();

                                System.out.println(
                                                "\n" + digimon2.getNombre()
                                                                + " intenta usar "
                                                                + digi.getNombre());

                                if (digi.intentarActivar()) {

                                        System.out.println("¡La Digievolución se activó!");

                                        if (digi.getEfecto() == TipoEfecto.DANAR_ENEMIGO) {
                                                ataque1 = batalla.aplicarDanoEnemigo(
                                                                digimon2, ataque1);
                                        } else {
                                                ataque2 = batalla.usarDigievolucion(
                                                                digimon2, 2, ataque2);
                                        }

                                } else {
                                        System.out.println("La Digievolución no se activó.");
                                }
                        }

                        // Resultado de la ronda
                        System.out.println(
                                        "\n" + digimon1.getNombre()
                                                        + " - Ataque total: " + ataque1);

                        System.out.println(
                                        digimon2.getNombre()
                                                        + " - Ataque total: " + ataque2);

                        int resultado = batalla.determinarGanadorRonda(
                                        ataque1, ataque2);

                        if (resultado == 1) {
                                System.out.println(
                                                "Gana " + entrenador1.getNombre());

                        } else if (resultado == 2) {
                                System.out.println(
                                                "Gana " + entrenador2.getNombre());

                        } else {
                                System.out.println("Empate");
                        }

                        System.out.println(
                                        "Marcador: "
                                                        + entrenador1.getNombre() + " "
                                                        + entrenador1.getRondasGanadas()
                                                        + " - "
                                                        + entrenador2.getRondasGanadas() + " "
                                                        + entrenador2.getNombre());

                        batalla.avanzarRonda();
                }

                // Ganador final
                Entrenador ganador = batalla.determinarGanadorBatalla();

                System.out.println("\n=== RESULTADO FINAL ===");

                if (ganador == null) {
                        System.out.println("La batalla terminó en empate.");
                } else {
                        System.out.println(
                                        "Ganador: " + ganador.getNombre());
                }

                scanner.close();
        }

        // Elegir Digimon
        public static Digimon elegirDigimon(
                        Scanner scanner, Entrenador entrenador) {

                System.out.println(
                                "\nDigimon de " + entrenador.getNombre() + ":");

                Digimon[] equipo = entrenador.getEquipo();

                for (int i = 0; i < equipo.length; i++) {

                        if (entrenador.estaDisponible(i)) {

                                System.out.println(
                                                (i + 1) + ". "
                                                                + equipo[i].getNombre()
                                                                + " - "
                                                                + equipo[i].getTipo());
                        }
                }

                System.out.print("Elige un Digimon: ");
                int opcion = scanner.nextInt() - 1;

                while (!entrenador.estaDisponible(opcion)) {

                        System.out.print(
                                        "No disponible. Elige otro: ");

                        opcion = scanner.nextInt() - 1;
                }

                return entrenador.seleccionarDigimon(opcion);
        }

        // Elegir acción
        public static int elegirAccion(
                        Scanner scanner, Entrenador entrenador) {

                System.out.println(
                                "\n" + entrenador.getNombre()
                                                + ", ¿qué deseas hacer?");

                System.out.println("1. Atacar");
                System.out.println("2. Usar Digievolución");

                System.out.print("Opción: ");
                int opcion = scanner.nextInt();

                while (opcion != 1 && opcion != 2) {

                        System.out.print(
                                        "Elige solamente 1 o 2: ");

                        opcion = scanner.nextInt();
                }

                return opcion;
        }
}