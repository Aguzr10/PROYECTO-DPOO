package consola.util;

import parque.Administración.Administrador;
import parque.Administración.Cliente;
import parque.Administración.Empleados;


import java.util.List;

public class Autenticador {

    public static Administrador autenticarAdministrador(String usuario, String clave) {
        List<Administrador> admins = SistemaParque.getInstancia().getAdministradores();
        for (Administrador a : admins) {
            if (a.getLogin().equals(usuario) && a.autenticar(clave)) {
                return a;
            }
        }
        return null;
    }

    public static Cliente autenticarCliente(String usuario, String clave) {
        List<Cliente> clientes = SistemaParque.getInstancia().getClientes();
        for (Cliente c : clientes) {
            if (c.getLogin().equals(usuario) && c.autenticar(clave)) {
                return c;
            }
        }
        return null;
    }

    public static Empleados autenticarEmpleado(String usuario, String clave) {
        List<Empleados> empleados = SistemaParque.getInstancia().getEmpleados();
        for (Empleados e : empleados) {
            if (e.getLogin().equals(usuario) && e.autenticar(clave)) {
                return e;
            }
        }
        return null;
    }
}
