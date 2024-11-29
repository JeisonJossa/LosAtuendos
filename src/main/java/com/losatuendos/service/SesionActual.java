package com.losatuendos.service;

import com.losatuendos.entity.Empleado;
import org.springframework.stereotype.Component;

/**
 * Clase que gestiona la sesión actual del sistema.
 */
@Component
public class SesionActual {

    private Empleado empleadoActual;

    /**
     * Establece el empleado autenticado.
     *
     * @param empleado Empleado autenticado.
     */
    public void setEmpleadoActual(Empleado empleado) {
        this.empleadoActual = empleado;
    }

    /**
     * Obtiene el empleado autenticado actualmente.
     *
     * @return Empleado autenticado.
     */
    public Empleado getEmpleadoActual() {
        return empleadoActual;
    }

    /**
     * Limpia la sesión actual.
     */
    public void cerrarSesion() {
        this.empleadoActual = null;
    }
}
