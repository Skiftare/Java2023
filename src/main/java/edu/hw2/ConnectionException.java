package edu.hw2;

public class ConnectionException extends RuntimeException {
    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionException() {

    }
}
