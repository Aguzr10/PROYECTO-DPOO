package parque.Servicios;

import java.util.List;

public class Cafeteria extends LugarServicio {

    public Cafeteria(String tipo, List<String> tipoPersonal) {
        super(tipo, tipoPersonal);
    }

    public boolean verificarCocinero() {
        if (tipoPersonal == null || tipoPersonal.isEmpty()) return false;
        for (String tipo : tipoPersonal) {
            if (tipo.equalsIgnoreCase("cocinero")) {
                return true;
            }
        }
        return false;
    }

    public void cocinar() {
        if (verificarCocinero()) {
            System.out.println("Cocinando alimentos...");
        } else {
            System.out.println("No hay cocinero disponible para preparar alimentos.");
        }
    }

    @Override
    public String serializar() {
        StringBuilder sb = new StringBuilder();
        sb.append(tipo).append(",");
        if (tipoPersonal != null && !tipoPersonal.isEmpty()) {
            sb.append(String.join(";", tipoPersonal));
        }
        return sb.toString();
    }

    public static Cafeteria deserializar(String linea) {
        String[] datos = deserializarDatos(linea);
        if (datos.length < 2) {
            return null;
        }
        String tipo = datos[0];
        List<String> tipoPersonal = List.of(datos[1].split(";"));
        return new Cafeteria(tipo, tipoPersonal);
    }
}
