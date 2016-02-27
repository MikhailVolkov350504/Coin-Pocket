package sample.model;

import com.google.gson.JsonObject;
import sample.model.network.DeleteTask;
import sample.model.network.GetTask;
import sample.model.network.PostTask;
import sample.model.network.RequestCallback;

public class ServerManager {

    public static final String SERVER_URL = "http://coinpocket.herokuapp.com/";

    //POST REQUESTS
    public static void signUp(String email, String password, RequestCallback callback) {

        JsonObject userInfo = new JsonObject();
        userInfo.addProperty(Constants.EMAIL, email);
        userInfo.addProperty(Constants.PASSWORD, password);

        JsonObject jsonToSend = new JsonObject();
        jsonToSend.add(Constants.USER, userInfo);

        String url = SERVER_URL + "users";
        PostTask signUpTask = new PostTask(url, callback, jsonToSend);
        new Thread(signUpTask).start();
    }

    public static void signIn(String email, String password, RequestCallback callback) {

        JsonObject userInfo = new JsonObject();
        userInfo.addProperty(Constants.EMAIL, email);
        userInfo.addProperty(Constants.PASSWORD, password);

        JsonObject jsonToSend = new JsonObject();
        jsonToSend.add(Constants.USER, userInfo);

        String url = SERVER_URL + "users/sign_in";
        PostTask signInTask = new PostTask(url, callback, jsonToSend);
        new Thread(signInTask).start();
    }

    //DELETE REQUEST
    public static void signOut(String token, RequestCallback callback) {

        JsonObject userInfo = new JsonObject();
        userInfo.addProperty(Constants.AUTH_TOKEN, token);

        String url = SERVER_URL + SERVER_URL + "users/sign_out";
        DeleteTask signOutTask = new DeleteTask(url, callback, userInfo);
        new Thread(signOutTask).start();
    }

    //GET REQUESTS
    public static void getContinents(RequestCallback callback) {

        String url = SERVER_URL + "coins/continents";
        GetTask getContinentsTask = new GetTask(url, callback);
        new Thread(getContinentsTask).start();
    }

    public static void getCountries(String continentName, RequestCallback callback) {

        String url = SERVER_URL + "coins/countries?name=" + continentName;
        GetTask getCountriesTask = new GetTask(url, callback);
        new Thread(getCountriesTask).start();
    }

    public static void getCointSets(String countryName, RequestCallback callback) {

        String url = SERVER_URL + "coins/coin_sets?name=" + countryName;
        GetTask getCoinSetsTask = new GetTask(url, callback);
        new Thread(getCoinSetsTask).start();
    }

    public static void getCoins(String countryName, String years, RequestCallback callback) {

        String url = SERVER_URL + "coins/coins?country_name=" + countryName + "&" + "years=" + years;
        GetTask getCoinsTask = new GetTask(url, callback);
        new Thread(getCoinsTask).start();
    }
}
