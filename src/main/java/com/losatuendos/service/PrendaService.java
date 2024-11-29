package com.losatuendos.service;

import com.losatuendos.entity.Prenda;
import com.losatuendos.repository.PrendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrendaService {

    @Autowired
    private PrendaRepository prendaRepository;

    /**
     * Busca una prenda por su referencia.
     *
     * @param referencia Referencia única de la prenda.
     * @return Prenda si existe, de lo contrario null.
     */
    public Prenda buscarPorReferencia(String referencia) {
        Optional<Prenda> prenda = prendaRepository.findById(referencia);
        return prenda.orElse(null);
    }

    /**
     * Busca todas las prendas disponibles para alquiler.
     *
     * @return Lista de prendas disponibles.
     */
    public List<Prenda> obtenerPrendasDisponibles() {
        return prendaRepository.findAllDisponibles();
    }

    /**
     * Obtiene todas las prendas en la base de datos.
     *
     * @return Lista de todas las prendas.
     */
    public List<Prenda> obtenerTodas() {
        return prendaRepository.findAllPrendas();
    }


    /**
     * Busca prendas por su talla.
     *
     * @param talla Talla de las prendas.
     * @return Lista de prendas con la talla especificada.
     */
    public List<Prenda> obtenerPorTalla(String talla) {
        return prendaRepository.findByTalla(talla);
    }

    /**
     * Busca prendas por su tipo.
     *
     * @param tipo Tipo de prenda (Dama, Caballero, Disfraz).
     * @return Lista de prendas del tipo especificado.
     */
    public List<Prenda> obtenerPorTipo(String tipo) {
        return prendaRepository.findByTipo(tipo);
    }

    /**
     * Busca prendas por tipo y talla.
     *
     * @param tipo Tipo de prenda.
     * @param talla Talla de la prenda.
     * @return Lista de prendas que coincidan con el tipo y la talla.
     */
    public List<Prenda> obtenerPorTipoYTalla(String tipo, String talla) {
        return prendaRepository.findByTipoAndTalla(tipo, talla);
    }

    /**
     * Busca prendas por su estado.
     *
     * @param estado Estado de la prenda (Disponible, Alquilada, Lavandería).
     * @return Lista de prendas con el estado especificado.
     */
    public List<Prenda> obtenerPorEstado(String estado) {
        return prendaRepository.findByEstado(estado);
    }

    /**
     * Busca disfraces por su nombre.
     *
     * @param nombreDisfraz Nombre del disfraz.
     * @return Lista de prendas que coincidan con el nombre del disfraz.
     */
    public List<Prenda> buscarDisfrazPorNombre(String nombreDisfraz) {
        return prendaRepository.findByNombreDisfraz(nombreDisfraz);
    }

    /**
     * Registra o actualiza una prenda.
     *
     * @param prenda Objeto prenda a registrar o actualizar.
     * @return Prenda registrada o actualizada.
     */
    public Prenda guardarPrenda(Prenda prenda) {
        return prendaRepository.save(prenda);
    }

    /**
     * Elimina una prenda por su referencia.
     *
     * @param referencia Referencia única de la prenda.
     * @return True si se eliminó correctamente, false si no se encontró la prenda.
     */
    public boolean eliminarPorReferencia(String referencia) {
        if (prendaRepository.existsById(referencia)) {
            prendaRepository.deleteById(referencia);
            return true;
        }
        return false;
    }

    /**
     * Cuenta el número total de prendas disponibles.
     *
     * @return Número total de prendas disponibles.
     */
    public long contarPrendasDisponibles() {
        return prendaRepository.countDisponibles();
    }

    /**
     * Cuenta el número total de prendas por tipo.
     *
     * @param tipo Tipo de prenda.
     * @return Número total de prendas del tipo especificado.
     */
    public long contarPrendasPorTipo(String tipo) {
        return prendaRepository.countByTipo(tipo);
    }
}
