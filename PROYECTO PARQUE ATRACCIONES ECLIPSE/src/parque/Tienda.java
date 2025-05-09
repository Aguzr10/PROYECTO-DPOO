package parque;

import java.util.List;

public class Tienda extends LugarServicio {

    public Tienda(String tipo, List<String> tipoPersonal) {
        super(tipo, tipoPersonal);
    }

    public void vender() {
        System.out.println("Venta realizada en la tienda de tipo: " + tipo);
    }

    @Override
    public boolean necesitaCajero() {
        return tipoPersonal != null && tipoPersonal.contains("cajero");
    }

    @Override
    public boolean necesitaCocinero() {
        return tipoPersonal != null && tipoPersonal.contains("cocinero");
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

    public static Tienda deserializar(String linea) {
        String[] datos = deserializarDatos(linea);
        if (datos.length < 2) {
            return null;
        }
        String tipo = datos[0];
        List<String> tipoPersonal = List.of(datos[1].split(";"));
        return new Tienda(tipo, tipoPersonal);
    }
}
