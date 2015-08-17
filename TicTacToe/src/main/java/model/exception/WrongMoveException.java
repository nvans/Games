package model.exception;

/**
 *  Exception wrapper
 *
 * @author Ivan Konovalov
 */
public class WrongMoveException
                    extends Exception {

    public WrongMoveException(String message) {
        super(message);
    }
}
