package sample.controller;

import com.google.gson.JsonObject;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import sample.model.Constants;
import sample.model.ServerManager;
import sample.model.network.RequestCallback;

import javafx.event.Event;

import java.io.IOException;

public class SignInController implements RequestCallback {

    public TextField emailField;
    public PasswordField passwordField;
    public Text infoMessageText;
    public Button signInButton;

    //Action handlers
    public void handleSignInButtonAction(Event actionEvent) {
        String email = emailField.getText();
        String password = passwordField.getText();
        ServerManager.signIn(email, password, this);
    }

    public void handleSignUpButtonAction(Event actionEvent) {
        try {
            Stage window = (Stage)signInButton.getScene().getWindow();
            this.showSignUpScene(window);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Request callback methods
    @Override
    public void success(JsonObject json) {
        Boolean success = json.get(Constants.SUCCESS).getAsBoolean();
        String message = json.get(success ? Constants.EMAIL : Constants.ERROR_DESCRIPTION).getAsString();
        Platform.runLater(() -> this.setInfoMessage(!success, message));
    }

    @Override
    public void failure(String errorMessage) {
        this.setInfoMessage(true, errorMessage);
    }

    //Private methods
    private void setInfoMessage(boolean error, String message) {
        infoMessageText.setFill(error ? Color.FIREBRICK : Color.GREEN);
        infoMessageText.setText(message);
    }

    private void showSignUpScene (Stage window) throws IOException {
        Parent signUpView = FXMLLoader.load(getClass().getResource("/sample/resources/SignUpView.fxml"));
        window.setScene(new Scene(signUpView, 400, 300));
        window.setTitle("Sign up");
        window.show();
    }
}
