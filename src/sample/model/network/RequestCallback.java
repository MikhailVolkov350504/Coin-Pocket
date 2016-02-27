package sample.model.network;

import com.google.gson.JsonObject;

public interface RequestCallback {
    void success(JsonObject json);
    void failure(String errorMessage);
}
