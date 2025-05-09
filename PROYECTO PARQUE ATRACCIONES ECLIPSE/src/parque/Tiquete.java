package parque;

public abstract class Tiquete {

    protected Categoria categoriaTiquete;
    protected boolean usoValidado;
    protected double precio;

    public Tiquete(Categoria categoriaTiquete, boolean usoValidado, double precio) {
        this.categoriaTiquete = categoriaTiquete;
        this.usoValidado = usoValidado;
        this.precio = precio;
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
        sb.append(precio);
        return sb.toString();
    }

    public static String[] deserializarDatos(String linea) {
        return linea.split(",");
    }
}
