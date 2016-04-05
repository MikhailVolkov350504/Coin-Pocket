package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import sample.model.ScreenManager;
import sample.model.ServerManager;

import javafx.event.Event;
import sample.model.network.callback.SignInCallback;

import java.net.URL;
import java.util.ResourceBundle;

public class SignInController implements SignInCallback, Initializable {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Text infoMessageText;
    @FXML private Button signInButton;
    @FXML private Button signUpButton;
    @FXML private ProgressIndicator activityIndicator;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        emailField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            infoMessageText.setText("");
        });

        passwordField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            infoMessageText.setText("");
        });
        emailField.setText("volchik@gmail.com");
        passwordField.setText("12345678");
    }

    //Action handlers
    public void handleSignInButtonAction(Event actionEvent) {
        disableUI();
        String email = emailField.getText();
        String password = passwordField.getText();
        ServerManager.signIn(email, password, this);
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
        enableUI(message);
    }

    @Override
    public void errorReceived(String errorMessage) {
        enableUI(errorMessage);
    }

    //Private methods
    private void setInfoMessage(boolean error, String message) {
        infoMessageText.setFill(error ? Color.FIREBRICK : Color.GREEN);
        infoMessageText.setText(message);
    }

    private void enableUI(String message) {
        this.setInfoMessage(true, message);
        activityIndicator.setVisible(false);
        signInButton.setVisible(true);
        signUpButton.setVisible(true);
        emailField.setEditable(true);
        passwordField.setEditable(true);
    }

    private void disableUI() {
        activityIndicator.setVisible(true);
        signInButton.setVisible(false);
        signUpButton.setVisible(false);
        emailField.setEditable(false);
        passwordField.setEditable(false);
    }

    //Navigation
    private void showSignUpScene (Stage window) {
        String title = "Sign up";
        String fxmlPath = "/sample/resources/SignUpView.fxml";
        ScreenManager.getInstance().showScene(window, fxmlPath, title, 400, 300);
    }

    private void showCollectionBoardScene (Stage window) {
        String title = "Collection";
        String fxmlPath = "/sample/resources/CollectionBoardView.fxml";
        ScreenManager.getInstance().showScene(window, fxmlPath, title, 1000, 800);
    }
}
