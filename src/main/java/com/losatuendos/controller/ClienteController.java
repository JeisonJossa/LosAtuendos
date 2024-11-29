package com.losatuendos.controller;

import com.losatuendos.entity.Cliente;
import com.losatuendos.service.ClienteService;
import com.losatuendos.view.ClienteView;
import com.losatuendos.view.MessageUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClienteController {

    private final ClienteService clienteService; // Servicio de clientes
    private ClienteView clienteView; // Vista asociada
    private final AppController appController;

    private ObservableList<Cliente> clienteObservableList; // Modelo de datos para la tabla

    /**
     * Constructor con inyección del servicio de clientes.
     *
     * @param clienteService Servicio de clientes.
     * @param appController  Controlador principal de la aplicación.
     */
    @Autowired
    public ClienteController(ClienteService clienteService, AppController appController) {
        this.clienteService = clienteService;
        this.appController = appController;
        this.clienteObservableList = FXCollections.observableArrayList();
    }

    /**
     * Establece la vista asociada con este controlador.
     *
     * @param clienteView Vista del cliente.
     */
    public void setClienteView(ClienteView clienteView) {
        this.clienteView = clienteView;
        cargarClientesEnTabla(); // Cargar datos al inicializar la vista
    }

    /**
     * Maneja el evento para guardar un cliente.
     */
    public void handleGuardar() {
        TextField txtIdentificacion = clienteView.getTxtIdentificacion();
        TextField txtNombre = clienteView.getTxtNombre();
        TextField txtDireccion = clienteView.getTxtDireccion();
        TextField txtTelefono = clienteView.getTxtTelefono();
        TextField txtCorreo = clienteView.getTxtCorreo();

        // Validación básica de campos requeridos
        if (txtIdentificacion.getText().isEmpty() ||
                txtNombre.getText().isEmpty() ||
                txtDireccion.getText().isEmpty() ||
                txtTelefono.getText().isEmpty() ||
                txtCorreo.getText().isEmpty()) {
            MessageUtils.showError("Error", "Digite todos los campos.");
            return;
        }

        String identificacion = txtIdentificacion.getText();
        String telefono = txtTelefono.getText();
        String correo = txtCorreo.getText();

        // Validación de formato del teléfono
        if (!identificacion.matches("\\d+")) { // Solo números
            MessageUtils.showError("Error", "El teléfono debe contener solo números.");
            return;
        }

        if (!telefono.matches("\\d+")) { // Solo números
            MessageUtils.showError("Error", "El teléfono debe contener solo números.");
            return;
        }

        // Validación de formato del correo electrónico
        if (!correo.isEmpty() && !correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            MessageUtils.showError("Error", "El correo electrónico ingresado no es válido.");
            return;
        }

        try {
            // Crear cliente a partir de los datos ingresados
            Cliente cliente = new Cliente();
            cliente.setNumeroIdentificacion(identificacion);
            cliente.setNombre(txtNombre.getText());
            cliente.setDireccion(txtDireccion.getText());
            cliente.setTelefono(telefono);
            cliente.setCorreo(correo);

            // Guardar cliente en la base de datos
            clienteService.guardarCliente(cliente);
            MessageUtils.showSuccess("Éxito", "Cliente guardado correctamente.");

            limpiarFormulario(); // Limpiar formulario
            cargarClientesEnTabla(); // Actualizar tabla
        } catch (Exception e) {
            MessageUtils.showError("Error", "No se pudo guardar el cliente: " + e.getMessage());
        }
    }

    /**
     * Maneja el evento para editar un cliente.
     */
    public void handleEditar() {
        TableView<Cliente> tblClientes = clienteView.getTblClientes();

        // Obtener cliente seleccionado
        Cliente clienteSeleccionado = tblClientes.getSelectionModel().getSelectedItem();
        if (clienteSeleccionado == null) {
            MessageUtils.showWarning("Advertencia", "Debe seleccionar un cliente para editar.");
            return;
        }

        // Validar que los campos requeridos no estén vacíos
        String identificacion = clienteView.getTxtIdentificacion().getText();
        String nombre = clienteView.getTxtNombre().getText();
        String direccion = clienteView.getTxtDireccion().getText();
        String telefono = clienteView.getTxtTelefono().getText();
        String correo = clienteView.getTxtCorreo().getText();

        if (identificacion.isEmpty() ||
                nombre.isEmpty() ||
                direccion.isEmpty() ||
                telefono.isEmpty() ||
                correo.isEmpty()) {
            MessageUtils.showError("Error", "Digite todos los campos.");
            return;
        }

        if (!identificacion.matches("\\d+")) { // Solo números
            MessageUtils.showError("Error", "La identificación debe contener solo números.");
            return;
        }

        if (!telefono.matches("\\d+")) { // Solo números
            MessageUtils.showError("Error", "El teléfono debe contener solo números.");
            return;
        }

        if (!correo.isEmpty() && !correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            MessageUtils.showError("Error", "El correo electrónico ingresado no es válido.");
            return;
        }

        try {
            // Actualizar los datos del cliente seleccionado
            clienteSeleccionado.setNumeroIdentificacion(identificacion);
            clienteSeleccionado.setNombre(nombre);
            clienteSeleccionado.setDireccion(direccion);
            clienteSeleccionado.setTelefono(telefono);
            clienteSeleccionado.setCorreo(correo);

            // Guardar cambios en la base de datos
            clienteService.guardarCliente(clienteSeleccionado);
            MessageUtils.showSuccess("Éxito", "Cliente actualizado correctamente.");

            limpiarFormulario(); // Limpiar formulario
            cargarClientesEnTabla(); // Refrescar la tabla
        } catch (Exception e) {
            MessageUtils.showError("Error", "No se pudo editar el cliente: " + e.getMessage());
        }
    }

    /**
     * Maneja el evento para eliminar un cliente.
     */
    public void handleEliminar() {
        TableView<Cliente> tblClientes = clienteView.getTblClientes();

        // Obtener cliente seleccionado
        Cliente clienteSeleccionado = tblClientes.getSelectionModel().getSelectedItem();
        if (clienteSeleccionado == null) {
            MessageUtils.showWarning("Advertencia", "Debe seleccionar un cliente para eliminar.");
            return;
        }

        try {
            // Confirmar eliminación
            clienteService.eliminarPorIdentificacion(clienteSeleccionado.getNumeroIdentificacion());
            MessageUtils.showSuccess("Éxito", "Cliente eliminado correctamente.");

            cargarClientesEnTabla(); // Actualizar tabla
        } catch (Exception e) {
            MessageUtils.showError("Error", "No se pudo eliminar el cliente: " + e.getMessage());
        }
    }

    /**
     * Maneja el evento para limpiar el formulario.
     */
    public void handleLimpiar() {
        limpiarFormulario();
    }

    /**
     * Carga todos los clientes en la tabla.
     */
    private void cargarClientesEnTabla() {
        try {
            List<Cliente> clientes = clienteService.obtenerTodos();
            clienteObservableList.setAll(clientes);
            clienteView.getTblClientes().setItems(clienteObservableList);
        } catch (Exception e) {
            MessageUtils.showError("Error", "No se pudieron cargar los clientes: " + e.getMessage());
        }
    }

    /**
     * Limpia los campos del formulario de cliente.
     */
    private void limpiarFormulario() {
        clienteView.getTxtIdentificacion().clear();
        clienteView.getTxtNombre().clear();
        clienteView.getTxtDireccion().clear();
        clienteView.getTxtTelefono().clear();
        clienteView.getTxtCorreo().clear();
    }

    /**
     * Maneja el evento para volver al menú principal.
     */
    public void handleVolverMenu() {
        if (appController != null) {
            appController.showMainMenu(); // Navega al menú principal
        } else {
            MessageUtils.showError("Error", "No se pudo navegar al menú principal. El controlador principal no está configurado.");
        }
    }
}
