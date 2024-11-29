package com.losatuendos.service;

import com.losatuendos.entity.Rol;
import com.losatuendos.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService {

    private final RolRepository rolRepository;

    @Autowired
    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    /**
     * Obtiene un rol por su nombre.
     *
     * @param nombre Nombre del rol.
     * @return El rol encontrado o null si no existe.
     */
    public Rol obtenerPorNombre(String nombre) {
        return rolRepository.findByNombre(nombre);
    }

    /**
     * Obtiene todos los roles registrados.
     *
     * @return Lista de roles.
     */
    public List<Rol> obtenerTodos() {
        return rolRepository.findAll();
    }

    /**
     * Guarda o actualiza un rol.
     *
     * @param rol Objeto rol a guardar o actualizar.
     * @return El rol guardado o actualizado.
     */
    public Rol guardarRol(Rol rol) {
        return rolRepository.save(rol);
    }

    /**
     * Elimina un rol por su ID.
     *
     * @param idRol ID del rol a eliminar.
     */
    public void eliminarRol(Integer idRol) {
        rolRepository.deleteById(idRol);
    }
}
