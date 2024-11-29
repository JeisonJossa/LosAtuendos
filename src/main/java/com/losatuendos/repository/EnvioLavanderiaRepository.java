package com.losatuendos.repository;

import com.losatuendos.entity.EnvioLavanderia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface EnvioLavanderiaRepository extends JpaRepository<EnvioLavanderia, Integer> {

    /**
     * Encuentra todos los envíos realizados en una fecha específica.
     *
     * @param fechaEnvio Fecha del envío.
     * @return Lista de envíos realizados en la fecha especificada.
     */
    List<EnvioLavanderia> findByFechaEnvio(Date fechaEnvio);

    /**
     * Encuentra envíos realizados dentro de un rango de fechas.
     *
     * @param fechaInicio Fecha inicial del rango.
     * @param fechaFin Fecha final del rango.
     * @return Lista de envíos realizados dentro del rango de fechas.
     */
    @Query("SELECT e FROM EnvioLavanderia e WHERE e.fechaEnvio BETWEEN :fechaInicio AND :fechaFin")
    List<EnvioLavanderia> findByFechaEnvioBetween(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);

    /**
     * Encuentra el envío más reciente realizado.
     *
     * @return El envío más reciente a lavandería.
     */
    @Query("SELECT e FROM EnvioLavanderia e ORDER BY e.fechaEnvio DESC LIMIT 1")
    EnvioLavanderia findUltimoEnvio();

    /**
     * Cuenta el número total de envíos realizados.
     *
     * @return Número total de envíos realizados.
     */
    @Query("SELECT COUNT(e) FROM EnvioLavanderia e")
    long countEnvios();

    /**
     * Elimina todos los envíos realizados en una fecha específica.
     *
     * @param fechaEnvio Fecha del envío.
     */
    void deleteByFechaEnvio(Date fechaEnvio);
}
