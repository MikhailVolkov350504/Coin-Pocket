package sample.model.network.callback;

public interface SignInCallback extends DataCallback {
    void signInSucceed(String email, String token);
    void singInFailed(String message);
}
