// InterfazAdmin.java
package consola.administrador;

import parque.Administración.Administrador;
import parque.Administración.Empleados;
import parque.Servicios.Cajero;
import consola.util.SistemaParque;
import consola.util.Autenticador;
import consola.util.ConsolaUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InterfazAdmin {

    public static void mostrarMenu(SistemaParque sistema, Administrador admin) {
        System.out.println("\n===== Menú Administrador (" + admin.getNombre() + ") =====");
        System.out.println("1. Ver empleados");
        System.out.println("2. Agregar empleado");
        System.out.println("3. Eliminar empleado");
        System.out.println("4. Salir");

        int opcion;
        do {
            opcion = ConsolaUtils.leerOpcionMenu("Seleccione una opción: ", 1, 4);

            switch (opcion) {
                case 1:
                    sistema.getEmpleados().forEach(e -> System.out.println(e.getNombre()));
                    break;
                case 2:
                    String nombre = ConsolaUtils.leerCadena("Nombre: ");
                    String login = ConsolaUtils.leerCadenaSinEspacios("Login: ");
                    String clave = ConsolaUtils.leerCadena("Contraseña: ");
                    Empleados nuevo = new Empleados(nombre, login, clave);
                    sistema.agregarEmpleado(nuevo);
                    System.out.println("Empleado agregado.");
                    break;
                case 3:
                    String loginEliminar = ConsolaUtils.leerCadenaSinEspacios("Login del empleado a eliminar: ");
                    boolean eliminado = sistema.eliminarEmpleado(loginEliminar);
                    System.out.println(eliminado ? "Empleado eliminado." : "No se encontró el empleado.");
                    break;
                case 4:
                    System.out.println("Saliendo del menú...");
                    break;
            }
        } while (opcion != 4);
    }
}
