package parque.Servicios;

import java.time.LocalDateTime;

public class ServicioGeneral {

	private LocalDateTime ultimaLimpieza;
	private LocalDateTime ultimoMantenimiento;

	public ServicioGeneral() {
		this.ultimaLimpieza = null;
		this.ultimoMantenimiento = null;
	}

	public void realizarAseo() {
		this.ultimaLimpieza = LocalDateTime.now();
	}

	public void realizarMantenimiento() {
		this.ultimoMantenimiento = LocalDateTime.now();
	}

	public LocalDateTime getUltimaLimpieza() {
		return ultimaLimpieza;
	}

	public LocalDateTime getUltimoMantenimiento() {
		return ultimoMantenimiento;
	}

	public String serializar() {
		return (ultimaLimpieza != null ? ultimaLimpieza.toString() : "null") + "," +
			   (ultimoMantenimiento != null ? ultimoMantenimiento.toString() : "null");
	}

	public static ServicioGeneral deserializar(String linea) {
		String[] partes = linea.split(",");
		ServicioGeneral servicio = new ServicioGeneral();
		if (!partes[0].equals("null")) {
			servicio.ultimaLimpieza = LocalDateTime.parse(partes[0]);
		}
		if (!partes[1].equals("null")) {
			servicio.ultimoMantenimiento = LocalDateTime.parse(partes[1]);
		}
		return servicio;
	}
}
