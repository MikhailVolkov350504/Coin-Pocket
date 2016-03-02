package sample.model;

import com.google.gson.JsonObject;
import javafx.application.Platform;
import sample.model.network.callback.*;
import sample.model.network.task.DeleteTask;
import sample.model.network.task.GetTask;
import sample.model.network.task.PostTask;
import sample.model.object.Coin;
import sample.model.object.CoinSet;
import sample.model.object.Continent;
import sample.model.object.Country;

import java.util.ArrayList;

public class ServerManager {

    public static final String SERVER_URL = "http://coinpocket.herokuapp.com/";

    //POST REQUESTS
    public static void signUp(String email, String password, SignUpCallback callback) {

        JsonObject userInfo = new JsonObject();
        userInfo.addProperty(Constants.EMAIL, email);
        userInfo.addProperty(Constants.PASSWORD, password);

        JsonObject jsonToSend = new JsonObject();
        jsonToSend.add(Constants.USER, userInfo);

        String url = SERVER_URL + "users/sign_up";
        PostTask signUpTask = new PostTask(url, jsonToSend, new NetworkTaskCallback() {
            @Override
            public void success(JsonObject json) {
                Boolean success = json.get(Constants.SUCCESS).getAsBoolean();
                if (success) {
                    String email = json.get(Constants.EMAIL).toString();
                    Platform.runLater(() -> callback.signUpSucceed(email));
                } else {
                    String message = json.get(Constants.ERROR_DESCRIPTION).toString();
                    Platform.runLater(() -> callback.singUpFailed(message));
                }
            }

            @Override
            public void failure(String errorMessage) {
                Platform.runLater(() -> callback.errorReceived(errorMessage));
            }
        });
        new Thread(signUpTask).start();
    }

    public static void signIn(String email, String password, SignInCallback callback) {

        JsonObject userInfo = new JsonObject();
        userInfo.addProperty(Constants.EMAIL, email);
        userInfo.addProperty(Constants.PASSWORD, password);

        JsonObject jsonToSend = new JsonObject();
        jsonToSend.add(Constants.USER, userInfo);

        String url = SERVER_URL + "users/sign_in";
        PostTask signInTask = new PostTask(url, jsonToSend, new NetworkTaskCallback() {
            @Override
            public void success(JsonObject json) {
                Boolean success = json.get(Constants.SUCCESS).getAsBoolean();
                if (success) {
                    String email = json.get(Constants.EMAIL).toString();
                    String token = json.get(Constants.AUTH_TOKEN).toString();
                    Platform.runLater(() -> callback.signInSucceed(email, token));
                } else {
                    String message = json.get(Constants.ERROR_DESCRIPTION).toString();
                    Platform.runLater(() -> callback.singInFailed(message));
                }
            }

            @Override
            public void failure(String errorMessage) {
                Platform.runLater(() -> callback.errorReceived(errorMessage));
            }
        });
        new Thread(signInTask).start();
    }

    //DELETE REQUEST
    public static void signOut(String token, SignOutCallback callback) {

        JsonObject jsonToSend = new JsonObject();
        jsonToSend.addProperty(Constants.AUTH_TOKEN, token);

        String url = SERVER_URL + SERVER_URL + "users/sign_out";
        DeleteTask signOutTask = new DeleteTask(url, jsonToSend, new NetworkTaskCallback() {
            @Override
            public void success(JsonObject json) {
                Boolean success = json.get(Constants.SUCCESS).getAsBoolean();
                if (success) {
                    Platform.runLater(() -> callback.signOutSucceed());
                } else {
                    Platform.runLater(() -> callback.signOutFailed());
                }
            }

            @Override
            public void failure(String errorMessage) {
                Platform.runLater(() -> callback.errorReceived(errorMessage));
            }
        });
        new Thread(signOutTask).start();
    }

    //GET REQUESTS
    public static void getContinents(ContinentsCallback callback) {

        String url = SERVER_URL + "coins/continents";
        GetTask getContinentsTask = new GetTask(url, new NetworkTaskCallback() {
            @Override
            public void success(JsonObject json) {
                ArrayList<Continent> continents = Parser.continentsFromJSON(json);
                Platform.runLater(() -> callback.continentsReceived(continents));
            }

            @Override
            public void failure(String errorMessage) {
                Platform.runLater(() -> callback.errorReceived(errorMessage));
            }
        });
        new Thread(getContinentsTask).start();
    }

    public static void getCountries(String continentName, CountriesCallback callback) {

        String url = SERVER_URL + "coins/countries?name=" + continentName;
        GetTask getCountriesTask = new GetTask(url, new NetworkTaskCallback() {
            @Override
            public void success(JsonObject json) {
                ArrayList<Country> countries = Parser.countriesFromJSON(json);
                Platform.runLater(() -> callback.countriesReceived(countries));
            }

            @Override
            public void failure(String errorMessage) {
                Platform.runLater(() -> callback.errorReceived(errorMessage));
            }
        });
        new Thread(getCountriesTask).start();
    }

    public static void getCointSets(String countryName, CoinSetsCallback callback) {

        String url = SERVER_URL + "coins/coin_sets?name=" + countryName;
        GetTask getCoinSetsTask = new GetTask(url, new NetworkTaskCallback() {
            @Override
            public void success(JsonObject json) {
                ArrayList<CoinSet> coinSets = Parser.coinSetsFromJSON(json);
                Platform.runLater(() -> callback.coinSetsReceived(coinSets));
            }

            @Override
            public void failure(String errorMessage) {
                Platform.runLater(() -> callback.errorReceived(errorMessage));
            }
        });
        new Thread(getCoinSetsTask).start();
    }

    public static void getCoins(String countryName, String years, CoinsCallback callback) {

        String url = SERVER_URL + "coins/coins?country_name=" + countryName + "&" + "years=" + years;
        GetTask getCoinsTask = new GetTask(url, new NetworkTaskCallback() {
            @Override
            public void success(JsonObject json) {
                ArrayList<Coin> coins = Parser.coinsFromJSON(json);
                Platform.runLater(() -> callback.coinsReceived(coins));
            }

            @Override
            public void failure(String errorMessage) {
                Platform.runLater(() -> callback.errorReceived(errorMessage));
            }
        });
        new Thread(getCoinsTask).start();
    }
}
