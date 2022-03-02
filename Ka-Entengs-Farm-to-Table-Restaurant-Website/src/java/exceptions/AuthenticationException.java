package exceptions;
public class AuthenticationException extends Exception {

    public AuthenticationException() {
        super();
    }

    public AuthenticationException(String errorMessage) {
        super(errorMessage);
    }
}
