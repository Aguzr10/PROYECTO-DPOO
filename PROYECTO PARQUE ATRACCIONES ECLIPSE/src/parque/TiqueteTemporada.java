package parque;

import java.time.LocalDate;

public class TiqueteTemporada extends Tiquete {

    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public TiqueteTemporada(Categoria categoriaTiquete, boolean usoValidado, double precio, LocalDate fechaInicio, LocalDate fechaFin) {
        super(categoriaTiquete, usoValidado, precio);

        if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("Fechas inválidas: la fecha de fin es anterior a la de inicio");
        }

        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean verificarRangoFecha(LocalDate fecha) {
        if (fechaInicio == null || fechaFin == null || fecha == null) {
            return false;
        }
        return !fecha.isBefore(fechaInicio) && !fecha.isAfter(fechaFin);
    }

    @Override
    public String serializar() {
        StringBuilder sb = new StringBuilder(super.serializar());
        sb.append(",").append(fechaInicio).append(",").append(fechaFin);
        return sb.toString();
    }

    public static TiqueteTemporada deserializar(String linea) {
        String[] datos = deserializarDatos(linea);
        Categoria categoriaTiquete = Categoria.valueOf(datos[0]);
        boolean usoValidado = Boolean.parseBoolean(datos[1]);
        double precio = Double.parseDouble(datos[2]);
        LocalDate fechaInicio = LocalDate.parse(datos[3]);
        LocalDate fechaFin = LocalDate.parse(datos[4]);

        return new TiqueteTemporada(categoriaTiquete, usoValidado, precio, fechaInicio, fechaFin);
    }

    public static String[] deserializarDatos(String linea) {
        return linea.split(",");
    }
}
