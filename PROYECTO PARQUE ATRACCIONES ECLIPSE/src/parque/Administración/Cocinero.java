package parque.Administración;

import java.util.List;

import parque.Tiquetes.Tiquete;

public class Cocinero extends Empleados {

    public Cocinero(String login, String password, List<Tiquete> tiquetesComprados, String metodoCompra, String nombre,
                    String turno, String lugarAsignado, String rol, double descuentoEmpleado) {
        super(login, password, tiquetesComprados, metodoCompra, nombre, turno, lugarAsignado, rol, descuentoEmpleado);
    }

    public void cocinar() {
        System.out.println("Cocinero " + nombre + " está cocinando en " + lugarAsignado);
    }

    @Override
    public String serializar() {
        return super.serializar();
    }

    public static Cocinero deserializar(String linea) {
        String[] datos = deserializarDatos(linea);
        String login = datos[0];
        String password = datos[1];
        String metodoCompra = datos[2];
        String nombre = datos[3];
        String turno = datos[4];
        String lugarAsignado = datos[5];
        String rol = datos[6];
        double descuentoEmpleado = Double.parseDouble(datos[7]);

        return new Cocinero(login, password, null, metodoCompra, nombre, turno, lugarAsignado, rol, descuentoEmpleado);
    }
}
