package com.losatuendos.view;

import com.losatuendos.controller.PrendaController;
import com.losatuendos.entity.Prenda;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.util.List;

public class PrendaView extends BaseView {

    private TableView<Prenda> tblPrendas;
    private TextField txtReferencia, txtColor, txtMarca, txtTalla, txtValorAlquiler, txtCantidadPiezas, txtNombreDisfraz, txtTipoTraje, txtAccesorios;
    private ComboBox<String> cbxTipo, cbxPedreria, cbxLargoCorto, cbxEstado;
    private Button btnGuardar, btnEditar, btnEliminar, btnLimpiar, btnVolverMenu;

    private PrendaController prendaController;

    public PrendaView(Stage stage, PrendaController prendaController) {
        super(stage);
        this.prendaController = prendaController; // Inyectar el controlador
        setTitle("Gestión de Prendas - Los Atuendos");
        initializeComponents();
        setupEventHandlers(); // Configurar eventos con el controlador
    }

    @Override
    protected void initializeComponents() {
        // Título
        Label lblTitulo = new Label("Gestión de Prendas");
        lblTitulo.setStyle("-fx-font-size: 24px; -fx-text-fill: " + StyleManager.SECONDARY_COLOR + ";");

        // Campos de formulario
        txtReferencia = createTextField("Referencia");
        cbxTipo = createComboBox("Tipo de Prenda", List.of("Dama", "Caballero", "Disfraz"), "Dama");
        txtColor = createTextField("Color");
        txtMarca = createTextField("Marca");
        txtTalla = createTextField("Talla");
        txtValorAlquiler = createTextField("Valor del Alquiler");
        cbxPedreria = createComboBox("¿Pedrería?", List.of("Sí", "No"), "No");
        cbxLargoCorto = createComboBox("Largo/Corto", List.of("Largo", "Corto"), "Corto");
        txtCantidadPiezas = createTextField("Cantidad de Piezas");
        txtTipoTraje = createTextField("Tipo de Traje");
        txtAccesorios = createTextField("Accesorios");
        txtNombreDisfraz = createTextField("Nombre del Disfraz");
        cbxEstado = createComboBox("Estado", List.of("Disponible", "Lavanderia", "Alquilado"), "Disponible");

        // GridPane para organizar el formulario
        GridPane gridForm = new GridPane();
        gridForm.setHgap(10);
        gridForm.setVgap(10);
        gridForm.add(createLabel("Referencia:"), 0, 0);
        gridForm.add(txtReferencia, 1, 0);
        gridForm.add(createLabel("Tipo de Prenda:"), 2, 0);
        gridForm.add(cbxTipo, 3, 0);
        gridForm.add(createLabel("Color:"), 0, 1);
        gridForm.add(txtColor, 1, 1);
        gridForm.add(createLabel("Marca:"), 2, 1);
        gridForm.add(txtMarca, 3, 1);
        gridForm.add(createLabel("Talla:"), 0, 2);
        gridForm.add(txtTalla, 1, 2);
        gridForm.add(createLabel("Valor Alquiler:"), 2, 2);
        gridForm.add(txtValorAlquiler, 3, 2);
        gridForm.add(createLabel("¿Pedrería?:"), 0, 3);
        gridForm.add(cbxPedreria, 1, 3);
        gridForm.add(createLabel("Largo/Corto:"), 2, 3);
        gridForm.add(cbxLargoCorto, 3, 3);
        gridForm.add(createLabel("Cantidad Piezas:"), 0, 4);
        gridForm.add(txtCantidadPiezas, 1, 4);
        gridForm.add(createLabel("Tipo de Traje:"), 2, 4);
        gridForm.add(txtTipoTraje, 3, 4);
        gridForm.add(createLabel("Accesorios:"), 0, 5);
        gridForm.add(txtAccesorios, 1, 5);
        gridForm.add(createLabel("Nombre Disfraz:"), 2, 5);
        gridForm.add(txtNombreDisfraz, 3, 5);
        gridForm.add(createLabel("Estado:"), 0, 6);
        gridForm.add(cbxEstado, 1, 6);
        gridForm.setAlignment(Pos.CENTER);

        // Botones
        btnGuardar = createButton("Guardar");
        btnEditar = createButton("Editar");
        btnEliminar = createButton("Eliminar");
        btnLimpiar = createButton("Limpiar");
        btnVolverMenu = createButton("Volver al Menú Principal");

        HBox hboxBotones = new HBox(15, btnGuardar, btnEditar, btnEliminar, btnLimpiar, btnVolverMenu);
        hboxBotones.setAlignment(Pos.CENTER);

        // Tabla de prendas
        tblPrendas = new TableView<>();
        tblPrendas.setPrefHeight(300);
        tblPrendas.getColumns().addAll(
                createColumn("Referencia", Prenda::getReferencia),
                createColumn("Tipo de Prenda", Prenda::getTipo),
                createColumn("Color", Prenda::getColor),
                createColumn("Marca", Prenda::getMarca),
                createColumn("Talla", Prenda::getTalla),
                createColumn("Valor Alquiler", prenda -> prenda.getValorAlquiler() != null ? prenda.getValorAlquiler().toString() : ""),
                createColumn("¿Pedrería?", prenda -> prenda.getPedreria() != null ? (prenda.getPedreria() ? "Sí" : "No") : "No"),
                createColumn("Largo/Corto", Prenda::getLargoCorto),
                createColumn("Cantidad Piezas", prenda -> prenda.getCantidadPiezas() != null ? prenda.getCantidadPiezas().toString() : ""),
                createColumn("Tipo Traje", Prenda::getTipoTraje),
                createColumn("Accesorios", Prenda::getAccesorios),
                createColumn("Nombre Disfraz", Prenda::getNombreDisfraz),
                createColumn("Estado", Prenda::getEstado)
        );


        // Layout principal
        VBox vboxMain = new VBox(20, lblTitulo, tblPrendas, gridForm, hboxBotones);
        vboxMain.setAlignment(Pos.CENTER);
        vboxMain.setStyle("-fx-padding: 20; -fx-background-color: " + StyleManager.PRIMARY_COLOR + ";");
        root.setCenter(vboxMain);
    }

