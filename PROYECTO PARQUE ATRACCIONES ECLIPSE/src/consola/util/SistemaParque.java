package consola.util;

import parque.Persistencia.PersistenciaArchivo;
import parque.Administración.Empleados;
import parque.Administración.Cliente;
import parque.Administración.Administrador;
import parque.Atracción.Atracciones;
import parque.Servicios.LugarServicio;
import parque.Tiquetes.Tiquete;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class SistemaParque {
    
    private PersistenciaArchivo persistencia;
    private static SistemaParque instancia = null;  
    private List<Empleados> empleados;
    private List<Cliente> clientes;
    private List<Administrador> administradores;
    private List<Atracciones> atracciones;
    private List<Tiquete> tiquetes;
    private List<LugarServicio> lugares;
    
    public SistemaParque() {
        this.persistencia = new PersistenciaArchivo();
        this.empleados = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.administradores = new ArrayList<>();
        this.atracciones = new ArrayList<>();
        this.tiquetes = new ArrayList<>();
        this.lugares = new ArrayList<>();
    }
    
    public static SistemaParque getInstancia() {
        if (instancia == null) {
            instancia = new SistemaParque();
        }
        return instancia;
    }
    
    public List<Empleados> getEmpleados() {
        return empleados;
    }
    
    public List<Cliente> getClientes() {
        return clientes;
    }
    
    public List<Administrador> getAdministradores() {
        return administradores;
    }
    
    public List<Atracciones> getAtracciones() {
        return atracciones;
    }
    
    public List<Tiquete> getTiquetes() {
        return tiquetes;
    }
    
    public List<LugarServicio> getLugares() {
        return lugares;
    }
    
    public void guardarEmpleados(List<Empleados> empleados) {
        this.empleados = empleados;
        persistencia.guardarEmpleados(empleados);
    }
    
    public void guardarClientes(List<Cliente> clientes) {
        this.clientes = clientes;
        persistencia.guardarClientes(clientes);
    }
    
    public void guardarAdministradores(List<Administrador> administradores) {
        this.administradores = administradores;
        persistencia.guardarAdministradores(administradores);
    }
    
    public void guardarAtracciones(List<Atracciones> atracciones) {
        this.atracciones = atracciones;
        persistencia.guardarAtracciones(atracciones);
    }
    
    public void guardarTiquetes(List<Tiquete> tiquetes) {
        this.tiquetes = tiquetes;
        persistencia.guardarTiquetes(tiquetes);
    }
    
    public void guardarLugares(List<LugarServicio> lugares) {
        this.lugares = lugares;
        persistencia.guardarLugares(lugares);
    }
    

    public void cargarDatos() {
        System.out.println("Cargando datos desde archivos...");
        this.empleados = persistencia.cargarEmpleados();
        this.clientes = persistencia.cargarClientes();
        this.administradores = persistencia.cargarAdministradores();
        this.atracciones = persistencia.cargarAtracciones();
        this.tiquetes = persistencia.cargarTiquetes();
        this.lugares = persistencia.cargarLugares();
        System.out.println("Datos cargados exitosamente.");
    }
    

    public void guardarDatos() {
        System.out.println("Guardando todos los datos...");
        persistencia.guardarEmpleados(empleados);
        persistencia.guardarClientes(clientes);
        persistencia.guardarAdministradores(administradores);
        persistencia.guardarAtracciones(atracciones);
        persistencia.guardarTiquetes(tiquetes);
        persistencia.guardarLugares(lugares);
        System.out.println("Todos los datos guardados exitosamente.");
    }
    
    public void agregarEmpleado(Empleados nuevoEmpleado) {
        empleados.add(nuevoEmpleado);
        persistencia.guardarEmpleados(empleados);
    }
    
    public boolean eliminarEmpleado(String login) {
        boolean eliminado = false;
        for(int i = 0; i < empleados.size(); i++) {
            if(empleados.get(i).getLogin().equals(login)) {
                empleados.remove(i);
                eliminado = true;
                break;
            }
        }
        if (eliminado) {
            persistencia.guardarEmpleados(empleados);
        }
        return eliminado;
    }
    
    public boolean existeEmpleado(String login) {
        for(int i = 0; i < empleados.size(); i++) {
            if(empleados.get(i).getLogin().equals(login)) {
                return true;
            }
        }
        return false;
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
        scanner.close();
    }
    
    private void iniciarSesion(String tipoUsuario) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese su nombre de usuario: ");
        String username = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine();
        scanner.close();
        
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