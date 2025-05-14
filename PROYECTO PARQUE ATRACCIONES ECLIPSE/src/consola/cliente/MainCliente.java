package consola.cliente;

import consola.util.Autenticador;
import consola.util.SistemaParque;
import parque.Administración.Cliente;

import java.util.Scanner;

public class MainCliente {

    public static void main(String[] args) {
        SistemaParque sistema = new SistemaParque();
        sistema.cargarDatos(); 

        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al sistema del parque - CLIENTE");
        System.out.print("Ingrese su login: ");
        String login = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine();

        Cliente cliente = Autenticador.autenticarCliente(login, password);

        if (cliente == null) {
            System.out.println("Cliente no encontrado. ¿Desea registrarse? (s/n): ");
            String opcion = scanner.nextLine();
            if (opcion.equalsIgnoreCase("s")) {
                System.out.print("Ingrese su nombre: ");
                String nombre = scanner.nextLine();

                cliente = new Cliente(login, password, null, "online", nombre, null);
                sistema.getClientes().add(cliente);
                sistema.guardarDatos();
                System.out.println("Registro exitoso. Iniciando sesión...");
            } else {
                System.out.println("Saliendo del sistema.");
                return;
            }
        }

        InterfazCliente.mostrarMenu(sistema, cliente);
    }
}
