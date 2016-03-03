package sample.model;

import sample.model.object.CoinSet;
import sample.model.object.Continent;
import sample.model.object.Country;

import java.util.ArrayList;

public class DataManager {

    private ArrayList<Continent> continents;
    private ArrayList<Country> countries;
    private ArrayList<CoinSet> coinSets;

    public DataManager() {
        continents = new ArrayList<>();
        countries = new ArrayList<>();
        coinSets = new ArrayList<>();
    }

    public ArrayList<Continent> getContinents() {
        return continents;
    }

    public void setContinents(ArrayList<Continent> continents) {
        this.continents = continents;
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }

    public void setCountries(ArrayList<Country> countries) {
        this.countries = countries;
    }

    public ArrayList<CoinSet> getCoinSets() {
        return coinSets;
    }

    public void setCoinSets(ArrayList<CoinSet> coinSets) {
        this.coinSets = coinSets;
    }

    public int indexOfContinent(int id) {
        for (Continent continent : continents) {
            if (continent.getId() == id) {
                return continents.indexOf(continent);
            }
        }
        return 0;
    }

    public int indexOfCoinSet(int id) {
        for (CoinSet set : coinSets) {
            if (set.getId() == id) {
                return coinSets.indexOf(set);
            }
        }
        return 0;
    }
}
