package tests;
import parque.Atracción.Atracciones;
import parque.Categorías.Categoria;
import parque.Tiquetes.*;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestTiquetes {
	// ATRIBUTOS E INSTANCIAS A USAR
	private static LocalDate fechaDisponibilidad, fechaInicio, fechaFinal;
	private static Categoria categoriaTiquete;
	private static boolean usoValidado, entradaParque;
	private static double precio;
	private static Atracciones atraccion;
	
	private static FastPass fastPass;
	private static TiqueteBasico básico;
	private static TiqueteIndividual individual;
	private static TiqueteTemporada temporada;
	
	void setUp() {
		fastPass = new FastPass(fechaDisponibilidad);
		básico = new TiqueteBasico(categoriaTiquete, usoValidado, precio, entradaParque);
		individual = new TiqueteIndividual(categoriaTiquete, usoValidado, precio, atraccion);
		temporada = new TiqueteTemporada(categoriaTiquete, usoValidado, precio, fechaInicio, fechaFinal);
	}
}
