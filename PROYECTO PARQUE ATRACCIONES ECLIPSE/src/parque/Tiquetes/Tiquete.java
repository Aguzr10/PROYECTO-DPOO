package parque.Tiquetes;

import java.io.Serializable;
import java.util.UUID;
import parque.Atracción.Atracciones;
import parque.Categorías.Categoria;

public abstract class Tiquete implements Serializable {
    private static final long serialVersionUID = 1L;  

    protected Categoria categoriaTiquete;
    protected boolean usoValidado;
    protected double precio;

    private final String id;
    private boolean impreso;

    public Tiquete(Categoria categoriaTiquete, boolean usoValidado, double precio) {
        this.categoriaTiquete = categoriaTiquete;
        this.usoValidado = usoValidado;
        this.precio = precio;
        this.id = UUID.randomUUID().toString(); // Genera ID único
        this.impreso = false;
    }

    public Categoria getCategoriaTiquete() {
        return categoriaTiquete;
    }

    public void setCategoriaTiquete(Categoria categoriaTiquete) {
        this.categoriaTiquete = categoriaTiquete;
    }

    public boolean isUsoValidado() {
        return usoValidado;
    }

    public void setUsoValidado(boolean usoValidado) {
        this.usoValidado = usoValidado;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public boolean estaUsado() {
        return usoValidado;
    }

    public boolean estaImpreso() {
        return impreso;
    }

    public void marcarImpreso() {
        this.impreso = true;
    }

    public boolean verificarValidez() {
        return !usoValidado;
    }

    public void marcarTiquete() {
        this.usoValidado = true;
    }

    public String serializar() {
        StringBuilder sb = new StringBuilder();
        sb.append(categoriaTiquete).append(",");
        sb.append(usoValidado).append(",");
        sb.append(precio).append(",");
        sb.append(id).append(",");
        sb.append(impreso);
        return sb.toString();
    }

    public static String[] deserializarDatos(String linea) {
        return linea.split(",");
    }

    public boolean verificaAcceso(Atracciones atraccion) {
        if (atraccion.getCategoria() instanceof Categoria) {
            return this.categoriaTiquete.permiteAcceso((Categoria) atraccion.getCategoria());
        }
        return false; 
    }
}
