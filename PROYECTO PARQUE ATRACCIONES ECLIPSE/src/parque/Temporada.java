package parque;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Temporada {

    public static final Temporada TODO_EL_AÑO = null;
	private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Temporada(LocalDate fechaInicio, LocalDate fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public long duracionEnDias() {
        return ChronoUnit.DAYS.between(fechaInicio, fechaFin);
    }

    public String serializar() {
        return fechaInicio.toString() + "," + fechaFin.toString();
    }

    public static Temporada deserializar(String linea) {
        String[] partes = linea.split(",");
        LocalDate inicio = LocalDate.parse(partes[0]);
        LocalDate fin = LocalDate.parse(partes[1]);
        return new Temporada(inicio, fin);
    }

    public boolean fechaDentroDeRango(LocalDate fecha) {
        return (fecha.isEqual(fechaInicio) || fecha.isAfter(fechaInicio)) && (fecha.isEqual(fechaFin) || fecha.isBefore(fechaFin));
    }

	public static Temporada valueOf(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}
