package com.losatuendos.controller;

import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.losatuendos.view.*;

@Component
public class AppController {

    private Stage stage;

    @Autowired
    private MainMenuController mainMenuController;

    @Autowired
    private ClienteController clienteController;

    @Autowired
    private EmpleadoController empleadoController;

    @Autowired
    private PrendaController prendaController;

    @Autowired
    private ServicioAlquilerController servicioAlquilerController;

    @Autowired
    private LavanderiaController lavanderiaController;

    private final LoginController loginController;

    // Constructor para evitar referencia circular
    @Autowired
    public AppController(LoginController loginController) {
        this.loginController = loginController;
        this.loginController.setAppController(this); // Configurar AppController en LoginController
    }

    /**
     * Establece el Stage principal de JavaFX.
     *
     * @param stage Stage principal.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Método para iniciar la aplicación.
     */
    public void start() {
        showLoginView(); // Comienza con la pantalla de inicio de sesión
    }

    // Métodos para mostrar cada vista
    public void showLoginView() {
        LoginView loginView = new LoginView(stage, loginController);
        loginController.setLoginView(loginView);
        loginView.show();
    }

    public void showMainMenu() {
        mainMenuController.setStage(stage); // Pasar el stage al controlador
        MainMenuView mainMenuView = new MainMenuView(stage, mainMenuController, this);
        mainMenuView.show();
    }


    public void showClienteView() {
        ClienteView clienteView = new ClienteView(stage, clienteController);
        clienteController.setClienteView(clienteView);
        clienteView.show();
    }

    public void showEmpleadoView() {
        EmpleadoView empleadoView = new EmpleadoView(stage, empleadoController);
        empleadoController.setEmpleadoView(empleadoView);
        empleadoView.show();
    }

    public void showPrendaView() {
        PrendaView prendaView = new PrendaView(stage, prendaController);
        prendaController.setPrendaView(prendaView);
        prendaView.show();
    }

    public void showServicioAlquilerView() {
        ServicioAlquilerView servicioAlquilerView = new ServicioAlquilerView(stage, servicioAlquilerController);
        servicioAlquilerController.setServicioAlquilerView(servicioAlquilerView);
        servicioAlquilerView.show();
    }

    public void showLavanderiaView() {
        LavanderiaView lavanderiaView = new LavanderiaView(stage, lavanderiaController);
        lavanderiaController.setLavanderiaView(lavanderiaView);
        lavanderiaView.show();
    }

    /**
     * Manejo del inicio de sesión desde LoginController.
     */
    public void handleSuccessfulLogin() {
        showMainMenu(); // Navegar al menú principal después de un login exitoso
    }
}
