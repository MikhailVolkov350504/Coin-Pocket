package sample.model.network;

import com.google.gson.JsonObject;

public class PostTask extends SendDataTask {

    public PostTask(String url, RequestCallback callback, JsonObject json) {
        super(url, callback, json, "POST");
    }
}
