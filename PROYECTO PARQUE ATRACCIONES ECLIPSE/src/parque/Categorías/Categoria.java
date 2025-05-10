package parque.Categorías;

public enum Categoria {
    BASICO, FAMILIAR, ORO, DIAMANTE;

    public boolean permiteAcceso(Categoria categoriaAtraccion) {
       
        switch (this) {
            case DIAMANTE:
                return true; 
            case ORO:
                return categoriaAtraccion != Categoria.DIAMANTE; 
            case FAMILIAR:
                return categoriaAtraccion == Categoria.FAMILIAR || categoriaAtraccion == Categoria.BASICO;
            case BASICO:
                return categoriaAtraccion == Categoria.BASICO;
            default:
                return false;
        }
    }
}
