package parque.Administración;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import parque.Atracción.Atracciones;
import parque.Servicios.LugarServicio;
import parque.Tiquetes.Tiquete;

public class Administrador extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private double descuentoEmpleado;

    public Administrador(String login, String password, List<Tiquete> tiquetesComprados, String metodoCompra, double descuentoEmpleado, String nombre) {
        super(login, password, tiquetesComprados, metodoCompra, nombre); 
        this.descuentoEmpleado = descuentoEmpleado;
    }

    public double getDescuentoEmpleado() {
        return descuentoEmpleado;
    }

    public void setDescuentoEmpleado(double descuentoEmpleado) {
        this.descuentoEmpleado = descuentoEmpleado;
    }

    public void asignarTurno(Empleados empleado, String turno, LugarServicio lugar) {
        if (empleado != null && turno != null && lugar != null) {
            empleado.setTurno(turno);
            empleado.setLugarAsignado(lugar.getTipo());
        }
    }

    public void modificarEmpleado(Empleados empleado, String nuevoRol) {
        if (empleado != null && nuevoRol != null && !nuevoRol.isEmpty()) {
            empleado.setRol(nuevoRol);
        }
    }

    public void modificarAtraccion(Atracciones atraccion, Map<String, Object> cambios) {
        if (atraccion != null && cambios != null) {
            if (cambios.containsKey("nombre")) {
                atraccion.setNombre((String) cambios.get("nombre"));
            }
            if (cambios.containsKey("capacidad")) {
                atraccion.setCapacidad((Integer) cambios.get("capacidad"));
            }
            if (cambios.containsKey("ubicacion")) {
                atraccion.setUbicacion((String) cambios.get("ubicacion"));
            }
            if (cambios.containsKey("nivelExclusividad")) {
                atraccion.setNivelExclusividad((String) cambios.get("nivelExclusividad"));
            }
            if (cambios.containsKey("empleadosMinimos")) {
                atraccion.setEmpleadosMinimos((Integer) cambios.get("empleadosMinimos"));
            }
        }
    }

    @Override
    public String serializar() {
        return "ADMINISTRADOR;" + login + ";" + password + ";" + metodoCompra + ";" + descuentoEmpleado + ";" + getNombre();
    }
}
