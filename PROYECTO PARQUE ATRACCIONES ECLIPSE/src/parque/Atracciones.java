package parque;

import java.time.LocalDate;
import java.util.List;

public abstract class Atracciones extends LugarServicio {

    protected String nombre;
    protected String ubicacion;
    protected int cupoMaximo;
    protected int empleadosEncargados;
    protected boolean disponibilidadClima;
    protected Categoria tipoTiquete;
    protected Temporada temporada;

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

    public boolean esTemporal() {
        return temporada != Temporada.TODO_EL_AÑO;
    }

    public boolean dispFecha(LocalDate fecha) {
        return !esTemporal() || temporada.fechaDentroDeRango(fecha);
    }

    public String mostrarInfo() {
        return "Nombre: " + nombre + ", Ubicación: " + ubicacion + ", Cupo: " + cupoMaximo +
               ", Requiere clima favorable: " + disponibilidadClima + ", Exclusividad: " + tipoTiquete +
               ", Temporada: " + temporada;
    }

    public String serializar() {
        return super.serializar() + ";" + nombre + ";" + ubicacion + ";" + cupoMaximo + ";" +
               empleadosEncargados + ";" + disponibilidadClima + ";" + tipoTiquete + ";" + temporada;
    }

    public static String[] deserializarDatos(String linea) {
        String[] datos = linea.split(";");
        return datos;
    }
}
