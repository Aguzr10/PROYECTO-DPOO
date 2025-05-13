package tests;

import parque.Atracción.AtraccionMecanica;
import parque.Atracción.Atracciones;
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
			precioTiqueteAtraccion = 12000.0,
			precioFastPass = 20000.0;

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
		assertEquals(fechaDisponibilidad, fastPass.getFechaDisponibilidad());
		assertNotNull(fastPass.verificarValidez());
	}

	@Test
	public void testTiqueteBasico() {
		básico.marcarTiquete();
		assertFalse(básico.verificarValidez());

		String serializado = básico.serializar();
		assertNotNull(serializado);
		assertTrue(serializado.contains(tiqueteBasico.toString()));
		assertTrue(serializado.contains(String.valueOf(básico.isUsoValidado())));
		assertTrue(serializado.contains(String.valueOf(precioTiqueteBasico)));

		básico.setUsoValidado(false);
	}

	@Test
	public void testTiqueteIndividual() {
		assertEquals(mecánica, individual.getAtraccion());
		assertTrue(individual.verificarValidez());

		individual.marcarTiquete();
		assertFalse(individual.verificarValidez());

		String serializado = individual.serializar();
		assertNotNull(serializado);
		assertTrue(serializado.contains(tiqueteAtraccion.toString()));
		assertTrue(serializado.contains(String.valueOf(individual.isUsoValidado())));
		assertTrue(serializado.contains(String.valueOf(precioTiqueteAtraccion)));

		individual.setUsoValidado(false);
	}

	@Test
	public void testTiqueteTemporada() {
		assertTrue(temporada.verificarRangoFecha(fecha));
		LocalDate fechaFuera = LocalDate.of(2025, 8, 1);
		assertFalse(temporada.verificarRangoFecha(fechaFuera));

		temporada.marcarTiquete();

		String serializado = temporada.serializar();
		assertNotNull(serializado);
		assertTrue(serializado.contains(tiqueteTemporada.toString()));
		assertTrue(serializado.contains(String.valueOf(temporada.isUsoValidado())));
		assertTrue(serializado.contains(String.valueOf(precioTiqueteTemporadaDiamante)));
		assertTrue(serializado.contains(fechaInicio.toString()));
		assertTrue(serializado.contains(fechaFinal.toString()));

		temporada.setUsoValidado(false);
	}

	@Test
	public void testPermisosAcceso() {
		assertTrue(Categoria.DIAMANTE.permiteAcceso(Categoria.BASICO));
		assertTrue(Categoria.DIAMANTE.permiteAcceso(Categoria.FAMILIAR));
		assertTrue(Categoria.DIAMANTE.permiteAcceso(Categoria.ORO));
		assertTrue(Categoria.DIAMANTE.permiteAcceso(Categoria.DIAMANTE));

		assertTrue(Categoria.ORO.permiteAcceso(Categoria.BASICO));
		assertTrue(Categoria.ORO.permiteAcceso(Categoria.FAMILIAR));
		assertTrue(Categoria.ORO.permiteAcceso(Categoria.ORO));
		assertFalse(Categoria.ORO.permiteAcceso(Categoria.DIAMANTE));

		assertTrue(Categoria.FAMILIAR.permiteAcceso(Categoria.BASICO));
		assertTrue(Categoria.FAMILIAR.permiteAcceso(Categoria.FAMILIAR));
		assertFalse(Categoria.FAMILIAR.permiteAcceso(Categoria.ORO));
		assertFalse(Categoria.FAMILIAR.permiteAcceso(Categoria.DIAMANTE));

		assertTrue(Categoria.BASICO.permiteAcceso(Categoria.BASICO));
		assertFalse(Categoria.BASICO.permiteAcceso(Categoria.FAMILIAR));
		assertFalse(Categoria.BASICO.permiteAcceso(Categoria.ORO));
		assertFalse(Categoria.BASICO.permiteAcceso(Categoria.DIAMANTE));
	}

	@AfterAll
	public static void tearDown() {}
}


    
