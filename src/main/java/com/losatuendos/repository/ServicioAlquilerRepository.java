package com.losatuendos.repository;

import com.losatuendos.entity.ServicioAlquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ServicioAlquilerRepository extends JpaRepository<ServicioAlquiler, Integer> {

    /**
     * Encuentra un servicio de alquiler por su ID.
     *
     * @param idServicio ID del servicio de alquiler.
     * @return Servicio de alquiler con el ID especificado.
     */
    ServicioAlquiler findById(int idServicio);

    /**
     * Encuentra todos los servicios de alquiler realizados por un cliente específico.
     *
     * @param idCliente ID del cliente.
     * @return Lista de servicios de alquiler del cliente.
     */
    List<ServicioAlquiler> findByIdCliente_Id(Integer idCliente);

    /**
     * Encuentra todos los servicios de alquiler realizados por un empleado específico.
     *
     * @param idEmpleado ID del empleado.
     * @return Lista de servicios de alquiler gestionados por el empleado.
     */
    List<ServicioAlquiler> findByIdEmpleado_Id(Integer idEmpleado);

    /**
     * Encuentra todos los servicios de alquiler en una fecha específica.
     *
     * @param fechaAlquiler Fecha del alquiler.
     * @return Lista de servicios de alquiler para esa fecha.
     */
    List<ServicioAlquiler> findByFechaAlquiler(Date fechaAlquiler);

    /**
     * Encuentra todos los servicios de alquiler con fecha igual o posterior a la actual (vigentes).
     *
     * @param fechaActual Fecha actual.
     * @return Lista de servicios vigentes.
     */
    @Query("SELECT s FROM ServicioAlquiler s WHERE s.fechaAlquiler >= :fechaActual")
    List<ServicioAlquiler> findVigentes(@Param("fechaActual") Date fechaActual);

    /**
     * Cuenta el número total de servicios de alquiler realizados por un cliente.
     *
     * @param idCliente ID del cliente.
     * @return Número total de servicios realizados por el cliente.
     */
    @Query("SELECT COUNT(s) FROM ServicioAlquiler s WHERE s.idCliente = :idCliente")
    long countByIdCliente(@Param("idCliente") Integer idCliente);

    /**
     * Cuenta el número total de servicios gestionados por un empleado.
     *
     * @param idEmpleado ID del empleado.
     * @return Número total de servicios gestionados por el empleado.
     */
    @Query("SELECT COUNT(s) FROM ServicioAlquiler s WHERE s.idEmpleado = :idEmpleado")
    long countByIdEmpleado(@Param("idEmpleado") Integer idEmpleado);

    /**
     * Encuentra los servicios de alquiler registrados en un rango de fechas.
     *
     * @param fechaInicio Fecha inicial del rango.
     * @param fechaFin Fecha final del rango.
     * @return Lista de servicios de alquiler en el rango de fechas.
     */
    @Query("SELECT s FROM ServicioAlquiler s WHERE s.fechaAlquiler BETWEEN :fechaInicio AND :fechaFin")
    List<ServicioAlquiler> findByFechaAlquilerBetween(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);
}
