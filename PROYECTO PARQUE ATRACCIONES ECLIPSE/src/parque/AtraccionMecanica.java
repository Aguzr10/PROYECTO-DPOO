package parque;

import java.util.List;

public class AtraccionMecanica extends Atracciones {

	private double alturaMin;
	private double alturaMax;
	private double pesoMin;
	private double pesoMax;
	private RestriccionMedica restriccionMedica;
	private String nivelRiesgo;
	
	public AtraccionMecanica(String tipo, List<String> tipoPersonal, String nombre, String ubicacion, int cupoMaximo,
			int empleadosEncargados, boolean disponibilidadClima, Categoria tipoTiquete, Temporada temporada,
			double alturaMin, double alturaMax, double pesoMin, double pesoMax, RestriccionMedica restriccionMedica, String nivelRiesgo) {
		super(tipo, tipoPersonal, nombre, ubicacion, cupoMaximo, empleadosEncargados, disponibilidadClima, tipoTiquete,
				temporada);
		this.alturaMin = alturaMin;
		this.alturaMax = alturaMax;
		this.pesoMin = pesoMin;
		this.pesoMax = pesoMax;
		this.restriccionMedica = restriccionMedica;
		this.nivelRiesgo = nivelRiesgo;
	}

	public double getAlturaMin() {
		return alturaMin;
	}

	public void setAlturaMin(double alturaMin) {
		this.alturaMin = alturaMin;
	}

	public double getAlturaMax() {
		return alturaMax;
	}

	public void setAlturaMax(double alturaMax) {
		this.alturaMax = alturaMax;
	}

	public double getPesoMin() {
		return pesoMin;
	}

	public void setPesoMin(double pesoMin) {
		this.pesoMin = pesoMin;
	}

	public double getPesoMax() {
		return pesoMax;
	}

	public void setPesoMax(double pesoMax) {
		this.pesoMax = pesoMax;
	}

	public RestriccionMedica getRestriccionMedica() {
		return restriccionMedica;
	}

	public void setRestriccionMedica(RestriccionMedica restriccionMedica) {
		this.restriccionMedica = restriccionMedica;
	}

	public String getNivelRiesgo() {
		return nivelRiesgo;
	}

	public void setNivelRiesgo(String nivelRiesgo) {
		this.nivelRiesgo = nivelRiesgo;
	}
	
	public boolean verfAltura(double altura) {
	    if (altura >= alturaMin && altura <= alturaMax) {
	        return true;
	    } else {
	        return false;
	    }
	}
	
	public boolean verfPeso(double peso) {
	    if (peso >= pesoMin && peso <= pesoMax) {
	        return true;
	    } else {
	        return false;
	    }
	}
	
		
	@Override
	public boolean isDisponibilidadClima() {
		// TODO Auto-generated method stub
		return super.isDisponibilidadClima();
	}

	public boolean verificarAcceso() {
		 boolean causaVertigo = true; 
	     boolean peligroCardiaco = true;
	     boolean restriccionDiscapacidad = true; 
	          return restriccionMedica.verificarAcceso(causaVertigo, peligroCardiaco, restriccionDiscapacidad);
	}

	@Override
	public void setDisponibilidadClima(boolean disponibilidadClima) {
		// TODO Auto-generated method stub
		super.setDisponibilidadClima(disponibilidadClima);
	}

	// Constructor para pruebas: Este constructor es para facilitar las pruebas sin tener que proporcionar todos los datos.
	// No forma parte del diseño UML original y solo se debe usar para facilitar la creación de objetos en los tests.
	public AtraccionMecanica(String nombre) {
		super("", null, nombre, "", 0, 0, false, null, null);
		this.alturaMin = 0.0;
		this.alturaMax = 0.0;
		this.pesoMin = 0.0;
		this.pesoMax = 0.0;
		this.restriccionMedica = null;
		this.nivelRiesgo = "";
	}
	
	// Método adicional para pruebas: Permite actualizar los atributos de una atracción mecánica directamente.
	// Es solo para pruebas y no forma parte de la lógica de negocio original.
	public void setAtraccionMecanica(double alturaMin, double alturaMax, double pesoMin, double pesoMax,
			RestriccionMedica restriccionMedica, String nivelRiesgo) {
		this.alturaMin = alturaMin;
		this.alturaMax = alturaMax;
		this.pesoMin = pesoMin;
		this.pesoMax = pesoMax;
		this.restriccionMedica = restriccionMedica;
		this.nivelRiesgo = nivelRiesgo;
	}

	public String serializar() {
		StringBuilder sb = new StringBuilder(super.serializar());
		sb.append(",").append(alturaMin).append(",").append(alturaMax).append(",").append(pesoMin).append(",")
		  .append(pesoMax).append(",").append(restriccionMedica).append(",").append(nivelRiesgo);
		return sb.toString();
	}

	public static String[] deserializarDatos(String linea) {
		String[] datos = Atracciones.deserializarDatos(linea);
		String[] result = new String[datos.length + 6];  
		System.arraycopy(datos, 0, result, 0, datos.length);
		String[] atraccionMecanica = linea.split(",");
		System.arraycopy(atraccionMecanica, datos.length, result, datos.length, 6); 
		return result;
	}
}
