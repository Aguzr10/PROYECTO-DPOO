package parque;

import java.time.LocalDate;

public class TiqueteIndividual extends Tiquete {

    private Atracciones atraccion;

    public TiqueteIndividual(Categoria categoriaTiquete, boolean usoValidado, double precio, Atracciones atraccion) {
        super(categoriaTiquete, usoValidado, precio);
        this.atraccion = atraccion;
    }

    public Atracciones getAtraccion() {
        return atraccion;
    }

    public void setAtraccion(Atracciones atraccion) {
        this.atraccion = atraccion;
    }

    public String serializar() {
        StringBuilder sb = new StringBuilder(super.serializar());
        sb.append(",").append(atraccion.getTipo()); // Serializa el tipo de atracción
        return sb.toString();
    }

    public static String[] deserializarDatos(String linea) {
        String[] datos = Tiquete.deserializarDatos(linea);
        String[] result = new String[datos.length + 1];
        System.arraycopy(datos, 0, result, 0, datos.length);
        String[] atraccion = linea.split(",");
        result[datos.length] = atraccion[datos.length];
        return result;
    }

    // Constructor adicional para pruebas
    // Este constructor es solo para pruebas y permite crear un TiqueteIndividual sin depender de una Atracción completa
    public TiqueteIndividual(Categoria categoriaTiquete) {
        super(categoriaTiquete);  // Usamos la categoría que se pase al constructor
        
        // Usamos una subclase de Atracciones, por ejemplo, AtraccionMecanica
        this.atraccion = new AtraccionMecanica("default", null, "default", "default", 0, 0, true, categoriaTiquete, new Temporada(LocalDate.of(2025, 1, 1), LocalDate.of(2025, 12, 31)), 1.0, 2.0, 30.0, 100.0, null, "bajo");
    }

    // Método adicional para pruebas: Permite modificar la atracción sin cambiar la estructura original
    public void setAtraccionParaPrueba(Atracciones atraccion) {
        this.atraccion = atraccion;
    }
}
