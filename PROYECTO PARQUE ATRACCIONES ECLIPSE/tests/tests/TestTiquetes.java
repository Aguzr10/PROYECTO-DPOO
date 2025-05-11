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
	private LocalDate fechaDisponibilidad, fechaInicio, fechaFinal;
	private Categoria categoriaTiquete;
	private boolean usoValidado, entradaParque;
	private double precio;
	private Atracciones atraccion;
	
	private FastPass fastPass;
	private TiqueteBasico básico;
	private TiqueteIndividual individual;
	private TiqueteTemporada temporada;
	
	void setUp() {
		fastPass = new FastPass(fechaDisponibilidad);
		básico = new TiqueteBasico(categoriaTiquete, usoValidado, precio, entradaParque);
		individual = new TiqueteIndividual(categoriaTiquete, usoValidado, precio, atraccion);
		temporada = new TiqueteTemporada(categoriaTiquete, usoValidado, precio, fechaInicio, fechaFinal);
	}
}
