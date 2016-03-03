package sample.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.model.ScreenManager;
import sample.model.ServerManager;
import sample.model.network.callback.SignUpCallback;

import java.io.IOException;

public class SignUpController implements SignUpCallback {

    @FXML private Text infoMessageText;
    @FXML private PasswordField passwordField;
    @FXML private TextField emailField;
    @FXML private Button signUpButton;
    @FXML private Button goBackButton;

    public void handleSignUpButtonAction(Event event) {
        String email = emailField.getText();
        String password = passwordField.getText();
        ServerManager.signUp(email, password, this);
        signUpButton.setDisable(true);
        goBackButton.setDisable(true);
    }

    public void handleGoBackButtonAction(Event event) {
        Stage window = (Stage) signUpButton.getScene().getWindow();
        this.showSignInScene(window);
    }

    //Request callback methods
    @Override
    public void signUpSucceed(String email) {
        Stage window = (Stage) signUpButton.getScene().getWindow();
        this.showSignInScene(window);
    }

    @Override
    public void singUpFailed(String message) {
        this.setInfoMessage(true, message);
        signUpButton.setDisable(false);
        goBackButton.setDisable(false);
    }

    @Override
    public void errorReceived(String errorMessage) {
        this.setInfoMessage(true, errorMessage);
        signUpButton.setDisable(false);
        goBackButton.setDisable(false);
    }

    //Private methods
    private void setInfoMessage(boolean error, String message) {
        infoMessageText.setFill(error ? Color.FIREBRICK : Color.GREEN);
        infoMessageText.setText(message);
    }

    private void showSignInScene(Stage window) {
        String title = "Sign in";
        String fxmlPath =  "/sample/resources/SignInView.fxml";
        ScreenManager.getInstance().showScene(window, fxmlPath, title, 400, 300);
    }
}
