package tests;
import parque.Atracción.*;
import parque.Categorías.Categoria;
import parque.Categorías.Temporada;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestAtracción {
	// ATRIBUTOS A USAR E INSTANCIAS
	private static String nombreCultural = "Barco Pirata",
				   nombreMecanica = "Montaña Rusa Infernal",
				   nombreEspectaculo = "Desfile Apertura",
				   ubicacionCultural = "Lago", 
				   ubicacionMecanica = "Centro",
				   nivelRiesgo = "Alto",
				   hora = "1:00PM";
	
	private static double alturaMin = 1.2,
				   alturaMax = 2.0,
				   pesoMin = 49.0,
				   pesoMax = 99.0;
	
	private static int cupoMaximoCultural = 30,
			cupoMaximoMecanica = 16,
			empleadosEncargadosCultural = 4,
			empleadosEncargadosMecanica = 2;
	
	private static List<String> tipoPersonalCultural = new ArrayList<String>(),
						 tipoPersonalMecanica = new ArrayList<String>();
	
	private static boolean disponibilidadClima = true,
			restriccionEdad = true;
	
	private static Categoria tipoTiqueteCultural = Categoria.BASICO,
					  tipoTiqueteMecanica = Categoria.ORO;
	
	private static LocalDate fechaInicio = LocalDate.of(2025, 1, 25),
					  fechaCierre = LocalDate.of(2025, 5, 31);
	
	private static Temporada temporadaCultural, temporadaMecanica;
	private static RestriccionMedica restriccion;
	
	private static AtraccionCultural cultural;
	private static AtraccionMecanica mecánica;
	private static Espectaculo espectáculo;
	
	@BeforeAll
	public static void setUp() {
		tipoPersonalCultural.add("actor capitán");
		tipoPersonalCultural.add("actor tripulación");
		tipoPersonalCultural.add("seguridad");
		tipoPersonalMecanica.add("operador atracción");
		tipoPersonalMecanica.add("asistente operador atracción");
		
		temporadaCultural = new Temporada(fechaInicio, fechaCierre);
		temporadaMecanica = new Temporada(fechaInicio, fechaCierre);
		restriccion = new RestriccionMedica(true, true, true);
		
		cultural = new AtraccionCultural(
				"Cultural", tipoPersonalCultural, nombreCultural, ubicacionCultural, cupoMaximoCultural, 
				empleadosEncargadosCultural, disponibilidadClima, tipoTiqueteCultural, temporadaCultural, restriccionEdad);
		
		mecánica = new AtraccionMecanica(
				"Mecánica", tipoPersonalMecanica, nombreMecanica, ubicacionMecanica, cupoMaximoMecanica, empleadosEncargadosMecanica, 
				disponibilidadClima, tipoTiqueteMecanica, temporadaMecanica, alturaMin, alturaMax, pesoMin, pesoMax, restriccion, nivelRiesgo);
		
		espectáculo = new Espectaculo(fechaInicio, hora, nombreEspectaculo);
	}
	
	@Test
	void testSerializarAtraccionCultural() {
		assertEquals(
				"Cultural,actor capitán;actor tripulación;seguridad;Barco Pirata;Lago;30;4;true;BASICO;2025-01-25,2025-05-31;Básico;0;true",
				cultural.serializar(), "La serialización de la atracción Cultural falló!");
	}
	
	@Test
	void testVerificarEdadCultural() {
		assertFalse(cultural.verificarEdad(12), "La restricción de edad falló para menores de 13 años!");
		assertTrue(cultural.verificarEdad(22), "La restricción de edad falló para adultos!");
	}
	
	@Test
	void testSerializarMecanica() {
		assertEquals(
				"Mecánica,operador atracción;asistente operador atracción;Montaña Rusa Infernal;Centro;16;2;true;ORO;2025-01-25,2025-05-31;Básico;0;1.2;2.0;49.0;99.0;true,true,true;Alto",
				mecánica.serializar(), "La serialización de la atracción Mecánica falló!");
	}
	
	@Test
	void testVerfAltura() {
		assertFalse(mecánica.verfAltura(0.9), "La verificación de altura falló para alturas mínimas!");
		assertTrue(mecánica.verfAltura(1.4), "La verificación de altura falló para alturas válidas!");
		assertFalse(mecánica.verfAltura(2.1), "La verificación de altura falló para alturas máximas!");
	}
	
	@Test
	void testVerfPeso() {
		assertFalse(mecánica.verfPeso(30), "La verificación de altura falló para pesos mínimos!");
		assertTrue(mecánica.verfPeso(69), "La verificación de altura falló para pesos válidos!");
		assertFalse(mecánica.verfPeso(110), "La verificación de altura falló para pesos máximos!");
	}
	
	
	@Test
	void testVerificarAcceso() {
		assertFalse(mecánica.verificarAcceso(true, true, true), "La verificación de acceso por restricción médica falló!");
	}
	
	@Test
	void testSerializarEspectaculo() {
		assertEquals("2025-01-25,1:00PM,Desfile Apertura", espectáculo.serializar(), "La serialización del Espetáculo falló!");
	}
	
	@Test
	void testMostrarInfo() {
		assertEquals("Espectáculo: Desfile Apertura - Fecha: 2025-01-25 - Hora: 1:00PM", espectáculo.mostrarInfo(), "La información mostrada no fue la esperada!");
	}
	
	@AfterAll
	public static void tearDown() {}
}
