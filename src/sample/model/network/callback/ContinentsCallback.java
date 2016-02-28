package sample.model.network.callback;

import sample.model.object.Continent;

import java.util.ArrayList;

public interface ContinentsCallback extends DataCallback {
    void continentsReceived(ArrayList<Continent> continents);
}
