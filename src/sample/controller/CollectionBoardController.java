package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import sample.model.DataManager;
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

    @FXML private TreeView treeView;
    @FXML private Accordion accordion;

    private String currentCountryName;
    private String currentContinentName;
    private DataManager dataManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dataManager = new DataManager();

        TreeItem<String> root = new TreeItem<>("Continents");
        root.setExpanded(true);
        treeView.setRoot(root);

        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            TreeItem selectedItem = (TreeItem) newValue;

            if (this.isRootItem(selectedItem)) {
                return;
            }

            if (this.isContinentItem(selectedItem)) {
                currentContinentName = selectedItem.getValue().toString();
                return;
            }

            String countryName = selectedItem.getValue().toString();
            currentCountryName = countryName;
            ServerManager.getCointSets(countryName, this);
        });

        ServerManager.getContinents(this);
    }

    //Callbacks
    @Override
    public void continentsReceived(ArrayList<Continent> continents) {
        dataManager.setContinents(continents);
        currentContinentName = continents.get(0).getName();
        for (Continent continent : continents) {
            TreeItem<String> continentItem = new TreeItem<>(continent.getName());
            treeView.getRoot().getChildren().add(continentItem);
            ServerManager.getCountries(continent.getName(), this);
        }
    }

    @Override
    public void countriesReceived(ArrayList<Country> countries) {
        dataManager.setCountries(countries);
        int continentIndex = dataManager.indexOfContinent(countries.get(0).getContinentId());
        String continentName = dataManager.getContinents().get(continentIndex).getName();
        TreeItem<String> continentItem = (TreeItem) treeView.getRoot().getChildren().get(continentIndex);

        for (Country country : countries) {

            String flagPath = this.flagPath(continentName, country.getName());
            ImageView imageView = new ImageView(flagPath);
            imageView.setFitHeight(20);
            imageView.setFitWidth(30);

            TreeItem<String> countryItem = new TreeItem<>(country.getName(), imageView);
            continentItem.getChildren().add(countryItem);
        }
    }

    @Override
    public void coinSetsReceived(ArrayList<CoinSet> sets) {

        dataManager.setCoinSets(sets);
        accordion.getPanes().removeAll(accordion.getPanes());

        for (CoinSet set : sets) {
            ServerManager.getCoins(currentCountryName, set.getYears(), this);
            TitledPane setPane = new TitledPane();
            setPane.setText(set.getYears());
            setPane.setContent(new ListView());
            accordion.getPanes().add(setPane);
        }
    }

    @Override
    public void coinsReceived(ArrayList<Coin> coins) {

        ObservableList items = FXCollections.observableArrayList();
        for (Coin coin : coins) {
            items.add(coin.getNominal() + " " + coin.getCurrency());
        }

        int paneIndex = dataManager.indexOfCoinSet(coins.get(0).getCoinSetID());
        TitledPane pane = accordion.getPanes().get(paneIndex);
        ListView content = (ListView) pane.getContent();
        content.getItems().addAll(items);
    }

    @Override
    public void errorReceived(String errorMessage) {
        System.out.println(errorMessage);
    }

    //PRIVATE METHODS
    private boolean isContinentItem(TreeItem selectedItem) {
        return treeView.getRoot().getChildren().contains(selectedItem);
    }

    private boolean isRootItem(TreeItem selectedItem) {
        return treeView.getRoot().getValue().toString().equals(selectedItem.getValue().toString());
    }

    private String flagPath(String continentName, String countryName) {
        return  "file:Images/" + continentName + "/" + countryName + "@2x.png";
    }
}