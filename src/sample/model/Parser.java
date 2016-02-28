package sample.model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import sample.model.object.Coin;
import sample.model.object.CoinSet;
import sample.model.object.Continent;
import sample.model.object.Country;

import java.util.ArrayList;

public class Parser {

    public static ArrayList<Continent> continentsFromJSON(JsonObject json) {

        String[] names = new Gson().fromJson(json.get(Constants.CONTINENT_NAMES), String[].class);
        ArrayList<Continent> continets = new ArrayList<Continent>();

        for (String name:names) {
            continets.add(new Continent(name));
        }

        return continets;
    }

    public static ArrayList<Country> countriesFromJSON(JsonObject json) {

        String[] names = new Gson().fromJson(json.get(Constants.COUNTRY_NAMES), String[].class);
        ArrayList<Country> countries = new ArrayList<Country>();

        for (String name:names) {
            countries.add(new Country(name));
        }

        return countries;
    }

    public static ArrayList<CoinSet> coinSetsFromJSON(JsonObject json) {

        String[] years = new Gson().fromJson(json.get(Constants.COIN_SETS), String[].class);
        ArrayList<CoinSet> coinSets = new ArrayList<CoinSet>();

        for (String year:years) {
            coinSets.add(new CoinSet(year));
        }

        return coinSets;
    }

    public static ArrayList<Coin> coinsFromJSON(JsonObject json) {

        Coin[] coins = new Gson().fromJson(json.get(Constants.COINS), Coin[].class);
        ArrayList<Coin> result = new ArrayList<Coin>();

        for (Coin coin : coins) {
             result.add(coin);
        }

        return result;
    }
}
