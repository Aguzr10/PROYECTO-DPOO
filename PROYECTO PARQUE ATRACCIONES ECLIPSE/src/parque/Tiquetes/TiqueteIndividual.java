package parque.Tiquetes;

import java.time.LocalDate;

import parque.Atracción.AtraccionMecanica;
import parque.Atracción.Atracciones;
import parque.Categorías.Categoria;
import parque.Categorías.Temporada;

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

    @Override
    public boolean verificarValidez() {
        return !isUsoValidado() && atraccion != null; 
    }

    @Override
    public String serializar() {
        StringBuilder sb = new StringBuilder(super.serializar());
        sb.append(",").append(atraccion.getTipo()); 
        return sb.toString();
    }

    public static TiqueteIndividual deserializar(String linea) {
        String[] datos = deserializarDatos(linea);
        Categoria categoriaTiquete = Categoria.valueOf(datos[0]);
        boolean usoValidado = Boolean.parseBoolean(datos[1]);
        double precio = Double.parseDouble(datos[2]);
        String tipoAtraccion = datos[3]; 
        
       
        Atracciones atraccion = new AtraccionMecanica(tipoAtraccion, null, "default", "default", 0, 0, true, categoriaTiquete, new Temporada(LocalDate.of(2025, 1, 1), LocalDate.of(2025, 12, 31)), 1.0, 2.0, 30.0, 100.0, null, "bajo"); // Esto es solo un ejemplo
        
        return new TiqueteIndividual(categoriaTiquete, usoValidado, precio, atraccion);
    }
}
