package consola.administrador;

import parque.Administración.Administrador;
import parque.Servicios.Cajero;
import consola.util.SistemaParque;
import consola.util.Autenticador;

import java.util.ArrayList;
import java.util.Scanner;

public class MainAdmin {
    public static void main(String[] args) {
        SistemaParque sistema = SistemaParque.getInstancia();
        sistema.cargarDatos();

        if (sistema.getAdministradores().isEmpty()) {
            Administrador admin = new Administrador("admin", "admin123", null, "manual", 0, "Administrador General");
            sistema.getAdministradores().add(admin);
            sistema.guardarAdministradores(sistema.getAdministradores());
            System.out.println("Administrador de prueba creado: admin / admin123");
        }

        if (sistema.getEmpleados().isEmpty()) {
            Cajero cajero = new Cajero("juan", "empleado123", new ArrayList<>(), "manual", "Juan Pérez", "mañana", "Taquilla", "Cajero", 0.10, "entradas");
            sistema.getEmpleados().add(cajero);
            sistema.guardarEmpleados(sistema.getEmpleados());
            System.out.println("Empleado de prueba creado: juan / empleado123");
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("==== Sistema Parque - Administrador ====");
        System.out.print("Ingrese su usuario: ");
        String login = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine();

        if (Autenticador.autenticarAdministrador(login, password) != null) {
            System.out.println("Inicio de sesión exitoso.");
            InterfazAdmin.mostrarMenu(sistema);
        } else {
            System.out.println("Autenticación fallida. Verifique sus credenciales.");
        }

        sistema.guardarDatos();
        scanner.close();
    }
}
