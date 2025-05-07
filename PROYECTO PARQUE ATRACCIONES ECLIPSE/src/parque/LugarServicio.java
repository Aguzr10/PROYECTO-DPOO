package parque;

import java.util.List;

public abstract class LugarServicio {
	
	protected String tipo;
	protected List<String> tipoPersonal;
	
	public LugarServicio(String tipo, List<String> tipoPersonal) {
		this.tipo = tipo;
		this.tipoPersonal = tipoPersonal;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<String> getTipoPersonal() {
		return tipoPersonal;
	}

	public void setTipoPersonal(List<String> tipoPersonal) {
		this.tipoPersonal = tipoPersonal;
	}
	
	public boolean necesitaCajero() {
		//TODO
		return false;
	}
	
	public boolean necesitaCocinero() {
		//TODO
		return false;
	}
	
	public String serializar() {
	    StringBuilder sb = new StringBuilder();
	    sb.append(tipo).append(",");
	    
	    if (tipoPersonal != null && !tipoPersonal.isEmpty()) {
	        sb.append(String.join(";", tipoPersonal)); 
	    }
	    
	    return sb.toString();  
	}
	
	public static String[] deserializarDatos(String linea) {
		return linea.split(",");
	}
}
