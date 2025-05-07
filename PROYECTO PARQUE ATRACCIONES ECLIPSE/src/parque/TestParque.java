package parque;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class TestParque {

    public static void main(String[] args) {
        ejecutarTodosLosCasos();
    }

    public static void ejecutarTodosLosCasos() {
        cargarArchivosPrueba();
        crearAtraccionMecanicaValida();
        asignarOperadorNoCertificado();
        validarAccesoTiqueteFamiliarAAtraccionOro();
        validarAccesoFastPass();
        marcarTiqueteComoUsado();
        validarOperacionConMalClima();
        crearTiqueteTemporadaConFechasInvalidas();
    }

    public static void crearAtraccionMecanicaValida() {
        System.out.println("Caso de Prueba 1: Crear atracción mecánica válida");
        AtraccionMecanica atraccionMecanica1 = new AtraccionMecanica("Montaña Rusa Infernal");
        atraccionMecanica1.setAtraccionMecanica(1.40, 2.0, 30.0, 100.0, new RestriccionMedica(true, false, false), "Alto");
        System.out.println("Atracción creada: " + atraccionMecanica1.getNombre());
    }

    public static void asignarOperadorNoCertificado() {
        System.out.println("Caso de Prueba 2: Asignar operador no certificado a atracción de riesgo");
        String operador = "Romino Pérez";
        String certificado = "Montaña Rusa";
        String atraccion = "Barco Pirata";
        if (!certificado.equals("Montaña Rusa Infernal")) {
            System.out.println("Error: Asignación Rechazada");
        }
    }

    public static void validarAccesoTiqueteFamiliarAAtraccionOro() {
        System.out.println("Caso de Prueba 3: Validar acceso con tiquete familiar a atracción oro");
        Cliente cliente1 = new Cliente("Camila Ríos");
        Tiquete tiqueteFamiliar = new TiqueteIndividual(Categoria.familiar);
        AtraccionMecanica atraccionOro = new AtraccionMecanica("Torre de Caída");
        if (tiqueteFamiliar.getCategoriaTiquete() == Categoria.familiar) {
            System.out.println("Se niega el acceso a la atracción oro");
        }
    }

    public static void validarAccesoFastPass() {
        System.out.println("Caso de Prueba 4: Validar acceso con FastPass en fecha correcta");
        FastPass fastPass1 = new FastPass(LocalDate.of(2025, 3, 26));
        LocalDate fechaActualSimulada = LocalDate.of(2025, 3, 26);

        System.out.println("Cliente: Luis Martínez. FastPass asignado a " + fastPass1.getFechaDisponibilidad()
            + ". Fecha actual: " + fechaActualSimulada + ".");

        if (fastPass1.getFechaDisponibilidad().isEqual(fechaActualSimulada)) {
            System.out.println("Acceso preferencial permitido");
        } else {
            System.out.println("No se permite el acceso. FastPass no disponible hoy.");
        }
    }

    public static void marcarTiqueteComoUsado() {
        System.out.println("Caso de Prueba 5: Marcar tiquete como usado");
        TiqueteIndividual tiqueteInd = new TiqueteIndividual(Categoria.familiar);
        tiqueteInd.setUsoValidado(true);
        System.out.println("El estado del tiquete ahora es: " + (tiqueteInd.isUsoValidado() ? "Usado" : "No usado"));
    }

    public static void validarOperacionConMalClima() {
        System.out.println("Caso de Prueba 6: Validar operación de atracción con mal clima");
        AtraccionMecanica atraccionClima = new AtraccionMecanica("Montaña Rusa Infernal");
        atraccionClima.setAtraccionMecanica(1.40, 2.0, 30.0, 120.0, new RestriccionMedica(true, false, true), "Alto");
        if (atraccionClima.isDisponibilidadClima()) {
            System.out.println("La atracción se habilita");
        } else {
            System.out.println("Condiciones climáticas adversas, atracción no habilitada");
        }
    }

    public static void crearTiqueteTemporadaConFechasInvalidas() {
        System.out.println("Caso de Prueba 7: Crear TiqueteTemporada con fechas inválidas");
        try {
            LocalDate fechaInicio = LocalDate.of(2025, 7, 1);
            LocalDate fechaFin = LocalDate.of(2025, 6, 30);
            if (fechaFin.isBefore(fechaInicio)) {
                System.out.println("Fechas inválidas: la fecha de fin es anterior a la de inicio");
            }
        } catch (Exception e) {
            System.out.println("Error en la validación de fechas");
        }
    }

    private static void cargarArchivosPrueba() {
        try (BufferedReader br = new BufferedReader(new FileReader("pruebas/atracciones.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println("Atracción cargada desde archivo: " + linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de atracciones: " + e.getMessage());
        }

        cargarArchivo("pruebas/clientes.txt");
        cargarArchivo("pruebas/empleados.txt");
        cargarArchivo("pruebas/tiquetes.txt");
    }

    private static void cargarArchivo(String archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println("Datos cargados desde archivo: " + linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
