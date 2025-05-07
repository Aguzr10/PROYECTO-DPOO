package parque;

import java.util.List;

public class Taquilla extends LugarServicio {

    public Taquilla(String tipo, List<String> tipoPersonal) {
        super(tipo, tipoPersonal);
    }

    public boolean verificarTiquete(Tiquete tiquete) {
        //TODO
        return false;
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

    public static Taquilla deserializar(String linea) {
        String[] datos = deserializarDatos(linea);
        if (datos.length < 2) {
            return null;
        }
        String tipo = datos[0];
        List<String> tipoPersonal = List.of(datos[1].split(";"));
        return new Taquilla(tipo, tipoPersonal);
    }
}
