package com.losatuendos.service;

import com.losatuendos.entity.DetalleAlquiler;
import com.losatuendos.entity.Prenda;
import com.losatuendos.entity.ServicioAlquiler;
import com.losatuendos.repository.DetalleAlquilerRepository;
import com.losatuendos.repository.PrendaRepository;
import com.losatuendos.repository.ServicioAlquilerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DetalleAlquilerService {

    @Autowired
    private DetalleAlquilerRepository detalleAlquilerRepository;

    @Autowired
    private ServicioAlquilerRepository servicioAlquilerRepository;

    @Autowired
    private PrendaRepository prendaRepository;

    /**
     * Guarda un detalle de alquiler.
     *
     * @param detalleAlquiler Detalle de alquiler a guardar.
     * @return DetalleAlquiler guardado.
     */
    @Transactional
    public DetalleAlquiler saveDetalleAlquiler(DetalleAlquiler detalleAlquiler) {
        return detalleAlquilerRepository.save(detalleAlquiler);
    }

    /**
     * Busca los detalles de alquiler asociados a un servicio.
     *
     * @param idServicio ID del servicio de alquiler.
     * @return Lista de DetalleAlquiler asociados.
     */
    public List<DetalleAlquiler> findDetallesByServicio(Integer idServicio) {
        Optional<ServicioAlquiler> servicio = servicioAlquilerRepository.findById(idServicio);
        if (servicio.isPresent()) {
            return detalleAlquilerRepository.findByIdServicio(servicio.get());
        } else {
            throw new IllegalArgumentException("Servicio de alquiler no encontrado.");
        }
    }

    /**
     * Busca los detalles de alquiler por referencia de prenda.
     *
     * @param referenciaPrenda Referencia de la prenda.
     * @return Lista de DetalleAlquiler asociados.
     */
    public List<DetalleAlquiler> findDetallesByReferenciaPrenda(String referenciaPrenda) {
        return detalleAlquilerRepository.findByReferenciaPrenda(referenciaPrenda);
    }

    /**
     * Busca los detalles de alquiler asociados a un cliente.
     *
     * @param idCliente ID del cliente.
     * @return Lista de DetalleAlquiler asociados.
     */
    public List<DetalleAlquiler> findDetallesByCliente(Integer idCliente) {
        return detalleAlquilerRepository.findByClienteId(idCliente);
    }

    /**
     * Busca los detalles de alquiler por fecha de alquiler.
     *
     * @param fechaAlquiler Fecha de alquiler.
     * @return Lista de DetalleAlquiler asociados.
     */
    public List<DetalleAlquiler> findDetallesByFechaAlquiler(LocalDate fechaAlquiler) {
        return detalleAlquilerRepository.findByFechaAlquiler(java.sql.Date.valueOf(fechaAlquiler));
    }

    /**
     * Obtiene todos los detalles de alquiler ordenados por fecha de alquiler.
     *
     * @return Lista de DetalleAlquiler ordenados por fecha.
     */
    public List<DetalleAlquiler> findAllOrderedByFechaAlquiler() {
        return detalleAlquilerRepository.findAllOrderedByFechaAlquiler();
    }

    /**
     * Valida si una prenda está disponible para alquiler en una fecha específica.
     *
     * @param referenciaPrenda Referencia de la prenda.
     * @param fechaAlquiler    Fecha de alquiler.
     * @return True si está disponible, False si no.
     */
    public boolean isPrendaDisponible(String referenciaPrenda, LocalDate fechaAlquiler) {
        List<DetalleAlquiler> detalles = detalleAlquilerRepository.findByReferenciaPrenda(referenciaPrenda);
        for (DetalleAlquiler detalle : detalles) {
            // Comparar directamente las fechas, ya que fechaAlquiler y la propiedad son LocalDate
            if (detalle.getIdServicio().getFechaAlquiler().equals(fechaAlquiler)) {
                return false; // No está disponible
            }
        }
        return true; // Disponible
    }

    /**
     * Registra un nuevo detalle de alquiler, validando la disponibilidad de las prendas.
     *
     * @param idServicio       ID del servicio de alquiler.
     * @param referenciaPrenda Referencia de la prenda.
     * @return DetalleAlquiler registrado.
     */
    @Transactional
    public DetalleAlquiler registrarDetalleAlquiler(Integer idServicio, String referenciaPrenda) {
        Optional<ServicioAlquiler> servicio = servicioAlquilerRepository.findById(idServicio);
        Optional<Prenda> prenda = prendaRepository.findById(referenciaPrenda);

        if (servicio.isEmpty()) {
            throw new IllegalArgumentException("Servicio de alquiler no encontrado.");
        }

        if (prenda.isEmpty()) {
            throw new IllegalArgumentException("Prenda no encontrada.");
        }

        // Validar disponibilidad de la prenda.
        if (!isPrendaDisponible(referenciaPrenda, servicio.get().getFechaAlquiler())) {
            throw new IllegalArgumentException("La prenda no está disponible para la fecha de alquiler.");
        }

        // Crear y guardar el detalle de alquiler.
        DetalleAlquiler detalleAlquiler = new DetalleAlquiler();
        detalleAlquiler.setIdServicio(servicio.get());
        detalleAlquiler.setReferenciaPrenda(prenda.get());

        return detalleAlquilerRepository.save(detalleAlquiler);
    }
}
