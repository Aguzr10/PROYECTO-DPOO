package parque;

public class RestriccionMedica {

	private boolean vertigo;
	private boolean enfermedadCardiaca;
	private boolean discapacidad;

	public RestriccionMedica(boolean vertigo, boolean enfermedadCardiaca, boolean discapacidad) {
		this.vertigo = vertigo;
		this.enfermedadCardiaca = enfermedadCardiaca;
		this.discapacidad = discapacidad;
	}

	public boolean isVertigo() {
		return vertigo;
	}

	public void setVertigo(boolean vertigo) {
		this.vertigo = vertigo;
	}

	public boolean isEnfermedadCardiaca() {
		return enfermedadCardiaca;
	}

	public void setEnfermedadCardiaca(boolean enfermedadCardiaca) {
		this.enfermedadCardiaca = enfermedadCardiaca;
	}

	public boolean isDiscapacidad() {
		return discapacidad;
	}

	public void setDiscapacidad(boolean discapacidad) {
		this.discapacidad = discapacidad;
	}

	public boolean verificarAcceso(boolean vertigo, boolean enfermedadCardiaca, boolean discapacidad) {
		return !(this.vertigo && vertigo)
			&& !(this.enfermedadCardiaca && enfermedadCardiaca)
			&& !(this.discapacidad && discapacidad);
	}

	public String serializar() {
		return vertigo + "," + enfermedadCardiaca + "," + discapacidad;
	}

	public static RestriccionMedica deserializar(String linea) {
		String[] partes = linea.split(",");
		boolean vertigo = Boolean.parseBoolean(partes[0]);
		boolean enfermedadCardiaca = Boolean.parseBoolean(partes[1]);
		boolean discapacidad = Boolean.parseBoolean(partes[2]);
		return new RestriccionMedica(vertigo, enfermedadCardiaca, discapacidad);
	}
}
