package com.losatuendos.view;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public abstract class BaseView {

    protected Stage stage;
    protected BorderPane root;
    protected Scene scene;

    /**
     * Constructor base que configura el Stage principal.
     *
     * @param stage Stage de la ventana.
     */
    public BaseView(Stage stage) {
        this.stage = stage;
        initializeRoot();
        initializeScene();
        setupStage();
    }

    /**
     * Inicializa el BorderPane raíz.
     */
    private void initializeRoot() {
        root = new BorderPane();
    }

    /**
     * Inicializa la escena con el BorderPane raíz.
     */
    private void initializeScene() {
        scene = new Scene(root, 800, 600); // Configura tamaño por defecto
    }

    /**
     * Configura el Stage principal.
     */
    private void setupStage() {
        stage.setScene(scene);
        stage.setResizable(false); // Por defecto, las ventanas no son redimensionables
    }

    /**
     * Muestra la ventana.
     */
    public void show() {
        stage.show();
    }

    /**
     * Cierra la ventana.
     */
    public void close() {
        stage.close();
    }

    /**
     * Muestra un mensaje de información.
     *
     * @param title   Título del mensaje.
     * @param message Mensaje a mostrar.
     */
    public void showInfoMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Muestra un mensaje de advertencia.
     *
     * @param title   Título del mensaje.
     * @param message Mensaje a mostrar.
     */
    public void showWarningMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Muestra un mensaje de error.
     *
     * @param title   Título del mensaje.
     * @param message Mensaje a mostrar.
     */
    public void showErrorMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Muestra un mensaje de confirmación.
     *
     * @param title   Título del mensaje.
     * @param message Mensaje a mostrar.
     * @return True si el usuario confirma, false de lo contrario.
     */
    public boolean showConfirmationMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert.showAndWait().filter(ButtonType.OK::equals).isPresent();
    }

    /**
     * Configura el título de la ventana.
     *
     * @param title Título de la ventana.
     */
    public void setTitle(String title) {
        stage.setTitle(title);
    }

    /**
     * Método abstracto para inicializar los elementos específicos de la vista.
     */
    protected abstract void initializeComponents();

    /**
     * Método abstracto para configurar los eventos de la vista.
     */
    protected abstract void setupEventHandlers();
}
