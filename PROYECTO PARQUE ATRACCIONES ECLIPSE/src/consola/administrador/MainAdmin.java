package consola.administrador;

import parque.Administración.Administrador;
import parque.Servicios.Cajero;
import consola.util.SistemaParque;
import consola.util.Autenticador;
import java.util.ArrayList;
import java.util.Scanner;

public class MainAdmin {
    public static void main(String[] args) {
        System.out.println("==== Sistema Parque - Administrador ====");
        
        SistemaParque sistema = SistemaParque.getInstancia();
        
        sistema.cargarDatos();
        
        if (sistema.getAdministradores().isEmpty()) {
            Administrador admin = new Administrador("juan", "empleado123", null, "Taquilla Virtual", 0, "Juan Perez");
            sistema.getAdministradores().add(admin);
            sistema.guardarAdministradores(sistema.getAdministradores());
            System.out.println("Administrador de prueba creado");
            System.out.println("Usuario: juan | Clave: empleado123");
        }
        

        if (sistema.getEmpleados().isEmpty()) {
            Cajero cajero = new Cajero("luisD123", "luis1234", new ArrayList<>(), "TaquillaVirtual", 
                                     "Luis Diaz", "Apertura", "Taquilla", "Cajero", 0.15, "Cajero");
            sistema.getEmpleados().add(cajero);
            sistema.guardarEmpleados(sistema.getEmpleados());
            System.out.println("Empleado de prueba creado: luisD123 / luis1234");
        }
        
        if (!sistema.getAdministradores().isEmpty()) {
            System.out.println("\n=== ADMINISTRADORES REGISTRADOS ===");
            for (Administrador admin : sistema.getAdministradores()) {
                System.out.println("Administrador de prueba - Usuario: " + admin.getLogin() + " | Clave: empleado123");
            }
            System.out.println("===================================\n");
        }
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese su usuario: ");
        String login = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine();
        

        Administrador adminAutenticado = Autenticador.autenticarAdministrador(login, password);
        if (adminAutenticado != null) {
            System.out.println("¡Inicio de sesión exitoso!");
            System.out.println("Bienvenido, " + adminAutenticado.getNombre());
            InterfazAdmin.mostrarMenu(sistema);
        } else {
            System.out.println("❌ Autenticación fallida.");
            System.out.println("Verifique sus credenciales.");
        }
        
        sistema.guardarDatos();
        scanner.close();
        System.out.println("Sistema finalizado.");
    }
}