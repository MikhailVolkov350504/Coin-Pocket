package sample.model.network;

import java.net.HttpURLConnection;
import com.google.gson.JsonObject;
import sample.model.Utilities;

public class SendDataTask extends NetworkTask {

    protected JsonObject json;

    protected SendDataTask(String url, RequestCallback callback, JsonObject json, String httpMethod) {
        super(url, callback, httpMethod);
        this.json = json;
    }

    @Override
    public void run() {
        try {
            HttpURLConnection connection = super.makeConnection();
            connection.setDoOutput(true);
            connection.getOutputStream().write(json.toString().getBytes());

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
            }

            JsonObject response = Utilities.jsonFromInputSream(connection.getInputStream());
            callback.success(response);

            connection.disconnect();
        } catch (Exception e) {
            callback.failure(e.getMessage());
        }
    }
}

