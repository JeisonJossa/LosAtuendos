package com.losatuendos.repository;

import com.losatuendos.entity.PrendaLavanderia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrendaLavanderiaRepository extends JpaRepository<PrendaLavanderia, Integer> {

    /**
     * Encuentra todas las prendas en lista para lavandería.
     *
     * @return Lista de prendas en lavandería.
     */
    @Query("SELECT p FROM PrendaLavanderia p")
    List<PrendaLavanderia> findAllPrendasParaLavanderia();

    /**
     * Encuentra todas las prendas en lavandería con prioridad alta.
     *
     * @param prioridad Valor booleano que indica si las prendas tienen prioridad.
     * @return Lista de prendas con prioridad.
     */
    List<PrendaLavanderia> findByPrioridad(Boolean prioridad);

    /**
     * Encuentra las prendas enviadas a lavandería por referencia específica.
     *
     * @param referenciaPrenda Referencia de la prenda.
     * @return Lista de prendas que coinciden con la referencia.
     */
    @Query("SELECT p FROM PrendaLavanderia p WHERE p.referenciaPrenda = :referenciaPrenda")
    List<PrendaLavanderia> findByReferenciaPrenda(@Param("referenciaPrenda") String referenciaPrenda);

    /**
     * Cuenta el número de prendas en lista para lavandería.
     *
     * @return Número total de prendas en lavandería.
     */
    @Query("SELECT COUNT(p) FROM PrendaLavanderia p")
    long countPrendasEnLavanderia();

    /**
     * Elimina las prendas en lista para lavandería con prioridad específica.
     *
     * @param prioridad Valor booleano que indica la prioridad.
     */
    void deleteByPrioridad(Boolean prioridad);

    /**
     * Elimina una prenda específica de la lista de lavandería por referencia.
     *
     * @param referenciaPrenda Referencia de la prenda.
     */
    void deleteByReferenciaPrenda_Referencia(String referenciaPrenda);
}
