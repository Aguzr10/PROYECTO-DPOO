package tests;
import parque.Categorías.*;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestCategorías {
	// ATRIBUTOS E INSTANCIAS A USAR
	private static  LocalDate fechaInicio = LocalDate.of(2025, 1, 25),
					  fechaIntermedia = LocalDate.of(2025, 3, 15),
					  fechaFinal = LocalDate.of(2025, 5, 31),
					  fechaPasada = LocalDate.of(2025, 6, 1);
	private static Temporada temporada;
	
	@BeforeAll
	public static void setUp() {
		temporada = new Temporada(fechaInicio, fechaFinal);
	}
	
	@Test
	void testSerializar() {
		assertEquals("2025-1-25,2025-5-31", temporada.serializar(), "La serialización de la temporada no fue la esperada!");
	}
	
	@Test
	void testDuracionEnDias() {
		assertEquals(128, temporada.duracionEnDias(), "El cálculo de días no fue el esperado!");
	}
	
	@Test
	void testFechaDentroDeRango() {
		assertTrue(temporada.fechaDentroDeRango(fechaIntermedia), "La prueba de rango falló para una fecha válida!");
		assertFalse(temporada.fechaDentroDeRango(fechaPasada), "La prueba de rango falló para una fecha inválida!");
	}
	
	@AfterAll
	public static void tearDown() {}
}
