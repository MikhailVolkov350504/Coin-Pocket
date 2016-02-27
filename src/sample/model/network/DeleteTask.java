package sample.model.network;

import com.google.gson.JsonObject;

public class DeleteTask extends SendDataTask {

    public DeleteTask(String url, RequestCallback callback, JsonObject json) {
        super(url, callback, json, "DELETE");
    }
}
