package com.losatuendos.controller;

import com.losatuendos.entity.Prenda;
import com.losatuendos.service.PrendaService;
import com.losatuendos.view.PrendaView;
import com.losatuendos.view.MessageUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class PrendaController {

    private final PrendaService prendaService;
    private final AppController appController;
    private PrendaView prendaView;

    @Autowired
    public PrendaController(PrendaService prendaService, AppController appController) {
        this.prendaService = prendaService;
        this.appController = appController;
    }

    public void setPrendaView(PrendaView prendaView) {
        this.prendaView = prendaView;
        cargarPrendasEnTabla(); // Cargar las prendas al iniciar la vista
    }

    /**
     * Maneja el evento para guardar una prenda.
     */
    public void handleGuardar() {
        try {
            // Validar campos
            String referencia = validarCampo(prendaView.getTxtReferencia().getText(), "Referencia");
            String tipo = validarCampo(prendaView.getCbxTipo().getValue(), "Tipo de Prenda");
            String color = validarCampo(prendaView.getTxtColor().getText(), "Color");
            String marca = validarCampo(prendaView.getTxtMarca().getText(), "Marca");
            String talla = validarCampo(prendaView.getTxtTalla().getText(), "Talla");
            BigDecimal valorAlquiler = new BigDecimal(validarCampo(prendaView.getTxtValorAlquiler().getText(), "Valor del Alquiler"));
            Boolean pedreria = prendaView.getCbxPedreria().getValue().equalsIgnoreCase("Sí");
            String largoCorto = validarCampo(prendaView.getCbxLargoCorto().getValue(), "Largo/Corto");
            Integer cantidadPiezas = Integer.parseInt(validarCampo(prendaView.getTxtCantidadPiezas().getText(), "Cantidad de Piezas"));
            String tipoTraje = validarCampo(prendaView.getTxtTipoTraje().getText(), "Tipo de Traje");
            String accesorios = validarCampo(prendaView.getTxtAccesorios().getText(), "Accesorios");
            String nombreDisfraz = validarCampo(prendaView.getTxtNombreDisfraz().getText(), "Nombre del Disfraz");
            String estado = validarCampo(prendaView.getCbxEstado().getValue(), "Estado");

            // Crear objeto prenda
            Prenda prenda = new Prenda();
            prenda.setReferencia(referencia);
            prenda.setTipo(tipo);
            prenda.setColor(color);
            prenda.setMarca(marca);
            prenda.setTalla(talla);
            prenda.setValorAlquiler(valorAlquiler);
            prenda.setPedreria(pedreria);
            prenda.setLargoCorto(largoCorto);
            prenda.setCantidadPiezas(cantidadPiezas);
            prenda.setTipoTraje(tipoTraje);
            prenda.setAccesorios(accesorios);
            prenda.setNombreDisfraz(nombreDisfraz);
            prenda.setEstado(estado);

            // Guardar prenda
            prendaService.guardarPrenda(prenda);

            MessageUtils.showSuccess("Éxito", "Prenda guardada correctamente.");
            limpiarFormulario();
            cargarPrendasEnTabla();

        } catch (Exception e) {
            MessageUtils.showError("Error", "No se pudo guardar la prenda: " );
            System.out.println(e.getMessage());
        }
    }

    /**
     * Maneja el evento para editar una prenda.
     */
    public void handleEditar() {
        try {
            // Obtener prenda seleccionada
            Prenda prendaSeleccionada = (Prenda) prendaView.getTblPrendas().getSelectionModel().getSelectedItem();
            if (prendaSeleccionada == null) {
                MessageUtils.showWarning("Advertencia", "Debe seleccionar una prenda para editar.");
                return;
            }

            // Validar campos
            String referencia = validarCampo(prendaView.getTxtReferencia().getText(), "Referencia");
            String tipo = validarCampo(prendaView.getCbxTipo().getValue(), "Tipo de Prenda");
            String color = validarCampo(prendaView.getTxtColor().getText(), "Color");
            String marca = validarCampo(prendaView.getTxtMarca().getText(), "Marca");
            String talla = validarCampo(prendaView.getTxtTalla().getText(), "Talla");
            BigDecimal valorAlquiler = new BigDecimal(validarCampo(prendaView.getTxtValorAlquiler().getText(), "Valor del Alquiler"));
            Boolean pedreria = prendaView.getCbxPedreria().getValue().equalsIgnoreCase("Sí");
            String largoCorto = validarCampo(prendaView.getCbxLargoCorto().getValue(), "Largo/Corto");
            Integer cantidadPiezas = Integer.parseInt(validarCampo(prendaView.getTxtCantidadPiezas().getText(), "Cantidad de Piezas"));
            String tipoTraje = validarCampo(prendaView.getTxtTipoTraje().getText(), "Tipo de Traje");
            String accesorios = validarCampo(prendaView.getTxtAccesorios().getText(), "Accesorios");
            String nombreDisfraz = validarCampo(prendaView.getTxtNombreDisfraz().getText(), "Nombre del Disfraz");
            String estado = validarCampo(prendaView.getCbxEstado().getValue(), "Estado");

            // Actualizar objeto prenda
            prendaSeleccionada.setReferencia(referencia);
            prendaSeleccionada.setTipo(tipo);
            prendaSeleccionada.setColor(color);
            prendaSeleccionada.setMarca(marca);
            prendaSeleccionada.setTalla(talla);
            prendaSeleccionada.setValorAlquiler(valorAlquiler);
            prendaSeleccionada.setPedreria(pedreria);
            prendaSeleccionada.setLargoCorto(largoCorto);
            prendaSeleccionada.setCantidadPiezas(cantidadPiezas);
            prendaSeleccionada.setTipoTraje(tipoTraje);
            prendaSeleccionada.setAccesorios(accesorios);
            prendaSeleccionada.setNombreDisfraz(nombreDisfraz);
            prendaSeleccionada.setEstado(estado);

            // Guardar cambios
            prendaService.guardarPrenda(prendaSeleccionada);

            MessageUtils.showSuccess("Éxito", "Prenda actualizada correctamente.");
            limpiarFormulario();
            cargarPrendasEnTabla();

        } catch (Exception e) {
            MessageUtils.showError("Error", "No se pudo editar la prenda: " + e.getMessage());
        }
    }

    /**
     * Maneja el evento para eliminar una prenda.
     */
    public void handleEliminar() {
        try {
            // Obtener prenda seleccionada
            Prenda prendaSeleccionada = (Prenda) prendaView.getTblPrendas().getSelectionModel().getSelectedItem();
            if (prendaSeleccionada == null) {
                MessageUtils.showWarning("Advertencia", "Debe seleccionar una prenda para eliminar.");
                return;
            }

            // Confirmar eliminación
            prendaService.eliminarPorReferencia(prendaSeleccionada.getReferencia());
            MessageUtils.showSuccess("Éxito", "Prenda eliminada correctamente.");
            cargarPrendasEnTabla();

        } catch (Exception e) {
            MessageUtils.showError("Error", "No se pudo eliminar la prenda: " + e.getMessage());
        }
    }

    /**
     * Maneja el evento para limpiar el formulario.
     */
    public void handleLimpiar() {
        limpiarFormulario();
    }

    /**
     * Maneja el evento para volver al menú principal.
     */
    public void handleVolverMenu() {
        appController.showMainMenu();
    }

    /**
     * Limpia los campos del formulario.
     */
    private void limpiarFormulario() {
        prendaView.getTxtReferencia().clear();
        prendaView.getCbxTipo().setValue("Dama");
        prendaView.getTxtColor().clear();
        prendaView.getTxtMarca().clear();
        prendaView.getTxtTalla().clear();
        prendaView.getTxtValorAlquiler().clear();
        prendaView.getCbxPedreria().setValue("No");
        prendaView.getCbxLargoCorto().setValue("Corto");
        prendaView.getTxtCantidadPiezas().clear();
        prendaView.getTxtTipoTraje().clear();
        prendaView.getTxtAccesorios().clear();
        prendaView.getTxtNombreDisfraz().clear();
        prendaView.getCbxEstado().setValue("Bueno");
    }

    /**
     * Valida un campo para asegurar que no sea nulo o vacío.
     *
     * @param campo  Valor del campo a validar.
     * @param nombre Nombre del campo (para el mensaje de error).
     * @return El valor validado.
     * @throws IllegalArgumentException Si el campo es nulo o vacío.
     */
    private String validarCampo(String campo, String nombre) {
        if (campo == null || campo.trim().isEmpty()) {
            throw new IllegalArgumentException(nombre + " no puede estar vacío.");
        }
        return campo.trim();
    }

    private void cargarPrendasEnTabla() {
        try {
            List<Prenda> prendas = prendaService.obtenerTodas();
            if (prendas != null && !prendas.isEmpty()) {
                ObservableList<Prenda> prendasObservableList = FXCollections.observableArrayList(prendas);
                prendaView.getTblPrendas().setItems(prendasObservableList);
            } else {
                prendaView.getTblPrendas().setItems(FXCollections.observableArrayList());
            }
        } catch (Exception e) {
            MessageUtils.showError("Error", "No se pudieron cargar las prendas: " + e.getMessage());
        }
    }


}
