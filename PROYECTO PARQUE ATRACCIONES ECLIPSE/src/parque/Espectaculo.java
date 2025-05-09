package parque;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Espectaculo {

    private LocalDate fecha;
    private String hora;
    private String nombre;

    public Espectaculo(LocalDate fecha, String hora, String nombre) {
        this.fecha = fecha;
        this.hora = hora;
        this.nombre = nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String mostrarInfo() {
        return "Espectáculo: " + nombre + " - Fecha: " + fecha + " - Hora: " + hora;
    }

    public String serializar() {
        return fecha.format(DateTimeFormatter.ISO_LOCAL_DATE) + "," + hora + "," + nombre;
    }

    public static Espectaculo deserializar(String linea) {
        String[] partes = linea.split(",");
        if (partes.length != 3) return null;
        LocalDate fecha = LocalDate.parse(partes[0]);
        String hora = partes[1];
        String nombre = partes[2];
        return new Espectaculo(fecha, hora, nombre);
    }
}
