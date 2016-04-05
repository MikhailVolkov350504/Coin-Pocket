package sample.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.model.ScreenManager;
import sample.model.ServerManager;
import sample.model.network.callback.SignUpCallback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements SignUpCallback, Initializable {

    @FXML private Text infoMessageText;
    @FXML private PasswordField passwordField;
    @FXML private TextField emailField;
    @FXML private Button signUpButton;
    @FXML private Button goBackButton;
    @FXML private ProgressIndicator activityIndicator;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        emailField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            infoMessageText.setText("");
        });

        passwordField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            infoMessageText.setText("");
        });
    }

    public void handleSignUpButtonAction(Event event) {
        String email = emailField.getText();
        String password = passwordField.getText();
        ServerManager.signUp(email, password, this);
        disableUI();
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
        enableUI(message);
    }

    @Override
    public void errorReceived(String errorMessage) {
        enableUI(errorMessage);
    }

    //Private methods

    private void enableUI(String message) {
        this.setInfoMessage(true, message);
        activityIndicator.setVisible(false);
        goBackButton.setVisible(true);
        signUpButton.setVisible(true);
        emailField.setEditable(true);
        passwordField.setEditable(true);
    }

    private void disableUI() {
        activityIndicator.setVisible(true);
        goBackButton.setVisible(false);
        signUpButton.setVisible(false);
        emailField.setEditable(false);
        passwordField.setEditable(false);
    }

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
