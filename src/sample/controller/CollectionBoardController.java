package sample.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import sample.model.ServerManager;
import sample.model.network.callback.CoinSetsCallback;
import sample.model.network.callback.CoinsCallback;
import sample.model.network.callback.ContinentsCallback;
import sample.model.network.callback.CountriesCallback;
import sample.model.object.Coin;
import sample.model.object.CoinSet;
import sample.model.object.Continent;
import sample.model.object.Country;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CollectionBoardController implements
        Initializable,
        ContinentsCallback,
        CountriesCallback,
        CoinSetsCallback,
        CoinsCallback {

    private int continentsIndex = 0;

    @FXML
    private TreeView countryTree;
    @FXML
    private Accordion countryTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        countryTree.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> System.out.println("selected text " + newValue)));

        TreeItem<String> root = new TreeItem<>("Continents");
        root.setExpanded(true);
        countryTree.setRoot(root);

        ServerManager.getContinents(this);
//        countryTable.getPanes().removeAll();
//        for(int i = 0;i< 6;i++) {
//            TitledPane tp = new TitledPane();
//            tp.setText("" + i);
//            tp.setContent(new TextArea("abc"));
//
//            countryTable.getPanes().add(tp);
//
//        }
    }

    //Actions
//    public void continentViewTouched(Event event) {
//
//        countryListView.getItems().clear();
//        coinSetListView.getItems().clear();
//        coinListView.getItems().clear();
//
//        String continentName = (String)continentListView.getSelectionModel().getSelectedItem();
//        ServerManager.getCountries(continentName, this);
//    }
//
//    public void countryViewTouched(Event event) {
//
//        coinSetListView.getItems().clear();
//        coinListView.getItems().clear();
//
//        String countryName = (String)countryListView.getSelectionModel().getSelectedItem();
//        ServerManager.getCointSets(countryName, this);
//    }
//
//    public void coinSetViewTouched(Event event) {
//
//        coinListView.getItems().clear();
//
//        String countryName = (String)countryListView.getSelectionModel().getSelectedItem();
//        String years = (String)coinSetListView.getSelectionModel().getSelectedItem();
//        ServerManager.getCoins(countryName, years, this);
//    }
//
//    public void coinViewTouched(Event event) {
//        System.out.println(coinListView.getSelectionModel().getSelectedItem());
//    }

    //Callbacks
    @Override
    public void continentsReceived(ArrayList<Continent> continents) {
        for (Continent continent:continents) {
            TreeItem<String> continentItem = new TreeItem<>(continent.getName());
            countryTree.getRoot().getChildren().add(continentItem);
            ServerManager.getCountries(continent.getName(), this);
        }
    }

    @Override
    public void countriesReceived(ArrayList<Country> countries) {
        TreeItem<String> continentItem = (TreeItem)countryTree.getRoot().getChildren().get(continentsIndex++);
        for (Country country:countries) {
            TreeItem<String> countryItem = new TreeItem<>(country.getName());

            EventHandler<MouseEvent> mouseEventHandle = (MouseEvent event) -> {
                ServerManager.getCointSets(country.getName(), this);
            };

            countryItem.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEventHandle);
            continentItem.getChildren().add(countryItem);
        }
    }

    @Override
    public void coinSetsReceived(ArrayList<CoinSet> sets) {
        for (CoinSet set:sets) {
            System.out.println(set.getYears());
        }
    }

    @Override
    public void coinsReceived(ArrayList<Coin> coins) {
        for (Coin coin:coins) {

        }
    }

    @Override
    public void errorReceived(String errorMessage) {
        System.out.println(errorMessage);
    }
}

