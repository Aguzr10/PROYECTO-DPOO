package parque;

import java.util.List;

public class AtraccionCultural extends Atracciones {

	private boolean restriccionEdad;

	public AtraccionCultural(String tipo, List<String> tipoPersonal, String nombre, String ubicacion, int cupoMaximo,
			int empleadosEncargados, boolean disponibilidadClima, Categoria tipoTiquete, Temporada temporada, boolean restriccionEdad) {
		
		super(tipo, tipoPersonal, nombre, ubicacion, cupoMaximo, empleadosEncargados, disponibilidadClima, tipoTiquete,
				temporada);
		this.restriccionEdad = restriccionEdad;
	}
	
	public boolean isRestriccionEdad() {
		return restriccionEdad;
	}

	public void setRestriccionEdad(boolean restriccionEdad) {
		this.restriccionEdad = restriccionEdad;
	}
	
	public boolean verificarEdad(int edad) {
		//TODO
		return false;
	}
	
	public String serializar() {
		StringBuilder sb = new StringBuilder(super.serializar());
		sb.append(",").append(restriccionEdad);
		return sb.toString();
	}

	public static String[] deserializarDatos(String linea) {
		String[] datos = Atracciones.deserializarDatos(linea);
		String[] result = new String[datos.length + 1]; 
		System.arraycopy(datos, 0, result, 0, datos.length);
		String[] atraccionCultural = linea.split(",");
		result[datos.length] = atraccionCultural[datos.length]; 
		return result;
	}
	
	// Constructor para pruebas: Solo usado para facilitar la creación de objetos en los tests.
	public AtraccionCultural(String nombre) {
		super("", null, nombre, "", 0, 0, false, null, null);
		this.restriccionEdad = false;
	}
	
	// Método adicional para pruebas: Permite actualizar los atributos de una atracción cultural directamente.
	// Es solo para pruebas
	public void setRestriccionEdad1(boolean restriccionEdad) {
		this.restriccionEdad = restriccionEdad;
	}
}




