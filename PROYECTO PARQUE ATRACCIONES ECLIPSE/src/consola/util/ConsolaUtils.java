package consola.util;

import java.util.Scanner;

public class ConsolaUtils {

    private static final Scanner scanner = new Scanner(System.in);

    public static String leerCadena(String mensaje) {
        System.out.print(mensaje);
        String entrada;
        do {
            entrada = scanner.nextLine().trim();
            if (entrada.isEmpty()) {
                System.out.println("Entrada no puede estar vacía. Intente de nuevo.");
                System.out.print(mensaje);
            }
        } while (entrada.isEmpty());
        return entrada;
    }

    public static int leerEntero(String mensaje) {
        int numero;
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            try {
                numero = Integer.parseInt(entrada);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número entero. Intente de nuevo.");
            }
        }
        return numero;
    }

    public static int leerOpcionMenu(String mensaje, int min, int max) {
        int opcion;
        do {
            opcion = leerEntero(mensaje);
            if (opcion < min || opcion > max) {
                System.out.println("Opción inválida. Debe estar entre " + min + " y " + max + ".");
            }
        } while (opcion < min || opcion > max);
        return opcion;
    }

    public static boolean confirmar(String mensaje) {
        String entrada;
        do {
            System.out.print(mensaje + " (s/n): ");
            entrada = scanner.nextLine().trim().toLowerCase();
            if (entrada.equals("s")) return true;
            if (entrada.equals("n")) return false;
            System.out.println("Entrada inválida. Escriba 's' para sí o 'n' para no.");
        } while (true);
    }

    public static String leerCadenaSinEspacios(String mensaje) {
        String entrada;
        do {
            entrada = leerCadena(mensaje);
            if (entrada.contains(" ")) {
                System.out.println("La entrada no puede contener espacios. Intente de nuevo.");
            }
        } while (entrada.contains(" "));
        return entrada;
    }

    public static String leerCadenaUpperCase(String mensaje) {
        return leerCadena(mensaje).toUpperCase();
    }
}
