package com.losatuendos.view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

public class MessageUtils {

    /**
     * Muestra un mensaje de error.
     *
     * @param titulo Título del mensaje.
     * @param mensaje Cuerpo del mensaje.
     */
    public static void showError(String titulo, String mensaje) {
        showMessage(AlertType.ERROR, titulo, mensaje);
    }

    /**
     * Muestra un mensaje de éxito.
     *
     * @param titulo Título del mensaje.
     * @param mensaje Cuerpo del mensaje.
     */
    public static void showSuccess(String titulo, String mensaje) {
        showMessage(AlertType.INFORMATION, titulo, mensaje);
    }

    /**
     * Muestra un mensaje de advertencia.
     *
     * @param titulo Título del mensaje.
     * @param mensaje Cuerpo del mensaje.
     */
    public static void showWarning(String titulo, String mensaje) {
        showMessage(AlertType.WARNING, titulo, mensaje);
    }

    /**
     * Método genérico para mostrar mensajes personalizados.
     *
     * @param alertType Tipo de alerta.
     * @param titulo Título del mensaje.
     * @param mensaje Cuerpo del mensaje.
     */
    private static void showMessage(AlertType alertType, String titulo, String mensaje) {
        Alert alert = new Alert(alertType);
        alert.initStyle(StageStyle.UTILITY); // Estilo de ventana mínima
        alert.setTitle(titulo);
        alert.setHeaderText(null); // Sin encabezado adicional
        alert.setContentText(mensaje);

        // Aplicar estilos personalizados
        StyleManager.applyAlertStyle(alert);

        alert.showAndWait();
    }
}
