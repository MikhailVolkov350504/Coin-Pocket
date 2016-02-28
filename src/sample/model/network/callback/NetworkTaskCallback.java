package sample.model.network.callback;

import com.google.gson.JsonObject;

public interface NetworkTaskCallback {
    void success(JsonObject json);
    void failure(String errorMessage);
}
