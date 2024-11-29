package com.losatuendos.service;

import com.losatuendos.entity.Empleado;
import com.losatuendos.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private SesionActual sesionActual;

    /**
     * Busca un empleado por su número de identificación.
     *
     * @param numeroIdentificacion Número de identificación del empleado.
     * @return Empleado si existe, de lo contrario null.
     */
    public Empleado buscarPorIdentificacion(String numeroIdentificacion) {
        validarEntrada(numeroIdentificacion, "El número de identificación", "^[a-zA-Z0-9]+$");
        try {
            return empleadoRepository.findByNumeroIdentificacion(numeroIdentificacion);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el empleado por identificación. Intente más tarde.", e);
        }
    }

    /**
     * Busca un empleado por su ID.
     *
     * @param idEmpleado ID del empleado.
     * @return Empleado si existe, de lo contrario null.
     */
    public Empleado buscarPorId(Integer idEmpleado) {
        if (idEmpleado == null) {
            throw new IllegalArgumentException("El ID del empleado no puede ser nulo.");
        }
        try {
            Optional<Empleado> empleado = empleadoRepository.findById(idEmpleado);
            return empleado.orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el empleado por ID. Intente más tarde.", e);
        }
    }

    /**
     * Busca un empleado por su usuario.
     *
     * @param usuario Usuario del empleado.
     * @return Empleado si existe, de lo contrario null.
     */
    public Empleado buscarPorUsuario(String usuario) {
        validarEntrada(usuario, "El usuario", "^[a-zA-Z0-9_.-]{3,20}$");
        try {
            return empleadoRepository.findByUsuario(usuario);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el empleado por usuario. Intente más tarde.", e);
        }
    }

    /**
     * Obtiene todos los empleados que tienen un rol específico.
     *
     * @param idRol ID del rol.
     * @return Lista de empleados con el rol especificado.
     */
    public List<Empleado> buscarPorRol(Integer idRol) {
        if (idRol == null) {
            throw new IllegalArgumentException("El ID del rol no puede ser nulo.");
        }
        try {
            return empleadoRepository.findByIdRol(idRol);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar empleados por rol. Intente más tarde.", e);
        }
    }

    /**
     * Registra o actualiza un empleado.
     *
     * @param empleado Objeto empleado a registrar o actualizar.
     * @return Empleado registrado o actualizado.
     */
    public Empleado guardarEmpleado(Empleado empleado) {
        if (empleado == null) {
            throw new IllegalArgumentException("El empleado no puede ser nulo.");
        }
        try {
            return empleadoRepository.save(empleado);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el empleado. Intente más tarde.", e);
        }
    }

    /**
     * Elimina un empleado por su número de identificación.
     *
     * @param numeroIdentificacion Número de identificación del empleado.
     * @return True si se eliminó correctamente, false si no se encontró el empleado.
     */
    public boolean eliminarPorIdentificacion(String numeroIdentificacion) {
        validarEntrada(numeroIdentificacion, "El número de identificación", "^[a-zA-Z0-9]+$");
        try {
            Empleado empleado = empleadoRepository.findByNumeroIdentificacion(numeroIdentificacion);
            if (empleado != null) {
                empleadoRepository.delete(empleado);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el empleado. Intente más tarde.", e);
        }
    }

    /**
     * Autentica a un empleado utilizando su usuario y contraseña.
     *
     * @param usuario Usuario del empleado.
     * @param password Contraseña del empleado.
     * @return Boolean para validar redireccion de pagina
     */
    public boolean autenticarEmpleadoYObtenerRol(String usuario, String password) {
        //validarEntrada(usuario, "El usuario", "^[a-zA-Z0-9_.-]{3,20}$");
        //validarEntrada(password, "La contraseña", "^[a-zA-Z0-9@#$%^&+=!]{8,30}$");
        System.out.println("validacion ok");
        try {
            Empleado empleado = empleadoRepository.findByUsuario(usuario);
            if (empleado != null && empleado.getPassword().equals(password)) {
                sesionActual.setEmpleadoActual(empleado);
                return true; // Autenticación exitosa
            }
            return false; // Credenciales incorrectas
        } catch (Exception e) {
            // Manejar errores técnicos de conexión o repositorio
            throw new RuntimeException("Error técnico al autenticar empleado. Intente más tarde.", e);
        }
    }


    /**
     * Cuenta la cantidad de empleados con un rol específico.
     *
     * @param idRol ID del rol.
     * @return Número de empleados con el rol especificado.
     */
    public long contarPorRol(Integer idRol) {
        if (idRol == null) {
            throw new IllegalArgumentException("El ID del rol no puede ser nulo.");
        }
        try {
            return empleadoRepository.countByIdRol(idRol);
        } catch (Exception e) {
            throw new RuntimeException("Error al contar empleados por rol. Intente más tarde.", e);
        }
    }

    /**
     * Obtiene todos los empleados registrados.
     *
     * @return Lista de todos los empleados.
     */
    public List<Empleado> obtenerTodos() {
        try {
            return empleadoRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la lista de empleados. Intente más tarde.", e);
        }
    }

    public void cerrarSesion() {
        sesionActual.cerrarSesion();
    }

    /**
     * Valida que una entrada no sea nula, vacía y que no contenga caracteres no válidos.
     *
     * @param entrada Entrada a validar.
     * @param mensajeError Mensaje de error si la validación falla.
     * @param regex Patrón permitido para la entrada.
     */
    private void validarEntrada(String entrada, String mensajeError, String regex) {
        if (entrada == null || entrada.trim().isEmpty()) {
            throw new IllegalArgumentException(mensajeError + " no puede estar vacío.");
        }
        if (!entrada.matches(regex)) {
            throw new IllegalArgumentException(mensajeError + " contiene caracteres no válidos.");
        }
    }


}
