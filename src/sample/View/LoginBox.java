package sample.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class LoginBox extends Pane implements EventHandler<ActionEvent> {

    static final int inset = 10;

    private LoginBoxCallback callback;

    private Text infoText;
    private Text headerText;
    private Label emailLabel;
    private Label passwordLabel;
    private TextField emailField;
    private PasswordField passwordField;
    private Button actionButton;
    private GridPane grid;

    public LoginBox(String title, String buttonTitle) {

        infoText = new Text();
        HBox infoTextBox = new HBox(infoText);
        infoTextBox.setAlignment(Pos.CENTER);

        headerText = new Text(title);
        headerText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        emailLabel = new Label("Email:");
        passwordLabel = new Label("Password:");
        emailField = new TextField();
        emailField.setPromptText("email");
        passwordField = new PasswordField();
        passwordField.setPromptText("password");

        actionButton = new Button();
        actionButton.setText(buttonTitle);
        actionButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        actionButton.setOnAction(this);

        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(inset);
        grid.setVgap(inset);

        grid.add(headerText, 0, 0, 2, 1);
        grid.add(emailLabel, 0, 1);
        grid.add(passwordLabel, 0, 2);
        grid.add(emailField, 1, 1);
        grid.add(passwordField, 1, 2);
        grid.add(actionButton, 0, 3, 2, 1);
        grid.add(infoTextBox, 0, 4, 2, 1);
        GridPane.setFillWidth(actionButton, true);

        this.getChildren().add(0, grid);
    }

    public void setInfoMessage(boolean error, String message) {
        infoText.setFill(error ? Color.FIREBRICK : Color.GREEN);
        infoText.setText(message);
    }

    public void setCallback(LoginBoxCallback callback) {
        this.callback = callback;
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == actionButton) {
            if (callback != null) {
                String email = emailField.getText();
                String password = passwordField.getText();
                callback.callback(email, password);
            }
        }
    }
}
