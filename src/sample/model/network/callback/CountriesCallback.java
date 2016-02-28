package sample.model.network.callback;

import sample.model.object.Country;

import java.util.ArrayList;

public interface CountriesCallback extends DataCallback {
    void countriesReceived(ArrayList<Country> countries);
}
