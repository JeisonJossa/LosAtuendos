package com.losatuendos.repository;

import com.losatuendos.entity.DetalleEnvioLavanderia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetalleEnvioLavanderiaRepository extends JpaRepository<DetalleEnvioLavanderia, Integer> {

    /**
     * Encuentra todos los detalles de un envío específico a lavandería.
     *
     * @param idEnvio ID del envío.
     * @return Lista de detalles del envío a lavandería.
     */
    List<DetalleEnvioLavanderia> findByIdEnvio_Id(Integer idEnvio);

    /**
     * Encuentra todos los envíos donde aparece una prenda específica.
     *
     * @param referenciaPrenda Referencia de la prenda.
     * @return Lista de detalles de envío en los que aparece la prenda.
     */
    List<DetalleEnvioLavanderia> findByReferenciaPrenda_Referencia(String referenciaPrenda);

    /**
     * Cuenta cuántas veces una prenda ha sido enviada a lavandería.
     *
     * @param referenciaPrenda Referencia de la prenda.
     * @return Número de veces que la prenda ha sido enviada.
     */
    @Query("SELECT COUNT(de) FROM DetalleEnvioLavanderia de WHERE de.referenciaPrenda = :referenciaPrenda")
    long countByReferenciaPrenda(@Param("referenciaPrenda") String referenciaPrenda);

    /**
     * Encuentra los detalles de envío asociados a una prenda específica en un envío específico.
     *
     * @param idEnvio ID del envío.
     * @param referenciaPrenda Referencia de la prenda.
     * @return Detalle de envío que coincide con los criterios.
     */
    @Query("SELECT de FROM DetalleEnvioLavanderia de WHERE de.idEnvio = :idEnvio AND de.referenciaPrenda = :referenciaPrenda")
    DetalleEnvioLavanderia findByIdEnvioAndReferenciaPrenda(@Param("idEnvio") Integer idEnvio, @Param("referenciaPrenda") String referenciaPrenda);

    /**
     * Elimina todos los detalles de un envío específico.
     *
     * @param idEnvio ID del envío.
     */
    void deleteByIdEnvio_Id(Integer idEnvio);

    /**
     * Lista todas las prendas enviadas en todos los envíos.
     *
     * @return Lista de todas las prendas asociadas a envíos.
     */
    @Query("SELECT DISTINCT de.referenciaPrenda FROM DetalleEnvioLavanderia de")
    List<String> findAllPrendasEnviadas();
}
