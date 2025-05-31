package consola.util;
import java.util.Random;

import parque.Categorías.Categoria;

public class CategoriaUtils {
    private static final Random random = new Random();

    public static Categoria obtenerCategoriaAleatoria() {
        Categoria[] categorias = Categoria.values();
        int indice = random.nextInt(categorias.length);
        return categorias[indice];
    }
}

