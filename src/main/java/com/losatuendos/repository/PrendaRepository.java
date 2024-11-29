package com.losatuendos.repository;

import com.losatuendos.entity.Prenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrendaRepository extends JpaRepository<Prenda, String> {

    /**
     * Encuentra todas las prendas disponibles para alquiler.
     *
     * @return Lista de prendas disponibles.
     */
    @Query("SELECT p FROM Prenda p WHERE p.estado = 'Disponible'")
    List<Prenda> findAllDisponibles();

    /**
     * Encuentra todas las prendas en la base de datos.
     *
     * @return Lista de todas las prendas.
     */
    @Query("SELECT p FROM Prenda p")
    List<Prenda> findAllPrendas();


    /**
     * Encuentra prendas por talla.
     *
     * @param talla Talla de las prendas.
     * @return Lista de prendas con la talla especificada.
     */
    List<Prenda> findByTalla(String talla);

    /**
     * Encuentra prendas por tipo.
     *
     * @param tipo Tipo de la prenda (Dama, Caballero, Disfraz).
     * @return Lista de prendas del tipo especificado.
     */
    List<Prenda> findByTipo(String tipo);

    /**
     * Encuentra prendas por tipo y talla.
     *
     * @param tipo Tipo de la prenda.
     * @param talla Talla de la prenda.
     * @return Lista de prendas que coincidan con el tipo y la talla.
     */
    List<Prenda> findByTipoAndTalla(String tipo, String talla);

    /**
     * Encuentra prendas por estado (Disponible, Alquilada, Lavandería).
     *
     * @param estado Estado de la prenda.
     * @return Lista de prendas con el estado especificado.
     */
    List<Prenda> findByEstado(String estado);

    /**
     * Encuentra prendas con un nombre específico (para disfraces).
     *
     * @param nombreDisfraz Nombre del disfraz.
     * @return Lista de prendas que coincidan con el nombre del disfraz.
     */
    @Query("SELECT p FROM Prenda p WHERE p.nombreDisfraz = :nombreDisfraz")
    List<Prenda> findByNombreDisfraz(@Param("nombreDisfraz") String nombreDisfraz);

    /**
     * Cuenta la cantidad de prendas disponibles en el sistema.
     *
     * @return Número total de prendas disponibles.
     */
    @Query("SELECT COUNT(p) FROM Prenda p WHERE p.estado = 'Disponible'")
    long countDisponibles();

    /**
     * Cuenta las prendas por tipo.
     *
     * @param tipo Tipo de la prenda.
     * @return Número total de prendas del tipo especificado.
     */
    @Query("SELECT COUNT(p) FROM Prenda p WHERE p.tipo = :tipo")
    long countByTipo(@Param("tipo") String tipo);
}
