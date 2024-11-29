package com.losatuendos.controller;

import com.losatuendos.entity.Cliente;
import com.losatuendos.entity.DetalleAlquiler;
import com.losatuendos.entity.Prenda;
import com.losatuendos.entity.ServicioAlquiler;
import com.losatuendos.service.ClienteService;
import com.losatuendos.service.DetalleAlquilerService;
import com.losatuendos.service.PrendaService;
import com.losatuendos.service.ServicioAlquilerService;
import com.losatuendos.view.ServicioAlquilerView;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador para gestionar la lógica de la vista de servicios de alquiler.
 */
@Component
public class ServicioAlquilerController {

    private final ClienteService clienteService;
    private final PrendaService prendaService;
    private final ServicioAlquilerService servicioAlquilerService;
    private final DetalleAlquilerService detalleAlquilerService;
    private ServicioAlquilerView servicioAlquilerView;

    private Cliente clienteSeleccionado;
    private List<Prenda> prendasSeleccionadas = new ArrayList<>();

    @Autowired
    public ServicioAlquilerController(ClienteService clienteService, PrendaService prendaService,
                                      ServicioAlquilerService servicioAlquilerService, DetalleAlquilerService detalleAlquilerService) {
        this.clienteService = clienteService;
        this.prendaService = prendaService;
        this.servicioAlquilerService = servicioAlquilerService;
        this.detalleAlquilerService = detalleAlquilerService;
    }

    public void setServicioAlquilerView(ServicioAlquilerView servicioAlquilerView) {
        this.servicioAlquilerView = servicioAlquilerView;
    }

    /**
     * Maneja la búsqueda de un cliente por su número de identificación.
     */
    public void handleBuscarCliente() {
        String identificacion = servicioAlquilerView.getTxtIdentificacionCliente().getText();
        if (identificacion.isEmpty()) {
            mostrarAlerta("Error", "Debe ingresar un número de identificación.", Alert.AlertType.ERROR);
            return;
        }

        Cliente cliente = clienteService.buscarPorIdentificacion(identificacion);
        if (cliente != null) {
            clienteSeleccionado = cliente;
            servicioAlquilerView.getLblNombreCliente().setText("Nombre del Cliente: " + cliente.getNombre());
        } else {
            mostrarAlerta("Error", "Cliente no encontrado.", Alert.AlertType.ERROR);
        }
    }

    /**
     * Maneja la búsqueda de una prenda por su referencia.
     */
    public void handleBuscarPrenda() {
        String referencia = servicioAlquilerView.getTxtReferenciaPrenda().getText();
        if (referencia.isEmpty()) {
            mostrarAlerta("Error", "Debe ingresar una referencia de prenda.", Alert.AlertType.ERROR);
            return;
        }

        Prenda prenda = prendaService.buscarPorReferencia(referencia);
        if (prenda != null) {
            if (detalleAlquilerService.isPrendaDisponible(referencia, LocalDate.now())) {
                servicioAlquilerView.getLblDetallesPrenda().setText("Tipo de Prenda: " + prenda.getTipo());
            } else {
                mostrarAlerta("Error", "La prenda no está disponible.", Alert.AlertType.ERROR);
            }
        } else {
            mostrarAlerta("Error", "Prenda no encontrada.", Alert.AlertType.ERROR);
        }
    }

    /**
     * Maneja la adición de una prenda al listado de alquiler.
     */
    public void handleAgregarPrenda() {
        String referencia = servicioAlquilerView.getTxtReferenciaPrenda().getText();
        if (referencia.isEmpty()) {
            mostrarAlerta("Error", "Debe buscar una prenda antes de agregarla.", Alert.AlertType.ERROR);
            return;
        }

        Prenda prenda = prendaService.buscarPorReferencia(referencia);
        if (prenda != null && detalleAlquilerService.isPrendaDisponible(referencia, LocalDate.now())) {
            prendasSeleccionadas.add(prenda);
            servicioAlquilerView.getTblPrendas().getItems().add(prenda);
            servicioAlquilerView.getTxtReferenciaPrenda().clear();
            servicioAlquilerView.getLblDetallesPrenda().setText("Tipo de Prenda: ");
        } else {
            mostrarAlerta("Error", "La prenda no está disponible o no es válida.", Alert.AlertType.ERROR);
        }
    }

    /**
     * Maneja el guardado de un servicio de alquiler.
     */
    public void handleGuardarServicio() {
        if (clienteSeleccionado == null) {
            mostrarAlerta("Error", "Debe seleccionar un cliente.", Alert.AlertType.ERROR);
            return;
        }

        if (prendasSeleccionadas.isEmpty()) {
            mostrarAlerta("Error", "Debe agregar al menos una prenda al servicio.", Alert.AlertType.ERROR);
            return;
        }

        ServicioAlquiler servicioAlquiler = new ServicioAlquiler();
        servicioAlquiler.setFechaSolicitud(LocalDate.now());
        servicioAlquiler.setFechaAlquiler(LocalDate.now().plusDays(1)); // Simulamos fecha de alquiler al día siguiente
        servicioAlquiler.setIdCliente(clienteSeleccionado);

        // Aquí debes obtener el empleado logueado de tu aplicación
        // servicioAlquiler.setIdEmpleado(empleadoLogueado);

        ServicioAlquiler servicioGuardado = servicioAlquilerService.guardarServicio(servicioAlquiler);

        for (Prenda prenda : prendasSeleccionadas) {
            DetalleAlquiler detalle = new DetalleAlquiler();
            detalle.setIdServicio(servicioGuardado);
            detalle.setReferenciaPrenda(prenda);
            detalleAlquilerService.saveDetalleAlquiler(detalle);
        }

        mostrarAlerta("Éxito", "Servicio de alquiler registrado exitosamente.", Alert.AlertType.INFORMATION);
        limpiarFormulario();
    }

    /**
     * Limpia los datos del formulario.
     */
    public void handleLimpiar() {
        limpiarFormulario();
    }

    private void limpiarFormulario() {
        clienteSeleccionado = null;
        prendasSeleccionadas.clear();
        servicioAlquilerView.getTxtReferenciaPrenda().clear();
        servicioAlquilerView.getTxtIdentificacionCliente().clear();
        servicioAlquilerView.getLblDetallesPrenda().setText("Tipo de Prenda: ");
        servicioAlquilerView.getLblNombreCliente().setText("Nombre del Cliente: ");
        servicioAlquilerView.getTblPrendas().getItems().clear();
    }

    /**
     * Maneja el evento para volver al menú principal.
     */
    public void handleVolverMenu() {
        // Aquí se llama al controlador principal para volver al menú
        // appController.showMainMenu();
    }

    /**
     * Muestra un mensaje de alerta en pantalla.
     *
     * @param titulo    Título del mensaje.
     * @param contenido Contenido del mensaje.
     * @param tipo      Tipo de alerta.
     */
    private void mostrarAlerta(String titulo, String contenido, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
