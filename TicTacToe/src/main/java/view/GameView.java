package view;


import model.Move;
import model.exception.WrongMoveException;

/**
 *
 * @author Ivan Konovalov
 */
public interface GameView {
    public void render();
    public Move inputMove() throws WrongMoveException;
}
