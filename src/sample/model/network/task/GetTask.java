package sample.model.network.task;

import com.google.gson.JsonObject;
import sample.model.Utilities;
import sample.model.network.callback.NetworkTaskCallback;

import java.net.HttpURLConnection;

public class GetTask extends NetworkTask {

    public GetTask(String url, NetworkTaskCallback callback) {
        super(url, callback, "GET");
    }

    @Override
    public void run() {
        try {
            HttpURLConnection connection = super.makeConnection();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
            }

            JsonObject response = Utilities.jsonFromInputSream(connection.getInputStream());
            callback.success(response);
        } catch (Exception e) {
            callback.failure(e.getMessage());
        }
    }
}
