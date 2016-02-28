package sample.controller;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.model.ServerManager;
import sample.model.network.callback.SignUpCallback;

import java.io.IOException;

public class SignUpController implements SignUpCallback {

    public Text infoMessageText;
    public PasswordField passwordField;
    public TextField emailField;
    public Button signUpButton;

    public void handleSignUpButtonAction(Event event) {
        String email = emailField.getText();
        String password = passwordField.getText();
        ServerManager.signUp(email, password, this);
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
    }

    @Override
    public void errorReceived(String errorMessage) {
        this.setInfoMessage(true, errorMessage);
    }

    //Private methods
    private void setInfoMessage(boolean error, String message) {
        infoMessageText.setFill(error ? Color.FIREBRICK : Color.GREEN);
        infoMessageText.setText(message);
    }

    private void showSignInScene (Stage window) {
        try {
            Parent signUpView = FXMLLoader.load(getClass().getResource("/sample/resources/SignInView.fxml"));
            window.setScene(new Scene(signUpView, 400, 300));
            window.setTitle("Sign in");
            window.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
