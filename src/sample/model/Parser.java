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

        Continent[] continents = new Gson().fromJson(json.get(Constants.CONTINENTS), Continent[].class);
        ArrayList<Continent> result = new ArrayList();

        for (Continent continent : continents) {
            result.add(continent);
        }

        return result;
    }

    public static ArrayList<Country> countriesFromJSON(JsonObject json) {

        Country[] countries = new Gson().fromJson(json.get(Constants.COUNTRIES), Country[].class);
        int continentId = json.get(Constants.CONTINENT_ID).getAsInt();
        ArrayList<Country> result = new ArrayList();

        for (Country country : countries) {
            country.setContinentId(continentId);
            result.add(country);
        }

        return result;
    }

    public static ArrayList<CoinSet> coinSetsFromJSON(JsonObject json) {

        CoinSet[] coinSets = new Gson().fromJson(json.get(Constants.COIN_SETS), CoinSet[].class);
        int countryId = json.get(Constants.COUNTRY_ID).getAsInt();
        ArrayList<CoinSet> result = new ArrayList<CoinSet>();

        for (CoinSet coinSet : coinSets) {
            coinSet.setCountryId(countryId);
            result.add(coinSet);
        }

        return result;
    }

    public static ArrayList<Coin> coinsFromJSON(JsonObject json) {

        Coin[] coins = new Gson().fromJson(json.get(Constants.COINS), Coin[].class);
        int coinSetId = json.get(Constants.COIN_SET_ID).getAsInt();
        ArrayList<Coin> result = new ArrayList();

        for (Coin coin : coins) {
            coin.setCoinSetID(coinSetId);
            result.add(coin);
        }

        return result;
    }
}