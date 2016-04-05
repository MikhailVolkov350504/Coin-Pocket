package sample.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ScreenManager {

    private static ScreenManager instance = null;

    private ScreenManager() {

    }

    public static ScreenManager getInstance( ) {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }

    public void showScene (Stage window, String path, String title, int width, int height) {
        try {
            Parent view = FXMLLoader.load(getClass().getResource(path));
            window.setScene(new Scene(view, width, height));
            window.setTitle(title);
            window.centerOnScreen();
            window.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
