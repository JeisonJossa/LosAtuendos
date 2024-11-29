package com.losatuendos.view;

import com.losatuendos.controller.AppController;
import com.losatuendos.controller.MainMenuController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenuView extends BaseView {

    private Button btnGestionClientes;
    private Button btnGestionEmpleados;
    private Button btnGestionPrendas;
    private Button btnServiciosAlquiler;
    private Button btnGestionLavanderia;
    private Button btnSalir;

    // Controlador asociado
    private MainMenuController mainMenuController;
    private AppController appController;

    // Constructor que acepta un Stage, el controlador del menú principal y el controlador principal
    public MainMenuView(Stage stage, MainMenuController mainMenuController, AppController appController) {
        super(stage);
        this.mainMenuController = mainMenuController; // Inyecta el controlador
        this.appController = appController; // Inyecta el controlador principal
        setTitle("Menú Principal - Los Atuendos");
        initializeComponents();
        setupEventHandlers(); // Configura los eventos con el controlador
    }

    @Override
    protected void initializeComponents() {
        // Título del menú
        Text lblTitulo = new Text("LOS ATUENDOS");
        lblTitulo.setFont(Font.font(36)); // Tamaño del título
        lblTitulo.setStyle("-fx-fill: " + StyleManager.SECONDARY_COLOR + ";");

        // Botones para las opciones del menú
        btnGestionClientes = new Button("Gestión de Clientes");
        StyleManager.applyPrimaryButtonStyle(btnGestionClientes);

        btnGestionEmpleados = new Button("Gestión de Empleados");
        StyleManager.applyPrimaryButtonStyle(btnGestionEmpleados);

        btnGestionPrendas = new Button("Gestión de Prendas");
        StyleManager.applyPrimaryButtonStyle(btnGestionPrendas);

        btnServiciosAlquiler = new Button("Servicios de Alquiler");
        StyleManager.applyPrimaryButtonStyle(btnServiciosAlquiler);

        btnGestionLavanderia = new Button("Gestión de Lavandería");
        StyleManager.applyPrimaryButtonStyle(btnGestionLavanderia);

        btnSalir = new Button("Salir");
        StyleManager.applyPrimaryButtonStyle(btnSalir);

        // Contenedor vertical para los botones y título
        VBox vbox = new VBox(20, lblTitulo, btnGestionClientes, btnGestionEmpleados, btnGestionPrendas,
                btnServiciosAlquiler, btnGestionLavanderia, btnSalir);
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color: " + StyleManager.PRIMARY_COLOR + ";" +
                "-fx-padding: 50; -fx-spacing: 15; -fx-alignment: center;");

        root.setCenter(vbox);
    }

    @Override
    protected void setupEventHandlers() {
        // Configura los eventos delegando la lógica al controlador
        btnGestionClientes.setOnAction(event -> mainMenuController.handleGestionClientes(appController));
        btnGestionEmpleados.setOnAction(event -> mainMenuController.handleGestionEmpleados(appController));
        btnGestionPrendas.setOnAction(event -> mainMenuController.handleGestionPrendas(appController));
        btnServiciosAlquiler.setOnAction(event -> mainMenuController.handleServiciosAlquiler(appController));
        btnGestionLavanderia.setOnAction(event -> mainMenuController.handleGestionLavanderia(appController));
        btnSalir.setOnAction(event -> mainMenuController.handleSalir(appController));
    }

    // Getters para los botones si se necesitan en pruebas o ajustes futuros
    public Button getBtnGestionClientes() {
        return btnGestionClientes;
    }

    public Button getBtnGestionEmpleados() {
        return btnGestionEmpleados;
    }

    public Button getBtnGestionPrendas() {
        return btnGestionPrendas;
    }

    public Button getBtnServiciosAlquiler() {
        return btnServiciosAlquiler;
    }

    public Button getBtnGestionLavanderia() {
        return btnGestionLavanderia;
    }

    public Button getBtnSalir() {
        return btnSalir;
    }
}
