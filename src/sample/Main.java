package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));

        Parent signInView = FXMLLoader.load(getClass().getResource("resources/SignInView.fxml"));
        primaryStage.setTitle("Sign in");
        primaryStage.setScene(new Scene(signInView, 400, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}