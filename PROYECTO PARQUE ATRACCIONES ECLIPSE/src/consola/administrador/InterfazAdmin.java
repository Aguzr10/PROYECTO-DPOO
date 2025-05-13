package consola.administrador;

import consola.util.ConsolaUtils;
import parque.Administración.Administrador;
import parque.Administración.Empleados;
import parque.Atracción.Atracciones;
import consola.util.SistemaParque;


import java.util.List;

public class InterfazAdmin {

    private final Administrador adminActual;
    private final SistemaParque sistema = SistemaParque.getInstancia();

    public InterfazAdmin(Administrador admin) {
        this.adminActual = admin;
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n==== Menú Administrador ====");
            System.out.println("1. Ver atracciones");
            System.out.println("2. Crear nueva atracción");
            System.out.println("3. Modificar atracción");
            System.out.println("4. Eliminar atracción");
            System.out.println("5. Ver empleados");
            System.out.println("6. Crear nuevo empleado");
            System.out.println("7. Modificar empleado");
            System.out.println("8. Eliminar empleado");
            System.out.println("9. Cambiar credenciales de usuario");
            System.out.println("10. Salir");
            opcion = ConsolaUtils.leerOpcionMenu("Seleccione una opción: ", 1, 10);

            switch (opcion) {
                case 1 -> mostrarAtracciones();
                case 2 -> crearAtraccion();
                case 3 -> modificarAtraccion();
                case 4 -> eliminarAtraccion();
                case 5 -> mostrarEmpleados();
                case 6 -> crearEmpleado();
                case 7 -> modificarEmpleado();
                case 8 -> eliminarEmpleado();
                case 9 -> cambiarCredenciales();
                case 10 -> System.out.println("Saliendo del menú de administrador.");
            }
        } while (opcion != 10);
    }

    private void mostrarAtracciones() {
        List<Atracciones> atracciones = sistema.getAtracciones();
        if (atracciones.isEmpty()) {
            System.out.println("No hay atracciones registradas.");
        } else {
            atracciones.forEach(a -> System.out.println(a.toString()));
        }
    }

    private void crearAtraccion() {
        
        System.out.println(">> Crear atracción aún no implementado completamente.");
    }

    private void modificarAtraccion() {
        System.out.println(">> Modificar atracción aún no implementado completamente.");
    }

    private void eliminarAtraccion() {
        System.out.println(">> Eliminar atracción aún no implementado completamente.");
    }

    private void mostrarEmpleados() {
        List<Empleados> empleados = sistema.getEmpleados();
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        } else {
            empleados.forEach(e -> System.out.println(e.toString()));
        }
    }

    private void crearEmpleado() {
        String nombre = ConsolaUtils.leerCadena("Nombre: ");
        String login = ConsolaUtils.leerCadenaSinEspacios("Login: ");
        String clave = ConsolaUtils.leerCadena("Clave: ");
        Empleados nuevo = new Empleados(nombre, login, clave); 
        List<Empleados> empleados = sistema.getEmpleados();
        empleados.add(nuevo);
        sistema.guardarEmpleados(empleados);
        System.out.println("Empleado creado y guardado.");
    }

    private void modificarEmpleado() {
        System.out.println(">> Modificar empleado aún no implementado completamente.");
    }

    private void eliminarEmpleado() {
        System.out.println(">> Eliminar empleado aún no implementado completamente.");
    }

    private void cambiarCredenciales() {
        String loginActual = ConsolaUtils.leerCadenaSinEspacios("Usuario actual: ");
        List<Empleados> empleados = sistema.getEmpleados();
        for (Empleados emp : empleados) {
            if (emp.getLogin().equals(loginActual)) {
                String nuevoLogin = ConsolaUtils.leerCadenaSinEspacios("Nuevo login: ");
                String nuevaClave = ConsolaUtils.leerCadena("Nueva clave: ");
                emp.setLogin(nuevoLogin);
                emp.setClave(nuevaClave);
                sistema.guardarEmpleados(empleados);
                System.out.println("Credenciales modificadas correctamente.");
                return;
            }
        }
        System.out.println("Empleado no encontrado.");
    }
}
