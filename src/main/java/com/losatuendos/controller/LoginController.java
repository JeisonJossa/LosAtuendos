package com.losatuendos.controller;

import com.losatuendos.service.EmpleadoService;
import com.losatuendos.view.LoginView;
import com.losatuendos.view.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {

    private AppController appController; // Referencia al controlador principal
    private LoginView loginView; // Vista asociada

    @Autowired
    private EmpleadoService empleadoService; // Servicio inyectado por Spring

    /**
     * Establece el AppController.
     * Este método es necesario para evitar la referencia circular.
     *
     * @param appController Controlador principal.
     */
    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    // Establece la vista asociada
    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    // Maneja el evento de login
    public void handleLogin(String usuario, String password) {
        try {
            System.out.println(usuario + " " + password);

            boolean autenticado = empleadoService.autenticarEmpleadoYObtenerRol(usuario, password);
            System.out.println("");
            if (autenticado) {
                MessageUtils.showSuccess("Login Exitoso", "Bienvenido, " + usuario);
                appController.handleSuccessfulLogin(); // Navega al menú principal
            } else {
                MessageUtils.showError("Login Fallido", "Usuario o contraseña incorrectos.");
            }
        } catch (IllegalArgumentException e) {
            // Errores de validación o credenciales incorrectas
            MessageUtils.showError("Login Fallido", e.getMessage());
        } catch (Exception e) {
            // Errores técnicos
            System.out.println(e);
            MessageUtils.showError("Error Técnico", "Ocurrió un error al intentar iniciar sesión. Intente más tarde.");
        }
    }

    // Maneja el evento para recuperación de contraseña
    public void handleForgotPassword() {
        MessageUtils.showWarning("Recuperar Contraseña", "Comuniquese con su administrador.");
    }
}
