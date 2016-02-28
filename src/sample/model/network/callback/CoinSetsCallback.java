package sample.model.network.callback;

import sample.model.object.CoinSet;

import java.util.ArrayList;

public interface CoinSetsCallback extends DataCallback {
    void coinSetsReceived(ArrayList<CoinSet> sets);
}
