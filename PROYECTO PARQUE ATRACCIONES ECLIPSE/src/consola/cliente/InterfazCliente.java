package consola.cliente;

import consola.util.ConsolaUtils;
import consola.util.SistemaParque;
import parque.Administración.Cliente;
import parque.Tiquetes.Tiquete;

public class InterfazCliente {

    public static void mostrarMenu(SistemaParque sistema, Cliente cliente) {
        int opcion;
        do {
            System.out.println("\n--- Menú Cliente ---");
            System.out.println("1. Ver información personal");
            System.out.println("2. Ver historial de compras");
            System.out.println("3. Ver atracciones");
            System.out.println("4. Cerrar sesión");

            opcion = ConsolaUtils.leerOpcionMenu("Seleccione una opción: ", 1, 4);

            switch (opcion) {
                case 1:
                    System.out.println("Nombre: " + cliente.getNombre());
                    System.out.println("Login: " + cliente.getLogin());
                    break;

                case 2:
                    System.out.println("Historial de compras:");
                    if (cliente.getHistorialCompras().isEmpty()) {
                        System.out.println(" - No hay compras registradas.");
                    } else {
                        for (Tiquete t : cliente.getHistorialCompras()) {
                            System.out.println(" - Categoría: " + t.getCategoriaTiquete() +
                                               " | Precio: $" + t.getPrecio() +
                                               " | Usado: " + (t.isUsoValidado() ? "Sí" : "No"));
                        }
                    }
                    break;

                case 3:
                    System.out.println("Atracciones disponibles:");
                    sistema.getAtracciones().forEach(a -> System.out.println("- " + a.getNombre()));
                    break;

                case 4:
                    System.out.println("Sesión cerrada.");
                    break;
            }
        } while (opcion != 4);
    }
}
