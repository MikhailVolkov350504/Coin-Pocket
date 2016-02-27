package sample.model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Utilities {

    public static JsonObject jsonFromInputSream(InputStream stream) throws IOException {

        String jsonString = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        for (String line; (line = reader.readLine()) != null; ) {
            jsonString += line;
        }
        reader.close();

        JsonParser jsonParser = new JsonParser();
        JsonObject json = jsonParser.parse(jsonString).getAsJsonObject();

        return json;
    }
}
