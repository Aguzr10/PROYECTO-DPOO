package consola.cliente;

import consola.util.Autenticador;
import consola.util.SistemaParque;
import consola.cliente.InterfazCliente;
import parque.Administración.Cliente;
import java.util.Scanner;

public class MainCliente {
    public static void main(String[] args) {
        SistemaParque sistema = new SistemaParque();
        sistema.cargarDatos();

        Scanner scanner = new Scanner(System.in);
        System.out.println("==== Sistema Parque - Cliente ====");
        System.out.print("Ingrese su usuario: ");
        String login = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine();

        Cliente cliente = Autenticador.autenticarCliente(login, password);

        if (cliente != null) {
            System.out.println("Bienvenido, " + cliente.getNombre() + ".");
            InterfazCliente.iniciar(cliente, sistema);
        } else {
            System.out.println("Credenciales incorrectas. Intente de nuevo.");
        }

        sistema.guardarDatos();
        scanner.close();
    }
}
