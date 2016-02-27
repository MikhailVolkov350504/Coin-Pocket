package sample.model.network;

import java.net.HttpURLConnection;
import java.net.URL;

abstract class NetworkTask implements Runnable {

    protected String url;
    protected RequestCallback callback;
    protected String httpMethod;

    protected NetworkTask(String url, RequestCallback callback, String httpMethod) {
        this.url = url;
        this.callback = callback;
        this.httpMethod = httpMethod;
    }

    protected HttpURLConnection makeConnection() throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod(httpMethod);
        return connection;
    }
}
