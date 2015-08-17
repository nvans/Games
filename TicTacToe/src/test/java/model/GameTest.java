package model;

import org.junit.Test;
import java.util.Random;
import model.exception.WrongMoveException;

import static org.junit.Assert.*;

/**
 *
 * @author Ivan Konovalov
 */
public class GameTest {

    @Test
    public void testField() throws WrongMoveException {
        // Generate random int
        // between 3 and 10
        Random random = new Random();
        int size = random.nextInt(10);

        // The game instances
        Game game = new Game();
        Game game1 = new Game(0);
        Game game2 = new Game(size);

        assertEquals("Default field 3x3", 3, game.getSize());
        assertEquals("Create field with zero-size", 3, game1.getSize());
        assertEquals("Create field with user size", size, game2.getSize());
    }

    @Test
    public void testWin() {
        Random random = new Random();
        int temp;

        // The game instances
//        Game game = new Game(3);

        Game game = new Game(random.nextInt(20));
        Game game1 = new Game(random.nextInt(20));
        Game game2 = new Game(random.nextInt(20));
        Game game3 = new Game(random.nextInt(20));

        // Fill the fields (major diagonal)
        for (int i = 0; i < game.getSize(); i++) {
            game.getField()[i][i] = 1;
        }

        // Fill the minor diagonal
        for (int i = 0; i < game1.getSize(); i++) {
            game1.getField()[i][game1.getSize() - i - 1] = -1;
        }

        // Fill the row with X
        temp = random.nextInt(game2.getSize());
        for (int i = 0; i < game2.getSize(); i++) {
            game2.getField()[i][temp] = 1;
        }

        // Fill the column with O
        temp = random.nextInt(game3.getSize());
        for (int i = 0; i < game3.getSize(); i++) {
            game3.getField()[0][i] = -1;
        }


        // start check
        game.isGameOver();
        game1.isGameOver();
        game2.isGameOver();
        game3.isGameOver();

        // tests
        assertEquals("X win major diagonal", GameState.X_WINS, game.getState());
        assertEquals("O win minor diagonal", GameState.O_WINS, game1.getState());
        assertEquals("X win with filled row", GameState.X_WINS, game2.getState());
        assertEquals("O win with filled column", GameState.O_WINS, game3.getState());

    }
}
