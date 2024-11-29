package com.losatuendos.view;

import com.losatuendos.controller.ServicioAlquilerController;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ServicioAlquilerView extends BaseView {

    private TableView<Object> tblPrendas;
    private TextField txtReferenciaPrenda, txtIdentificacionCliente;
    private Label lblDetallesPrenda, lblNombreCliente, lblEmpleadoLogueado;
    private Button btnBuscarPrenda, btnBuscarCliente, btnAgregarPrenda, btnGuardar, btnLimpiar, btnVolverMenu;

    // Controlador asociado
    private ServicioAlquilerController servicioAlquilerController;

    // Constructor que acepta Stage y Controlador
    public ServicioAlquilerView(Stage stage, ServicioAlquilerController servicioAlquilerController) {
        super(stage);
        this.servicioAlquilerController = servicioAlquilerController; // Inyección del controlador
        setTitle("Gestión de Servicios de Alquiler - Los Atuendos");
        initializeComponents();
        setupEventHandlers();
    }

    @Override
    protected void initializeComponents() {
        // Título de la vista
        Label lblTitulo = new Label("Gestión de Servicios de Alquiler");
        lblTitulo.setStyle("-fx-font-size: 24px; -fx-text-fill: " + StyleManager.SECONDARY_COLOR + ";");

        // Campo para buscar prenda
        txtReferenciaPrenda = createTextField("Referencia de la Prenda");
        btnBuscarPrenda = createButton("Buscar Prenda");
        lblDetallesPrenda = createLabel("Tipo de Prenda: ");

        // Botón para agregar prenda
        btnAgregarPrenda = createButton("Agregar Prenda");

        // Campo para buscar cliente
        txtIdentificacionCliente = createTextField("Número de Identificación del Cliente");
        btnBuscarCliente = createButton("Buscar Cliente");
        lblNombreCliente = createLabel("Nombre del Cliente: ");

        // Label para mostrar el empleado logueado
        lblEmpleadoLogueado = createLabel("Empleado Logueado: [Nombre del Empleado]");

        // Botones principales
        btnGuardar = createButton("Guardar Servicio");
        btnLimpiar = createButton("Limpiar");
        btnVolverMenu = createButton("Volver al Menú Principal");

        // Tabla para mostrar prendas asociadas al servicio
        tblPrendas = new TableView<>();
        tblPrendas.setPrefWidth(1500);
        tblPrendas.getColumns().addAll(
                createColumn("Referencia", 100),
                createColumn("Tipo", 100),
                createColumn("Color", 100),
                createColumn("Marca", 100),
                createColumn("Talla", 80),
                createColumn("Valor Alquiler", 100),
                createColumn("Pedrería", 80),
                createColumn("Largo/Corto", 80),
                createColumn("Cantidad Piezas", 100),
                createColumn("Tipo Traje", 100),
                createColumn("Accesorios", 100),
                createColumn("Nombre Disfraz", 120),
                createColumn("Estado", 80)
        );

        // Layouts para cliente y prenda
        HBox hboxPrenda = new HBox(10, txtReferenciaPrenda, btnBuscarPrenda, lblDetallesPrenda);
        hboxPrenda.setAlignment(Pos.CENTER);

        VBox vboxAgregarPrenda = new VBox(10, hboxPrenda, btnAgregarPrenda);
        vboxAgregarPrenda.setAlignment(Pos.CENTER);

        HBox hboxCliente = new HBox(10, txtIdentificacionCliente, btnBuscarCliente, lblNombreCliente);
        hboxCliente.setAlignment(Pos.CENTER);

        // Layout para botones principales
        HBox hboxBotones = new HBox(15, btnGuardar, btnLimpiar);
        hboxBotones.setAlignment(Pos.CENTER);

        // Layout principal
        VBox vboxMain = new VBox(20, lblTitulo, lblEmpleadoLogueado, tblPrendas, vboxAgregarPrenda, hboxCliente, hboxBotones, btnVolverMenu);
        vboxMain.setAlignment(Pos.CENTER);
        vboxMain.setStyle("-fx-padding: 20; -fx-background-color: " + StyleManager.PRIMARY_COLOR + ";");
        root.setCenter(vboxMain);
    }

    @Override
    protected void setupEventHandlers() {
        // Delegar los eventos al controlador
        btnBuscarPrenda.setOnAction(event -> servicioAlquilerController.handleBuscarPrenda());
        btnAgregarPrenda.setOnAction(event -> servicioAlquilerController.handleAgregarPrenda());
        btnBuscarCliente.setOnAction(event -> servicioAlquilerController.handleBuscarCliente());
        btnGuardar.setOnAction(event -> servicioAlquilerController.handleGuardarServicio());
        btnLimpiar.setOnAction(event -> servicioAlquilerController.handleLimpiar());
        btnVolverMenu.setOnAction(event -> servicioAlquilerController.handleVolverMenu());
    }

    // Métodos auxiliares para crear componentes
    private TextField createTextField(String promptText) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        StyleManager.applyDefaultStyle(textField);
        return textField;
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-text-fill: " + StyleManager.SECONDARY_COLOR + ";");
        return label;
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        StyleManager.applyPrimaryButtonStyle(button);
        return button;
    }

    private TableColumn<Object, String> createColumn(String title, int width) {
        TableColumn<Object, String> column = new TableColumn<>(title);
        column.setMinWidth(width);
        return column;
    }

    // Getters para los controladores
    public TableView<Object> getTblPrendas() {
        return tblPrendas;
    }

    public TextField getTxtReferenciaPrenda() {
        return txtReferenciaPrenda;
    }

    public Button getBtnBuscarPrenda() {
        return btnBuscarPrenda;
    }

    public Label getLblDetallesPrenda() {
        return lblDetallesPrenda;
    }

    public TextField getTxtIdentificacionCliente() {
        return txtIdentificacionCliente;
    }

    public Button getBtnBuscarCliente() {
        return btnBuscarCliente;
    }

    public Label getLblNombreCliente() {
        return lblNombreCliente;
    }

    public Label getLblEmpleadoLogueado() {
        return lblEmpleadoLogueado;
    }

    public Button getBtnAgregarPrenda() {
        return btnAgregarPrenda;
    }

    public Button getBtnGuardar() {
        return btnGuardar;
    }

    public Button getBtnLimpiar() {
        return btnLimpiar;
    }

    public Button getBtnVolverMenu() {
        return btnVolverMenu;
    }
}
