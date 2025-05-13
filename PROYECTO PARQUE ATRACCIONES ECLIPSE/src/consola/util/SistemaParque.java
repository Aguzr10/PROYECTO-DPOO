package consola.util;

import parque.Persistencia.PersistenciaArchivo;
import parque.Persistencia.IPersistencia;
import parque.Administración.Empleados;
import parque.Administración.Cliente;
import parque.Administración.Administrador;
import parque.Atracción.Atracciones;
import parque.Servicios.LugarServicio;
import parque.Tiquetes.Tiquete;

import java.util.List;
import java.util.Scanner;

public class SistemaParque {

    private IPersistencia persistencia;
    private static SistemaParque instancia = null;

    public SistemaParque() {
        this.persistencia = new PersistenciaArchivo();
    }

    public static SistemaParque getInstancia() {
        if (instancia == null) {
            instancia = new SistemaParque();
        }
        return instancia;
    }

    public List<Empleados> getEmpleados() {
        return persistencia.cargarEmpleados();
    }

    public List<Cliente> getClientes() {
        return persistencia.cargarClientes();
    }

    public List<Administrador> getAdministradores() {
        return persistencia.cargarAdministradores();
    }

    public List<Atracciones> getAtracciones() {
        return persistencia.cargarAtracciones();
    }

    public List<Tiquete> getTiquetes() {
        return persistencia.cargarTiquetes();
    }

    public List<LugarServicio> getLugares() {
        return persistencia.cargarLugares();
    }

    public void guardarEmpleados(List<Empleados> empleados) {
        persistencia.guardarEmpleados(empleados);
    }

    public void guardarClientes(List<Cliente> clientes) {
        persistencia.guardarClientes(clientes);
    }

    public void guardarAdministradores(List<Administrador> administradores) {
        persistencia.guardarAdministradores(administradores);
    }

    public void guardarAtracciones(List<Atracciones> atracciones) {
        persistencia.guardarAtracciones(atracciones);
    }

    public void guardarTiquetes(List<Tiquete> tiquetes) {
        persistencia.guardarTiquetes(tiquetes);
    }

    public void guardarLugares(List<LugarServicio> lugares) {
        persistencia.guardarLugares(lugares);
    }

    public void cargarDatos() {
        getEmpleados();
        getClientes();
        getAdministradores();
        getAtracciones();
        getTiquetes();
        getLugares();
    }

    public void guardarDatos() {
        guardarEmpleados(getEmpleados());
        guardarClientes(getClientes());
        guardarAdministradores(getAdministradores());
        guardarAtracciones(getAtracciones());
        guardarTiquetes(getTiquetes());
        guardarLugares(getLugares());
    }

    public void agregarEmpleado(Empleados nuevoEmpleado) {
        List<Empleados> empleados = getEmpleados();
        empleados.add(nuevoEmpleado);
        guardarEmpleados(empleados);
    }

    public boolean eliminarEmpleado(String login) {
        List<Empleados> empleados = getEmpleados();
        boolean eliminado = empleados.removeIf(e -> e.getLogin().equals(login));
        if (eliminado) {
            guardarEmpleados(empleados);
        }
        return eliminado;
    }

    public void mostrarMenu() {
        System.out.println("Bienvenido al Parque de Diversiones!");
        System.out.println("Seleccione una opción:");
        System.out.println("1. Administrador");
        System.out.println("2. Empleado");
        System.out.println("3. Cliente");
        System.out.println("4. Salir");

        Scanner scanner = new Scanner(System.in);
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                iniciarSesion("admin");
                break;
            case 2:
                iniciarSesion("empleado");
                break;
            case 3:
                iniciarSesion("cliente");
                break;
            case 4:
                System.out.println("¡Hasta luego!");
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    private void iniciarSesion(String tipoUsuario) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese su nombre de usuario: ");
        String username = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine();

        boolean autenticado = false;
        switch (tipoUsuario) {
            case "admin":
                autenticado = Autenticador.autenticarAdministrador(username, password) != null;
                break;
            case "empleado":
                autenticado = Autenticador.autenticarEmpleado(username, password) != null;
                break;
            case "cliente":
                autenticado = Autenticador.autenticarCliente(username, password) != null;
                break;
        }

        if (autenticado) {
            System.out.println("Bienvenido " + tipoUsuario + ".");
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }
    }
}
