package consola.administrador;

import consola.util.ConsolaUtils;
import consola.util.Autenticador;
import parque.Administración.Administrador;

public class MainAdmin {

    public static void main(String[] args) {
        System.out.println("==== Sistema Parque - Administrador ====");
        String usuario = ConsolaUtils.leerCadenaSinEspacios("Ingrese su usuario: ");
        String clave = ConsolaUtils.leerCadena("Ingrese su contraseña: ");
        Administrador admin = Autenticador.autenticarAdministrador(usuario, clave);
        if (admin != null) {
            InterfazAdmin interfaz = new InterfazAdmin(admin);
            interfaz.mostrarMenu();
        } else {
            System.out.println("Autenticación fallida. Verifique sus credenciales.");
        }
    }
}
