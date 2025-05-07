package parque;

import java.time.LocalDate;

public class TiqueteTemporada extends Tiquete {

	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	
	public TiqueteTemporada(Categoria categoriaTiquete, boolean usoValidado, double precio, LocalDate fechaInicio, LocalDate fechaFin) {
		super(categoriaTiquete, usoValidado, precio);
		
		if (fechaFin.isBefore(fechaInicio)) {
			throw new IllegalArgumentException("Fechas inválidas: la fecha de fin es anterior a la de inicio");
		}
		
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public boolean verfRangoFecha(LocalDate fecha) {
		if (fechaInicio == null || fechaFin == null || fecha == null) {
			return false;
		}
		return !fecha.isBefore(fechaInicio) && !fecha.isAfter(fechaFin);
	}

	@Override
	public String serializar() {
		StringBuilder sb = new StringBuilder(super.serializar());
		sb.append(",").append(fechaInicio).append(",").append(fechaFin);
		return sb.toString();
	}

	public static String[] deserializarDatos(String linea) {
		String[] datos = Tiquete.deserializarDatos(linea);
		String[] result = new String[datos.length + 2];
		System.arraycopy(datos, 0, result, 0, datos.length);
		String[] fecha = linea.split(",");
		result[datos.length] = fecha[datos.length];
		result[datos.length + 1] = fecha[datos.length + 1];
		return result;
	}
}
