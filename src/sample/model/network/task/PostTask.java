package sample.model.network.task;

import com.google.gson.JsonObject;
import sample.model.network.callback.NetworkTaskCallback;

public class PostTask extends SendDataTask {

    public PostTask(String url, JsonObject json, NetworkTaskCallback callback) {
        super("POST", url, json, callback);
    }
}
