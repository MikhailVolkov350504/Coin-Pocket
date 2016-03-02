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
            System.out.println(coin.getNominal() + coin.getCurrency());
        }
    }

    @Override
    public void errorReceived(String errorMessage) {
        System.out.println(errorMessage);
    }
}

