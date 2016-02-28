package sample.model.network.task;

import java.net.HttpURLConnection;
import com.google.gson.JsonObject;
import sample.model.Utilities;
import sample.model.network.callback.NetworkTaskCallback;

public class SendDataTask extends NetworkTask {

    protected JsonObject json;

    protected SendDataTask(String httpMethod, String url,JsonObject json, NetworkTaskCallback callback) {
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

