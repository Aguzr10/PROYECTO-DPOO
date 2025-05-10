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
        return !restriccionEdad || edad >= 13; 
    }

    @Override
    public String serializar() {
        return super.serializar() + ";" + restriccionEdad;
    }

    public static AtraccionCultural deserializar(String linea) {
        String[] partes = linea.split(";");
        AtraccionCultural atr = new AtraccionCultural(
            partes[0],
            List.of(partes[1].split(",")), 
            partes[2], 
            partes[3], 
            Integer.parseInt(partes[4]), 
            Integer.parseInt(partes[5]), 
            Boolean.parseBoolean(partes[6]), 
            Categoria.valueOf(partes[7]), 
            Temporada.valueOf(partes[8]), 
            Boolean.parseBoolean(partes[9]) 
        );
        return atr;
    }
}
