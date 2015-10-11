package edu.unlam.pacman.shared.exception;

public class GameException extends Exception {
    public GameException(String message) {
        super(message);
    }

    public GameException(Throwable cause) {
        super(cause.getMessage(), cause);
    }

    public GameException(String message, Throwable cause) {
        super(message, cause);
    }
}
