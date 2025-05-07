package parque;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Temporada {
	
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

}
