package com.losatuendos.service;

import com.losatuendos.entity.Cliente;
import com.losatuendos.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    /**
     * Obtiene un cliente por su número de identificación.
     *
     * @param numeroIdentificacion Número de identificación del cliente.
     * @return Cliente si existe, de lo contrario null.
     */
    public Cliente buscarPorIdentificacion(String numeroIdentificacion) {
        return clienteRepository.findByNumeroIdentificacion(numeroIdentificacion);
    }

    /**
     * Obtiene un cliente por su ID.
     *
     * @param idCliente ID del cliente.
     * @return Cliente si existe, de lo contrario null.
     */
    public Cliente buscarPorId(Integer idCliente) {
        Optional<Cliente> cliente = clienteRepository.findById(idCliente);
        return cliente.orElse(null);
    }

    /**
     * Busca clientes cuyo nombre contiene una cadena específica.
     *
     * @param nombre Cadena parcial del nombre.
     * @return Lista de clientes que coinciden con el criterio.
     */
    public List<Cliente> buscarPorNombre(String nombre) {
        return clienteRepository.findByNombreContaining(nombre);
    }

    /**
     * Registra o actualiza un cliente.
     *
     * @param cliente Objeto cliente a registrar o actualizar.
     * @return Cliente registrado o actualizado.
     */
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    /**
     * Obtiene todos los clientes registrados.
     *
     * @return Lista de todos los clientes.
     */
    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    /**
     * Elimina un cliente por su número de identificación.
     *
     * @param numeroIdentificacion Número de identificación del cliente.
     * @return True si se eliminó correctamente, false si no se encontró el cliente.
     */
    public boolean eliminarPorIdentificacion(String numeroIdentificacion) {
        Cliente cliente = clienteRepository.findByNumeroIdentificacion(numeroIdentificacion);
        if (cliente != null) {
            clienteRepository.delete(cliente);
            return true;
        }
        return false;
    }

    /**
     * Cuenta el número total de clientes registrados.
     *
     * @return Número total de clientes.
     */
    public long contarClientes() {
        return clienteRepository.count();
    }
}
