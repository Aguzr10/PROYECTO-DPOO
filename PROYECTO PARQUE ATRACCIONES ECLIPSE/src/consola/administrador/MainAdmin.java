// MainAdmin.java
package consola.administrador;

import parque.Administración.Administrador;
import consola.util.SistemaParque;
import consola.util.Autenticador;

import java.util.Scanner;

public class MainAdmin {
    public static void main(String[] args) {
        SistemaParque sistema = new SistemaParque();
        sistema.cargarDatos();  

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
