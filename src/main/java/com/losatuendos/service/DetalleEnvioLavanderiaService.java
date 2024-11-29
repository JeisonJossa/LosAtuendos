package com.losatuendos.service;

import com.losatuendos.entity.DetalleEnvioLavanderia;
import com.losatuendos.repository.DetalleEnvioLavanderiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleEnvioLavanderiaService {

    @Autowired
    private DetalleEnvioLavanderiaRepository detalleEnvioLavanderiaRepository;

    /**
     * Obtiene un detalle de envío por su ID.
     *
     * @param idDetalleEnvio ID del detalle de envío.
     * @return Detalle de envío si existe, de lo contrario null.
     */
    public DetalleEnvioLavanderia buscarPorId(Integer idDetalleEnvio) {
        Optional<DetalleEnvioLavanderia> detalle = detalleEnvioLavanderiaRepository.findById(idDetalleEnvio);
        return detalle.orElse(null);
    }

    /**
     * Obtiene todos los detalles de un envío específico.
     *
     * @param idEnvio ID del envío.
     * @return Lista de detalles asociados al envío.
     */
    public List<DetalleEnvioLavanderia> obtenerPorEnvio(Integer idEnvio) {
        return detalleEnvioLavanderiaRepository.findByIdEnvio_Id(idEnvio);
    }

    /**
     * Obtiene todos los detalles de envío donde aparece una prenda específica.
     *
     * @param referenciaPrenda Referencia de la prenda.
     * @return Lista de detalles asociados a la prenda.
     */
    public List<DetalleEnvioLavanderia> obtenerPorPrenda(String referenciaPrenda) {
        return detalleEnvioLavanderiaRepository.findByReferenciaPrenda_Referencia(referenciaPrenda);
    }

    /**
     * Registra o actualiza un detalle de envío.
     *
     * @param detalleEnvioLavanderia Detalle de envío a registrar o actualizar.
     * @return Detalle registrado o actualizado.
     */
    public DetalleEnvioLavanderia guardarDetalle(DetalleEnvioLavanderia detalleEnvioLavanderia) {
        return detalleEnvioLavanderiaRepository.save(detalleEnvioLavanderia);
    }

    /**
     * Elimina todos los detalles asociados a un envío específico.
     *
     * @param idEnvio ID del envío.
     * @return True si se eliminaron registros, false si no se encontró ninguno.
     */
    public boolean eliminarPorEnvio(Integer idEnvio) {
        List<DetalleEnvioLavanderia> detalles = detalleEnvioLavanderiaRepository.findByIdEnvio_Id(idEnvio);
        if (!detalles.isEmpty()) {
            detalleEnvioLavanderiaRepository.deleteAll(detalles);
            return true;
        }
        return false;
    }

    /**
     * Cuenta cuántas veces una prenda ha sido enviada a lavandería.
     *
     * @param referenciaPrenda Referencia de la prenda.
     * @return Número de veces que la prenda ha sido enviada.
     */
    public long contarEnviosPorPrenda(String referenciaPrenda) {
        return detalleEnvioLavanderiaRepository.countByReferenciaPrenda(referenciaPrenda);
    }

    /**
     * Lista todas las referencias de prendas enviadas a lavandería.
     *
     * @return Lista de referencias de prendas.
     */
    public List<String> obtenerPrendasEnviadas() {
        return detalleEnvioLavanderiaRepository.findAllPrendasEnviadas();
    }
}
