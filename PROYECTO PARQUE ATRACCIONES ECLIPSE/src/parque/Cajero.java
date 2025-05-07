package parque;

import java.util.List;

public class Cajero extends Empleados {

    private String tipoFuncion;
    
    public Cajero(String login, String password, List<Tiquete> tiquetesComprados, String metodoCompra, String nombre,
            String turno, String lugarAsignado, String rol, double descuentoEmpleado, String tipoFuncion) {
        super(login, password, tiquetesComprados, metodoCompra, nombre, turno, lugarAsignado, rol, descuentoEmpleado);
        this.tipoFuncion = tipoFuncion;
    }

    public String getTipoFuncion() {
        return tipoFuncion;
    }

    public void setTipoFuncion(String tipoFuncion) {
        this.tipoFuncion = tipoFuncion;
    }

    public void registrarVenta() {
        //TODO
    }
    
    public boolean verificarTiquete(Atracciones atraccion) {
        //TODO
        return false;
    }

    @Override
    public String serializar() {
        StringBuilder sb = new StringBuilder(super.serializar());
        sb.append(",").append(tipoFuncion);
        return sb.toString();
    }

    public static Cajero deserializar(String linea) {
        String[] datos = deserializarDatos(linea);
        String login = datos[0];
        String password = datos[1];
        String metodoCompra = datos[2];
        String nombre = datos[3];
        String turno = datos[4];
        String lugarAsignado = datos[5];
        String rol = datos[6];
        double descuentoEmpleado = Double.parseDouble(datos[7]);
        String tipoFuncion = datos[8];
        
        return new Cajero(login, password, null, metodoCompra, nombre, turno, lugarAsignado, rol, descuentoEmpleado, tipoFuncion);
    }
}
