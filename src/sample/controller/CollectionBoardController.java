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

    private int continentsIndex = 0;

    @FXML
    private TreeView countryTree;
    @FXML
    private Accordion countryTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        TreeItem<String> root = new TreeItem<>("Continents");
        root.setExpanded(true);
        countryTree.setRoot(root);

        countryTree.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    checkContinents((TreeItem) newValue);
                });

        ServerManager.getContinents(this);



        countryTable.getPanes().removeAll();
        for(int i = 0;i< 6;i++) {
            ListView<String> list = new ListView<String>();
            ObservableList<String> items = FXCollections.observableArrayList(
                    "Single", "Double", "Suite", "Family App");
            list.setItems(items);
            TitledPane tp = new TitledPane();
            tp.setText("" + i);
            tp.setContent(list);

            countryTable.getPanes().add(tp);

        }
    }

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

        int itemIndex = countries.get(0).getContinentId() - 1;
        TreeItem<String> continentItem = (TreeItem)countryTree.getRoot().getChildren().get(itemIndex);
        for (Country country:countries) {
            TreeItem<String> countryItem = new TreeItem<>(country.getName());

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
            System.out.println(coin.getNominal() + coin.getCurrency());
        }
    }

    @Override
    public void errorReceived(String errorMessage) {
        System.out.println(errorMessage);
    }

    public  void checkContinents(TreeItem selectedItem){


        System.out.println(countryTree.getRoot().getValue().toString().equals(selectedItem.getValue().toString()));
        if(!(countryTree.getRoot().getChildren().contains(selectedItem)) || (countryTree.getRoot().getValue().toString().equals(selectedItem.getValue().toString()))){
            Country country = new Country(selectedItem.getValue().toString());
            System.out.println(country.getName());
            ServerManager.getCointSets(country.getName(), this);
            System.out.println(country.getName());
//            System.out.println(selectedItem.getValue());

        }
    }
}