    private TextField createTextField(String promptText) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        StyleManager.applyDefaultStyle(textField);
        return textField;
    }

    private ComboBox<String> createComboBox(String promptText, List<String> items, String defaultValue) {
        ComboBox<String> comboBox = new ComboBox<>(FXCollections.observableArrayList(items));
        comboBox.setPromptText(promptText);
        comboBox.setValue(defaultValue);
        StyleManager.applyDefaultStyle(comboBox);
        return comboBox;
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

    private <T> TableColumn<Prenda, String> createColumn(String title, java.util.function.Function<Prenda, String> mapper) {
        TableColumn<Prenda, String> column = new TableColumn<>(title);
        column.setCellValueFactory(cellData -> {
            Prenda prenda = cellData.getValue();
            String value = prenda == null ? "" : mapper.apply(prenda);
            return new javafx.beans.property.SimpleStringProperty(value);
        });
        column.setMinWidth(100);
        return column;
    }


    @Override
    protected void setupEventHandlers() {
        btnGuardar.setOnAction(event -> prendaController.handleGuardar());
        btnEditar.setOnAction(event -> prendaController.handleEditar());
        btnEliminar.setOnAction(event -> prendaController.handleEliminar());
        btnLimpiar.setOnAction(event -> prendaController.handleLimpiar());
        btnVolverMenu.setOnAction(event -> prendaController.handleVolverMenu());
    }

    public TableView<Prenda> getTblPrendas() {
        return tblPrendas;
    }

    public TextField getTxtReferencia() {
        return txtReferencia;
    }

    public ComboBox<String> getCbxTipo() {
        return cbxTipo;
    }

    public TextField getTxtColor() {
        return txtColor;
    }

    public TextField getTxtMarca() {
        return txtMarca;
    }

    public TextField getTxtTalla() {
        return txtTalla;
    }

    public TextField getTxtValorAlquiler() {
        return txtValorAlquiler;
    }

    public ComboBox<String> getCbxPedreria() {
        return cbxPedreria;
    }

    public ComboBox<String> getCbxLargoCorto() {
        return cbxLargoCorto;
    }

    public TextField getTxtCantidadPiezas() {
        return txtCantidadPiezas;
    }

    public TextField getTxtTipoTraje() {
        return txtTipoTraje;
    }

    public TextField getTxtAccesorios() {
        return txtAccesorios;
    }

    public TextField getTxtNombreDisfraz() {
        return txtNombreDisfraz;
    }

    public ComboBox<String> getCbxEstado() {
        return cbxEstado;
    }
}
