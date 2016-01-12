package exception;

/**
 * Created by adam on 11/01/16.
 */
public class InvalidDeleteException extends Exception {

    private static final String INVALID_DATA_DELETE_MESSAGE = "Invalid data delete: ";

    public InvalidDeleteException(String message) {
        super(INVALID_DATA_DELETE_MESSAGE + message);
    }
}
