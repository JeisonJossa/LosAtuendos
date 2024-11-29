package com.losatuendos.view;

import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.Region;

public class StyleManager {

    // Paleta de colores
    public static final String PRIMARY_COLOR = "#000000"; // Negro
    public static final String SECONDARY_COLOR = "#FFFFFF"; // Blanco
    public static final String ERROR_COLOR = "#F44336"; // Rojo
    public static final String WARNING_COLOR = "#FF9800"; // Naranja
    public static final String SUCCESS_COLOR = "#4CAF50"; // Verde
    public static final String BUTTON_HOVER_COLOR = "#FF6666"; // Rojo más claro
    public static final String BACKGROUND_COLOR = "#F5F5F5";

    // Fuentes predeterminadas
    public static final String FONT_FAMILY = "Arial";
    public static final int FONT_SIZE_NORMAL = 14;
    public static final int FONT_SIZE_LARGE = 18;

    /**
     * Aplica el estilo predeterminado a un control.
     *
     * @param control El control al que se aplicará el estilo.
     */
    public static void applyDefaultStyle(Control control) {
        control.setStyle("-fx-font-family: " + FONT_FAMILY + ";" +
                "-fx-font-size: " + FONT_SIZE_NORMAL + ";" +
                "-fx-text-fill: " + PRIMARY_COLOR + ";");
    }

    /**
     * Aplica un estilo de botón primario con hover.
     *
     * @param button El botón al que se aplicará el estilo.
     */
    public static void applyPrimaryButtonStyle(Control button) {
        // Estilo inicial
        button.setStyle("-fx-background-color: #FF0000;" + // Fondo rojo
                "-fx-text-fill: " + SECONDARY_COLOR + ";" + // Texto blanco
                "-fx-font-family: " + FONT_FAMILY + ";" +
                "-fx-font-size: " + FONT_SIZE_NORMAL + ";" +
                "-fx-padding: 10 20;" +
                "-fx-background-radius: 20;" + // Bordes redondeados
                "-fx-border-radius: 20;" + // Bordes redondeados
                "-fx-cursor: hand;"); // Cambia el cursor al pasar por encima

        // Evento al pasar el ratón
        button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: " + BUTTON_HOVER_COLOR + ";" +
                "-fx-text-fill: " + SECONDARY_COLOR + ";" +
                "-fx-font-family: " + FONT_FAMILY + ";" +
                "-fx-font-size: " + FONT_SIZE_NORMAL + ";" +
                "-fx-padding: 10 20;" +
                "-fx-background-radius: 20;" +
                "-fx-border-radius: 20;" +
                "-fx-cursor: hand;"));

        // Evento al quitar el ratón
        button.setOnMouseExited(event -> button.setStyle("-fx-background-color: #FF0000;" +
                "-fx-text-fill: " + SECONDARY_COLOR + ";" +
                "-fx-font-family: " + FONT_FAMILY + ";" +
                "-fx-font-size: " + FONT_SIZE_NORMAL + ";" +
                "-fx-padding: 10 20;" +
                "-fx-background-radius: 20;" +
                "-fx-border-radius: 20;" +
                "-fx-cursor: hand;"));
    }

    /**
     * Aplica un estilo de contenedor general.
     *
     * @param region Región a la que se aplicará el estilo.
     */
    public static void applyContainerStyle(Region region) {
        region.setStyle("-fx-background-color: " + SECONDARY_COLOR + ";" +
                "-fx-padding: 15;" +
                "-fx-border-color: " + PRIMARY_COLOR + ";" +
                "-fx-border-width: 2;");
    }

    /**
     * Aplica un estilo de texto de error.
     *
     * @param control El control al que se aplicará el estilo.
     */
    public static void applyErrorStyle(Control control) {
        control.setStyle("-fx-background-color: #FFF0F0;" + // Fondo claro rojo
                "-fx-text-fill: " + ERROR_COLOR + ";" +
                "-fx-font-family: " + FONT_FAMILY + ";" +
                "-fx-font-size: " + FONT_SIZE_NORMAL + ";" +
                "-fx-border-color: " + ERROR_COLOR + ";" +
                "-fx-border-width: 2;" +
                "-fx-padding: 5;");
    }

    /**
     * Aplica un estilo de texto de advertencia.
     *
     * @param control El control al que se aplicará el estilo.
     */
    public static void applyWarningStyle(Control control) {
        control.setStyle("-fx-background-color: #FFF8E1;" + // Fondo claro naranja
                "-fx-text-fill: " + WARNING_COLOR + ";" +
                "-fx-font-family: " + FONT_FAMILY + ";" +
                "-fx-font-size: " + FONT_SIZE_NORMAL + ";" +
                "-fx-border-color: " + WARNING_COLOR + ";" +
                "-fx-border-width: 2;" +
                "-fx-padding: 5;");
    }

    /**
     * Aplica un estilo de texto de éxito.
     *
     * @param control El control al que se aplicará el estilo.
     */
    public static void applySuccessStyle(Control control) {
        control.setStyle("-fx-background-color: #E8F5E9;" + // Fondo claro verde
                "-fx-text-fill: " + SUCCESS_COLOR + ";" +
                "-fx-font-family: " + FONT_FAMILY + ";" +
                "-fx-font-size: " + FONT_SIZE_NORMAL + ";" +
                "-fx-border-color: " + SUCCESS_COLOR + ";" +
                "-fx-border-width: 2;" +
                "-fx-padding: 5;");
    }

    /**
     * Aplica estilos personalizados a las alertas de MessageUtils.
     *
     * @param alert Alerta a personalizar.
     */
    public static void applyAlertStyle(Alert alert) {
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-font-family: " + FONT_FAMILY + ";" +
                "-fx-font-size: " + FONT_SIZE_NORMAL + ";" +
                "-fx-background-color: " + BACKGROUND_COLOR + ";" + // Fondo gris claro
                "-fx-border-color: " + PRIMARY_COLOR + ";" + // Bordes negros
                "-fx-border-width: 2;");

        // Verificar si existen los nodos antes de intentar modificarlos
        if (dialogPane.lookup(".content") != null) {
            dialogPane.lookup(".content").setStyle("-fx-text-fill: " + PRIMARY_COLOR + ";"); // Texto negro
        }

        if (dialogPane.lookup(".header-panel") != null) {
            dialogPane.lookup(".header-panel").setStyle("-fx-text-fill: " + PRIMARY_COLOR + ";"); // Encabezado negro
        }
    }



}
