package parque;

import java.time.LocalDate;

public class Espectaculo {
	
	private LocalDate fecha;
	private String hora;
	private String nombre;
	
	public Espectaculo(LocalDate fecha, String hora, String nombre) {
		this.fecha = fecha;
		this.hora = hora;
		this.nombre = nombre;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String mostrarInfo() {
		//TODO
		return "";
	}
	
}
