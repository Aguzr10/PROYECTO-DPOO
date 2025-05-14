package consola.empleado;

import consola.util.Autenticador;
import consola.util.ConsolaUtils;
import consola.util.SistemaParque;
import parque.Administración.Empleados;

public class MainEmpleado {
    public static void main(String[] args) {
        SistemaParque sistema = SistemaParque.getInstancia();
        sistema.cargarDatos();

        System.out.println("=== Inicio de Sesión - Empleado ===");
        String usuario = ConsolaUtils.leerCadenaSinEspacios("Usuario: ");
        String clave = ConsolaUtils.leerCadena("Clave: ");

        Empleados empleado = Autenticador.autenticarEmpleado(usuario, clave);
        if (empleado != null) {
            System.out.println("Bienvenido, " + empleado.getNombre() + ".");
            InterfazEmpleado.mostrarMenu(sistema, empleado);
        } else {
            System.out.println("Credenciales inválidas. Saliendo...");
        }
    }
}
