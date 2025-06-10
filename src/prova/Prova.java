package prova;

import java.net.URL;

import controllers.FXMLDocumentController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.dao.IUsuarioDAO;
import model.dao.UsuarioDAO;
import validator.IUsuarioValidator;
import validator.UsuarioValidator;

public class Prova extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 1. Instancie as dependências concretas
        IUsuarioDAO usuarioDAO = new UsuarioDAO(); // Cria o DAO concreto
        IUsuarioValidator usuarioValidator = new UsuarioValidator(); // Cria o validador concreto

        // 2. Configure o FXMLLoader com uma ControllerFactory
        FXMLLoader loader = new FXMLLoader();
        URL fxmlLocation = getClass().getResource("/view/FXMLDocument.fxml"); // Ajuste o caminho do seu FXML
        if (fxmlLocation == null) {
            System.err.println("Erro: FXMLDocument.fxml não encontrado. Verifique o caminho.");
            return;
        }
        loader.setLocation(fxmlLocation);

        loader.setControllerFactory(controllerClass -> {
            if (controllerClass == FXMLDocumentController.class) {
                // Retorna uma nova instância do seu controller, injetando as dependências
                return new FXMLDocumentController(usuarioValidator);
            }
            try {
                return controllerClass.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        Parent root = loader.load(); // Carrega a view, usando a fábrica para criar o controller

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cadastro");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}