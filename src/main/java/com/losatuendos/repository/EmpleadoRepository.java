package com.losatuendos.repository;

import com.losatuendos.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para gestionar la entidad Empleado.
 */
@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

    /**
     * Encuentra un empleado por su número de identificación.
     *
     * @param numeroIdentificacion Número de identificación del empleado.
     * @return Empleado que coincide con el número de identificación.
     */
    Empleado findByNumeroIdentificacion(String numeroIdentificacion);

    /**
     * Encuentra un empleado por su usuario para autenticación.
     *
     * @param usuario Nombre de usuario del empleado.
     * @return Empleado que coincide con el usuario.
     */
    Empleado findByUsuario(String usuario);

    /**
     * Encuentra todos los empleados que tienen un rol específico.
     *
     * @param idRol ID del rol.
     * @return Lista de empleados que tienen el rol especificado.
     */
    @Query("SELECT e FROM Empleado e WHERE e.idRol.id = :idRol")
    List<Empleado> findByIdRol(@Param("idRol") Integer idRol);

    /**
     * Encuentra empleados cuyo nombre contiene una cadena específica.
     *
     * @param nombre Nombre parcial o completo del empleado.
     * @return Lista de empleados que contienen el nombre especificado.
     */
    @Query("SELECT e FROM Empleado e WHERE e.nombre LIKE %:nombre%")
    List<Empleado> findByNombreContaining(@Param("nombre") String nombre);

    /**
     * Cuenta la cantidad de empleados que tienen un rol específico.
     *
     * @param idRol ID del rol.
     * @return Cantidad de empleados con el rol especificado.
     */
    @Query("SELECT COUNT(e) FROM Empleado e WHERE e.idRol.id = :idRol")
    long countByIdRol(@Param("idRol") Integer idRol);

    /**
     * Elimina un empleado por su número de identificación.
     *
     * @param numeroIdentificacion Número de identificación del empleado.
     */
    void deleteByNumeroIdentificacion(String numeroIdentificacion);
}
