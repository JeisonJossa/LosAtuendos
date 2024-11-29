package com.losatuendos.view;

import com.losatuendos.controller.LoginController; // Importa el controlador correspondiente
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginView extends BaseView {

    private TextField txtUsuario;
    private PasswordField txtPassword;
    private Button btnLogin;
    private Hyperlink linkForgotPassword;

    // Controlador asociado
    private LoginController loginController;

    // Constructor que acepta un Stage y un controlador
    public LoginView(Stage stage, LoginController loginController) {
        super(stage);
        this.loginController = loginController;
        setTitle("Login - Los Atuendos");
        initializeComponents();
        setupEventHandlers(); // Configura los eventos con el controlador
    }

    @Override
    protected void initializeComponents() {
        // Título de la pantalla
        Text lblTitle = new Text("Login");
        lblTitle.setFont(Font.font(24));
        lblTitle.setStyle("-fx-fill: " + StyleManager.SECONDARY_COLOR + ";");

        // Campo de texto para usuario
        txtUsuario = new TextField();
        txtUsuario.setPromptText("Usuario");
        txtUsuario.setMaxWidth(250);
        StyleManager.applyDefaultStyle(txtUsuario);

        // Campo de texto para contraseña
        txtPassword = new PasswordField();
        txtPassword.setPromptText("Contraseña");
        txtPassword.setMaxWidth(250);
        StyleManager.applyDefaultStyle(txtPassword);

        // Botón de iniciar sesión
        btnLogin = new Button("Iniciar Sesión");
        StyleManager.applyPrimaryButtonStyle(btnLogin);

        // Enlace para "¿Olvidaste tu contraseña?"
        linkForgotPassword = new Hyperlink("¿Olvidaste tu contraseña?");
        linkForgotPassword.setStyle("-fx-text-fill: " + StyleManager.SECONDARY_COLOR + ";" +
                "-fx-font-size: " + StyleManager.FONT_SIZE_NORMAL + ";");

        // Contenedor vertical para los elementos
        VBox vbox = new VBox(10, lblTitle, txtUsuario, txtPassword, linkForgotPassword, btnLogin);
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color: " + StyleManager.PRIMARY_COLOR + ";" +
                "-fx-padding: 40; -fx-spacing: 15; -fx-alignment: center;");

        root.setCenter(vbox);
    }

    @Override
    protected void setupEventHandlers() {
        // Configura eventos delegando la lógica al controlador
        btnLogin.setOnAction(event -> loginController.handleLogin(txtUsuario.getText(), txtPassword.getText()));
        linkForgotPassword.setOnAction(event -> loginController.handleForgotPassword());
    }

    // Getters para el controlador
    public TextField getTxtUsuario() {
        return txtUsuario;
    }

    public PasswordField getTxtPassword() {
        return txtPassword;
    }

    public Button getBtnLogin() {
        return btnLogin;
    }

    public Hyperlink getLinkForgotPassword() {
        return linkForgotPassword;
    }
}
