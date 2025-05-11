package parque.Administración;

import java.time.LocalDate;
import java.util.List;

import parque.Tiquetes.Tiquete;

public abstract class Empleados extends Usuario {

    protected String turno;
    protected String lugarAsignado;
    protected String rol;
    protected double descuentoEmpleado;
    
    public Empleados(String login, String password, List<Tiquete> tiquetesComprados, String metodoCompra, 
                     String nombre, String turno, String lugarAsignado, String rol, double descuentoEmpleado) {
        super(login, password, tiquetesComprados, metodoCompra, nombre);  
        this.turno = turno;
        this.lugarAsignado = lugarAsignado;
        this.rol = rol;
        this.descuentoEmpleado = descuentoEmpleado;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getLugarAsignado() {
        return lugarAsignado;
    }

    public void setLugarAsignado(String lugarAsignado) {
        this.lugarAsignado = lugarAsignado;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public double getDescuentoEmpleado() {
        return descuentoEmpleado;
    }

    public void setDescuentoEmpleado(double descuentoEmpleado) {
        this.descuentoEmpleado = descuentoEmpleado;
    }

    public String consultarAsignacion() {
        return "Rol: " + rol + ", Turno: " + turno + ", Lugar: " + lugarAsignado;
    }

    public boolean verfTurnoDoble(LocalDate fecha) {
        return false;
    }

    @Override
    public String serializar() {
        StringBuilder sb = new StringBuilder();
        sb.append(login).append(",");
        sb.append(password).append(",");
        sb.append(metodoCompra).append(",");
        sb.append(getNombre()).append(",");  
        sb.append(turno).append(",");
        sb.append(lugarAsignado).append(",");
        sb.append(rol).append(",");
        sb.append(descuentoEmpleado);
        return sb.toString();
    }

    public static String[] deserializarDatos(String linea) {
        return linea.split(",");
    }
}
