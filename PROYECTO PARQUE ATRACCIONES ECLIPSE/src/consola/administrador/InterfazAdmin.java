
package consola.administrador;

import parque.Administración.Administrador;
import parque.Administración.Empleados;
import parque.Servicios.Cajero;
import parque.Tiquetes.Tiquete;
import consola.util.SistemaParque;
import consola.util.Autenticador;
import consola.util.ConsolaUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InterfazAdmin {
    public static void mostrarMenu(SistemaParque sistema) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n=== MENÚ ADMINISTRADOR ===");
            System.out.println("1. Ver empleados");
            System.out.println("2. Crear nuevo empleado (Cajero)");
            System.out.println("3. Eliminar empleado");
            System.out.println("4. Ver atracciones");
            System.out.println("5. Agregar atracción");
            System.out.println("6. Eliminar atracción");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    mostrarEmpleados(sistema);
                    break;
                case "2":
                    String nombre = ConsolaUtils.leerCadena("Nombre: ");
                    String login = ConsolaUtils.leerCadenaSinEspacios("Login: ");
                    String clave = ConsolaUtils.leerCadena("Contraseña: ");
                    String metodoCompra = ConsolaUtils.leerCadena("Método de compra (Taquilla / taquilla virtual): ");
                    String turno = ConsolaUtils.leerCadena("Turno del empleado: ");
                    String lugarAsignado = ConsolaUtils.leerCadena("Lugar asignado del empleado: ");
                    String rol = ConsolaUtils.leerCadena("Rol del empleado: ");
                    Empleados nuevo = new Empleados(login, clave, new ArrayList<Tiquete>(), metodoCompra, nombre, turno, lugarAsignado, rol, 0.0);
                    sistema.agregarEmpleado(nuevo);
                    System.out.println("Empleado agregado.");
                    break;
                case "3":
                    eliminarEmpleado(sistema, scanner);
                    break;
                case "4":
                    mostrarAtracciones(sistema);
                    break;
                case "5":
                    System.out.println("Funcionalidad no implementada aún.");
                    break;
                case "6":
                    System.out.println("Funcionalidad no implementada aún.");
                    break;
                case "0":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void mostrarEmpleados(SistemaParque sistema) {
        System.out.println("=== Lista de empleados ===");
        List<Empleados> empleados = sistema.getEmpleados();
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        } else {
            for (Empleados e : empleados) {
                System.out.println("Nombre: " + e.getNombre() + " | Rol: " + e.getRol() + " | Login: " + e.getLogin());
            }
        }
    }

    private static void crearEmpleado(SistemaParque sistema, Scanner scanner) {
        System.out.println("=== Crear nuevo empleado (Cajero) ===");
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine();
        System.out.print("Turno (mañana/tarde/noche): ");
        String turno = scanner.nextLine();
        System.out.print("Lugar asignado: ");
        String lugar = scanner.nextLine();
        System.out.print("Rol (Cajero): ");
        String rol = scanner.nextLine();
        System.out.print("Descuento (%): ");
        double descuento = Double.parseDouble(scanner.nextLine());
        System.out.print("Tipo de función (entradas, souvenirs, etc.): ");
        String tipoFuncion = scanner.nextLine();

        Cajero nuevo = new Cajero(login, password, new ArrayList<>(), "manual",
                nombre, turno, lugar, rol, descuento, tipoFuncion);

        sistema.agregarEmpleado(nuevo);
        System.out.println("Empleado creado exitosamente.");
    }

    private static void eliminarEmpleado(SistemaParque sistema, Scanner scanner) {
        System.out.println("=== Eliminar empleado ===");
        System.out.print("Ingrese login del empleado a eliminar: ");
        String login = scanner.nextLine();
        boolean eliminado = sistema.eliminarEmpleado(login);
        if (eliminado) {
            System.out.println("Empleado eliminado correctamente.");
        } else {
            System.out.println("No se encontró empleado con ese login.");
        }
    }

    private static void mostrarAtracciones(SistemaParque sistema) {
        System.out.println("=== Lista de atracciones ===");
        if (sistema.getAtracciones().isEmpty()) {
            System.out.println("No hay atracciones registradas.");
        } else {
            sistema.getAtracciones().forEach(a ->
                System.out.println(a.getNombre() + " | Tipo: " + a.getClass().getSimpleName())
            );
        }
    }
}
