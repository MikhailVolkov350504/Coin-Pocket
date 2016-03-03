package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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



    @FXML
    private TreeView treeView;
    @FXML
    private Accordion accordion;

    private ArrayList<Continent> continents;
    private ArrayList<Country> countries;
    private ArrayList<CoinSet> coinSets;

    private String currentCountryName;
    private String currentContinentName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TreeItem<String> root = new TreeItem<>("Continents");
        root.setExpanded(true);
        treeView.setRoot(root);

        treeView.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    checkContinents((TreeItem) newValue);
                });

        ServerManager.getContinents(this);
    }

    //Callbacks
    @Override
    public void continentsReceived(ArrayList<Continent> continents) {
        this.continents = continents;
        for (Continent continent : continents) {
            TreeItem<String> continentItem = new TreeItem<>(continent.getName());
            treeView.getRoot().getChildren().add(continentItem);
            ServerManager.getCountries(continent.getName(), this);
        }
    }

    @Override
    public void countriesReceived(ArrayList<Country> countries) {
        this.countries = countries;
        int itemIndex = this.indexOfContinent(countries.get(0).getContinentId());

        TreeItem<String> continentItem = (TreeItem) treeView.getRoot().getChildren().get(itemIndex);
        for (Country country : countries) {
            TreeItem<String> countryItem = new TreeItem<>(country.getName());
            continentItem.getChildren().add(countryItem);
        }
    }

    @Override
    public void coinSetsReceived(ArrayList<CoinSet> sets) {

        this.coinSets = sets;
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

        int paneIndex = this.indexOfCoinSet(coins.get(0).getCoinSetID());
        TitledPane pane = accordion.getPanes().get(paneIndex);
        ListView content = (ListView) pane.getContent();
        content.getItems().addAll(items);
    }

    @Override
    public void errorReceived(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void checkContinents(TreeItem selectedItem) {

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
    }

    public boolean isContinentItem(TreeItem selectedItem) {
        return treeView.getRoot().getChildren().contains(selectedItem);
    }

    public boolean isRootItem(TreeItem selectedItem) {
        return treeView.getRoot().getValue().toString().equals(selectedItem.getValue().toString());
    }

    private int indexOfContinent(int id) {
        for (Continent continent : continents) {
            if (continent.getId() == id) {
                return continents.indexOf(continent);
            }
        }
        return 0;
    }

    private int indexOfCountry(int id) {
        for (Country country : countries) {
            if (country.getId() == id) {
                return countries.indexOf(country);
            }
        }
        return 0;
    }

    private int indexOfCoinSet(int id) {
        for (CoinSet set : coinSets) {
            if (set.getId() == id) {
                return coinSets.indexOf(set);
            }
        }
        return 0;
    }
}
