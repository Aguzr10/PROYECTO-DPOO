package parque;

import java.util.List;

public class AtraccionCultural extends Atracciones {

    private boolean restriccionEdad;

    public AtraccionCultural(String tipo, List<String> tipoPersonal, String nombre, String ubicacion, int cupoMaximo,
                             int empleadosEncargados, boolean disponibilidadClima, Categoria tipoTiquete,
                             Temporada temporada, boolean restriccionEdad) {
        super(tipo, tipoPersonal, nombre, ubicacion, cupoMaximo, empleadosEncargados, disponibilidadClima, tipoTiquete, temporada);
        this.restriccionEdad = restriccionEdad;
    }

    public boolean isRestriccionEdad() {
        return restriccionEdad;
    }

    public void setRestriccionEdad(boolean restriccionEdad) {
        this.restriccionEdad = restriccionEdad;
    }

    public boolean verificarEdad(int edad) {
        return !restriccionEdad || edad >= 13; // ejemplo: no se permite si menor de 13 y hay restricción
    }

    @Override
    public String serializar() {
        return super.serializar() + ";" + restriccionEdad;
    }

    public static AtraccionCultural deserializar(String linea) {
        String[] partes = linea.split(";");
        AtraccionCultural atr = new AtraccionCultural(
            partes[0], // tipo
            List.of(partes[1].split(",")), // tipoPersonal
            partes[2], // nombre
            partes[3], // ubicacion
            Integer.parseInt(partes[4]), // cupoMaximo
            Integer.parseInt(partes[5]), // empleadosEncargados
            Boolean.parseBoolean(partes[6]), // disponibilidadClima
            Categoria.valueOf(partes[7]), // tipoTiquete
            Temporada.valueOf(partes[8]), // temporada
            Boolean.parseBoolean(partes[9]) // restriccionEdad
        );
        return atr;
    }
}
