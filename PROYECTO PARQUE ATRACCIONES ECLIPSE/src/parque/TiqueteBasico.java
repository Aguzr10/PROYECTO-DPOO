package parque;

public class TiqueteBasico extends Tiquete {

    private boolean entradaParque;

    public TiqueteBasico(Categoria categoriaTiquete, boolean usoValidado, double precio, boolean entradaParque) {
        super(categoriaTiquete, usoValidado, precio); 
        this.entradaParque = entradaParque;
    }

    public boolean isEntradaParque() {
        return entradaParque;
    }

    public void setEntradaParque(boolean entradaParque) {
        this.entradaParque = entradaParque;
    }

    @Override
    public boolean verificarValidez() {
        return !isUsoValidado() && entradaParque; // Si no ha sido validado y tiene entrada al parque
    }

    @Override
    public String serializar() {
        StringBuilder sb = new StringBuilder(super.serializar());
        sb.append(",").append(entradaParque);
        return sb.toString();
    }

    public static TiqueteBasico deserializar(String linea) {
        String[] datos = deserializarDatos(linea);
        Categoria categoriaTiquete = Categoria.valueOf(datos[0]);
        boolean usoValidado = Boolean.parseBoolean(datos[1]);
        double precio = Double.parseDouble(datos[2]);
        boolean entradaParque = Boolean.parseBoolean(datos[3]);
        return new TiqueteBasico(categoriaTiquete, usoValidado, precio, entradaParque);
    }
}
