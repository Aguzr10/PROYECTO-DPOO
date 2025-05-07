package parque;

import java.util.Scanner;

public class MenuPruebas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("\n=== MENÚ DE PRUEBAS ===");
            System.out.println("1. Crear atracción mecánica válida");
            System.out.println("2. Asignar operador no certificado");
            System.out.println("3. Validar acceso con tiquete familiar a atracción oro");
            System.out.println("4. Validar acceso con FastPass");
            System.out.println("5. Marcar tiquete como usado");
            System.out.println("6. Validar operación con mal clima");
            System.out.println("7. Crear TiqueteTemporada con fechas inválidas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            String entrada = scanner.nextLine().trim();

            if (entrada.isEmpty() || !entrada.matches("\\d+")) {
                System.out.println("Opción inválida. Intente de nuevo.");
                continue;
            }

            opcion = Integer.parseInt(entrada);

            switch (opcion) {
                case 1:
                    TestParque.crearAtraccionMecanicaValida();
                    break;
                case 2:
                    TestParque.asignarOperadorNoCertificado();
                    break;
                case 3:
                    TestParque.validarAccesoTiqueteFamiliarAAtraccionOro();
                    break;
                case 4:
                    TestParque.validarAccesoFastPass();
                    break;
                case 5:
                    TestParque.marcarTiqueteComoUsado();
                    break;
                case 6:
                    TestParque.validarOperacionConMalClima();
                    break;
                case 7:
                    TestParque.crearTiqueteTemporadaConFechasInvalidas();
                    break;
                case 0:
                    System.out.println("Saliendo del menú de pruebas...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }

        } while (opcion != 0);

        scanner.close();
    }
}
