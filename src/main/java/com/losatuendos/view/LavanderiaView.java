package com.losatuendos.view;

import com.losatuendos.controller.LavanderiaController; // Importar el controlador asociado
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LavanderiaView extends BaseView {

    private TableView<Object> tblPrendasLavanderia;
    private TextField txtReferenciaPrenda, txtIdentificacionCliente;
    private Label lblTipoPrenda, lblNombreCliente, lblPrioridad;
    private ComboBox<String> cbxPrioridad;
    private Button btnBuscarPrenda, btnBuscarCliente, btnRegistrar, btnEliminar, btnEnviar, btnLimpiar, btnVolverMenu;

    // Controlador asociado
    private LavanderiaController lavanderiaController;

    // Constructor que acepta Stage y controlador
    public LavanderiaView(Stage stage, LavanderiaController lavanderiaController) {
        super(stage);
        this.lavanderiaController = lavanderiaController; // Inyección del controlador
        setTitle("Gestión de Lavandería - Los Atuendos");
        initializeComponents();
        setupEventHandlers();
    }

    @Override
    protected void initializeComponents() {
        // Título de la vista
        Label lblTitulo = new Label("Gestión de Lavandería");
        lblTitulo.setStyle("-fx-font-size: 24px; -fx-text-fill: " + StyleManager.SECONDARY_COLOR + ";");

        // Campo de texto para referencia de prenda
        txtReferenciaPrenda = createTextField("Referencia de la Prenda");
        btnBuscarPrenda = createButton("Buscar Prenda");
        lblTipoPrenda = createLabel("Tipo de Prenda: ");

        // Campo de texto para cliente
        txtIdentificacionCliente = createTextField("Número de Identificación del Cliente");
        btnBuscarCliente = createButton("Buscar Cliente");
        lblNombreCliente = createLabel("Nombre del Cliente: ");

        // ComboBox para prioridad
        lblPrioridad = createLabel("Prioridad:");
        cbxPrioridad = new ComboBox<>(FXCollections.observableArrayList("Alta", "Baja"));
        cbxPrioridad.setPromptText("Seleccione Prioridad");
        cbxPrioridad.setValue("Baja");
        StyleManager.applyDefaultStyle(cbxPrioridad);

        // Botones principales
        btnRegistrar = createButton("Registrar en Lavandería");
        btnEliminar = createButton("Eliminar");
        btnEnviar = createButton("Enviar a Lavandería");
        btnLimpiar = createButton("Limpiar");
        btnVolverMenu = createButton("Volver al Menú Principal");

        // Tabla para listar prendas en lavandería
        tblPrendasLavanderia = new TableView<>();
        tblPrendasLavanderia.setPrefWidth(1500);
        tblPrendasLavanderia.getColumns().addAll(
                createColumn("Referencia", 100),
                createColumn("Tipo", 100),
                createColumn("Color", 100),
                createColumn("Marca", 100),
                createColumn("Talla", 80),
                createColumn("Pedrería", 80),
                createColumn("Largo/Corto", 80),
                createColumn("Cantidad Piezas", 100),
                createColumn("Tipo Traje", 100),
                createColumn("Accesorios", 100),
                createColumn("Nombre Disfraz", 120),
                createColumn("Estado", 80)
        );

        // Layouts
        HBox hboxPrenda = new HBox(10, txtReferenciaPrenda, btnBuscarPrenda, lblTipoPrenda);
        hboxPrenda.setAlignment(Pos.CENTER);

        HBox hboxCliente = new HBox(10, txtIdentificacionCliente, btnBuscarCliente, lblNombreCliente);
        hboxCliente.setAlignment(Pos.CENTER);

        HBox hboxPrioridad = new HBox(10, lblPrioridad, cbxPrioridad);
        hboxPrioridad.setAlignment(Pos.CENTER);

        HBox hboxBotones = new HBox(15, btnRegistrar, btnEliminar, btnEnviar, btnLimpiar);
        hboxBotones.setAlignment(Pos.CENTER);

        VBox vboxMain = new VBox(20, lblTitulo, tblPrendasLavanderia, hboxPrenda, hboxCliente, hboxPrioridad, hboxBotones, btnVolverMenu);
        vboxMain.setAlignment(Pos.CENTER);
        vboxMain.setStyle("-fx-padding: 20; -fx-background-color: " + StyleManager.PRIMARY_COLOR + ";");
        root.setCenter(vboxMain);
    }

    @Override
    protected void setupEventHandlers() {
        // Delegar eventos al controlador
        btnBuscarPrenda.setOnAction(event -> lavanderiaController.handleBuscarPrenda());
        btnBuscarCliente.setOnAction(event -> lavanderiaController.handleBuscarCliente());
        btnRegistrar.setOnAction(event -> lavanderiaController.handleRegistrar());
        btnEliminar.setOnAction(event -> lavanderiaController.handleEliminar());
        btnEnviar.setOnAction(event -> lavanderiaController.handleEnviar());
        btnLimpiar.setOnAction(event -> lavanderiaController.handleLimpiar());
        btnVolverMenu.setOnAction(event -> lavanderiaController.handleVolverMenu());
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
    public TableView<Object> getTblPrendasLavanderia() {
        return tblPrendasLavanderia;
    }

    public TextField getTxtReferenciaPrenda() {
        return txtReferenciaPrenda;
    }

    public TextField getTxtIdentificacionCliente() {
        return txtIdentificacionCliente;
    }

    public ComboBox<String> getCbxPrioridad() {
        return cbxPrioridad;
    }

    public Button getBtnBuscarPrenda() {
        return btnBuscarPrenda;
    }

    public Button getBtnBuscarCliente() {
        return btnBuscarCliente;
    }

    public Button getBtnRegistrar() {
        return btnRegistrar;
    }

    public Button getBtnEliminar() {
        return btnEliminar;
    }

    public Button getBtnEnviar() {
        return btnEnviar;
    }

    public Button getBtnLimpiar() {
        return btnLimpiar;
    }

    public Button getBtnVolverMenu() {
        return btnVolverMenu;
    }

    public Label getLblTipoPrenda() {
        return lblTipoPrenda;
    }

    public Label getLblNombreCliente() {
        return lblNombreCliente;
    }
}
