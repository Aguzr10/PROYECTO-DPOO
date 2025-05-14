package consola.cliente;

import consola.util.Autenticador;
import consola.util.ConsolaUtils;
import consola.util.SistemaParque;
import parque.Administración.Cliente;

public class MainCliente {
    public static void main(String[] args) {
        SistemaParque sistema = SistemaParque.getInstancia();
        sistema.cargarDatos();

        System.out.println("=== Inicio de Sesión - Cliente ===");
        String usuario = ConsolaUtils.leerCadenaSinEspacios("Usuario: ");
        String clave = ConsolaUtils.leerCadena("Clave: ");

        Cliente cliente = Autenticador.autenticarCliente(usuario, clave);
        if (cliente != null) {
            System.out.println("Bienvenido, " + cliente.getNombre() + ".");
            InterfazCliente.mostrarMenu(sistema, cliente);
        } else {
            System.out.println("Credenciales inválidas. Saliendo...");
        }
    }
}
