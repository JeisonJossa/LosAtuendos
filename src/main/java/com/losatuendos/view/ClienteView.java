package com.losatuendos.view;

import com.losatuendos.controller.ClienteController; // Importar el controlador asociado
import com.losatuendos.entity.Cliente;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClienteView extends BaseView {

    private TableView<Cliente> tblClientes; // Cambiado el tipo genérico a Cliente
    private TextField txtIdentificacion, txtNombre, txtDireccion, txtTelefono, txtCorreo;
    private Button btnGuardar, btnEditar, btnEliminar, btnLimpiar, btnVolverMenu;

    // Controlador asociado
    private ClienteController clienteController;

    // Constructor que acepta un Stage y un controlador
    public ClienteView(Stage stage, ClienteController clienteController) {
        super(stage);
        this.clienteController = clienteController; // Inyectar el controlador
        setTitle("Gestión de Clientes - Los Atuendos");
        initializeComponents();
        setupEventHandlers(); // Configurar eventos con el controlador
    }

    @Override
    protected void initializeComponents() {
        // Título de la vista
        Label lblTitulo = new Label("Gestión de Clientes");
        lblTitulo.setStyle("-fx-font-size: 24px; -fx-text-fill: " + StyleManager.SECONDARY_COLOR + ";");

        // Labels y campos de texto para los datos del cliente
        Label lblIdentificacion = new Label("Número de Identificación:");
        lblIdentificacion.setStyle("-fx-text-fill: " + StyleManager.SECONDARY_COLOR + ";");
        txtIdentificacion = new TextField();
        txtIdentificacion.setPromptText("Número de Identificación");
        StyleManager.applyDefaultStyle(txtIdentificacion);

        Label lblNombre = new Label("Nombre Completo:");
        lblNombre.setStyle("-fx-text-fill: " + StyleManager.SECONDARY_COLOR + ";");
        txtNombre = new TextField();
        txtNombre.setPromptText("Nombre Completo");
        StyleManager.applyDefaultStyle(txtNombre);

        Label lblDireccion = new Label("Dirección:");
        lblDireccion.setStyle("-fx-text-fill: " + StyleManager.SECONDARY_COLOR + ";");
        txtDireccion = new TextField();
        txtDireccion.setPromptText("Dirección");
        StyleManager.applyDefaultStyle(txtDireccion);

        Label lblTelefono = new Label("Teléfono:");
        lblTelefono.setStyle("-fx-text-fill: " + StyleManager.SECONDARY_COLOR + ";");
        txtTelefono = new TextField();
        txtTelefono.setPromptText("Teléfono");
        StyleManager.applyDefaultStyle(txtTelefono);

        Label lblCorreo = new Label("Correo Electrónico:");
        lblCorreo.setStyle("-fx-text-fill: " + StyleManager.SECONDARY_COLOR + ";");
        txtCorreo = new TextField();
        txtCorreo.setPromptText("Correo Electrónico");
        StyleManager.applyDefaultStyle(txtCorreo);

        // GridPane para distribuir los campos en dos columnas
        GridPane gridForm = new GridPane();
        gridForm.setHgap(15);
        gridForm.setVgap(10);

        gridForm.add(lblIdentificacion, 0, 0);
        gridForm.add(txtIdentificacion, 1, 0);
        gridForm.add(lblNombre, 2, 0);
        gridForm.add(txtNombre, 3, 0);

        gridForm.add(lblDireccion, 0, 1);
        gridForm.add(txtDireccion, 1, 1);
        gridForm.add(lblTelefono, 2, 1);
        gridForm.add(txtTelefono, 3, 1);

        gridForm.add(lblCorreo, 0, 2);
        gridForm.add(txtCorreo, 1, 2);

        gridForm.setAlignment(Pos.CENTER);

        // Botones de acción
        btnGuardar = new Button("Guardar");
        StyleManager.applyPrimaryButtonStyle(btnGuardar);

        btnEditar = new Button("Editar");
        StyleManager.applyPrimaryButtonStyle(btnEditar);

        btnEliminar = new Button("Eliminar");
        StyleManager.applyPrimaryButtonStyle(btnEliminar);

        btnLimpiar = new Button("Limpiar");
        StyleManager.applyPrimaryButtonStyle(btnLimpiar);

        btnVolverMenu = new Button("Volver al Menú Principal");
        StyleManager.applyPrimaryButtonStyle(btnVolverMenu);

        // Layout de botones
        HBox hboxBotones = new HBox(15, btnGuardar, btnEditar, btnEliminar, btnLimpiar);
        hboxBotones.setAlignment(Pos.CENTER);

        // Tabla para mostrar clientes
        tblClientes = new TableView<>();
        TableColumn<Cliente, String> colIdentificacion = new TableColumn<>("Identificación");
        colIdentificacion.setCellValueFactory(new PropertyValueFactory<>("numeroIdentificacion"));

        TableColumn<Cliente, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Cliente, String> colDireccion = new TableColumn<>("Dirección");
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        TableColumn<Cliente, String> colTelefono = new TableColumn<>("Teléfono");
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        TableColumn<Cliente, String> colCorreo = new TableColumn<>("Correo");
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));

        // Ajustar el tamaño de las columnas
        colIdentificacion.setMinWidth(150);
        colNombre.setMinWidth(150);
        colDireccion.setMinWidth(150);
        colTelefono.setMinWidth(150);
        colCorreo.setMinWidth(150);

        tblClientes.getColumns().addAll(colIdentificacion, colNombre, colDireccion, colTelefono, colCorreo);

        // ScrollPane para la tabla con scroll horizontal y vertical siempre visible
        ScrollPane scrollPane = new ScrollPane(tblClientes);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setFitToWidth(true);

        // Layout principal
        VBox vboxMain = new VBox(20, lblTitulo, scrollPane, gridForm, hboxBotones, btnVolverMenu);
        vboxMain.setAlignment(Pos.CENTER);
        vboxMain.setStyle("-fx-padding: 20; -fx-background-color: " + StyleManager.PRIMARY_COLOR + ";");
        root.setCenter(vboxMain);
    }

    @Override
    protected void setupEventHandlers() {
        // Delegar la lógica al controlador
        btnGuardar.setOnAction(event -> clienteController.handleGuardar());
        btnEditar.setOnAction(event -> clienteController.handleEditar());
        btnEliminar.setOnAction(event -> clienteController.handleEliminar());
        btnLimpiar.setOnAction(event -> clienteController.handleLimpiar());
        btnVolverMenu.setOnAction(event -> clienteController.handleVolverMenu());
    }

    // Getters para los controladores si se necesitan en pruebas o ajustes futuros
    public TableView<Cliente> getTblClientes() {
        return tblClientes;
    }

    public TextField getTxtIdentificacion() {
        return txtIdentificacion;
    }

    public TextField getTxtNombre() {
        return txtNombre;
    }

    public TextField getTxtDireccion() {
        return txtDireccion;
    }

    public TextField getTxtTelefono() {
        return txtTelefono;
    }

    public TextField getTxtCorreo() {
        return txtCorreo;
    }
}
