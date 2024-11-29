package com.losatuendos.service;

import com.losatuendos.entity.PrendaLavanderia;
import com.losatuendos.repository.PrendaLavanderiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrendaLavanderiaService {

    @Autowired
    private PrendaLavanderiaRepository prendaLavanderiaRepository;

    /**
     * Obtiene una prenda registrada para lavandería por su ID.
     *
     * @param idPrendaLavanderia ID de la prenda en lavandería.
     * @return Prenda si existe, de lo contrario null.
     */
    public PrendaLavanderia buscarPorId(Integer idPrendaLavanderia) {
        Optional<PrendaLavanderia> prenda = prendaLavanderiaRepository.findById(idPrendaLavanderia);
        return prenda.orElse(null);
    }

    /**
     * Obtiene todas las prendas registradas para lavandería.
     *
     * @return Lista de todas las prendas en lavandería.
     */
    public List<PrendaLavanderia> obtenerTodasPrendas() {
        return prendaLavanderiaRepository.findAllPrendasParaLavanderia();
    }

    /**
     * Obtiene las prendas registradas para lavandería con una prioridad específica.
     *
     * @param prioridad Valor booleano que indica si las prendas tienen prioridad.
     * @return Lista de prendas con prioridad especificada.
     */
    public List<PrendaLavanderia> obtenerPorPrioridad(Boolean prioridad) {
        return prendaLavanderiaRepository.findByPrioridad(prioridad);
    }

    /**
     * Obtiene las prendas registradas para lavandería por su referencia.
     *
     * @param referenciaPrenda Referencia de la prenda.
     * @return Lista de prendas con la referencia especificada.
     */
    public List<PrendaLavanderia> buscarPorReferencia(String referenciaPrenda) {
        return prendaLavanderiaRepository.findByReferenciaPrenda(referenciaPrenda);
    }

    /**
     * Registra o actualiza una prenda para lavandería.
     *
     * @param prendaLavanderia Prenda a registrar o actualizar.
     * @return Prenda registrada o actualizada.
     */
    public PrendaLavanderia guardarPrenda(PrendaLavanderia prendaLavanderia) {
        return prendaLavanderiaRepository.save(prendaLavanderia);
    }

    /**
     * Elimina una prenda registrada para lavandería por su referencia.
     *
     * @param referenciaPrenda Referencia de la prenda.
     * @return True si se eliminó correctamente, false si no se encontró.
     */
    public boolean eliminarPorReferencia(String referenciaPrenda) {
        List<PrendaLavanderia> prendas = prendaLavanderiaRepository.findByReferenciaPrenda(referenciaPrenda);
        if (!prendas.isEmpty()) {
            prendaLavanderiaRepository.deleteAll(prendas);
            return true;
        }
        return false;
    }

    /**
     * Elimina todas las prendas registradas para lavandería con una prioridad específica.
     *
     * @param prioridad Valor booleano que indica la prioridad.
     * @return True si se eliminaron registros, false si no se encontraron.
     */
    public boolean eliminarPorPrioridad(Boolean prioridad) {
        List<PrendaLavanderia> prendas = prendaLavanderiaRepository.findByPrioridad(prioridad);
        if (!prendas.isEmpty()) {
            prendaLavanderiaRepository.deleteAll(prendas);
            return true;
        }
        return false;
    }

    /**
     * Cuenta el número total de prendas registradas para lavandería.
     *
     * @return Número total de prendas en lavandería.
     */
    public long contarPrendas() {
        return prendaLavanderiaRepository.countPrendasEnLavanderia();
    }
}
