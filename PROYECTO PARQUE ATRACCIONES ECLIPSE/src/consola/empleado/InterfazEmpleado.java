package consola.empleado;

import parque.Administración.Empleados;
import consola.util.SistemaParque;
import parque.Tiquetes.Tiquete;
import java.util.List;
import java.util.Scanner;

public class InterfazEmpleado {
    public static void iniciar(Empleados empleado, SistemaParque sistema) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n=== MENÚ EMPLEADO ===");
            System.out.println("1. Ver lugar y turno asignado");
            System.out.println("2. Validar tiquete");
            System.out.println("3. Consultar información de atracción asignada");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                	System.out.println("Lugar: " + empleado.getLugarAsignado());
                    System.out.println("Turno: " + empleado.getTurno());
                    break;
                case "2":
                    List<Tiquete> tiquetes = sistema.getTiquetes();

                    if (tiquetes.isEmpty()) {
                        System.out.println("No hay tiquetes registrados.");
                        break;
                    }

                    System.out.println("Seleccione el número del tiquete a validar:");
                    for (int i = 0; i < tiquetes.size(); i++) {
                        Tiquete t = tiquetes.get(i);
                        System.out.println((i + 1) + ". " + t.getCategoriaTiquete() + " | Usado: " + (t.estaUsado() ? "Sí" : "No"));
                    }

                    try {
                        int seleccion = Integer.parseInt(scanner.nextLine()) - 1;

                        if (seleccion >= 0 && seleccion < tiquetes.size()) {
                            Tiquete seleccionado = tiquetes.get(seleccion);
                            if (!seleccionado.estaUsado()) {
                                seleccionado.marcarTiquete();  
                                System.out.println("Tiquete validado exitosamente.");
                            } else {
                                System.out.println("Este tiquete ya ha sido usado.");
                            }
                        } else {
                            System.out.println("Selección inválida.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Debe ingresar un número válido.");
                    }
                    break;
            	}
        }
        scanner.close();
    }
}