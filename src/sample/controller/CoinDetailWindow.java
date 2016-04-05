package sample.controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradientBuilder;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import sample.model.object.Coin;

import javax.swing.border.Border;

public class CoinDetailWindow {

    private static Stage window = new Stage();

    public static void show(Coin coin, String continentName, String countryName) {
        if (window.isShowing())
            window.close();

        window = new Stage();

        String imagePath = "file:Images/" + continentName + "/" + countryName + "@2x.png";;
        ImageView imageView = new ImageView(imagePath);
        imageView.setFitHeight(50);
        imageView.setFitWidth(70);
        HBox hBox = new HBox();
        hBox.getChildren().add(imageView);
        hBox.setAlignment(Pos.CENTER);

        CheckBox checkBox = new CheckBox();
        checkBox.setSelected(true);
        checkBox.setText("is in collection");

        Button closeButton = new Button();
        closeButton.setText("Close");
        closeButton.setOnAction(event -> {
            window.close();
        });

        Label currencyLabel = new Label();
        currencyLabel.setText("Currency:");
        Label currencyValueLabel = new Label();
        currencyValueLabel.setText(coin.getCurrency());

        Label nominalLabel = new Label();
        nominalLabel.setText("Nominal:");
        Label nominalValueLabel = new Label();
        nominalValueLabel.setText(coin.getNominal());

        Label locationLabel = new Label();
        locationLabel.setText("Continent, country:");
        Label locationValueLabel = new Label();
        locationValueLabel.setText(continentName + ", " + countryName);

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setStyle("-fx-background-color: gray");
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(10);
        pane.setHgap(10);
        pane.add(hBox, 0, 0, 2, 1);
        pane.add(currencyLabel, 0, 1);
        pane.add(currencyValueLabel, 1, 1);
        pane.add(nominalLabel,0,2);
        pane.add(nominalValueLabel,1,2);
        pane.add(locationLabel,0,3);
        pane.add(locationValueLabel,1,3);
        pane.add(checkBox, 0, 4, 2, 1);
        pane.add(closeButton, 0, 5, 2, 1);

        BorderPane scene = new BorderPane();
        scene.setCenter(pane);

        window.setScene(new Scene(scene, 300, 300));
        window.setTitle("Coin info");
        window.setResizable(false);
        window.centerOnScreen();
        window.show();
    }
}
