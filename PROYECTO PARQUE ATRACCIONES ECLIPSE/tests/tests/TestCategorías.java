package tests;
import parque.Categorías.*;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestCategorías {
	// ATRIBUTOS E INSTANCIAS A USAR
	private LocalDate fechaInicio, fechaFinal;
	private Temporada temporada;
	
	@BeforeAll
	void setUp() {
		temporada = new Temporada(fechaInicio, fechaFinal);
	}
	
	@AfterAll
	void tearDown() {}
}
