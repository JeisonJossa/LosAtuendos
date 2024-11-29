package com.losatuendos.app;

import com.losatuendos.controller.AppController;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.losatuendos.entity")
@ComponentScan(basePackages = {"com.losatuendos"})
@EnableJpaRepositories(basePackages = {"com.losatuendos.repository"})
public class LosAtuendosApplication extends Application {

	private static ApplicationContext springContext;

	public static void main(String[] args) {
		try {
			// Inicializa el contexto de Spring
			Thread springInitThread = new Thread(() -> {
				springContext = new SpringApplicationBuilder(LosAtuendosApplication.class)
						.headless(false) // Necesario para JavaFX
						.properties("spring.jmx.enabled=false") // Deshabilita JMX
						.run(args);
			});

			springInitThread.setDaemon(true);
			springInitThread.start();

			// Lanza la aplicación JavaFX
			launch(args);
		} catch (Exception e) {
			System.err.println("Error al iniciar la aplicación: " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			// Espera hasta que Spring esté completamente inicializado
			while (springContext == null) {
				System.out.println("Esperando a que Spring se inicialice...");
				Thread.sleep(100);
			}

			// Obtiene el controlador principal de Spring
			AppController appController = springContext.getBean(AppController.class);

			// Configura y lanza la interfaz gráfica
			appController.setStage(primaryStage);
			appController.start();
		} catch (Exception e) {
			System.err.println("Error durante el inicio de la aplicación: " + e.getMessage());
			e.printStackTrace();
			javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("No se pudo iniciar la aplicación");
			alert.setContentText("Detalles: " + e.getMessage());
			alert.showAndWait();
			System.exit(1);
		}
	}
}
