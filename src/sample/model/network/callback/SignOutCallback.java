package sample.model.network.callback;

public interface SignOutCallback extends DataCallback {
    void signOutSucceed();
    void signOutFailed();
}
