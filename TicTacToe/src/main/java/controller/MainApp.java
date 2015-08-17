package controller;

import model.Game;
import model.Move;
import model.exception.WrongMoveException;
import view.GameView;
import view.console.ConsoleView;
import view.gui.swing.SwingView;

/**
 *
 * Game initialization
 *
 * @author Ivan Konovalov
 */
public class MainApp {
    public static void main(String[] args) {
        Game game = new Game();
        GameView gameView;

        // when we starts the game with
        // argument c - load console version
        if (args.length != 0 && "c".equals(args[0])){
            gameView = new ConsoleView(game);

            while (!game.isGameOver()){
                gameView.render();
                try {
                    Move move = gameView.inputMove();
                    game.move(move);

                } catch (WrongMoveException e) {
                    System.out.println(e.getMessage());
                    continue;
                }

            }
            gameView.render();

            System.out.println(game.getState().toString());

        } else {
            new SwingView(game);
        }


    }
}
