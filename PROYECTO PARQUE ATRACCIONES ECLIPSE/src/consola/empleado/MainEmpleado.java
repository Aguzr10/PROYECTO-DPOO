package consola.empleado;

import consola.util.Autenticador;
import consola.util.ConsolaUtils;
import consola.util.SistemaParque;
import parque.Administración.Empleados;
import parque.Servicios.Cajero;

import java.util.ArrayList;

public class MainEmpleado {
    public static void main(String[] args) {
        SistemaParque sistema = SistemaParque.getInstancia();
        sistema.cargarDatos();

        if (sistema.getEmpleados().isEmpty()) {
            Cajero cajero = new Cajero("juan", "empleado123", new ArrayList<>(), "manual", "Juan Pérez", "mañana", "Taquilla", "Cajero", 0.10, "entradas");
            sistema.getEmpleados().add(cajero);
            sistema.guardarEmpleados(sistema.getEmpleados());
            System.out.println("Empleado de prueba creado: juan / empleado123");
        }

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

        sistema.guardarDatos();
    }
}
