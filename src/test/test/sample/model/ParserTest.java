package sample.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jdk.nashorn.internal.parser.JSONParser;
import junit.framework.TestCase;
import sample.model.object.Continent;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Demon on 24.03.2016.
 */
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

//    public void testContinentsFromJSONWithInvalidData() throws Exception {
//
//        JsonObject europe = new JsonObject();
//        europe.addProperty("s", "Europe");
//
//        JsonObject asia = new JsonObject();
//        asia.addProperty("a", "Asia");
//
//        JsonArray array = new JsonArray();
//        array.add(europe);
//        array.add(asia);
//
//        JsonObject jsonToParse = new JsonObject();
//        jsonToParse.add("tinents", array);
//
//
//        ArrayList<Continent> continents = new Parser().continentsFromJSON(jsonToParse);
//        System.out.println(continents.get(0));
//
//        assertEquals(0, continents.size());
//    }

    public void testCountriesFromJSON() throws Exception {

    }

    public void testCoinSetsFromJSON() throws Exception {

    }

    public void testCoinsFromJSON() throws Exception {

    }
}