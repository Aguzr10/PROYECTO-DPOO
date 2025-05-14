package consola.empleado;

import consola.util.SistemaParque;
import parque.Administración.Empleados;
import consola.util.Autenticador;

import java.util.Scanner;

public class MainEmpleado {
    public static void main(String[] args) {
        SistemaParque sistema = SistemaParque.getInstancia();
        sistema.cargarDatos();

        Scanner scanner = new Scanner(System.in);
        System.out.println("==== Sistema Parque - Empleado ====");
        System.out.print("Ingrese su usuario: ");
        String login = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine();

        Empleados empleado = Autenticador.autenticarEmpleado(login, password);

        if (empleado != null) {
            System.out.println("Bienvenido, " + empleado.getNombre() + ".");
            InterfazEmpleado.iniciar(empleado, sistema);
        } else {
            System.out.println("Credenciales incorrectas.");
        }

        sistema.guardarDatos();
        scanner.close();
    }
}
