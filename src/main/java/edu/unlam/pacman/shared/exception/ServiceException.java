package edu.unlam.pacman.shared.exception;

/**
 * @author Cristian Miranda
 * @since 10/11/15 - 17:56
 */
public class ServiceException extends GameException {
    public ServiceException(Exception e) {
        super(e);
    }

    public ServiceException(String message, Exception e) {
        super(message, e);
    }

    public ServiceException(String message) {
        super(message);
    }
}
