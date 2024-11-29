package com.losatuendos.controller;

import com.losatuendos.entity.Empleado;
import com.losatuendos.entity.Rol;
import com.losatuendos.service.EmpleadoService;
import com.losatuendos.service.RolService;
import com.losatuendos.view.EmpleadoView;
import com.losatuendos.view.MessageUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmpleadoController {

    private final EmpleadoService empleadoService;
    private final RolService rolService;
    private final AppController appController;
    private EmpleadoView empleadoView;

    private ObservableList<Empleado> empleadoObservableList; // Modelo de datos para la tabla

    @Autowired
    public EmpleadoController(EmpleadoService empleadoService, RolService rolService, AppController appController) {
        this.empleadoService = empleadoService;
        this.rolService = rolService;
        this.appController = appController;
        this.empleadoObservableList = FXCollections.observableArrayList();
    }

    public void setEmpleadoView(EmpleadoView empleadoView) {
        this.empleadoView = empleadoView;
        cargarEmpleadosEnTabla();
    }

    public void handleGuardar() {
        if (!validarCampos()) {
            return;
        }

        try {
            // Buscar el rol basado en el nombre seleccionado en el ComboBox
            String nombreRol = empleadoView.getCbxRol().getValue();
            Rol rol = rolService.obtenerPorNombre(nombreRol);

            if (rol == null) {
                MessageUtils.showError("Error", "El rol seleccionado no es válido.");
                return;
            }

            // Crear empleado
            Empleado empleado = new Empleado();
            empleado.setNumeroIdentificacion(empleadoView.getTxtIdentificacion().getText());
            empleado.setNombre(empleadoView.getTxtNombre().getText());
            empleado.setDireccion(empleadoView.getTxtDireccion().getText());
            empleado.setTelefono(empleadoView.getTxtTelefono().getText());
            empleado.setCorreo(empleadoView.getTxtCorreo().getText());
            empleado.setUsuario(empleadoView.getTxtUsuario().getText());
            empleado.setPassword(empleadoView.getTxtPassword().getText());
            empleado.setIdRol(rol); // Asignar el objeto Rol

            // Guardar empleado
            empleadoService.guardarEmpleado(empleado);
            MessageUtils.showSuccess("Éxito", "Empleado guardado correctamente.");

            limpiarFormulario();
            cargarEmpleadosEnTabla();
        } catch (Exception e) {
            MessageUtils.showError("Error", "No se pudo guardar el empleado: " + e.getMessage());
        }
    }

    public void handleEditar() {
        TableView<Empleado> tblEmpleados = empleadoView.getTblEmpleados();
        Empleado empleadoSeleccionado = tblEmpleados.getSelectionModel().getSelectedItem();

        if (empleadoSeleccionado == null) {
            MessageUtils.showWarning("Advertencia", "Debe seleccionar un empleado para editar.");
            return;
        }

        if (!validarCampos()) {
            return;
        }

        try {
            // Buscar el rol basado en el nombre seleccionado en el ComboBox
            String nombreRol = empleadoView.getCbxRol().getValue();
            Rol rol = rolService.obtenerPorNombre(nombreRol);

            if (rol == null) {
                MessageUtils.showError("Error", "El rol seleccionado no es válido.");
                return;
            }

            // Actualizar empleado
            empleadoSeleccionado.setNumeroIdentificacion(empleadoView.getTxtIdentificacion().getText());
            empleadoSeleccionado.setNombre(empleadoView.getTxtNombre().getText());
            empleadoSeleccionado.setDireccion(empleadoView.getTxtDireccion().getText());
            empleadoSeleccionado.setTelefono(empleadoView.getTxtTelefono().getText());
            empleadoSeleccionado.setCorreo(empleadoView.getTxtCorreo().getText());
            empleadoSeleccionado.setUsuario(empleadoView.getTxtUsuario().getText());
            empleadoSeleccionado.setPassword(empleadoView.getTxtPassword().getText());
            empleadoSeleccionado.setIdRol(rol); // Asignar el objeto Rol

            empleadoService.guardarEmpleado(empleadoSeleccionado);
            MessageUtils.showSuccess("Éxito", "Empleado actualizado correctamente.");

            limpiarFormulario();
            cargarEmpleadosEnTabla();
        } catch (Exception e) {
            MessageUtils.showError("Error", "No se pudo editar el empleado: " + e.getMessage());
        }
    }

    public void handleEliminar() {
        TableView<Empleado> tblEmpleados = empleadoView.getTblEmpleados();
        Empleado empleadoSeleccionado = tblEmpleados.getSelectionModel().getSelectedItem();

        if (empleadoSeleccionado == null) {
            MessageUtils.showWarning("Advertencia", "Debe seleccionar un empleado para eliminar.");
            return;
        }

        try {
            empleadoService.eliminarPorIdentificacion(empleadoSeleccionado.getNumeroIdentificacion());
            MessageUtils.showSuccess("Éxito", "Empleado eliminado correctamente.");

            cargarEmpleadosEnTabla();
        } catch (Exception e) {
            MessageUtils.showError("Error", "No se pudo eliminar el empleado: " + e.getMessage());
        }
    }

    public void handleLimpiar() {
        limpiarFormulario();
    }

    private void cargarEmpleadosEnTabla() {
        try {
            List<Empleado> empleados = empleadoService.obtenerTodos();
            empleadoObservableList.setAll(empleados);
            empleadoView.getTblEmpleados().setItems(empleadoObservableList);
        } catch (Exception e) {
            MessageUtils.showError("Error", "No se pudieron cargar los empleados: " + e.getMessage());
        }
    }

    private void limpiarFormulario() {
        empleadoView.getTxtIdentificacion().clear();
        empleadoView.getTxtNombre().clear();
        empleadoView.getTxtDireccion().clear();
        empleadoView.getTxtTelefono().clear();
        empleadoView.getTxtCorreo().clear();
        empleadoView.getTxtUsuario().clear();
        empleadoView.getTxtPassword().clear();
        empleadoView.getCbxRol().setValue("Empleado"); // Valor predeterminado
    }

    public void handleVolverMenu() {
        appController.showMainMenu();
    }

    private boolean validarCampos() {
        String identificacion = empleadoView.getTxtIdentificacion().getText();
        String nombre = empleadoView.getTxtNombre().getText();
        String direccion = empleadoView.getTxtDireccion().getText();
        String telefono = empleadoView.getTxtTelefono().getText();
        String correo = empleadoView.getTxtCorreo().getText();
        String usuario = empleadoView.getTxtUsuario().getText();
        String password = empleadoView.getTxtPassword().getText();
        String rol = empleadoView.getCbxRol().getValue();

        if (identificacion.isEmpty() || nombre.isEmpty() || direccion.isEmpty() ||
                telefono.isEmpty() || correo.isEmpty() || usuario.isEmpty() || password.isEmpty() || rol == null) {
            MessageUtils.showError("Error", "Todos los campos son obligatorios.");
            return false;
        }

        if (!telefono.matches("\\d+")) {
            MessageUtils.showError("Error", "El teléfono debe contener solo números.");
            return false;
        }

        if (!correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            MessageUtils.showError("Error", "El correo electrónico ingresado no es válido.");
            return false;
        }

        return true;
    }
}
