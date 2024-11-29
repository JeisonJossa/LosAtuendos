package com.losatuendos.repository;

import com.losatuendos.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

    /**
     * Busca un rol por su nombre.
     *
     * @param nombre Nombre del rol.
     * @return El rol encontrado o null si no existe.
     */
    Rol findByNombre(String nombre);
}
