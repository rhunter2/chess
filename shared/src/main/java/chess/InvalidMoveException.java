package chess;

/**
 * Indicates an invalid move was made in a game.
 */
public class InvalidMoveException extends Exception {

    /**
     * Default constructor with no message.
     */
    public InvalidMoveException() {
        super("Invalid move attempted."); // Default message for invalid moves
    }

    /**
     * Constructor with a custom error message.
     * @param message The detail message explaining the exception.
     */
    public InvalidMoveException(String message) {
        super(message);
    }

    /**
     * Constructor with a custom error message and a cause.
     * @param message The detail message explaining the exception.
     * @param cause The underlying reason for this exception (root cause).
     */
    public InvalidMoveException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with a cause.
     * @param cause The underlying reason for this exception (root cause).
     */
    public InvalidMoveException(Throwable cause) {
        super(cause);
    }
}
