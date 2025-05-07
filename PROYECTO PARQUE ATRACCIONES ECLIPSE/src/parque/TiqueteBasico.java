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
        //TODO
        return false;
    }

	public String serializar() {
		StringBuilder sb = new StringBuilder(super.serializar());
		sb.append(",").append(entradaParque);
		return sb.toString();
	}

	public static String[] deserializarDatos(String linea) {
		String[] datos = Tiquete.deserializarDatos(linea);
		String[] result = new String[datos.length + 1];
		System.arraycopy(datos, 0, result, 0, datos.length);
		String[] entrada = linea.split(",");
		result[datos.length] = entrada[datos.length];
		return result;
	}
}
