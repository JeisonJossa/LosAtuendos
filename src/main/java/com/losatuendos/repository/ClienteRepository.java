package com.losatuendos.repository;

import com.losatuendos.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    /**
     * Encuentra un cliente por su número de identificación.
     *
     * @param numeroIdentificacion Número de identificación del cliente.
     * @return Cliente que coincide con el número de identificación.
     */
    Cliente findByNumeroIdentificacion(String numeroIdentificacion);

    /**
     * Encuentra todos los clientes cuyo nombre contiene una cadena específica.
     *
     * @param nombre Nombre parcial o completo del cliente.
     * @return Lista de clientes que contienen el nombre especificado.
     */
    @Query("SELECT c FROM Cliente c WHERE c.nombre LIKE %:nombre%")
    List<Cliente> findByNombreContaining(@Param("nombre") String nombre);

    /**
     * Encuentra todos los clientes registrados en el sistema.
     *
     * @return Lista de todos los clientes.
     */
    @Query("SELECT c FROM Cliente c")
    List<Cliente> findAllClientes();

    /**
     * Encuentra los clientes por su correo electrónico.
     *
     * @param correo Correo electrónico del cliente.
     * @return Lista de clientes con el correo especificado.
     */
    List<Cliente> findByCorreo(String correo);

    /**
     * Encuentra clientes por su dirección exacta.
     *
     * @param direccion Dirección completa del cliente.
     * @return Lista de clientes con la dirección exacta proporcionada.
     */
    List<Cliente> findByDireccion(String direccion);

    /**
     * Encuentra clientes cuyo teléfono contiene una cadena específica.
     *
     * @param telefono Número de teléfono parcial o completo.
     * @return Lista de clientes cuyo teléfono coincide parcialmente.
     */
    @Query("SELECT c FROM Cliente c WHERE c.telefono LIKE %:telefono%")
    List<Cliente> findByTelefonoContaining(@Param("telefono") String telefono);

    /**
     * Encuentra un cliente por su ID.
     *
     * @param idCliente ID del cliente.
     * @return Cliente con el ID especificado.
     */
    Cliente findById(int idCliente);

    /**
     * Elimina un cliente por su número de identificación.
     *
     * @param numeroIdentificacion Número de identificación del cliente.
     */
    void deleteByNumeroIdentificacion(String numeroIdentificacion);

    /**
     * Cuenta la cantidad de clientes registrados en el sistema.
     *
     * @return Cantidad total de clientes.
     */
    @Query("SELECT COUNT(c) FROM Cliente c")
    long countClientes();
}
