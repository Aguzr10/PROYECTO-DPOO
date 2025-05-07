package parque;

import java.util.ArrayList;
import java.util.List;

public class OperadorAtracciones extends Empleados {

    private String nivelCapacitacion;
    private List<String> atraccionesCertificadas;
    
    public OperadorAtracciones(String login, String password, List<Tiquete> tiquetesComprados, String metodoCompra,
            String nombre, String turno, String lugarAsignado, String rol, double descuentoEmpleado, String nivelCapacitacion, List<String> atraccionesCertificadas) {
        
        super(login, password, tiquetesComprados, metodoCompra, nombre, turno, lugarAsignado, rol, descuentoEmpleado);
        this.nivelCapacitacion = nivelCapacitacion;
        this.atraccionesCertificadas = new ArrayList<>();
    }
    
    public String getNivelCapacitacion() {
        return nivelCapacitacion;
    }

    public void setNivelCapacitacion(String nivelCapacitacion) {
        this.nivelCapacitacion = nivelCapacitacion;
    }

    public List<String> getAtraccionesCertificadas() {
        return atraccionesCertificadas;
    }

    public void setAtraccionesCertificadas(List<String> atraccionesCertificadas) {
        this.atraccionesCertificadas = atraccionesCertificadas;
    }

    public void agregarCertifi(AtraccionMecanica atraccion) {
        if (atraccion != null && !atraccionesCertificadas.contains(atraccion.getNombre())) {
            atraccionesCertificadas.add(atraccion.getNombre());
        }
    }
    
    public boolean verfOperacion(AtraccionMecanica atraccion) {
        if (atraccion == null) {
            return false;
        }
        return atraccionesCertificadas.contains(atraccion.getNombre());
    }

    @Override
    public String serializar() {
        StringBuilder sb = new StringBuilder(super.serializar());
        sb.append(",").append(nivelCapacitacion);
        sb.append(",").append(String.join(";", atraccionesCertificadas));
        return sb.toString();
    }

    public static OperadorAtracciones deserializar(String linea) {
        String[] datos = deserializarDatos(linea);
        String login = datos[0];
        String password = datos[1];
        String metodoCompra = datos[2];
        String nombre = datos[3];
        String turno = datos[4];
        String lugarAsignado = datos[5];
        String rol = datos[6];
        double descuentoEmpleado = Double.parseDouble(datos[7]);
        String nivelCapacitacion = datos[8];
        List<String> atraccionesCertificadas = List.of(datos[9].split(";"));
        
        return new OperadorAtracciones(login, password, null, metodoCompra, nombre, turno, lugarAsignado, rol, descuentoEmpleado, nivelCapacitacion, atraccionesCertificadas);
    }
}
