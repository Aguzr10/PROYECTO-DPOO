package tests;

import parque.Atracción.AtraccionMecanica;
import parque.Atracción.RestriccionMedica;
import parque.Categorías.Categoria;
import parque.Categorías.Temporada;
import parque.Tiquetes.*;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestTiquetes {
	private static LocalDate fechaDisponibilidad = LocalDate.of(2025,6,25),
			fechaInicio = LocalDate.of(2025,5,25),
			fechaFinal = LocalDate.of(2025,7,25),
			fecha = LocalDate.of(2025,6,25);

	private static Categoria tiqueteBasico = Categoria.BASICO,
			tiqueteTemporada = Categoria.DIAMANTE,
			tiqueteAtraccion = Categoria.BASICO;

	private static double precioTiqueteTemporadaDiamante = 70000.0,
			precioTiqueteBasico = 30000.0,
			precioTiqueteAtraccion = 12000.0;

	private static AtraccionMecanica mecánica;

	private static FastPass fastPass;
	private static TiqueteBasico básico;
	private static TiqueteIndividual individual;
	private static TiqueteTemporada temporada;

	@BeforeAll
	public static void setUp() {
		RestriccionMedica restriccion = new RestriccionMedica(true, true, true);
		List<String> tipoPersonalMecanica = new ArrayList<>();
		tipoPersonalMecanica.add("operador atracción");
		tipoPersonalMecanica.add("asistente operador atracción");

		Temporada temporadaMecanica = new Temporada(fechaInicio, fechaFinal);

		mecánica = new AtraccionMecanica("Mecánica", tipoPersonalMecanica, "Montaña Rusa", "Zona Central", 20, 2, true,
				tiqueteAtraccion, temporadaMecanica, 1.2, 2.0, 45.0, 100.0, restriccion, "Alto");

		fastPass = new FastPass(fechaDisponibilidad);
		básico = new TiqueteBasico(tiqueteBasico, false, precioTiqueteBasico, true);
		individual = new TiqueteIndividual(tiqueteAtraccion, false, precioTiqueteAtraccion, mecánica);
		temporada = new TiqueteTemporada(tiqueteTemporada, false, precioTiqueteTemporadaDiamante, fechaInicio, fechaFinal);
	}

	@Test
	public void testFastPass() {
		assertEquals(fechaDisponibilidad, fastPass.getFechaDisponibilidad(), "No me está devolviendo la fecha de disponibilidad que configuré.");
		assertNotNull(fastPass.verificarValidez(), "Esperaba que el FastPass fuera válido, pero devolvió null.");
	}

	@Test
	public void testTiqueteBasico() {
		básico.marcarTiquete();
		assertFalse(básico.verificarValidez(), "Marqué el tiquete básico como usado, pero aún aparece como válido.");

		String serializado = básico.serializar();
		assertNotNull(serializado, "Al serializar el tiquete básico obtuve null, y esperaba un string.");
		assertTrue(serializado.contains(tiqueteBasico.toString()), "La categoría del tiquete básico no se incluyó en la serialización.");
		assertTrue(serializado.contains(String.valueOf(básico.isUsoValidado())), "El estado de uso del tiquete básico no aparece en la serialización.");
		assertTrue(serializado.contains(String.valueOf(precioTiqueteBasico)), "El precio del tiquete básico no fue incluido en la serialización.");

		básico.setUsoValidado(false);
	}

	@Test
	public void testTiqueteIndividual() {
		assertEquals(mecánica, individual.getAtraccion(), "La atracción asignada al tiquete individual no es la que configuré.");
		assertTrue(individual.verificarValidez(), "Esperaba que el tiquete individual fuera válido al inicio.");

		individual.marcarTiquete();
		assertFalse(individual.verificarValidez(), "Usé el tiquete individual pero sigue siendo válido.");

		String serializado = individual.serializar();
		assertNotNull(serializado, "La serialización del tiquete individual me devolvió null.");
		assertTrue(serializado.contains(tiqueteAtraccion.toString()), "La categoría del tiquete individual no aparece en la serialización.");
		assertTrue(serializado.contains(String.valueOf(individual.isUsoValidado())), "El estado de uso no fue serializado correctamente.");
		assertTrue(serializado.contains(String.valueOf(precioTiqueteAtraccion)), "El precio del tiquete individual no está en la serialización.");

		individual.setUsoValidado(false);
	}

	@Test
	public void testTiqueteTemporada() {
		assertTrue(temporada.verificarRangoFecha(fecha), "La fecha debería estar dentro del rango válido, pero me dice que no.");
		LocalDate fechaFuera = LocalDate.of(2025, 8, 1);
		assertFalse(temporada.verificarRangoFecha(fechaFuera), "Probé con una fecha fuera del rango y aún así la considera válida.");

		temporada.marcarTiquete();

		String serializado = temporada.serializar();
		assertNotNull(serializado, "Al serializar el tiquete de temporada me devolvió null.");
		assertTrue(serializado.contains(tiqueteTemporada.toString()), "La categoría no se incluyó en la serialización del tiquete de temporada.");
		assertTrue(serializado.contains(String.valueOf(temporada.isUsoValidado())), "El estado de uso no aparece correctamente serializado.");
		assertTrue(serializado.contains(String.valueOf(precioTiqueteTemporadaDiamante)), "El precio del tiquete de temporada no fue serializado.");
		assertTrue(serializado.contains(fechaInicio.toString()), "La fecha de inicio no está en la serialización.");
		assertTrue(serializado.contains(fechaFinal.toString()), "La fecha final no está en la serialización.");

		temporada.setUsoValidado(false);
	}

	@Test
	public void testPermisosAcceso() {
		assertTrue(Categoria.DIAMANTE.permiteAcceso(Categoria.BASICO), "DIAMANTE debería permitir acceso a BASICO ");
		assertTrue(Categoria.DIAMANTE.permiteAcceso(Categoria.FAMILIAR), "DIAMANTE debería permitir acceso a FAMILIAR.");
		assertTrue(Categoria.DIAMANTE.permiteAcceso(Categoria.ORO), "DIAMANTE debería permitir acceso a ORO.");
		assertTrue(Categoria.DIAMANTE.permiteAcceso(Categoria.DIAMANTE), "DIAMANTE no se está permitiendo a sí mismo.");

		assertTrue(Categoria.ORO.permiteAcceso(Categoria.BASICO), "ORO debería permitir acceso a BASICO.");
		assertTrue(Categoria.ORO.permiteAcceso(Categoria.FAMILIAR), "ORO debería permitir acceso a FAMILIAR.");
		assertTrue(Categoria.ORO.permiteAcceso(Categoria.ORO), "ORO no se está permitiendo a sí mismo.");
		assertFalse(Categoria.ORO.permiteAcceso(Categoria.DIAMANTE), "ORO no debería permitir acceso a DIAMANTE");

		assertTrue(Categoria.FAMILIAR.permiteAcceso(Categoria.BASICO), "FAMILIAR debería permitir acceso a BASICO.");
		assertTrue(Categoria.FAMILIAR.permiteAcceso(Categoria.FAMILIAR), "FAMILIAR debería permitirse a sí mismo.");
		assertFalse(Categoria.FAMILIAR.permiteAcceso(Categoria.ORO), "FAMILIAR no debería permitir acceso a ORO.");
		assertFalse(Categoria.FAMILIAR.permiteAcceso(Categoria.DIAMANTE), "FAMILIAR no debería permitir acceso a DIAMANTE.");

		assertTrue(Categoria.BASICO.permiteAcceso(Categoria.BASICO), "BÁSICO debería permitirse a sí mismo.");
		assertFalse(Categoria.BASICO.permiteAcceso(Categoria.FAMILIAR), "BÁSICO no debería permitir acceso a FAMILIAR.");
		assertFalse(Categoria.BASICO.permiteAcceso(Categoria.ORO), "BÁSICO no debería permitir acceso a ORO.");
		assertFalse(Categoria.BASICO.permiteAcceso(Categoria.DIAMANTE), "BÁSICO no debería permitir acceso a DIAMANTE.");
	}

	@AfterAll
	public static void tearDown() {}
}


    
