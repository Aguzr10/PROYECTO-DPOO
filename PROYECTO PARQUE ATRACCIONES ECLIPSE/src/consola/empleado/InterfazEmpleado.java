package consola.empleado;

import consola.util.ConsolaUtils;
import consola.util.SistemaParque;
import parque.Administración.Empleados;

public class InterfazEmpleado {

    public static void mostrarMenu(SistemaParque sistema, Empleados empleado) {
        int opcion;
        do {
            System.out.println("\n--- Menú Empleado ---");
            System.out.println("1. Ver información personal");
            System.out.println("2. Consultar atracciones");
            System.out.println("3. Cerrar sesión");

            opcion = ConsolaUtils.leerOpcionMenu("Seleccione una opción: ", 1, 3);

            switch (opcion) {
                case 1:
                    System.out.println("Nombre: " + empleado.getNombre());
                    System.out.println("Login: " + empleado.getLogin());
                    System.out.println("Cargo: " + empleado.getClass().getSimpleName());
                    break;
                case 2:
                    sistema.getAtracciones().forEach(a -> System.out.println("- " + a.getNombre()));
                    break;
                case 3:
                    System.out.println("Sesión cerrada.");
                    break;
            }
        } while (opcion != 3);
    }
}
