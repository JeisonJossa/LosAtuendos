package com.losatuendos.repository;

import com.losatuendos.entity.DetalleAlquiler;
import com.losatuendos.entity.ServicioAlquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleAlquilerRepository extends JpaRepository<DetalleAlquiler, Integer> {

    /**
     * Encuentra todos los detalles de alquiler asociados a un servicio de alquiler específico.
     *
     * @param servicioAlquiler El servicio de alquiler asociado.
     * @return Lista de DetalleAlquiler asociados.
     */
    List<DetalleAlquiler> findByIdServicio(ServicioAlquiler servicioAlquiler);

    /**
     * Encuentra todos los detalles de alquiler por referencia de una prenda.
     *
     * @param referenciaPrenda La referencia de la prenda.
     * @return Lista de DetalleAlquiler que contienen la referencia dada.
     */
    @Query("SELECT da FROM DetalleAlquiler da WHERE da.referenciaPrenda.referencia = :referenciaPrenda")
    List<DetalleAlquiler> findByReferenciaPrenda(String referenciaPrenda);

    /**
     * Consulta todos los detalles de alquiler para un cliente específico
     * a través de los servicios de alquiler asociados.
     *
     * @param idCliente El ID del cliente.
     * @return Lista de DetalleAlquiler asociados al cliente.
     */
    @Query("SELECT da FROM DetalleAlquiler da WHERE da.idServicio.idCliente = :idCliente")
    List<DetalleAlquiler> findByClienteId(Integer idCliente);

    /**
     * Consulta los detalles de alquiler asociados a una fecha específica.
     *
     * @param fechaAlquiler La fecha de alquiler.
     * @return Lista de DetalleAlquiler asociados a la fecha dada.
     */
    @Query("SELECT da FROM DetalleAlquiler da WHERE da.idServicio.fechaAlquiler = :fechaAlquiler")
    List<DetalleAlquiler> findByFechaAlquiler(java.util.Date fechaAlquiler);

    /**
     * Consulta todos los detalles de alquiler ordenados por la fecha de alquiler del servicio.
     *
     * @return Lista de DetalleAlquiler ordenados por fecha de alquiler.
     */
    @Query("SELECT da FROM DetalleAlquiler da ORDER BY da.idServicio.fechaAlquiler")
    List<DetalleAlquiler> findAllOrderedByFechaAlquiler();
}
