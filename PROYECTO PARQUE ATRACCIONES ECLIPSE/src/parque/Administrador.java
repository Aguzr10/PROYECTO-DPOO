package parque;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Administrador extends Usuario {

    private String nombre;
    private double descuentoEmpleado;

    public Administrador(String login, String password, List<Tiquete> tiquetesComprados, String metodoCompra, double descuentoEmpleado) {
        super(login, password, tiquetesComprados, metodoCompra);
        this.descuentoEmpleado = descuentoEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getDescuentoEmpleado() {
        return descuentoEmpleado;
    }

    public void setDescuentoEmpleado(double descuentoEmpleado) {
        this.descuentoEmpleado = descuentoEmpleado;
    }

    public void asignarTurno(Empleados empleado, LocalDate fecha, String turno, LugarServicio lugar) {
        if (empleado != null && turno != null && lugar != null) {
            empleado.setTurno(turno);
            empleado.setLugarAsignado(lugar.getTipo());
            System.out.println("Turno asignado a " + empleado.getNombre() + " en " + lugar.getTipo());
        } else {
            System.out.println("No se pudo asignar turno: datos inválidos.");
        }
    }

    public void modificarEmpleado(Empleados empleado, String nuevoRol) {
        if (empleado != null && nuevoRol != null && !nuevoRol.isEmpty()) {
            empleado.setRol(nuevoRol);
            System.out.println("Rol modificado a " + nuevoRol + " para " + empleado.getNombre());
        } else {
            System.out.println("No se pudo modificar el empleado: datos inválidos.");
        }
    }

    public void modificarAtraccion(Atracciones atraccion, Map<String, Object> cambios) {
        if (atraccion != null) {
           
        }
    }
}


//prueba loles
