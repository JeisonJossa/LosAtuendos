package com.losatuendos.service;

import com.losatuendos.entity.EnvioLavanderia;
import com.losatuendos.repository.EnvioLavanderiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EnvioLavanderiaService {

    @Autowired
    private EnvioLavanderiaRepository envioLavanderiaRepository;

    /**
     * Busca un envío a lavandería por su ID.
     *
     * @param idEnvio ID del envío.
     * @return Envío si existe, de lo contrario null.
     */
    public EnvioLavanderia buscarPorId(Integer idEnvio) {
        Optional<EnvioLavanderia> envio = envioLavanderiaRepository.findById(idEnvio);
        return envio.orElse(null);
    }

    /**
     * Busca todos los envíos realizados en una fecha específica.
     *
     * @param fechaEnvio Fecha del envío.
     * @return Lista de envíos realizados en esa fecha.
     */
    public List<EnvioLavanderia> buscarPorFecha(Date fechaEnvio) {
        return envioLavanderiaRepository.findByFechaEnvio(fechaEnvio);
    }

    /**
     * Busca todos los envíos realizados dentro de un rango de fechas.
     *
     * @param fechaInicio Fecha inicial del rango.
     * @param fechaFin Fecha final del rango.
     * @return Lista de envíos realizados en el rango.
     */
    public List<EnvioLavanderia> buscarPorRangoDeFechas(Date fechaInicio, Date fechaFin) {
        return envioLavanderiaRepository.findByFechaEnvioBetween(fechaInicio, fechaFin);
    }

    /**
     * Busca el último envío registrado en el sistema.
     *
     * @return Último envío registrado.
     */
    public EnvioLavanderia obtenerUltimoEnvio() {
        return envioLavanderiaRepository.findUltimoEnvio();
    }

    /**
     * Registra un nuevo envío o actualiza uno existente.
     *
     * @param envioLavanderia Objeto de envío a registrar o actualizar.
     * @return Envío registrado o actualizado.
     */
    public EnvioLavanderia guardarEnvio(EnvioLavanderia envioLavanderia) {
        return envioLavanderiaRepository.save(envioLavanderia);
    }

    /**
     * Elimina un envío a lavandería por su ID.
     *
     * @param idEnvio ID del envío.
     * @return True si se eliminó correctamente, false si no se encontró.
     */
    public boolean eliminarPorId(Integer idEnvio) {
        if (envioLavanderiaRepository.existsById(idEnvio)) {
            envioLavanderiaRepository.deleteById(idEnvio);
            return true;
        }
        return false;
    }

    /**
     * Elimina todos los envíos realizados en una fecha específica.
     *
     * @param fechaEnvio Fecha de los envíos a eliminar.
     * @return True si se eliminaron registros, false si no se encontraron envíos en esa fecha.
     */
    public boolean eliminarPorFecha(Date fechaEnvio) {
        List<EnvioLavanderia> envios = envioLavanderiaRepository.findByFechaEnvio(fechaEnvio);
        if (!envios.isEmpty()) {
            envioLavanderiaRepository.deleteAll(envios);
            return true;
        }
        return false;
    }

    /**
     * Cuenta el número total de envíos registrados.
     *
     * @return Número total de envíos.
     */
    public long contarEnvios() {
        return envioLavanderiaRepository.count();
    }
}
