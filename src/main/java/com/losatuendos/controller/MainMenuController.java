package com.losatuendos.controller;

import org.springframework.stereotype.Component;
import javafx.stage.Stage;

/**
 * Controlador para manejar las acciones en el menú principal de la aplicación.
 */
@Component
public class MainMenuController {

    private Stage stage;

    /**
     * Establece el stage principal donde se mostrarán las vistas.
     *
     * @param stage Stage principal de la aplicación.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Maneja el evento para gestionar clientes.
     * Navega a la vista de gestión de clientes.
     *
     * @param appController Controlador principal de la aplicación.
     */
    public void handleGestionClientes(AppController appController) {
        appController.showClienteView();
    }

    /**
     * Maneja el evento para gestionar empleados.
     * Navega a la vista de gestión de empleados.
     *
     * @param appController Controlador principal de la aplicación.
     */
    public void handleGestionEmpleados(AppController appController) {
        appController.showEmpleadoView();
    }

    /**
     * Maneja el evento para gestionar prendas.
     * Navega a la vista de gestión de prendas.
     *
     * @param appController Controlador principal de la aplicación.
     */
    public void handleGestionPrendas(AppController appController) {
        appController.showPrendaView();
    }

    /**
     * Maneja el evento para gestionar servicios de alquiler.
     * Navega a la vista de servicios de alquiler.
     *
     * @param appController Controlador principal de la aplicación.
     */
    public void handleServiciosAlquiler(AppController appController) {
        appController.showServicioAlquilerView();
    }

    /**
     * Maneja el evento para gestionar lavandería.
     * Navega a la vista de gestión de lavandería.
     *
     * @param appController Controlador principal de la aplicación.
     */
    public void handleGestionLavanderia(AppController appController) {
        appController.showLavanderiaView();
    }

    /**
     * Maneja el evento para salir de la sesión.
     * Regresa a la vista de inicio de sesión.
     *
     * @param appController Controlador principal de la aplicación.
     */
    public void handleSalir(AppController appController) {
        appController.showLoginView();
    }
}
