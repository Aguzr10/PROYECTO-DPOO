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
                              double alturaMin, double alturaMax, double pesoMin, double pesoMax,
                              RestriccionMedica restriccionMedica, String nivelRiesgo) {
        super(tipo, tipoPersonal, nombre, ubicacion, cupoMaximo, empleadosEncargados,
                disponibilidadClima, tipoTiquete, temporada);
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
        return altura >= alturaMin && altura <= alturaMax;
    }

    public boolean verfPeso(double peso) {
        return peso >= pesoMin && peso <= pesoMax;
    }

    public boolean verificarAcceso(boolean causaVertigo, boolean peligroCardiaco, boolean restriccionDiscapacidad) {
        return restriccionMedica.verificarAcceso(causaVertigo, peligroCardiaco, restriccionDiscapacidad);
    }

    @Override
    public String serializar() {
        return super.serializar() + ";" + alturaMin + ";" + alturaMax + ";" + pesoMin + ";" + pesoMax + ";" +
               restriccionMedica.serializar() + ";" + nivelRiesgo;
    }

    public static String[] deserializarDatos(String linea) {
        String[] datos = linea.split(";");
        return datos;
    }
}
