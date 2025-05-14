package parque.Atracción;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import parque.Categorías.Categoria;
import parque.Categorías.Temporada;
import parque.Servicios.LugarServicio;

public abstract class Atracciones extends LugarServicio implements Serializable {
    private static final long serialVersionUID = 1L; 

    protected String nombre;
    protected String ubicacion;
    protected int cupoMaximo;
    protected int empleadosEncargados;
    protected boolean disponibilidadClima;
    protected Categoria tipoTiquete;
    protected Temporada temporada;
    protected String nivelExclusividad;
    protected int empleadosMinimos;

    public Atracciones(String tipo, List<String> tipoPersonal, String nombre, String ubicacion, int cupoMaximo,
                       int empleadosEncargados, boolean disponibilidadClima, Categoria tipoTiquete, Temporada temporada) {
        super(tipo, tipoPersonal);
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.cupoMaximo = cupoMaximo;
        this.empleadosEncargados = empleadosEncargados;
        this.disponibilidadClima = disponibilidadClima;
        this.tipoTiquete = tipoTiquete;
        this.temporada = temporada;
        this.nivelExclusividad = "Básico";  
        this.empleadosMinimos = 0;  
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public int getEmpleadosEncargados() {
        return empleadosEncargados;
    }

    public void setEmpleadosEncargados(int empleadosEncargados) {
        this.empleadosEncargados = empleadosEncargados;
    }

    public boolean isDisponibilidadClima() {
        return disponibilidadClima;
    }

    public void setDisponibilidadClima(boolean disponibilidadClima) {
        this.disponibilidadClima = disponibilidadClima;
    }

    public Categoria getTipoTiquete() {
        return tipoTiquete;
    }

    public void setTipoTiquete(Categoria tipoTiquete) {
        this.tipoTiquete = tipoTiquete;
    }

    public Temporada getTemporada() {
        return temporada;
    }

    public void setTemporada(Temporada temporada) {
        this.temporada = temporada;
    }

    public String getNivelExclusividad() {
        return nivelExclusividad;
    }

    public void setNivelExclusividad(String nivelExclusividad) {
        this.nivelExclusividad = nivelExclusividad;
    }

    public int getEmpleadosMinimos() {
        return empleadosMinimos;
    }

    public void setEmpleadosMinimos(int empleadosMinimos) {
        this.empleadosMinimos = empleadosMinimos;
    }

    public void setCapacidad(int capacidad) {
        this.cupoMaximo = capacidad;
    }

    public boolean esTemporal() {
        return temporada != Temporada.TODO_EL_AÑO;
    }

    public boolean dispFecha(LocalDate fecha) {
        return !esTemporal() || temporada.fechaDentroDeRango(fecha);
    }

    public String mostrarInfo() {
        return "Nombre: " + nombre + ", Ubicación: " + ubicacion + ", Cupo: " + cupoMaximo +
               ", Requiere clima favorable: " + disponibilidadClima + ", Exclusividad: " + tipoTiquete +
               ", Temporada: " + temporada + ", Nivel Exclusividad: " + nivelExclusividad +
               ", Empleados Mínimos: " + empleadosMinimos;
    }

    public String serializar() {
        return super.serializar() + ";" + nombre + ";" + ubicacion + ";" + cupoMaximo + ";" +
               empleadosEncargados + ";" + disponibilidadClima + ";" + tipoTiquete + ";" +
               temporada.getFechaInicio().format(DateTimeFormatter.ISO_DATE) + "," +
               temporada.getFechaFin().format(DateTimeFormatter.ISO_DATE) + ";" +
               nivelExclusividad + ";" + empleadosMinimos;
    }

    public static String[] deserializarDatos(String linea) {
        String[] datos = linea.split(";");
        return datos;
    }

	public Object getCategoria() {
		// TODO Auto-generated method stub
		return null;
	}
}
