package model;

import model.exception.WrongMoveException;

/**
 * Move
 *
 * @author Ivan Konovalov
 */
public class Move {
    // Move coordinates
    public final int x, y;

    /**
     * Constructor with the coordinates check.
     *
     * @param game
     * @param x
     * @param y
     * @throws model.exception.WrongMoveException
     */
    public Move(Game game, int x, int y) throws WrongMoveException {

        if (x < 0 || x >= game.getSize()){
            throw new WrongMoveException("Move not available");
        }
        else if (y < 0 || y >= game.getSize()){
            throw new WrongMoveException("Move not available.");
        }

        if (game.getField()[x][y] != 0) {
            throw new WrongMoveException("The cell is already reserved!");
        }

        this.x = x;
        this.y = y;
    }

    /**
     * This constructor should use only with GUI,
     * because we do not need to check the coordinates
     * in this case.
     *
     * @param x
     * @param y
     */
    public Move(int x, int y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public String toString() {
        return "Move{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
