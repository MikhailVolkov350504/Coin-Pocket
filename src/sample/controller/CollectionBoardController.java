package sample.controller;

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

//    public ListView continentListView;
//    public ListView countryListView;
//    public ListView coinSetListView;
//    public ListView coinListView;
    public ArrayList<String> coinSetList;
    public ArrayList<String> countriesList;
    public TreeItem<String> currentContinent;
    public int continentsIndex = 0;

    @FXML
    private TreeView countryTree;
    @FXML
    private Accordion countryTable;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        countriesList = new ArrayList<String>();
        countryTree.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> System.out.println("selected text " + newValue)));
        ServerManager.getContinents(this);
        setContinents();
        countryTable.getPanes().removeAll();
//        for(int i = 0;i< 6;i++) {
//            TitledPane tp = new TitledPane();
//            tp.setText("" + i);
//            tp.setContent(new TextArea("abc"));
//
//            countryTable.getPanes().add(tp);
//
//        }
//
//        TitledPane tp = new TitledPane("1234", new Button("Button"));
//        TitledPane tp2 = new TitledPane("5678", new Button("Button2"));
//        sectionBox.getChildren().addAll(tp,tp2);
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
//            continentListView.getItems().addAll(continent.getName());
//            addContinents(continent.getName(),countryTree.getRoot());
            System.out.println(continent.getName());
        }

    }

    @Override
    public void countriesReceived(ArrayList<Country> countries) {
        for (Country country:countries) {
            countriesList.add(country.getName());
        }
        addCountries();
    }

    @Override
    public void coinSetsReceived(ArrayList<CoinSet> sets) {
//        for (CoinSet set:sets)
//            coinSetListView.getItems().addAll(set.getYears());
    }

    @Override
    public void coinsReceived(ArrayList<Coin> coins) {
//        for (Coin coin:coins)
//            coinListView.getItems().addAll(coin.getNominal() + " " + coin.getCurrency());
    }

    @Override
    public void errorReceived(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void setContinents(){
        //create root
        TreeItem<String> root = new TreeItem<>("Continents");
        root.setExpanded(true);
        TreeItem<String> asia = new TreeItem<>("Asia");
        TreeItem<String> africa = new TreeItem<>("Africa");
        TreeItem<String> australia = new TreeItem<>("Australia");
        TreeItem<String> europe = new TreeItem<>("Europe");
        TreeItem<String> latAmerica = new TreeItem<>("L.America");
        TreeItem<String> northAmerica = new TreeItem<>("N.America");
        root.getChildren().addAll(asia, africa, australia, europe, latAmerica, northAmerica);
        countryTree.setRoot(root);
        getCountriesFromServer(root.getChildren());
    }




    public void getCountriesFromServer(ObservableList<TreeItem<String>> continentsList){
//        for(int i = 0; i < continentsList.size(); i++){
            String continent = continentsList.get(continentsIndex).getValue();
            switch (continent){
                case "Asia": currentContinent = continentsList.get(continentsIndex);
                    ServerManager.getCountries(continent, this);
                    break;
                case "Africa": currentContinent = continentsList.get(continentsIndex);
                    ServerManager.getCountries(continent, this);
                    break;
                case "Europe": currentContinent = continentsList.get(continentsIndex);
                    ServerManager.getCountries(continent, this);
                    break;
                case "L.America": currentContinent = continentsList.get(continentsIndex);
                    ServerManager.getCountries(continent, this);
                    break;
                case "N.America": currentContinent = continentsList.get(continentsIndex);
                    ServerManager.getCountries(continent, this);
                    break;
                case "Australia": currentContinent = continentsList.get(continentsIndex);
                    ServerManager.getCountries(continent, this);
            }
//        }
    }

    public void addCountries(){
        for(int i = 0; i < countriesList.size();i++){
            TreeItem<String> treeItem = new TreeItem<>(countriesList.get(i));
            currentContinent.getChildren().add(treeItem);
        }
        countriesList.clear();
        continentsIndex = continentsIndex + 1;
        if(continentsIndex < countryTree.getRoot().getChildren().size())
        getCountriesFromServer(countryTree.getRoot().getChildren());

    }

    public void showCoinsSets(){

    }
}

