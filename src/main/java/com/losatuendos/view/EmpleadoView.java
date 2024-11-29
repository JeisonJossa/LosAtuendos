package com.losatuendos.view;

import com.losatuendos.controller.EmpleadoController;
import com.losatuendos.entity.Empleado;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EmpleadoView extends BaseView {

    private TableView<Empleado> tblEmpleados;
    private TextField txtIdentificacion, txtNombre, txtDireccion, txtTelefono, txtCorreo, txtUsuario;
    private PasswordField txtPassword;
    private ComboBox<String> cbxRol;
    private Button btnGuardar, btnEditar, btnEliminar, btnLimpiar, btnVolverMenu;

    private final EmpleadoController empleadoController;

    public EmpleadoView(Stage stage, EmpleadoController empleadoController) {
        super(stage);
        this.empleadoController = empleadoController;
        setTitle("Gestión de Empleados - Los Atuendos");
        initializeComponents();
        setupEventHandlers();
    }

    @Override
    protected void initializeComponents() {
        // Título de la vista
        Label lblTitulo = new Label("Gestión de Empleados");
        lblTitulo.setStyle("-fx-font-size: 24px; -fx-text-fill: " + StyleManager.SECONDARY_COLOR + ";");

        // Campos de texto y etiquetas
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

        Label lblUsuario = new Label("Usuario:");
        lblUsuario.setStyle("-fx-text-fill: " + StyleManager.SECONDARY_COLOR + ";");
        txtUsuario = new TextField();
        txtUsuario.setPromptText("Usuario");
        StyleManager.applyDefaultStyle(txtUsuario);

        Label lblPassword = new Label("Contraseña:");
        lblPassword.setStyle("-fx-text-fill: " + StyleManager.SECONDARY_COLOR + ";");
        txtPassword = new PasswordField();
        txtPassword.setPromptText("Contraseña");
        StyleManager.applyDefaultStyle(txtPassword);

        Label lblRol = new Label("Rol:");
        lblRol.setStyle("-fx-text-fill: " + StyleManager.SECONDARY_COLOR + ";");
        cbxRol = new ComboBox<>(FXCollections.observableArrayList("Administrador", "Empleado"));
        cbxRol.setPromptText("Seleccione el Rol");
        cbxRol.setValue("Empleado"); // Valor predeterminado
        StyleManager.applyDefaultStyle(cbxRol);

        // GridPane para los campos del formulario
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
        gridForm.add(lblUsuario, 2, 2);
        gridForm.add(txtUsuario, 3, 2);
        gridForm.add(lblPassword, 0, 3);
        gridForm.add(txtPassword, 1, 3);
        gridForm.add(lblRol, 2, 3);
        gridForm.add(cbxRol, 3, 3);
        gridForm.setAlignment(Pos.CENTER);

        // Botones
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

        HBox hboxBotones = new HBox(15, btnGuardar, btnEditar, btnEliminar, btnLimpiar);
        hboxBotones.setAlignment(Pos.CENTER);

        // Tabla para mostrar empleados
        tblEmpleados = new TableView<>();
        TableColumn<Empleado, String> colIdentificacion = new TableColumn<>("Identificación");
        colIdentificacion.setCellValueFactory(new PropertyValueFactory<>("numeroIdentificacion"));

        TableColumn<Empleado, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Empleado, String> colDireccion = new TableColumn<>("Dirección");
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        TableColumn<Empleado, String> colTelefono = new TableColumn<>("Teléfono");
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        TableColumn<Empleado, String> colCorreo = new TableColumn<>("Correo");
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));

        TableColumn<Empleado, String> colUsuario = new TableColumn<>("Usuario");
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));

        TableColumn<Empleado, String> colRol = new TableColumn<>("Rol");
        colRol.setCellValueFactory(data -> {
            if (data.getValue().getIdRol() != null) {
                return new SimpleStringProperty(data.getValue().getIdRol().getNombre());
            } else {
                return new SimpleStringProperty(""); // Si no hay rol, muestra vacío
            }
        });

        tblEmpleados.getColumns().addAll(colIdentificacion, colNombre, colDireccion, colTelefono, colCorreo, colUsuario, colRol);

        VBox vboxMain = new VBox(20, lblTitulo, tblEmpleados, gridForm, hboxBotones, btnVolverMenu);
        vboxMain.setAlignment(Pos.CENTER);
        vboxMain.setStyle("-fx-padding: 20; -fx-background-color: " + StyleManager.PRIMARY_COLOR + ";");
        root.setCenter(vboxMain);
    }

    @Override
    protected void setupEventHandlers() {
        btnGuardar.setOnAction(event -> empleadoController.handleGuardar());
        btnEditar.setOnAction(event -> empleadoController.handleEditar());
        btnEliminar.setOnAction(event -> empleadoController.handleEliminar());
        btnLimpiar.setOnAction(event -> empleadoController.handleLimpiar());
        btnVolverMenu.setOnAction(event -> empleadoController.handleVolverMenu());
    }

    public TableView<Empleado> getTblEmpleados() {
        return tblEmpleados;
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

    public TextField getTxtUsuario() {
        return txtUsuario;
    }

    public PasswordField getTxtPassword() {
        return txtPassword;
    }

    public ComboBox<String> getCbxRol() {
        return cbxRol;
    }
}
