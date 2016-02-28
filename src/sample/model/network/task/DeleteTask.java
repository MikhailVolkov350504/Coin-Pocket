package sample.model.network.task;

import com.google.gson.JsonObject;
import sample.model.network.callback.NetworkTaskCallback;

public class DeleteTask extends SendDataTask {

    public DeleteTask(String url, JsonObject json, NetworkTaskCallback callback) {
        super("DELETE", url, json, callback);
    }
}
