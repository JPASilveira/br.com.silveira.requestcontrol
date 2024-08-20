package exceptions;

public class ConnectionFactoryException extends RuntimeException {
    public ConnectionFactoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
