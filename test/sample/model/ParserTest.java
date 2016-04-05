package sample.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import junit.framework.TestCase;
import sample.model.object.Continent;

import java.util.ArrayList;

public class ParserTest extends TestCase {

    public void testContinentsFromJSON() throws Exception {

        JsonObject europe = new JsonObject();
        europe.addProperty("name", "Europe");
        europe.addProperty("id", 2);

        JsonObject asia = new JsonObject();
        asia.addProperty("name", "Asia");
        asia.addProperty("id", 2);

        JsonArray array = new JsonArray();
        array.add(europe);
        array.add(asia);

        JsonObject jsonToParse = new JsonObject();
        jsonToParse.add("continents", array);

        ArrayList<Continent> continents = new Parser().continentsFromJSON(jsonToParse);

        assertEquals(2, continents.size());
        assertEquals(continents.get(0).getName(), "Europe");
    }
}