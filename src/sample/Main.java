package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.View.LoginBox;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        LoginBox signUpBox = new LoginBox("Registration", "Sign up");
        signUpBox.setCallback((email, password) -> {
            signUpBox.setInfoMessage(false, email + " " +  password);
        });

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.add(signUpBox, 0, 0);

        primaryStage.setTitle("Sign up");
        primaryStage.setScene(new Scene(grid, 300, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
