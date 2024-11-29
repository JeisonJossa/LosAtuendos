package com.losatuendos.service;

import com.losatuendos.entity.ServicioAlquiler;
import com.losatuendos.repository.ServicioAlquilerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioAlquilerService {

    @Autowired
    private ServicioAlquilerRepository servicioAlquilerRepository;

    /**
     * Busca un servicio de alquiler por su ID.
     *
     * @param idServicio ID del servicio de alquiler.
     * @return Servicio de alquiler si existe, de lo contrario null.
     */
    public ServicioAlquiler buscarPorId(Integer idServicio) {
        Optional<ServicioAlquiler> servicio = servicioAlquilerRepository.findById(idServicio);
        return servicio.orElse(null);
    }

    /**
     * Busca todos los servicios realizados por un cliente específico.
     *
     * @param idCliente ID del cliente.
     * @return Lista de servicios de alquiler asociados al cliente.
     */
    public List<ServicioAlquiler> obtenerPorCliente(Integer idCliente) {
        return servicioAlquilerRepository.findByIdCliente_Id(idCliente);
    }

    /**
     * Busca todos los servicios gestionados por un empleado específico.
     *
     * @param idEmpleado ID del empleado.
     * @return Lista de servicios gestionados por el empleado.
     */
    public List<ServicioAlquiler> obtenerPorEmpleado(Integer idEmpleado) {
        return servicioAlquilerRepository.findByIdEmpleado_Id(idEmpleado);
    }

    /**
     * Busca todos los servicios realizados en una fecha específica.
     *
     * @param fecha Fecha del alquiler.
     * @return Lista de servicios realizados en esa fecha.
     */
    public List<ServicioAlquiler> obtenerPorFecha(Date fecha) {
        return servicioAlquilerRepository.findByFechaAlquiler(fecha);
    }

    /**
     * Busca todos los servicios vigentes con fecha igual o posterior a la actual.
     *
     * @param fechaActual Fecha actual.
     * @return Lista de servicios vigentes.
     */
    public List<ServicioAlquiler> obtenerServiciosVigentes(Date fechaActual) {
        return servicioAlquilerRepository.findVigentes(fechaActual);
    }

    /**
     * Registra o actualiza un servicio de alquiler.
     *
     * @param servicioAlquiler Objeto servicio a registrar o actualizar.
     * @return Servicio registrado o actualizado.
     */
    public ServicioAlquiler guardarServicio(ServicioAlquiler servicioAlquiler) {
        return servicioAlquilerRepository.save(servicioAlquiler);
    }

    /**
     * Elimina un servicio de alquiler por su ID.
     *
     * @param idServicio ID del servicio.
     * @return True si se eliminó correctamente, false si no se encontró.
     */
    public boolean eliminarPorId(Integer idServicio) {
        if (servicioAlquilerRepository.existsById(idServicio)) {
            servicioAlquilerRepository.deleteById(idServicio);
            return true;
        }
        return false;
    }

    /**
     * Cuenta los servicios realizados por un cliente específico.
     *
     * @param idCliente ID del cliente.
     * @return Número de servicios realizados por el cliente.
     */
    public long contarPorCliente(Integer idCliente) {
        return servicioAlquilerRepository.countByIdCliente(idCliente);
    }

    /**
     * Cuenta los servicios gestionados por un empleado específico.
     *
     * @param idEmpleado ID del empleado.
     * @return Número de servicios gestionados por el empleado.
     */
    public long contarPorEmpleado(Integer idEmpleado) {
        return servicioAlquilerRepository.countByIdEmpleado(idEmpleado);
    }

    /**
     * Busca servicios registrados en un rango de fechas.
     *
     * @param fechaInicio Fecha inicial del rango.
     * @param fechaFin Fecha final del rango.
     * @return Lista de servicios registrados en el rango.
     */
    public List<ServicioAlquiler> obtenerPorRangoDeFechas(Date fechaInicio, Date fechaFin) {
        return servicioAlquilerRepository.findByFechaAlquilerBetween(fechaInicio, fechaFin);
    }
}
