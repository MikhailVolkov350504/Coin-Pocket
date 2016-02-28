package sample.model.network.callback;

public interface SignUpCallback extends DataCallback {
    void signUpSucceed(String email);
    void singUpFailed(String message);
}
