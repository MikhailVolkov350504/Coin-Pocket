package sample.model.network.callback;

import sample.model.object.Coin;

import java.util.ArrayList;

public interface CoinsCallback extends DataCallback {
    void coinsReceived(ArrayList<Coin> coins);
}
