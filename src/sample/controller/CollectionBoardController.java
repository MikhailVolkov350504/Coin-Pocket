package sample.controller;

import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
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

    public ListView continentListView;
    public ListView countryListView;
    public ListView coinSetListView;
    public ListView coinListView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ServerManager.getContinents(this);
    }

    //Actions
    public void continentViewTouched(Event event) {

        countryListView.getItems().clear();
        coinSetListView.getItems().clear();
        coinListView.getItems().clear();

        String continentName = (String)continentListView.getSelectionModel().getSelectedItem();
        ServerManager.getCountries(continentName, this);
    }

    public void countryViewTouched(Event event) {

        coinSetListView.getItems().clear();
        coinListView.getItems().clear();

        String countryName = (String)countryListView.getSelectionModel().getSelectedItem();
        ServerManager.getCointSets(countryName, this);
    }

    public void coinSetViewTouched(Event event) {

        coinListView.getItems().clear();

        String countryName = (String)countryListView.getSelectionModel().getSelectedItem();
        String years = (String)coinSetListView.getSelectionModel().getSelectedItem();
        ServerManager.getCoins(countryName, years, this);
    }

    public void coinViewTouched(Event event) {
        System.out.println(coinListView.getSelectionModel().getSelectedItem());
    }

    //Callbacks
    @Override
    public void continentsReceived(ArrayList<Continent> continents) {
        for (Continent continent:continents)
            continentListView.getItems().addAll(continent.getName());
    }

    @Override
    public void countriesReceived(ArrayList<Country> countries) {
        for (Country country:countries)
            countryListView.getItems().addAll(country.getName());
    }

    @Override
    public void coinSetsReceived(ArrayList<CoinSet> sets) {
        for (CoinSet set:sets)
            coinSetListView.getItems().addAll(set.getYears());
    }

    @Override
    public void coinsReceived(ArrayList<Coin> coins) {
        for (Coin coin:coins)
            coinListView.getItems().addAll(coin.getNominal() + " " + coin.getCurrency());
    }

    @Override
    public void errorReceived(String errorMessage) {
        System.out.println(errorMessage);
    }
}
