package com.losatuendos.controller;

import com.losatuendos.view.LavanderiaView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Controlador para gestionar la lógica de la vista de lavandería.
 */
@Component
public class LavanderiaController {

    private final AppController appController; // Controlador principal
    private LavanderiaView lavanderiaView; // Vista asociada

    /**
     * Constructor para inyectar el controlador principal.
     *
     * @param appController Controlador principal de la aplicación.
     */
    @Autowired
    public LavanderiaController(AppController appController) {
        this.appController = appController;
    }

    /**
     * Establece la vista asociada con este controlador.
     *
     * @param lavanderiaView Vista de lavandería.
     */
    public void setLavanderiaView(LavanderiaView lavanderiaView) {
        this.lavanderiaView = lavanderiaView;
    }

    // Métodos de manejo de eventos

    /**
     * Maneja el evento para buscar una prenda.
     */
    public void handleBuscarPrenda() {
        // Implementar lógica de búsqueda de prenda
    }

    /**
     * Maneja el evento para buscar un cliente.
     */
    public void handleBuscarCliente() {
        // Implementar lógica de búsqueda de cliente
    }

    /**
     * Maneja el evento para registrar una prenda en lavandería.
     */
    public void handleRegistrar() {
        // Implementar lógica de registro
    }

    /**
     * Maneja el evento para eliminar una prenda de la lavandería.
     */
    public void handleEliminar() {
        // Implementar lógica de eliminación
    }

    /**
     * Maneja el evento para enviar prendas a la lavandería.
     */
    public void handleEnviar() {
        // Implementar lógica de envío a lavandería
    }

    /**
     * Maneja el evento para limpiar el formulario de lavandería.
     */
    public void handleLimpiar() {
        lavanderiaView.getTxtReferenciaPrenda().clear();
        lavanderiaView.getTxtIdentificacionCliente().clear();
        lavanderiaView.getLblTipoPrenda().setText("Tipo de Prenda: ");
        lavanderiaView.getLblNombreCliente().setText("Nombre del Cliente: ");
        lavanderiaView.getCbxPrioridad().setValue("Baja");
        lavanderiaView.getTblPrendasLavanderia().getItems().clear();
    }

    /**
     * Maneja el evento para volver al menú principal.
     */
    public void handleVolverMenu() {
        appController.showMainMenu();
    }
}
