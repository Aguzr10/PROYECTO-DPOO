package consola.cliente;

import parque.Administración.Cliente;
import consola.util.SistemaParque;
import parque.Tiquetes.Tiquete;

import java.util.List;
import java.util.Scanner;

public class InterfazCliente {
    public static void iniciar(Cliente cliente, SistemaParque sistema) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n=== MENÚ CLIENTE ===");
            System.out.println("1. Ver atracciones disponibles");
            System.out.println("2. Comprar tiquete");
            System.out.println("3. Consultar historial de tiquetes");
            System.out.println("4. Usar tiquete");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    sistema.getAtracciones().forEach(a ->
                            System.out.println(a.getNombre() + " | Tipo: " + a.getClass().getSimpleName()));
                    break;
                case "2":
                    System.out.println("Funcionalidad no implementada aún.");
                    break;
                case "3":
                	List<Tiquete> tiquetes = cliente.getHistorialCompras();
                    if (tiquetes.isEmpty()) {
                        System.out.println("No tiene tiquetes en su historial.");
                    } else {
                        for (Tiquete t : tiquetes) {
                            System.out.println(t.toString());
                        }
                    }
                    break;
                case "4":
                    System.out.println("Funcionalidad no implementada aún.");
                    break;
                case "0":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}
