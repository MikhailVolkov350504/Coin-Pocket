package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import sample.model.ServerManager;

import javafx.event.Event;
import sample.model.network.callback.SignInCallback;

import java.net.URL;
import java.util.ResourceBundle;

public class SignInController implements SignInCallback, Initializable {

    public TextField emailField;
    public PasswordField passwordField;
    public Text infoMessageText;
    public Button signInButton;

    @FXML
    private Button signUpButton;

    //Action handlers
    public void handleSignInButtonAction(Event actionEvent) {
        String email = emailField.getText();
        String password = passwordField.getText();
        ServerManager.signIn(email, password, this);
        signInButton.setDisable(true);
        signUpButton.setDisable(true);

    }

    public void handleSignUpButtonAction(Event actionEvent) {
        Stage window = (Stage)signInButton.getScene().getWindow();
        this.showSignUpScene(window);
    }

    //Request callback methods
    @Override
    public void signInSucceed(String email, String token) {
        Stage window = (Stage) signInButton.getScene().getWindow();
        showCollectionBoardScene(window);
        this.setInfoMessage(false, email + " " + token);
    }

    @Override
    public void singInFailed(String message) {
        this.setInfoMessage(true, message);
        signInButton.setDisable(false);
        signUpButton.setDisable(false);
    }

    @Override
    public void errorReceived(String errorMessage) {
        this.setInfoMessage(true, errorMessage);
        signInButton.setDisable(false);
        signUpButton.setDisable(false);
    }

    //Navigation
    private void showSignUpScene (Stage window) {
        String title = "Sign up";
        String fxmlPath = "/sample/resources/SignUpView.fxml";
        this.showScene(window, fxmlPath, title, 400, 300);
    }

    private void showScene(Stage window, String resourcePath, String title, int width, int height) {
        try {
            Parent scene = FXMLLoader.load(getClass().getResource(resourcePath));
            window.setScene(new Scene(scene, width, height));
            window.setTitle(title);
            window.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Private methods
    private void setInfoMessage(boolean error, String message) {
        infoMessageText.setFill(error ? Color.FIREBRICK : Color.GREEN);
        infoMessageText.setText(message);
    }

    private void showCollectionBoardScene (Stage window) {
        String title = "Collection";
        String fxmlPath = "/sample/resources/CollectionBoardView.fxml";
        this.showScene(window, fxmlPath, title, 800, 600);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        emailField.setText("volchik@gmail.com");
        passwordField.setText("12345678");
    }
}
