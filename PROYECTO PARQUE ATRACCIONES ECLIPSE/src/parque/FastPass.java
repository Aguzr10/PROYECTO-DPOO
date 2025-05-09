package parque;

import java.time.LocalDate;

public class FastPass {

    private LocalDate fechaDisponibilidad;

    public FastPass(LocalDate fechaDisponibilidad) {
        this.fechaDisponibilidad = fechaDisponibilidad;
    }

    public LocalDate getFechaDisponibilidad() {
        return fechaDisponibilidad;
    }

    public void setFechaDisponibilidad(LocalDate fechaDisponibilidad) {
        this.fechaDisponibilidad = fechaDisponibilidad;
    }

    public boolean verificarValidez() {
        return LocalDate.now().isEqual(fechaDisponibilidad);
    }
}
