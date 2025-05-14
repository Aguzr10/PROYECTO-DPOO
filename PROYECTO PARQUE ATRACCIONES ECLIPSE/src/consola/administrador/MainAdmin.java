package consola.administrador;

import consola.util.ConsolaUtils;
import consola.util.Autenticador;
import parque.Administración.Administrador;
import consola.util.SistemaParque;

public class MainAdmin {

    public static void main(String[] args) {
        SistemaParque sistema = SistemaParque.getInstancia();

        System.out.println("=== Bienvenido, Administrador ===");

        String usuario = ConsolaUtils.leerCadena("Ingrese su nombre de usuario: ");
        String contrasena = ConsolaUtils.leerCadena("Ingrese su contraseña: ");

        Administrador admin = Autenticador.autenticarAdministrador(usuario, contrasena);

        if (admin != null) {
            System.out.println("✅ Autenticación exitosa. Bienvenido, " + admin.getNombre());
            InterfazAdmin.mostrarMenu(sistema, admin);
        } else {
            System.out.println("❌ Autenticación fallida. Cerrando...");
        }
    }
}
