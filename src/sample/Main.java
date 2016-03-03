package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.model.ScreenManager;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));

        String fxmlPath = "/sample/resources/SignInView.fxml";
        ScreenManager.getInstance().showScene(primaryStage, fxmlPath, "Sign in", 400, 300);
    }

    public static void main(String[] args) {
        launch(args);
    }
}